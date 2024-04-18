package models.dtos.servicio3;

import lombok.Getter;

import java.util.List;

@Getter
public class RankingGenerado {

  private String descripcion;

  private List<EntradaRanking> ranking;

  public RankingGenerado(String descripcion, List<EntradaRanking> ranking) {
    this.descripcion = descripcion;
    this.ranking = ranking;
  }

  public RankingGenerado(){}
}
