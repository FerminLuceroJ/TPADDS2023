package models.modulos.Notificador.Notificaciones;

import models.entidades.incidente.Incidente;

public interface Notificable {
  public String getMentsajeDeNotificacion(Incidente incidente);

  public String getAsuntoDeNotificacion();
}
