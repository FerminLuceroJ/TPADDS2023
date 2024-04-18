package controllers;

import io.javalin.http.Context;
import io.javalin.http.HttpStatus;
import models.entidades.persona.MedioDeNotificacion;
import models.entidades.persona.Miembro;
import models.entidades.persona.autentificacion.Rol;
import models.entidades.persona.autentificacion.Usuario;
import models.repositorios.RepositorioMiembros;
import models.repositorios.RepositorioUsuarios;

import java.time.LocalDate;
import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

public class RegistroController {

    RepositorioUsuarios repositorioUsuarios;

    public RegistroController(){
        this.repositorioUsuarios = new RepositorioUsuarios();
    }

    public void show(Context context) {
        Map<String, Object> model = new HashMap<>();
        context.status(HttpStatus.OK);
        context.render("registro.hbs", model);
    }

    public void create(Context context){
        Usuario usuario = new Usuario();
        Miembro miembro = new Miembro();

        asignarParametros(usuario, miembro, context);
        usuario.setFechaContrasenia(LocalDate.now());
        usuario.setRol(Rol.MIEMBRO);
        miembro.setUsuario(usuario);
        System.out.println(context.formParam("medio-notificacion"));

        RepositorioMiembros repositorioMiembros = new RepositorioMiembros();
        repositorioMiembros.persistir(miembro);

        context.status(HttpStatus.OK);
        context.redirect("/home");
    }

    private void asignarParametros(Usuario usuario, Miembro miembro, Context context) {
        if(!Objects.equals(context.formParam("usuario"), "")) {
            usuario.setUsuario(context.formParam("usuario"));
        }
        if(!Objects.equals(context.formParam("contraseña"), "")) {
            usuario.setContrasenia(context.formParam("contraseña"));
        }
        if(!Objects.equals(context.formParam("nombre"), "")) {
            miembro.setNombre(context.formParam("nombre"));
        }
        if(!Objects.equals(context.formParam("apellido"), "")) {
            miembro.setApellido(context.formParam("apellido"));
        }
        if(!Objects.equals(context.formParam("dni"), "")) {
            miembro.setDni(context.formParam("dni"));
        }
        if(!Objects.equals(context.formParam("email"), "")) {
            usuario.setCorreoElectronico(context.formParam("email"));
        }
        if(!Objects.equals(context.formParam("telefono"), "")) {
            miembro.setTelefono(context.formParam("telefono"));
        }
        if(!Objects.equals(context.formParam("medio-notificacion"), "")) {
            if(context.formParam("medio-notificacion").equals("email")){
                miembro.setMedioDeNotificacion(MedioDeNotificacion.MAIL);
            }else{
                miembro.setMedioDeNotificacion(MedioDeNotificacion.WHATSAPP);
            }
        }
    }
}
