package models.modulos.contrasenias.restricciones.expresionregular;

import models.entidades.persona.autentificacion.Usuario;
import models.modulos.contrasenias.restricciones.Restriccion;

public abstract class ExpresionRegular implements Restriccion {

  @Override
  public boolean esValidaContrasenia(Usuario usuario) {
    return usuario.getContrasenia().matches(obtenerExpresionRegular());
  }

  @Override
  public String getMensajeError() {
    return "La contraseña debe cumplir la expresión regular: " + obtenerExpresionRegular() + " .";
  }

  protected abstract String obtenerExpresionRegular();

}

