package models.dtos.servicio3;

import lombok.Getter;

@Getter
public class EntradaRanking {


  private Integer puesto;
  private Float puntaje;
  private String entidad;
  private Integer entidadId;

  public EntradaRanking(Integer puesto, Float puntaje, String entidad, Integer entidadId) {
    this.puesto = puesto;
    this.puntaje = puntaje;
    this.entidad = entidad;
    this.entidadId = entidadId;
  }

  public EntradaRanking(){}

}
