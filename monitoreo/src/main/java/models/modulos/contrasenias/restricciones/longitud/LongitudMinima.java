package models.modulos.contrasenias.restricciones.longitud;

import models.entidades.persona.autentificacion.Usuario;

public class LongitudMinima implements Longitud {
  private static final Integer tamanioMinimo = 8;

  @Override
  public boolean esValidaContrasenia(Usuario usuario) {
    return usuario.getContrasenia().length() >= tamanioMinimo;
  }

  @Override
  public String getMensajeError() {
    return "La contraseña debe tener al menos " + tamanioMinimo + " caracteres.";
  }
}
