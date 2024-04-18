package controllers;

import com.github.jknack.handlebars.Handlebars;
import com.github.jknack.handlebars.Template;
import com.github.jknack.handlebars.io.ClassPathTemplateLoader;
import com.github.jknack.handlebars.io.TemplateLoader;
import io.javalin.http.Context;
import io.javalin.http.HttpStatus;
import models.entidades.incidente.Estado;
import models.entidades.incidente.Incidente;
import models.entidades.organizaciones.Establecimiento;
import models.entidades.organizaciones.entidad.Entidad;
import models.entidades.persona.autentificacion.Usuario;
import models.entidades.servicios.servicio.PrestacionDeServicio;
import models.entidades.servicios.servicio.Servicio;
import models.repositorios.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class IncidentesController extends UserController{

  private RepositorioIncidentes repositorioIncidentes;

  private RepositorioEstablecimientos repositorioEstablecimientos;

  private RepositorioEntidades repositorioEntidades;

  private RepositorioPrestacionesDeServicios repositorioPrestacionesDeServicios;

  private RepositorioServicios repositorioServicios;

  private RepositorioUsuarios repositorioUsuarios;

  public IncidentesController(){
    this.repositorioIncidentes = new RepositorioIncidentes();
    this.repositorioEstablecimientos = new RepositorioEstablecimientos();
    this.repositorioEntidades = new RepositorioEntidades();
    this.repositorioServicios = new RepositorioServicios();
    this.repositorioPrestacionesDeServicios = new RepositorioPrestacionesDeServicios();
    this.repositorioUsuarios = new RepositorioUsuarios();
  }

  public void index(Context context){

    Map<String, Object> model = new HashMap<>();

    List<Incidente> incidentes = this.repositorioIncidentes.incidentes();

    model.put("esAdmin", this.usuarioEsAdmin(context));
    model.put("incidentes",incidentes);

    TemplateLoader loader = new ClassPathTemplateLoader();
    loader.setPrefix("/templates");
    Handlebars handlebars = new Handlebars(loader);

    try {
      Template template = handlebars.compile("gestionIncidentes");

      context.html(template.apply(model));
    }catch (Exception e){
      System.out.println(e.getMessage());
      context.status(HttpStatus.INTERNAL_SERVER_ERROR);
      throw new RuntimeException("No pude cargar la pantalla");
    }

    context.status(HttpStatus.OK);

  }

  public void update(Context context){
    Incidente incidente = this.repositorioIncidentes.incidenteSegunId(Integer.parseInt(context.pathParam("id")));
    incidente.setEstado(Estado.CERRADO);
    this.repositorioIncidentes.modificar(incidente);
    context.status(HttpStatus.ACCEPTED);
    context.redirect("/incidentes");
  }

  public void abiertos(Context context){
    Map<String, Object> model = new HashMap<>();
    List<Incidente> incidentes = this.repositorioIncidentes.incidentesSegunEstado(Estado.ABIERTO);
    model.put("incidentes", incidentes);
    model.put("esAdmin", this.usuarioEsAdmin(context));

    TemplateLoader loader = new ClassPathTemplateLoader();
    loader.setPrefix("/templates");
    Handlebars handlebars = new Handlebars(loader);

    try {
      Template template = handlebars.compile("gestionIncidentes");

      context.html(template.apply(model));
    }catch (Exception e){
      System.out.println(e.getMessage());
      context.status(HttpStatus.INTERNAL_SERVER_ERROR);
      throw new RuntimeException("No pude cargar la pantalla");
    }

  }

  public void cerrados(Context context){
    Map<String, Object> model = new HashMap<>();
    List<Incidente> incidentes = this.repositorioIncidentes.incidentesSegunEstado(Estado.CERRADO);
    model.put("incidentes", incidentes);
    model.put("esAdmin", this.usuarioEsAdmin(context));

    TemplateLoader loader = new ClassPathTemplateLoader();
    loader.setPrefix("/templates");
    Handlebars handlebars = new Handlebars(loader);

    try {
      Template template = handlebars.compile("gestionIncidentes");

      context.html(template.apply(model));
    }catch (Exception e){
      System.out.println(e.getMessage());
      context.status(HttpStatus.INTERNAL_SERVER_ERROR);
      throw new RuntimeException("No pude cargar la pantalla");
    }
  }

  public void create(Context context){

    //Entidad entidad = (Entidad) this.repositorioEntidades.entidadSegunNombre(context.formParam("entidad"));
    Establecimiento establecimiento = (Establecimiento) this.repositorioEstablecimientos.establecimientoSegunNombre(context.formParam("establecimiento"));
    Servicio servicio = (Servicio) this.repositorioServicios.servicioSegunNombre(context.formParam("servicio"));

    PrestacionDeServicio prestacion = (PrestacionDeServicio)
        this.repositorioPrestacionesDeServicios
            .prestacionSegunEstablecimientoYServicio(establecimiento.getId(), servicio.getId());

    Incidente nuevoIncidente = new Incidente();
    nuevoIncidente.setEstablecimientoAfectado(establecimiento);
    nuevoIncidente.setServicioAfectado(prestacion);
    nuevoIncidente.setDescripcion(context.formParam("descripcion"));

    //Usuario usuario = (Usuario) repositorioUsuarios.usuarioSegunId(context.sessionAttribute("id"));

    repositorioIncidentes.persistir(nuevoIncidente);

    context.status(HttpStatus.CREATED);
    context.redirect("/incidentes");
  }

  public void show(Context context){

    Map<String, Object> model = new HashMap<>();

    model.put("esAdmin", this.usuarioEsAdmin(context));

    TemplateLoader loader = new ClassPathTemplateLoader();
    loader.setPrefix("/templates");
    Handlebars handlebars = new Handlebars(loader);

    List<Entidad> entidads = this.repositorioEntidades.entidades();
    List<Establecimiento> establecimientos = this.repositorioEstablecimientos.establecimientos();
    List<Servicio> servicios = this.repositorioServicios.servicios();

    try {
      Template template = handlebars.compile("aperturaIncidentes");
      model.put("entidades",entidads);
      model.put("establecimientos", establecimientos);
      model.put("servicios", servicios);

      context.html(template.apply(model));
      context.status(HttpStatus.OK);
    } catch (Exception e) {
      System.out.println(e.getMessage());
      context.status(HttpStatus.INTERNAL_SERVER_ERROR);
      throw new RuntimeException("No pude cargar la pantalla");
    }
  }
}

