package models.entidades.incidente;

import lombok.Getter;
import lombok.Setter;
import models.entidades.persona.Miembro;

import javax.persistence.*;
import java.time.LocalDateTime;

@Entity
@Table(name = "observacion")
public class Observacion {

  @Id
  @Setter@Getter
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private int id;

  @Getter @Setter
  @ManyToOne(fetch = FetchType.LAZY)
  @JoinColumn(name = "id_miembro", referencedColumnName = "id")
  private Miembro notificante;

  @Getter @Setter
  @Column(name = "descripcion", columnDefinition = "TEXT")
  private String descripcion;

  @Setter@Getter
  @Column(name = "fecha", columnDefinition = "TIMESTAMP")
  private LocalDateTime fecha;

  @Setter@Getter
  @ManyToOne(fetch = FetchType.LAZY)
  @JoinColumn(name = "id_incidente", referencedColumnName = "id")
  private Incidente incidente;

  public Observacion(Miembro notificante, String descripcion){
    this.notificante = notificante;
    this.descripcion = descripcion;
    this.fecha = LocalDateTime.now();
  }

  public Observacion(){
    this.fecha = LocalDateTime.now();
  }
}
