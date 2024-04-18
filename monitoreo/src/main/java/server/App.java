package server;

import models.entidades.incidente.Estado;
import models.entidades.incidente.Incidente;
import models.entidades.organizaciones.Establecimiento;
import models.entidades.organizaciones.entidad.Entidad;
import models.entidades.organizaciones.entidad.TipoEntidad;
import models.entidades.persona.EstrategiaDeNotificacion;
import models.entidades.persona.MedioDeNotificacion;
import models.entidades.persona.Miembro;
import models.entidades.persona.autentificacion.Usuario;
import models.entidades.servicios.servicio.PrestacionDeServicio;
import models.entidades.servicios.servicio.Servicio;
import models.entidades.ubicacion.Localizacion;
import models.entidades.ubicacion.Provincia;
import models.repositorios.*;
import org.apache.poi.ss.formula.functions.T;
import utils.CargarDatosPruebaBaseDatos;

public class App {

    public static void main(String[] args) {
        Server.init();
        RepositorioIncidentes repositorioIncidentes = new RepositorioIncidentes();

        CargarDatosPruebaBaseDatos cargarDatosPruebaBaseDatos = new CargarDatosPruebaBaseDatos();
        cargarDatosPruebaBaseDatos.persistir();

    }

    public static void cargarDatos(){

        /* TIPOS DE ENTIDAD */
        TipoEntidad tipoEntidad = new TipoEntidad("Bancos", "Banco ", "Filial ");
        TipoEntidad tipoEntidad2 = new TipoEntidad("Estaciones", "Linea de subtes ", "Estacion ");
        TipoEntidad tipoEntidad3 = new TipoEntidad("Gimnasios", "Gimnasio ", "Sucursal ");

        /* ENTIDADES */
        Entidad entidad1 = new Entidad();
        entidad1.setTipo(tipoEntidad);
        entidad1.setNombre("Galicia");

        Entidad entidad2 = new Entidad();
        entidad2.setTipo(tipoEntidad2);
        entidad2.setNombre("A");

        Entidad entidad3 = new Entidad();
        entidad3.setTipo(tipoEntidad3);
        entidad3.setNombre("Sport Club");

        /* ESTABLECIMIENTOS */
        Provincia provincia = new Provincia();
        provincia.setEsProvincia(true);
        provincia.setNombre("Buenos Aires");

        Establecimiento establecimiento1 = new Establecimiento("Flores", provincia, entidad1);
        Establecimiento establecimiento2 = new Establecimiento("Munro", provincia, entidad1);
        Establecimiento establecimiento3 = new Establecimiento("Mataderos", provincia, entidad1);

        Establecimiento establecimiento4 = new Establecimiento("Carabobo", provincia, entidad2);
        Establecimiento establecimiento5 = new Establecimiento("Plaza Flores", provincia, entidad2);
        Establecimiento establecimiento6 = new Establecimiento("Congreso", provincia, entidad2);

        Establecimiento establecimiento7 = new Establecimiento("Flores", provincia, entidad3);
        Establecimiento establecimiento8 = new Establecimiento("La Matanza", provincia, entidad3);
        Establecimiento establecimiento9 = new Establecimiento("Palermo", provincia, entidad3);

        entidad1.agregarEstablecimientos(establecimiento1);
        entidad1.agregarEstablecimientos(establecimiento2);
        entidad1.agregarEstablecimientos(establecimiento3);

        entidad2.agregarEstablecimientos(establecimiento4);
        entidad2.agregarEstablecimientos(establecimiento5);
        entidad2.agregarEstablecimientos(establecimiento6);

        entidad3.agregarEstablecimientos(establecimiento7);
        entidad3.agregarEstablecimientos(establecimiento8);
        entidad3.agregarEstablecimientos(establecimiento9);

        /* SERVICIOS */

        Servicio servicio1 = new Servicio("Baños");
        Servicio servicio2 = new Servicio("Escaleras mecánicas");
        Servicio servicio3 = new Servicio("Rampa para discapacitados");
        Servicio servicio4 = new Servicio("Luces de emergencia");
        Servicio servicio5 = new Servicio("Alarma para ciegos");
        Servicio servicio6 = new Servicio("Puertas automáticas ");

        /* PRESTACIONES DE SERVICIO */

        PrestacionDeServicio prestacionDeServicio1 = new PrestacionDeServicio(servicio1,establecimiento1);
        PrestacionDeServicio prestacionDeServicio2 = new PrestacionDeServicio(servicio3, establecimiento2);
        PrestacionDeServicio prestacionDeServicio3 = new PrestacionDeServicio(servicio6, establecimiento3);

        PrestacionDeServicio prestacionDeServicio4 = new PrestacionDeServicio(servicio2, establecimiento4);
        PrestacionDeServicio prestacionDeServicio5 = new PrestacionDeServicio(servicio2, establecimiento5);
        PrestacionDeServicio prestacionDeServicio6 = new PrestacionDeServicio(servicio4, establecimiento6);

        PrestacionDeServicio prestacionDeServicio7 = new PrestacionDeServicio(servicio5, establecimiento7);
        PrestacionDeServicio prestacionDeServicio8 = new PrestacionDeServicio(servicio1, establecimiento8);
        PrestacionDeServicio prestacionDeServicio9 = new PrestacionDeServicio(servicio1, establecimiento9);

        establecimiento1.agregarPrestacionDeServicio(prestacionDeServicio1);
        establecimiento2.agregarPrestacionDeServicio(prestacionDeServicio2);
        establecimiento3.agregarPrestacionDeServicio(prestacionDeServicio3);

        establecimiento4.agregarPrestacionDeServicio(prestacionDeServicio4);
        establecimiento5.agregarPrestacionDeServicio(prestacionDeServicio5);
        establecimiento6.agregarPrestacionDeServicio(prestacionDeServicio6);

        establecimiento7.agregarPrestacionDeServicio(prestacionDeServicio7);
        establecimiento8.agregarPrestacionDeServicio(prestacionDeServicio8);
        establecimiento9.agregarPrestacionDeServicio(prestacionDeServicio9);

        /* INCIDENTES */
        Usuario user = new Usuario("1234", "fpiaggio@frba.utn.edu.ar");
        Miembro miembro = new Miembro("Facundo", "Piaggio", provincia);
        miembro.setMedioDeNotificacion(MedioDeNotificacion.MAIL);
        miembro.setDni("42215059");
        miembro.setEstrategiaDeNotificacion(EstrategiaDeNotificacion.SINAPUROS);
        miembro.setUsuario(user);


        Incidente incidente1 = new Incidente(miembro, prestacionDeServicio1, establecimiento1, "El inodoro explotó");
        incidente1.setEstado(Estado.ABIERTO);
        Incidente incidente2 = new Incidente(miembro, prestacionDeServicio2, establecimiento2, "La rampa esta obstruida");
        incidente2.setEstado(Estado.CERRADO);
        Incidente incidente3 = new Incidente(miembro, prestacionDeServicio3, establecimiento3, "Las puertas automaticas se traban a cada rato");
        incidente3.setEstado(Estado.CERRADO);
        Incidente incidente4 = new Incidente(miembro, prestacionDeServicio4, establecimiento4, "Estan en reparacion las escaleras mecanicas");
        incidente4.setEstado(Estado.ABIERTO);
        Incidente incidente5 = new Incidente(miembro, prestacionDeServicio5, establecimiento5, "Las escaleras van lentisimo, conviene subir normal");
        incidente5.setEstado(Estado.ABIERTO);
        Incidente incidente6 = new Incidente(miembro, prestacionDeServicio6, establecimiento6, "No estan las puertas :V");
        incidente6.setEstado(Estado.CERRADO);
        Incidente incidente7 = new Incidente(miembro, prestacionDeServicio7, establecimiento7, "La alarma suena bajisimo... o capaz soy sordo");
        incidente7.setEstado(Estado.ABIERTO);
        Incidente incidente8 = new Incidente(miembro, prestacionDeServicio8, establecimiento8, "No hay papel higienicooo");
        incidente8.setEstado(Estado.ABIERTO);
        Incidente incidente9 = new Incidente(miembro, prestacionDeServicio9, establecimiento9, "No hay baños????");
        incidente9.setEstado(Estado.CERRADO);


        /* PERSISTENCIA */
        RepositorioTipoEntidades repositorioTipoEntidades = new RepositorioTipoEntidades();
        repositorioTipoEntidades.persistir(tipoEntidad);
        repositorioTipoEntidades.persistir(tipoEntidad2);
        repositorioTipoEntidades.persistir(tipoEntidad3);

        RepositorioLocalizaciones repositorioLocalizaciones = new RepositorioLocalizaciones();
        repositorioLocalizaciones.persistir(provincia);

        RepositorioEntidades repositorioEntidades = new RepositorioEntidades();
        repositorioEntidades.persistir(entidad1);
        repositorioEntidades.persistir(entidad2);
        repositorioEntidades.persistir(entidad3);

        RepositorioEstablecimientos repositorioEstablecimientos = new RepositorioEstablecimientos();
        repositorioEstablecimientos.persistir(establecimiento1);
        repositorioEstablecimientos.persistir(establecimiento2);
        repositorioEstablecimientos.persistir(establecimiento3);
        repositorioEstablecimientos.persistir(establecimiento4);
        repositorioEstablecimientos.persistir(establecimiento5);
        repositorioEstablecimientos.persistir(establecimiento6);
        repositorioEstablecimientos.persistir(establecimiento7);
        repositorioEstablecimientos.persistir(establecimiento8);
        repositorioEstablecimientos.persistir(establecimiento9);


        RepositorioServicios repositorioServicios = new RepositorioServicios();
        repositorioServicios.persistir(servicio1);
        repositorioServicios.persistir(servicio2);
        repositorioServicios.persistir(servicio3);
        repositorioServicios.persistir(servicio4);
        repositorioServicios.persistir(servicio5);
        repositorioServicios.persistir(servicio6);

        RepositorioUsuarios repositorioUsuarios = new RepositorioUsuarios();
        repositorioUsuarios.persistir(user);

        RepositorioMiembros repositorioMiembros = new RepositorioMiembros();
        repositorioMiembros.persistir(miembro);

        RepositorioPrestacionesDeServicios repositorioPrestacionesDeServicios = new RepositorioPrestacionesDeServicios();
        repositorioPrestacionesDeServicios.persistir(prestacionDeServicio1);
        repositorioPrestacionesDeServicios.persistir(prestacionDeServicio2);
        repositorioPrestacionesDeServicios.persistir(prestacionDeServicio3);
        repositorioPrestacionesDeServicios.persistir(prestacionDeServicio4);
        repositorioPrestacionesDeServicios.persistir(prestacionDeServicio5);
        repositorioPrestacionesDeServicios.persistir(prestacionDeServicio6);
        repositorioPrestacionesDeServicios.persistir(prestacionDeServicio7);
        repositorioPrestacionesDeServicios.persistir(prestacionDeServicio8);
        repositorioPrestacionesDeServicios.persistir(prestacionDeServicio9);

        RepositorioIncidentes repositorioIncidentes = new RepositorioIncidentes();
        repositorioIncidentes.persistir(incidente1);
        repositorioIncidentes.persistir(incidente2);
        repositorioIncidentes.persistir(incidente3);
        repositorioIncidentes.persistir(incidente4);
        repositorioIncidentes.persistir(incidente5);
        repositorioIncidentes.persistir(incidente6);
        repositorioIncidentes.persistir(incidente7);
        repositorioIncidentes.persistir(incidente8);
        repositorioIncidentes.persistir(incidente9);



    }
}
