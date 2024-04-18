package models.entidades.incidente;

import models.entidades.organizaciones.entidad.Entidad;
import lombok.Getter;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Objects;

public class RepositorioIncidentes {

  @Getter
  List<Incidente> incidentes;
  public RepositorioIncidentes(){
    this.incidentes = new ArrayList<>();
  }

  public void agregarIncidente(Incidente... incidentes){
    Collections.addAll(this.incidentes, incidentes);
  }

  public List<Incidente> getIncidentesCerrados() {
    return this.incidentes.stream().filter(incidente -> incidente.getEstado() == Estado.CERRADO).toList();
  }

  public List<Incidente> getIncidentesEnEntidad(Entidad entidad){
    return this.incidentes.stream().filter(incidente -> Objects.equals(incidente.getEstablecimientoAfectado().getNombreEntidad(), entidad.getNombre())).toList();
  }

  public List<Incidente> getIncidentesCerradosEnEntidad(Entidad entidad){
    List<Incidente> incidentesCerrados = this.getIncidentesCerrados();
    return incidentesCerrados.stream().filter(incidente -> Objects.equals(incidente.getEstablecimientoAfectado().getNombreEntidad(), entidad.getNombre())).toList();
  }

  public List<Incidente> getIncidentesRepetidosDe(Incidente unIncidente){
    return incidentes.stream().filter(incidente ->
        (incidente.getEstablecimientoAfectado().getNombre().equals(unIncidente.getEstablecimientoAfectado().getNombre())
        &&
        incidente.getServicioAfectado().getServicio().getNombre().equals(unIncidente.getServicioAfectado().getServicio().getNombre()))).toList();
  }

  public Integer cantidadDeIncidentesRepetidosDe(Incidente unIncidente){
    return this.getIncidentesRepetidosDe(unIncidente).size();
  }

  public List<Incidente> getIncidentesRepetidosEnMismaFecha(Incidente unIncidente){
    return this.getIncidentesRepetidosDe(unIncidente).stream().filter(incidente ->
        incidente.getFechaAlta().toLocalDate().equals(unIncidente.getFechaAlta().toLocalDate()))
        .toList();
  }

  public List<Incidente> getIncidentesRepetidosEnDistintaFecha(Incidente unIncidente){
    return this.getIncidentesRepetidosDe(unIncidente).stream().filter(incidente ->
            !incidente.getFechaAlta().toLocalDate().equals(unIncidente.getFechaAlta().toLocalDate()))
        .toList();
  }

}
