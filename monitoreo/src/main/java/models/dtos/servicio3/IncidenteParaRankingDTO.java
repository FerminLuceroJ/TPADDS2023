package models.dtos.servicio3;

import lombok.Getter;

import java.time.LocalDateTime;

public class IncidenteParaRankingDTO {

  @Getter
  private LocalDateTime fechaHoraApertura;

  @Getter
  private LocalDateTime fechaHoraCierre;

  @Getter
  private Integer idEstablecimiento;

  public IncidenteParaRankingDTO(LocalDateTime fechaHoraApertura, LocalDateTime fechaHoraCierre, Integer idEstablecimiento){
    this.fechaHoraApertura = fechaHoraApertura;
    this.fechaHoraCierre = fechaHoraCierre;
    this.idEstablecimiento = idEstablecimiento;
  }

  public IncidenteParaRankingDTO(){}
}
