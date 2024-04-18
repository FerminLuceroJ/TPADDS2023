package models.entidades.rankings.ValueObjects;

import models.entidades.organizaciones.entidad.Entidad;
import lombok.Getter;
import lombok.Setter;

public class DatosParaRankingDos {
  @Setter@Getter
  private Entidad entidad;
  @Setter@Getter
  private Integer suma;
  public DatosParaRankingDos(Entidad entidad, Integer suma){
    this.entidad = entidad;
    this.suma = suma;
  }

  public String getDatos(){
    return "La cantidad de incidentes reportados en " + entidad.getNombre() + " fue de: " + suma;
  }


}
