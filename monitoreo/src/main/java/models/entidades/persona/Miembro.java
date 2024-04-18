package models.entidades.persona;

import models.entidades.comunidad.Comunidad;
import models.entidades.incidente.Estado;
import models.entidades.incidente.Incidente;
import models.entidades.ubicacion.Localizacion;
import lombok.Getter;
import lombok.Setter;
import models.entidades.persona.autentificacion.Usuario;
import models.modulos.Notificador.Notificaciones.Notificacion.RevisionIncidente;
import models.modulos.Notificador.Notificador;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name="miembro")
public class Miembro {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  @Getter @Setter
  private int id;

  @Getter @Setter
  @Column(name = "nombre")
  private String nombre;

  @Getter @Setter
  @Column(name = "apellido")
  private String apellido;

  @Getter @Setter
  @Column(name = "dni")
  private String dni;

  @Getter @Setter
  @OneToOne(cascade = {CascadeType.PERSIST, CascadeType.MERGE, CascadeType.REMOVE}, fetch = FetchType.LAZY)
  private Usuario usuario;

  @Getter @Setter
  @ManyToOne(fetch = FetchType.LAZY)
  @JoinColumn(name = "id_localizacion", referencedColumnName = "id")
  private Localizacion localizacionDeInteres;

  @Getter @Setter
  @OneToMany(mappedBy = "miembro", cascade = {CascadeType.PERSIST, CascadeType.MERGE, CascadeType.REMOVE}, fetch = FetchType.LAZY)
  private List<Interes> intereses;

  @Getter @Setter
  @ManyToOne(fetch = FetchType.LAZY)
  @JoinColumn(name="id_comunidad",referencedColumnName = "id")
  private Comunidad comunidad;

  @Getter @Setter
  @Column(name = "medio_notificacion")
  @Enumerated(EnumType.STRING)
  private MedioDeNotificacion medioDeNotificacion;

  @Getter @Setter
  @Column(name = "estrategia_notificacion")
  @Enumerated(EnumType.STRING)
  private EstrategiaDeNotificacion estrategiaDeNotificacion;

  @Getter @Setter
  @Column(name = "telefono")
  private String telefono;

  @Getter @Setter
  @Column(name = "tipo_miembro")
  @Enumerated(EnumType.STRING)
  private TipoMiembro tipo;

  public Miembro(String nombre, String apellido, Localizacion localizacion) {
    this.setNombre(nombre);
    this.setApellido(apellido);
    this.localizacionDeInteres = localizacion;
    this.intereses = new ArrayList<>();
    this.tipo = TipoMiembro.AFECTADO;
  }

  public Miembro(){
    this.intereses = new ArrayList<>();
    this.tipo = TipoMiembro.AFECTADO;
  }

  public void agregarInteres(Interes interes) {
    this.intereses.add(interes);
    interes.setMiembro(this);
  }

  public void removerInteres(Interes interes) {
    this.intereses.remove(interes);
  }

  public Boolean estaCercaDeIncidente(Localizacion localizacion) {

    if ((localizacion.getEsProvincia() && localizacionDeInteres.getEsProvincia()) || (!localizacion.getEsProvincia() && !localizacionDeInteres.getEsProvincia())) {
      return this.localizacionDeInteres.getId().equals(localizacion.getId());
    } else if (localizacion.getEsProvincia()) {
      return this.localizacionDeInteres.getProvincia().getId().equals(localizacion.getId());
    } else {
      return this.localizacionDeInteres.getId().equals(localizacion.getProvincia().getId());
    }

  }

  public void setLocalizacionDeInteres(Localizacion localizacion, Notificador notificador) {
    this.localizacionDeInteres = localizacion;

    if(this.comunidad == null)
        return;

    for (Incidente incidente : comunidad.incidentesSegunEstado(Estado.ABIERTO)) {
      if (this.estaCercaDeIncidente(incidente.getLocalizacion())) {
        notificador.notificarAMiembro(this, new RevisionIncidente(), incidente);
      }
    }
  }
}
