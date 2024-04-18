package models.entidades.rankings.reportes;

import lombok.Getter;

import java.util.ArrayList;
import java.util.List;

public class Reporte{
  @Getter
  List<String> datos;

  public Reporte(){
    this.datos = new ArrayList<>();
  }

  public void agregarDato(String dato){
    this.datos.add(dato);
  }
}
