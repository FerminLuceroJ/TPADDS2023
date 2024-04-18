package models.dtos.servicio3;

import lombok.Getter;

import java.util.List;

public class RankingDTO {

  private Float cnf;

  @Getter
  private List<ComunidadParaRankingDTO> comunidades;

  @Getter
  private List<EntidadParaRankingDTO> entidades;

  public RankingDTO(Float cnf, List<ComunidadParaRankingDTO> comunidades, List<EntidadParaRankingDTO> entidades){
    this.cnf = cnf;
    this.comunidades = comunidades;
    this.entidades = entidades;
  }

  public RankingDTO(){}
}
