package modulos.Rankings.RepositorioIncidentes;

import models.entidades.incidente.Incidente;
import models.entidades.incidente.RepositorioIncidentes;
import models.entidades.organizaciones.Establecimiento;
import models.entidades.organizaciones.entidad.Entidad;
import models.entidades.organizaciones.entidad.TipoEntidad;
import models.entidades.persona.Miembro;
import models.entidades.rankings.ValueObjects.IncidenteRepetidoPorFecha;
import models.entidades.servicios.servicio.PrestacionDeServicio;
import models.entidades.servicios.servicio.Servicio;
import models.entidades.ubicacion.Localizacion;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

public class RepositorioIncidentesTests {
  private RepositorioIncidentes repositorioIncidentes;
  private Incidente incidenteRepetido1, incidenteRepetido2, incidenteRepetido3, incidenteRepetido4, incidenteNoRepetido1, incidenteNoRepetido2;
  private Servicio servicio1, servicio3, servicio2;
  private Miembro miembroNotificante;
  private Entidad entidad1, entidad2, entidad3;
  private Establecimiento establecimiento1, establecimiento2, establecimiento3;
  private PrestacionDeServicio prestacion1, prestacion2, prestacion3;

  @BeforeEach
  public void init(){
    Localizacion localizacion = null;
    TipoEntidad tipo = null;
    miembroNotificante = new Miembro("Juan", "Sanchez", localizacion);

    entidad1 = new Entidad("Banco Ciudad", tipo);
    entidad2 = new Entidad("Linea A de subtes", tipo);
    entidad3 = new Entidad("Hospital Italiano", tipo);

    establecimiento1 = new Establecimiento("Sucursal Rivadavia 6920", localizacion, entidad1);
    establecimiento2 = new Establecimiento("Estacion Carabobo", localizacion, entidad2);
    establecimiento3 = new Establecimiento("Sede Caballito", localizacion, entidad3);

    entidad1.agregarEstablecimientos(establecimiento1);
    entidad2.agregarEstablecimientos(establecimiento2);
    entidad3.agregarEstablecimientos(establecimiento3);

    servicio1 = new Servicio("Escaleras");
    servicio2 = new Servicio("Baños");
    servicio3 = new Servicio("Rampa de ingreso");

    prestacion1 = new PrestacionDeServicio(servicio1, establecimiento1);
    prestacion2 = new PrestacionDeServicio(servicio2, establecimiento2);
    prestacion3 = new PrestacionDeServicio(servicio3, establecimiento3);

    establecimiento1.agregarPrestacionDeServicio(prestacion1);
    establecimiento2.agregarPrestacionDeServicio(prestacion2);
    establecimiento3.agregarPrestacionDeServicio(prestacion3);

    incidenteRepetido1 = new Incidente(miembroNotificante, prestacion1, establecimiento1, "Escaleras bloqueadas por personal del banco");
    incidenteRepetido2 = new Incidente(miembroNotificante, prestacion1, establecimiento1, "Escaleras en limpieza");
    incidenteRepetido3 = new Incidente(miembroNotificante, prestacion1, establecimiento1, "Escaleras bajo reparacion");
    incidenteRepetido4 = new Incidente(miembroNotificante, prestacion1, establecimiento1, "Algo");
    incidenteRepetido4.setFechaAlta(LocalDateTime.now().plusDays(1));
    incidenteNoRepetido1 = new Incidente(miembroNotificante, prestacion2, establecimiento2, "descripcion generica 4");
    incidenteNoRepetido2 = new Incidente(miembroNotificante, prestacion3, establecimiento3, "descripcion generica 5");


    repositorioIncidentes = new RepositorioIncidentes();
    repositorioIncidentes.agregarIncidente(incidenteRepetido1, incidenteRepetido2, incidenteRepetido3, incidenteRepetido4, incidenteNoRepetido1, incidenteNoRepetido2);
  }

  @Test
  public void LaCantidadDeIncidentesRepetidosEs4(){
    Integer cantidadDeIncidentesRepetidos = this.repositorioIncidentes.cantidadDeIncidentesRepetidosDe(incidenteRepetido1);
    Assertions.assertEquals(4,cantidadDeIncidentesRepetidos);
  }

  @Test
  public void LaCantidadDeIncidentesNoRepetidosEs1(){
    Integer cantidadDeIncidentesNoRepetidos1 = this.repositorioIncidentes.cantidadDeIncidentesRepetidosDe(incidenteNoRepetido1);
    Assertions.assertEquals(1,cantidadDeIncidentesNoRepetidos1);

    Integer cantidadDeIncidentesNoRepetidos2 = this.repositorioIncidentes.cantidadDeIncidentesRepetidosDe(incidenteNoRepetido2);
    Assertions.assertEquals(1,cantidadDeIncidentesNoRepetidos2);
  }

  @Test
  public void LaCantidadDeIncidentesRepetidosEnMismaFechaEs3(){
    Integer cantidadDeIncidentesRePetidosEnMismaFecha = this.repositorioIncidentes.getIncidentesRepetidosEnMismaFecha(incidenteRepetido2).size();
    Assertions.assertEquals(3, cantidadDeIncidentesRePetidosEnMismaFecha);
  }

  @Test
  public void LaCantidadDeIncidentesRepetidosEnDistintaFechaEs2(){
    Integer cantidadDeIncidentesRePetidosEnDistintaFecha = this.repositorioIncidentes.getIncidentesRepetidosEnDistintaFecha(incidenteRepetido2).size();
    Assertions.assertEquals(1, cantidadDeIncidentesRePetidosEnDistintaFecha);
  }

  @Test
  public void test2(){
    List<Incidente> incidentes = this.repositorioIncidentes.getIncidentes();
    List<IncidenteRepetidoPorFecha> incidentesRepetidos = new ArrayList<>();

    int suma = 0;
    for(Incidente incidente : incidentes){
      if(incidentesRepetidos.size()== 0){
        IncidenteRepetidoPorFecha incidenteRepetidoPorFecha = new IncidenteRepetidoPorFecha(incidente, incidente.getFechaAlta());
        incidentesRepetidos.add(incidenteRepetidoPorFecha);
      }
      if(incidentesRepetidos.stream().
          anyMatch((incidenteRepetidoPorFecha ->
              incidenteRepetidoPorFecha.getIncidente().getServicioAfectado().getServicio().getNombre().equals(incidente.getServicioAfectado().getServicio().getNombre())
                  &&
                  incidenteRepetidoPorFecha.getFecha().toLocalDate().equals(incidente.getFechaAlta().toLocalDate()))))
      {
        continue;
      }else {
        IncidenteRepetidoPorFecha incidenteRepetidoPorFecha = new IncidenteRepetidoPorFecha(incidente, incidente.getFechaAlta());
        incidentesRepetidos.add(incidenteRepetidoPorFecha);
      }
    }

    suma = (int) incidentesRepetidos.stream().count();
    Assertions.assertEquals(4, suma);
  }
}
