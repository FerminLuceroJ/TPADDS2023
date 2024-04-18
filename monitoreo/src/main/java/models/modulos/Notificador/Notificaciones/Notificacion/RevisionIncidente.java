package models.modulos.Notificador.Notificaciones.Notificacion;

import models.entidades.incidente.Incidente;
import models.modulos.Notificador.Notificaciones.Notificable;

public class RevisionIncidente implements Notificable {

  public RevisionIncidente(){}
  @Override
  public String getMentsajeDeNotificacion(Incidente incidente) {
    return "Al parecer se encuentra cerca de un incidente sobre el servicio: " +
        incidente.getServicioAfectado().getServicio().getNombre() +
        ", ocurrido en: " +
        incidente.getEstablecimientoAfectado().getNombre() +
        ".  Se le solicita porfavor acercarse al mismo y verificar si ya se encuentra solucionado. Muchas gracias";
  }

  @Override
  public String getAsuntoDeNotificacion() {
    return "- Revision de incidente! -";
  }
}
