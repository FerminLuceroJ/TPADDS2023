package models.entidades.rankings.ValueObjects;

import models.entidades.incidente.Incidente;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

public class IncidenteRepetidoPorFecha {
  @Getter@Setter
  private Incidente incidente;
  @Getter@Setter
  private LocalDateTime fecha;

  public IncidenteRepetidoPorFecha(Incidente incidente, LocalDateTime fecha){
    this.incidente = incidente;
    this.fecha = fecha;
  }
}
