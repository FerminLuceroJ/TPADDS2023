package models.modulos.contrasenias.restricciones.longitud;

import models.entidades.persona.autentificacion.Usuario;
import models.modulos.contrasenias.restricciones.Restriccion;

public interface Longitud extends Restriccion {

  @Override
  public abstract boolean esValidaContrasenia(Usuario usuario);

  @Override
  public String getMensajeError();
}
