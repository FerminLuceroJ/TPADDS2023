package models.entidades.servicios.servicio;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import models.entidades.organizaciones.Establecimiento;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

@Entity
@Table(name = "prestacion_de_servicio")
public class PrestacionDeServicio {
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  @Setter @Getter
  private int id;

  @Getter @Setter
  @ManyToOne(fetch = FetchType.EAGER)
  @JoinColumn(name = "id_servicio", referencedColumnName = "id")
  private Servicio servicio;

  @Getter @Setter
  @JsonManagedReference
  @ManyToOne(fetch = FetchType.EAGER)
  @JoinColumn(name = "id_establecimiento", referencedColumnName = "id")
  private Establecimiento establecimiento;

  public PrestacionDeServicio(Servicio servicio, Establecimiento establecimiento){
    this.servicio = servicio;
    this.establecimiento = establecimiento;
  }

  public PrestacionDeServicio() {
  }

}
