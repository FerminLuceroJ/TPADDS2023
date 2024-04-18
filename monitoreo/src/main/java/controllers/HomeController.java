package controllers;

import com.github.jknack.handlebars.Handlebars;
import com.github.jknack.handlebars.Template;
import com.github.jknack.handlebars.io.ClassPathTemplateLoader;
import com.github.jknack.handlebars.io.TemplateLoader;
import io.javalin.http.Context;
import io.javalin.http.HttpStatus;
import models.entidades.incidente.Estado;
import models.entidades.incidente.Incidente;
import models.entidades.persona.Miembro;
import models.repositorios.RepositorioComunidades;
import models.repositorios.RepositorioMiembros;
import models.repositorios.RepositorioUsuarios;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class HomeController  extends UserController {

    RepositorioMiembros  repositorioMiembros;
    RepositorioComunidades repositorioComunidades;


    public HomeController() {
        this.repositorioMiembros = new RepositorioMiembros();
        this.repositorioComunidades = new RepositorioComunidades();
    }

    public void show(Context context){

        String sessionValueId = context.sessionAttribute("id");

        if(sessionValueId == null){
            context.render("home.hbs");
        }else{

            Map<String, Object> model = new HashMap<>();

            Miembro miembro = repositorioMiembros.miembrosSegunUsuario(Integer.parseInt(sessionValueId));
            List<Incidente> incidentes;

            if(miembro.getComunidad() != null) {
                incidentes = (miembro.getComunidad().getIncidentesReportados());

                incidentes = incidentes.stream().filter(
                    incidente -> miembro.estaCercaDeIncidente(incidente.getLocalizacion())
                        && incidente.getEstado() == Estado.ABIERTO).toList();
            }
            else{
                incidentes = null;
            }

            model.put("esAdmin",this.usuarioEsAdmin(context));
            model.put("incidentes",incidentes);

            TemplateLoader loader = new ClassPathTemplateLoader();
            loader.setPrefix("/templates");
            Handlebars handlebars = new Handlebars(loader);

            try {
                Template template = handlebars.compile("homeUsuario");

                context.html(template.apply(model));
                context.status(HttpStatus.OK);
            }catch (Exception e){
                System.out.println(e.getMessage());
                context.status(HttpStatus.INTERNAL_SERVER_ERROR);
                throw new RuntimeException("No pude cargar la pantalla");
            }
        }
    }
}
