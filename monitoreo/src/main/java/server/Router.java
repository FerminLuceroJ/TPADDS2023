package server;

import controllers.*;
import models.entidades.incidente.Incidente;

import static io.javalin.apibuilder.ApiBuilder.*;

public class Router {

    public static void init() {

        Server.app().routes(() -> {

            get("/", new HomeController()::show);
            get("/home", new HomeController()::show);

            get("registro", new RegistroController()::show);
            post("registro", new RegistroController()::create);

            get("login", new LoginController()::show);
            post("login", new LoginController()::login);

            get("entidadesYorganismos", new EntidadesYOrganismosController()::show);
            post("entidadesYorganismos", new EntidadesYOrganismosController()::create);

            get("incidentes", new IncidentesController()::index);
            get("incidentes/abrir", new IncidentesController()::show);
            post("incidentes", new IncidentesController():: create); // el abrir esta de mas
            post("incidentes/{id}/cerrar", new IncidentesController()::update); // Esta mal que sea un get
            get("incidentes/abiertos", new IncidentesController()::abiertos);
            get("incidentes/cerrados", new IncidentesController()::cerrados); //Hubiera estado bueno usar Query params
            //get("incidentes/{estado}", new IncidentesController()::index);
            path("incidentes/{id}/observaciones", () -> {
                get((new ObservacionesController()::index));
            });

            get("rankings", new rankingController()::index);

            get("administracion", new administradorController()::index);
            get("administracion/usuarios/{id}/editar", new administradorController()::show);
            post("administracion/usuarios/{id}/editar", new administradorController()::update);

            //Estaría bueno un cerrar sesión

            ////////////////////////////////////////////////////////////////////////////////////////
            get("/comunidades", new ComunidadController()::obtenerComunidadesParaFusion);
            get("/propuestas-de-fusion", new FusionarComunidadesController()::obtenerPropuestasDeFusion);
            post("/propuestas-de-fusion/{propuestaIndice}/aceptar", new FusionarComunidadesController()::aceptarPropuestaDeFusion);
            post("/propuestas-de-fusion/{propuestaIndice}/rechazar", new FusionarComunidadesController()::rechazarPropuestaDeFusion);
            ////////////////////////////////////////////////////////////////////////////////////////

            get("/ranking3", new RankingExternoController()::obtenerSolicitudDeRanking);
            get("/obtenerRanking3", new RankingExternoController()::obtenerRankingExterno);

        });
    }
}
