package models.modulos.Notificador.Notificaciones.Notificacion;

import models.entidades.incidente.Incidente;
import models.modulos.Notificador.Notificaciones.Notificable;

public class CierreIncidente implements Notificable {

  public CierreIncidente(){}
  @Override
  public String getMentsajeDeNotificacion(Incidente incidente) {
    return "Se ha reportado que la incidencia en el servicio: " +
        incidente.getServicioAfectado().getServicio().getNombre() +
        ", ocurrido en: " +
        incidente.getEstablecimientoAfectado().getNombre() +
        ". ha sido solucionado. Ya puede utilizarse con normalidad";
  }
  @Override
  public String getAsuntoDeNotificacion() {
    return "- Un incidente ha sido solucionado! -";
  }
}
