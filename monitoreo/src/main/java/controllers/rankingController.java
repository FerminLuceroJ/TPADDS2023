package controllers;

import com.github.jknack.handlebars.Handlebars;
import com.github.jknack.handlebars.Template;
import com.github.jknack.handlebars.io.ClassPathTemplateLoader;
import com.github.jknack.handlebars.io.TemplateLoader;
import io.javalin.http.Context;
import io.javalin.http.HttpStatus;
import models.entidades.incidente.RepositorioIncidentes;
import models.entidades.organizaciones.RepositorioEntidades;
import models.entidades.rankings.GeneradorDeRankings;
import models.entidades.rankings.ValueObjects.Registro;
import models.entidades.rankings.reportes.ReporteWeb;


import java.util.HashMap;
import java.util.List;
import java.util.Map;


public class rankingController extends UserController{

    public rankingController() {

    }

    public void index(Context context){

        context.status(HttpStatus.OK);

        Map<String, Object> model = new HashMap<>();

        GeneradorDeRankings generadorDeRankings = new GeneradorDeRankings();

        ReporteWeb reporteMayorPromedioCierre = generadorDeRankings.generarRankingDeMayorPromedioDeCierreWeb();
        List<Registro> registrosDeMayorPromedioCierre = reporteMayorPromedioCierre.getRegistro();
        ReporteWeb reporteMayorCantidadDeIncidentesEnSemana = generadorDeRankings.generarRankingDeMayorCantidadDeIncidentesEnSemanaWeb();
        List<Registro> registrosDeMayorCantidadDeIncidentesEnSemana = reporteMayorCantidadDeIncidentesEnSemana.getRegistro();

        model.put("esAdmin",this.usuarioEsAdmin(context));
        model.put("registrosDeMayorPromedioCierre",registrosDeMayorPromedioCierre);
        model.put("registrosDeMayorCantidadDeIncidentesEnSemana", registrosDeMayorCantidadDeIncidentesEnSemana);

        TemplateLoader loader = new ClassPathTemplateLoader();
        loader.setPrefix("/templates");
        Handlebars handlebars = new Handlebars(loader);

        try {
            Template template = handlebars.compile("rankings");

            context.html(template.apply(model));
            context.status(HttpStatus.OK);
        }catch (Exception e){
            System.out.println(e.getMessage());
            context.status(HttpStatus.INTERNAL_SERVER_ERROR);
            throw new RuntimeException("No pude cargar la pantalla");
        }
    }
}
