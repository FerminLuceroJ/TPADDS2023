package controllers;

import com.github.jknack.handlebars.Handlebars;
import com.github.jknack.handlebars.Template;
import com.github.jknack.handlebars.io.ClassPathTemplateLoader;
import com.github.jknack.handlebars.io.TemplateLoader;
import io.javalin.http.Context;
import io.javalin.http.HttpStatus;
import models.entidades.incidente.Incidente;
import models.entidades.incidente.Observacion;
import models.repositorios.RepositorioIncidentes;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class ObservacionesController extends UserController {

  private RepositorioIncidentes repositorioIncidentes;

  public ObservacionesController(){
    this.repositorioIncidentes = new RepositorioIncidentes();
  }

  public void index(Context context){

    Incidente incidente = (Incidente) this.repositorioIncidentes.incidenteSegunId(Integer.parseInt(context.pathParam("id")));
    Map<String, Object> model = new HashMap<>();
    model.put("esAdmin", this.usuarioEsAdmin(context));
    model.put("incidente", incidente);
    model.put("observaciones", incidente.getObservaciones());

    TemplateLoader loader = new ClassPathTemplateLoader();
    loader.setPrefix("/templates");
    Handlebars handlebars = new Handlebars(loader);

    try {
      Template template = handlebars.compile("observaciones");

      context.html(template.apply(model));
      context.status(HttpStatus.OK);
    }catch (Exception e){
      System.out.println(e.getMessage());
      context.status(HttpStatus.INTERNAL_SERVER_ERROR);
      throw new RuntimeException("No pude cargar la pantalla");
    }

  }
}
