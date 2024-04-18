package models.entidades.ubicacion;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;

@Entity
@DiscriminatorValue("Provincia")
public class Provincia extends Localizacion {
  public Provincia getProvincia(){
    return null;
  }
}
