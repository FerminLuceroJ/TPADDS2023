package models.entidades.rankings.ValueObjects;

import models.entidades.organizaciones.entidad.Entidad;
import lombok.Getter;
import lombok.Setter;

public class PromedioDuracionIncidente {
  @Setter @Getter
  private Entidad entidad;
  @Setter @Getter
  private Double promedio;

  public PromedioDuracionIncidente(Entidad entidad, Double promedio){
    this.entidad = entidad;
    this.promedio = promedio;
  }
}
