package models.modulos.contrasenias.restricciones;

import models.entidades.persona.autentificacion.Usuario;

public interface Restriccion {
  public boolean esValidaContrasenia(Usuario usuario);

  public String getMensajeError();
}
