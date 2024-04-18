package models.dtos.servicio3;

import lombok.Getter;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;

public class EntidadParaRankingDTO {

  @Getter
  private Integer id;

  @Getter
  private String nombre;

  @Getter
  private List<Integer> idEstablecimientos;

  public EntidadParaRankingDTO(){
    this.idEstablecimientos = new ArrayList<>();
  }

  public EntidadParaRankingDTO(Integer id, String nombre, List<Integer> idEstablecimientos){
    this.id = id;
    this.nombre = nombre;
    this.idEstablecimientos = idEstablecimientos;
  }
}
