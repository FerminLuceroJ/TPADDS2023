package models.dtos.servicio3;

import lombok.Getter;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;

public class ComunidadParaRankingDTO {

  @Getter @Setter
  private Integer cantMiembrosAfectados;

  @Getter
  private List<IncidenteParaRankingDTO> incidentes;

  public ComunidadParaRankingDTO(){
    this.incidentes = new ArrayList<>();
  }

  public ComunidadParaRankingDTO(Integer cantMiembrosAfectados, List<IncidenteParaRankingDTO> incidentes){
    this.cantMiembrosAfectados = cantMiembrosAfectados;
    this.incidentes = incidentes;
  }
}
