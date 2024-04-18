package controllers;

import io.javalin.http.Context;
import io.javalin.http.HttpStatus;
import models.entidades.persona.Miembro;
import models.entidades.persona.autentificacion.Usuario;
import models.repositorios.RepositorioUsuarios;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Objects;

public class LoginController {

    RepositorioUsuarios repositorioUsuarios;

    public LoginController(){
        this.repositorioUsuarios = new RepositorioUsuarios();
    }

    public void show(Context context) {
        Map<String, Object> model = new HashMap<>();
        context.status(HttpStatus.OK);
        context.render("login.hbs", model);
    }

    public void login(Context context){

        Usuario usuario = new Usuario();
        asignarParametros(usuario, context);

        usuario = repositorioUsuarios.usuarioSegunCorreoElectronica(usuario.getCorreoElectronico());

        if(usuario == null || context.formParam("checkboxSoyRobot") == null){
            recargarPagina(context);
            return;
        }

        if(usuario.getContrasenia().equals(context.formParam("contraseña"))){
            context.sessionAttribute("id",String.valueOf(usuario.getId()));
            context.sessionAttribute("rol",String.valueOf(usuario.getRol()));

            context.redirect("home");
        }else{
            recargarPagina(context);
        }
    }

    private void recargarPagina(Context context){
        context.status(HttpStatus.FORBIDDEN);
        context.redirect("login");
    }

    private void asignarParametros(Usuario usuario, Context context) {
        if(!Objects.equals(context.formParam("email"), "")) {
            usuario.setCorreoElectronico(context.formParam("email"));
        }

        if(!Objects.equals(context.formParam("contraseña"), "")) {
            usuario.setContrasenia(context.formParam("contraseña"));
        }
    }
}