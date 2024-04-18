package controllers;

import com.github.jknack.handlebars.Handlebars;
import com.github.jknack.handlebars.Template;
import com.github.jknack.handlebars.io.ClassPathTemplateLoader;
import com.github.jknack.handlebars.io.TemplateLoader;
import io.javalin.http.Context;
import io.javalin.http.HttpStatus;
import io.javalin.http.UploadedFile;
import models.modulos.lectorCSV.LectorDeArchivosCSV;
import java.util.List;
import java.util.Map;

public class EntidadesYOrganismosController extends UserController {

    public EntidadesYOrganismosController(){
    }

    public void show(Context context) {

        Map<String, Object> model = Map.of("esAdmin", this.usuarioEsAdmin(context));

        TemplateLoader loader = new ClassPathTemplateLoader();
        loader.setPrefix("/templates");
        Handlebars handlebars = new Handlebars(loader);

        try {
            Template template = handlebars.compile("administracionEntidadesPrestadoresOrganismos");

            context.html(template.apply(model));
            context.status(HttpStatus.OK);
        }catch (Exception e){
            System.out.println(e.getMessage());
            context.status(HttpStatus.INTERNAL_SERVER_ERROR);
            throw new RuntimeException("No pude cargar la pantalla");
        }

    }

    public void create(Context context){
        UploadedFile archivoEmpresas = context.uploadedFile("archivoEmpresas");
        UploadedFile archivoOrganismos = context.uploadedFile("archivoOrganismos");
        LectorDeArchivosCSV lector = null;

        try{
            lector = new LectorDeArchivosCSV();
        }catch (Exception e){
            System.out.println(e.getMessage());
        }

        if(archivoEmpresas != null){
            List<String> empresas;
            empresas = lector.LeerArchivoCsvWeb(archivoEmpresas.content());
        }

        if(archivoOrganismos != null){
            List<String> organismos;
            organismos = lector.LeerArchivoCsvWeb(archivoOrganismos.content());
        }
        context.status(HttpStatus.CREATED);
        context.redirect("/entidadesYorganismos");
    }
}
