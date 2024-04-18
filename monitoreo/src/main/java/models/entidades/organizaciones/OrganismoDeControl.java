package models.entidades.organizaciones;

import lombok.Getter;
import lombok.Setter;

public class OrganismoDeControl {
  @Getter @Setter
  private String nombre;

  public OrganismoDeControl(String nombre)
  {
    this.nombre = nombre;
  }

  public OrganismoDeControl(){}
}
