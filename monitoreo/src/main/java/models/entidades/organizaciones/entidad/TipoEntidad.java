package models.entidades.organizaciones.entidad;

import models.entidades.organizaciones.Establecimiento;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

@Entity
@Table(name = "tipo_entidad")
public class TipoEntidad {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  @Getter@Setter
  private int id;

  @Getter @Setter
  @Column(name = "nombre")
  private String nombre;

  @Getter @Setter
  @Column(name = "prefijo_entidad")
  private String prefijoEntidad;

  @Getter @Setter
  @Column(name = "prefijo_establecimiento")
  private String prefijoEstablecimiento;

  public TipoEntidad (String nombre, String prefijoEntidad, String prefijoEstablecimiento) {
    this.nombre = nombre;
    this.prefijoEntidad = prefijoEntidad;
    this.prefijoEstablecimiento = prefijoEstablecimiento;
  }

  public TipoEntidad(){
  }

  public String getNombreCompletoEntidad(Entidad entidad) {

    return this.prefijoEntidad + " " + entidad.getNombre();
  }

  public String getNombreCompletoEstablecimiento(Establecimiento establecimiento) {

    return this.prefijoEstablecimiento + " " + establecimiento.getNombre();
  }

}
