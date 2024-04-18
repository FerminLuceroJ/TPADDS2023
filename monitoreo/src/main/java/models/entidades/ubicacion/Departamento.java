package models.entidades.ubicacion;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

@Entity
@DiscriminatorValue("Departamento")
public class Departamento extends Localizacion {

  @Getter @Setter
  @OneToOne(fetch = FetchType.LAZY)
  @JoinColumn(name = "id_provincia", referencedColumnName = "id")
  public Provincia provincia;
}
