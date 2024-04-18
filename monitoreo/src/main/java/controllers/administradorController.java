package controllers;

import com.github.jknack.handlebars.Handlebars;
import com.github.jknack.handlebars.Template;
import com.github.jknack.handlebars.io.ClassPathTemplateLoader;
import com.github.jknack.handlebars.io.TemplateLoader;
import io.javalin.http.Context;
import io.javalin.http.HttpStatus;
import models.entidades.incidente.Incidente;
import models.entidades.persona.autentificacion.Rol;
import models.entidades.persona.autentificacion.Usuario;
import models.repositorios.RepositorioUsuarios;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class administradorController extends UserController {

    RepositorioUsuarios repositorioUsuarios;

    public administradorController(){
        this.repositorioUsuarios = new RepositorioUsuarios();
    }

    public void index(Context context) {

        context.status(HttpStatus.OK);

        Map<String, Object> model = new HashMap<>();
        model.put("esAdmin", this.usuarioEsAdmin(context));
        List<Usuario> usuarios = this.repositorioUsuarios.usuarios();
        if(!usuarios.isEmpty()) {
            model.put("usuarios", usuarios);
        }

        TemplateLoader loader = new ClassPathTemplateLoader();
        loader.setPrefix("/templates");
        Handlebars handlebars = new Handlebars(loader);

        try {
            Template template = handlebars.compile("administracionUsuarios");

            context.html(template.apply(model));
        }catch (Exception e){
            System.out.println(e.getMessage());
            context.status(HttpStatus.INTERNAL_SERVER_ERROR);
            throw new RuntimeException("No pude cargar la pantalla");
        }

    }

    public void show(Context context) {

        String rol = context.sessionAttribute("rol");

        context.status(HttpStatus.OK);
        boolean esAdmin = rol.equals("ADMIN_COMUNIDAD");

        Map<String, Object> model = Map.of("esAdmin", esAdmin);

        TemplateLoader loader = new ClassPathTemplateLoader();
        loader.setPrefix("/templates");
        Handlebars handlebars = new Handlebars(loader);

        try {
            Template template = handlebars.compile("administracionUsuariosCambioRol");

            context.html(template.apply(model));
        }catch (Exception e){
            System.out.println(e.getMessage());
            context.status(HttpStatus.INTERNAL_SERVER_ERROR);
            throw new RuntimeException("No pude cargar la pantalla");
        }

    }

    public void update(Context context){
        String nuevoRol = context.formParam("nuevoRol");
        Rol rol = Rol.valueOf(nuevoRol);

        Usuario usuario = repositorioUsuarios.usuarioSegunId(Integer.parseInt(context.sessionAttribute("id")));
        usuario.setRol(rol);
        repositorioUsuarios.modificar(usuario);
        context.sessionAttribute("rol",String.valueOf(usuario.getRol()));

        context.redirect("/administracion");
    }

}
