package utils;

import io.github.flbulgarelli.jpa.extras.simple.WithSimplePersistenceUnit;
import models.entidades.comunidad.Comunidad;
import models.entidades.incidente.Estado;
import models.entidades.incidente.Incidente;
import models.entidades.incidente.Observacion;
import models.entidades.organizaciones.Establecimiento;
import models.entidades.organizaciones.entidad.Entidad;
import models.entidades.organizaciones.entidad.TipoEntidad;
import models.entidades.persona.Miembro;
import models.entidades.persona.autentificacion.Rol;
import models.entidades.persona.autentificacion.Usuario;
import models.entidades.servicios.servicio.PrestacionDeServicio;
import models.entidades.servicios.servicio.Servicio;
import models.entidades.ubicacion.Localizacion;

import models.entidades.ubicacion.Provincia;
import models.repositorios.*;

import java.time.DayOfWeek;
import java.time.LocalDateTime;
import java.time.temporal.TemporalAdjusters;
import java.util.ArrayList;
import java.util.List;

public class CargarDatosPruebaBaseDatos implements WithSimplePersistenceUnit {

    private Miembro miembroNotificante;
    private Incidente incidente1, incidente2, incidente3, incidente4, incidente5, incidente6, incidenteRepetido1, incidenteRepetido2, incidenteRepetido3;
    private Entidad entidad1, entidad2, entidad3;
    private Establecimiento establecimiento1, establecimiento2, establecimiento3;
    private PrestacionDeServicio prestacion1, prestacion2, prestacion3, prestacion4, prestacion5;
    private Servicio servicio1, servicio2, servicio3, servicio4, servicio5;
    private Usuario usuario;
    private Comunidad comunidad;


    private RepositorioEntidades repositorioEntidades;
    private RepositorioIncidentes repositorioIncidentes;
    private RepositorioMiembros repositorioMiembros;
    private RepositorioEstablecimientos repositorioEstablecimientos;
    private RepositorioServicios repositorioServicios;
    private RepositorioPrestacionesDeServicios repositorioPrestacionesDeServicios;

    private RepositorioLocalizaciones repositorioLocalizaciones;

    private RepositorioComunidades repositorioComunidades;

