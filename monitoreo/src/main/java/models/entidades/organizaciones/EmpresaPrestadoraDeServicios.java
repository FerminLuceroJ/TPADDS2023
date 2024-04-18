package models.entidades.organizaciones;

import lombok.Getter;
import lombok.Setter;

public class EmpresaPrestadoraDeServicios {

  @Getter @Setter
  private String nombre;

  public EmpresaPrestadoraDeServicios(String nombre)
  {
    this.nombre = nombre;
  }

  public EmpresaPrestadoraDeServicios(){}
}
