package modulos.Rankings;

import models.entidades.incidente.Estado;
import models.entidades.incidente.Incidente;
import models.entidades.incidente.Observacion;
import models.entidades.incidente.RepositorioIncidentes;
import models.entidades.organizaciones.Establecimiento;
import models.entidades.organizaciones.RepositorioEntidades;
import models.entidades.organizaciones.entidad.Entidad;
import models.entidades.organizaciones.entidad.TipoEntidad;
import models.entidades.persona.Miembro;
import models.entidades.rankings.GeneradorDeRankings;
import models.entidades.rankings.ValueObjects.Registro;
import models.entidades.rankings.reportes.ReporteWeb;
import models.entidades.servicios.servicio.PrestacionDeServicio;
import models.entidades.servicios.servicio.Servicio;
import models.entidades.ubicacion.Localizacion;
import models.modulos.exportador.Exportador;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.time.DayOfWeek;
import java.time.LocalDateTime;
import java.time.temporal.TemporalAdjusters;
import java.util.List;

public class GeneradorDeRankingsTest {

  private Miembro miembroNotificante;
  private Incidente incidente1, incidente2, incidente3, incidente4, incidente5, incidente6, incidenteRepetido1, incidenteRepetido2, incidenteRepetido3;
  private RepositorioIncidentes repositorioIncidentes;
  private RepositorioEntidades repositorioEntidades;
  private Entidad entidad1, entidad2, entidad3;
  private Establecimiento establecimiento1, establecimiento2, establecimiento3;
  private PrestacionDeServicio prestacion1, prestacion2, prestacion3, prestacion4, prestacion5;
  private Servicio servicio1, servicio2, servicio3, servicio4, servicio5;
  private Exportador exportador;
  private GeneradorDeRankings generadorDeRankings;


  @BeforeEach
  public void init(){
    Localizacion localizacion = null;
    TipoEntidad tipo = null;
    miembroNotificante = new Miembro("Juan", "Sanchez", localizacion);

    repositorioEntidades = new RepositorioEntidades();

    entidad1 = new Entidad("Banco Ciudad", tipo);
    entidad2 = new Entidad("Linea A de subtes", tipo);
    entidad3 = new Entidad("Hospital Italiano", tipo);

    repositorioEntidades.agregarEntidad(entidad1, entidad2, entidad3);

    establecimiento1 = new Establecimiento("Sucursal Rivadavia 6920", localizacion, entidad1);
    establecimiento2 = new Establecimiento("Estacion Carabobo", localizacion, entidad2);
    establecimiento3 = new Establecimiento("Sede Caballito", localizacion, entidad3);

    entidad1.agregarEstablecimientos(establecimiento1);
    entidad2.agregarEstablecimientos(establecimiento2);
    entidad3.agregarEstablecimientos(establecimiento3);

    servicio1 = new Servicio("Escaleras");
    servicio2 = new Servicio("Baños");
    servicio3 = new Servicio("Rampa de ingreso");
    servicio4 = new Servicio("Barandas de escaleras");
    servicio5 = new Servicio("Servicio generico 5");

    prestacion1 = new PrestacionDeServicio(servicio1, establecimiento1);
    prestacion2 = new PrestacionDeServicio(servicio2, establecimiento2);
    prestacion3 = new PrestacionDeServicio(servicio3, establecimiento3);
    prestacion4 = new PrestacionDeServicio(servicio4, establecimiento3);
    prestacion5 = new PrestacionDeServicio(servicio5, establecimiento3);

    establecimiento1.agregarPrestacionDeServicio(prestacion1);
    establecimiento2.agregarPrestacionDeServicio(prestacion2);
    establecimiento3.agregarPrestacionDeServicio(prestacion3);

    incidente1 = new Incidente(miembroNotificante, prestacion1, establecimiento1, "Escaleras bloqueadas por personal del banco");
    incidente2 = new Incidente(miembroNotificante, prestacion2, establecimiento2, "Los baños se encuentran en limpieza");
    incidente3 = new Incidente(miembroNotificante, prestacion3, establecimiento3, "La rampa de acceso esta rota y no se puede usar con facilidad");
    incidente4 = new Incidente(miembroNotificante, prestacion4, establecimiento3, "descripcion generica 4");
    incidente5 = new Incidente(miembroNotificante, prestacion5, establecimiento3, "descripcion generica 5");
    incidente6 = new Incidente(miembroNotificante, prestacion5, establecimiento3, "descripcion generica 6");
    LocalDateTime fechaAntigua = LocalDateTime.now().minusWeeks(1).with(TemporalAdjusters.previousOrSame(DayOfWeek.MONDAY));
    incidente6.setFechaAlta(fechaAntigua);

    incidenteRepetido1 = new Incidente(miembroNotificante, prestacion1, establecimiento1, "des");
    incidenteRepetido2 = new Incidente(miembroNotificante, prestacion1, establecimiento1, "des2");
    incidenteRepetido3 = new Incidente(miembroNotificante, prestacion1, establecimiento1, "des3");
    incidenteRepetido3.setFechaAlta(LocalDateTime.now().plusDays(2));

    Observacion observacion1 = new Observacion(miembroNotificante, "Ya no esta el incidente");
    observacion1.setFecha(LocalDateTime.now().plusDays(1));

    Observacion observacion2 = new Observacion(miembroNotificante, "Ya no esta el incidente");
    observacion2.setFecha(LocalDateTime.now().plusDays(2));

    Observacion observacion3 = new Observacion(miembroNotificante, "Ya no esta el incidente");
    observacion3.setFecha(LocalDateTime.now().plusDays(3));

    incidente1.agregarObservacion(observacion1);
    incidente2.agregarObservacion(observacion2);
    incidente3.agregarObservacion(observacion3);

    incidente1.setEstado(Estado.CERRADO);
    incidente2.setEstado(Estado.CERRADO);
    incidente3.setEstado(Estado.CERRADO);

    repositorioIncidentes = new RepositorioIncidentes();
    repositorioIncidentes.agregarIncidente(incidente1, incidente2, incidente3, incidente4, incidente5, incidente6, incidenteRepetido1, incidenteRepetido2, incidenteRepetido3);

    generadorDeRankings = new GeneradorDeRankings();
    exportador = new Exportador();
  }

  @Test
  public void elPrimerRankingSeHaceBien() {
    this.generadorDeRankings.exportarRankingDeMayorPromedioDeCierre(exportador, "PDF");
  }

  @Test
  public void elSegundoRankingSeHaceBien(){
    this.generadorDeRankings.exportarRankingDeMayorCantidadDeIncidentesEnSemana(exportador, "PDF");
  }

  @Test void elSegundoRankingPasaPiola(){
    this.generadorDeRankings.exportarRankingMayorCantidadDeIncidentesEnSemana(exportador, "PDF");
  }

  @Test
  public void elPrimerEstabien() {
    ReporteWeb reporteWeb = this.generadorDeRankings.generarRankingDeMayorPromedioDeCierreWeb();
    List<Registro> registros = reporteWeb.getRegistro();
    for (Registro registro: registros) {
      System.out.println(registro.getNombreEntidad() + " " + registro.getValor());
    }
  }

  @Test
  public void elSegundoEstabien() {
    ReporteWeb reporteWeb = this.generadorDeRankings.generarRankingDeMayorCantidadDeIncidentesEnSemanaWeb();
    List<Registro> registros = reporteWeb.getRegistro();
    for (Registro registro: registros) {
      System.out.println(registro.getNombreEntidad() + " " + registro.getValor());
    }
  }
}