    public void persistir() {
        Provincia localizacion = new Provincia();
        localizacion.setNombre("Ciudad Autónoma de Buenos Aires");
        localizacion.setEsProvincia(true);
        TipoEntidad tipo = null;

        usuario = new Usuario("1234", "correoReal@gmail.com");
        usuario.setUsuario("unUsuario");
        usuario.setRol(Rol.ADMIN_PLATAFORMA);

        miembroNotificante = new Miembro("Juan", "Sanchez", localizacion);
        miembroNotificante.setUsuario(usuario);

        comunidad = new Comunidad();
        comunidad.setNombre("ComunidadMaster");
        comunidad.setGradoDeConfianza(6.0);

        miembroNotificante.setComunidad(comunidad);

        repositorioEntidades = new RepositorioEntidades();

        repositorioIncidentes = new RepositorioIncidentes();

        repositorioEstablecimientos = new RepositorioEstablecimientos();

        repositorioMiembros = new RepositorioMiembros();

        repositorioPrestacionesDeServicios = new RepositorioPrestacionesDeServicios();

        repositorioServicios = new RepositorioServicios();

        repositorioLocalizaciones = new RepositorioLocalizaciones();

        repositorioComunidades = new RepositorioComunidades();

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
        incidente1.setComunidad(comunidad);
        incidente2 = new Incidente(miembroNotificante, prestacion2, establecimiento2, "Los baños se encuentran en limpieza");
        incidente2.setComunidad(comunidad);
        incidente3 = new Incidente(miembroNotificante, prestacion3, establecimiento3, "La rampa de acceso esta rota y no se puede usar con facilidad");
        incidente3.setComunidad(comunidad);
        incidente4 = new Incidente(miembroNotificante, prestacion4, establecimiento3, "descripcion generica 4");
        incidente4.setComunidad(comunidad);
        incidente5 = new Incidente(miembroNotificante, prestacion5, establecimiento3, "descripcion generica 5");
        incidente5.setComunidad(comunidad);
        incidente6 = new Incidente(miembroNotificante, prestacion5, establecimiento3, "descripcion generica 6");
        LocalDateTime fechaAntigua = LocalDateTime.now().minusWeeks(1).with(TemporalAdjusters.previousOrSame(DayOfWeek.MONDAY));
        incidente6.setFechaAlta(fechaAntigua);
        incidente6.setComunidad(comunidad);

        incidenteRepetido1 = new Incidente(miembroNotificante, prestacion1, establecimiento1, "des");
        incidenteRepetido1.setComunidad(comunidad);
        incidenteRepetido2 = new Incidente(miembroNotificante, prestacion1, establecimiento1, "des2");
        incidenteRepetido2.setComunidad(comunidad);
        incidenteRepetido3 = new Incidente(miembroNotificante, prestacion1, establecimiento1, "des3");
        incidenteRepetido3.setFechaAlta(LocalDateTime.now().plusDays(2));
        incidenteRepetido3.setComunidad(comunidad);

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

        ////////////////////////////////////////////////////////////////////////////////////
        /////////////////////////DATOS PARA PROBAR INTEGRACION CON EL SERVICIO /////////////

        Comunidad comunidad1 = new Comunidad("comunidad1", 2.0);
        Comunidad comunidad2 = new Comunidad("comunidad2", 5.0);
        Comunidad comunidad3 = new Comunidad("comunidad3",2.0);
        Comunidad comunidad4 = new Comunidad("comunidad4", 5.0);
        Comunidad comunidad5 = new Comunidad("comunidad5", 10.0);
        Comunidad comunidad6 = new Comunidad("comunidad6", 7.0);
        Comunidad comunidad7 = new Comunidad("comunidad7", 7.0);

        comunidad1.agregarServicioObservado(prestacion1);
        comunidad3.agregarServicioObservado(prestacion1);
        comunidad2.agregarServicioObservado(prestacion2);
        comunidad4.agregarServicioObservado(prestacion2);
        comunidad6.agregarServicioObservado(prestacion3);
        comunidad7.agregarServicioObservado(prestacion3);
        comunidad5.agregarServicioObservado(prestacion4);

        //Persistencia

        repositorioLocalizaciones.persistir(localizacion);

        repositorioComunidades.persistir(comunidad);

        repositorioMiembros.persistir(miembroNotificante);

        repositorioServicios.persistir(servicio1);
        repositorioServicios.persistir(servicio2);
        repositorioServicios.persistir(servicio3);
        repositorioServicios.persistir(servicio4);
        repositorioServicios.persistir(servicio5);

        repositorioEntidades.persistir(entidad1);
        repositorioEntidades.persistir(entidad2);
        repositorioEntidades.persistir(entidad3);

        repositorioEstablecimientos.persistir(establecimiento1);
        repositorioEstablecimientos.persistir(establecimiento2);
        repositorioEstablecimientos.persistir(establecimiento3);

        repositorioPrestacionesDeServicios.persistir(prestacion1);
        repositorioPrestacionesDeServicios.persistir(prestacion2);
        repositorioPrestacionesDeServicios.persistir(prestacion3);
        repositorioPrestacionesDeServicios.persistir(prestacion4);
        repositorioPrestacionesDeServicios.persistir(prestacion5);

        repositorioIncidentes.persistir(incidente1);
        repositorioIncidentes.persistir(incidente2);
        repositorioIncidentes.persistir(incidente3);
        repositorioIncidentes.persistir(incidente4);
        repositorioIncidentes.persistir(incidente5);
        repositorioIncidentes.persistir(incidente6);
        repositorioIncidentes.persistir(incidenteRepetido1);
        repositorioIncidentes.persistir(incidenteRepetido2);
        repositorioIncidentes.persistir(incidenteRepetido3);

        ///////////////////////////////////////////////////////////////////////////
        repositorioComunidades.persistir(comunidad1);
        repositorioComunidades.persistir(comunidad2);
        repositorioComunidades.persistir(comunidad3);
        repositorioComunidades.persistir(comunidad4);
        repositorioComunidades.persistir(comunidad5);
        repositorioComunidades.persistir(comunidad6);
        repositorioComunidades.persistir(comunidad7);
    }

    public static void main(String[] args){
        CargarDatosPruebaBaseDatos repo = new CargarDatosPruebaBaseDatos();

        repo.persistir();;
    }
}