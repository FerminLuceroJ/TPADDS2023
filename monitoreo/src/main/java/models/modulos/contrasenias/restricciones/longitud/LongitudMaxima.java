package models.modulos.contrasenias.restricciones.longitud;

import models.entidades.persona.autentificacion.Usuario;

public class LongitudMaxima implements Longitud {
  private static final Integer tamanioMaximo = 64;

  @Override
  public boolean esValidaContrasenia(Usuario usuario) {
    return usuario.getContrasenia().length() <= tamanioMaximo;
  }

  @Override
  public String getMensajeError() {
    return "La contraseña debe tener como maximo " + tamanioMaximo + " caracteres.";
  }
}
