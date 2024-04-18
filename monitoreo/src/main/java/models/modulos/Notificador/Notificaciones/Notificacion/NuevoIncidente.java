package models.modulos.Notificador.Notificaciones.Notificacion;

import models.entidades.incidente.Incidente;
import models.modulos.Notificador.Notificaciones.Notificable;

public class NuevoIncidente implements Notificable {

  public NuevoIncidente(){}
  @Override
  public String getMentsajeDeNotificacion(Incidente incidente) {
    return "Se ha reportado una nueva incidencia en el servicio " +
        incidente.getServicioAfectado().getServicio().getNombre() +
        ", ocurrido en: " +
        incidente.getEstablecimientoAfectado().getNombre() +
        ". Observacion del incidente: " +
        incidente.getDescripcion();
  }
  @Override
  public String getAsuntoDeNotificacion() {
    return "- Nuevo incidente! -";
  }
}
