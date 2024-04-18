package models.dtos.servicio3;

import models.entidades.comunidad.Comunidad;
import models.entidades.incidente.Estado;
import models.entidades.incidente.Incidente;
import models.entidades.organizaciones.Establecimiento;
import models.entidades.organizaciones.entidad.Entidad;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class ConverterEntidadADTO {

  public List<ComunidadParaRankingDTO> convertirAComunidadParaRankingDTO(List<Comunidad> comunidades){
    List<ComunidadParaRankingDTO> comunidadesParaRankingDTO = new ArrayList<>();

    for(Comunidad comunidad : comunidades){
      ComunidadParaRankingDTO comunidadParaRankingDTO = new ComunidadParaRankingDTO(
          comunidad.getMiembros().size(),
          this.convertirAIncidentesParaRankingDTO(
              comunidad
                  .getIncidentesReportados()
                  .stream()
                  .filter(Incidente -> Incidente.getEstado() == Estado.CERRADO)
                  .toList()
          )
      );
      comunidadesParaRankingDTO.add(comunidadParaRankingDTO);
    }
    return comunidadesParaRankingDTO;
  }

  public List<IncidenteParaRankingDTO> convertirAIncidentesParaRankingDTO(List<Incidente> incidentes){
    List<IncidenteParaRankingDTO> incidentesParaRankingDTO = new ArrayList<>();

    for(Incidente incidente : incidentes){
      IncidenteParaRankingDTO incidenteParaRankingDTO = new IncidenteParaRankingDTO(
          incidente.getFechaAlta(),
          incidente.getFechaDeCierre(),
          incidente.getEstablecimientoAfectado().getId()
      );
      incidentesParaRankingDTO.add(incidenteParaRankingDTO);
    }
    return incidentesParaRankingDTO;
  }

  public List<EntidadParaRankingDTO> convertirAEntidadesParaRankingDTO(List<Entidad> entidades){
    List<EntidadParaRankingDTO> entidadesParaRankingDTO = new ArrayList<>();

    for(Entidad entidad : entidades){
      EntidadParaRankingDTO entidadParaRankingDTO = new EntidadParaRankingDTO(
        entidad.getId(),
        entidad.getNombre(),
        entidad.getEstablecimientos().stream()
            .map(Establecimiento::getId)
            .collect(Collectors.toList())
      );
      entidadesParaRankingDTO.add(entidadParaRankingDTO);
    }
    return entidadesParaRankingDTO;
  }


}
