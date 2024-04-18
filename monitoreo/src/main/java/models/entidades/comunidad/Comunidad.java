package models.entidades.comunidad;

import models.entidades.incidente.Incidente;
import models.entidades.incidente.Estado;
import models.entidades.persona.Miembro;
import models.entidades.servicios.servicio.PrestacionDeServicio;
import lombok.Getter;
import lombok.Setter;
import models.modulos.Notificador.Notificaciones.Notificacion.NuevoIncidente;
import models.modulos.Notificador.Notificador;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "comunidad")
public class Comunidad {

  @Id
  @Setter@Getter
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private int id;

  @Getter @Setter
  @Column(name = "nombre")
  private String nombre;

  @Getter
  @OneToMany(mappedBy = "comunidad", cascade = {CascadeType.PERSIST, CascadeType.MERGE}, fetch = FetchType.LAZY)
  private List<Miembro> miembros;

  @Getter
  @OneToMany(mappedBy = "comunidad", cascade = {CascadeType.PERSIST, CascadeType.MERGE}, fetch = FetchType.LAZY)
  private List<Incidente> incidentesReportados;

  @Getter @Setter
  @Column(name = "gradoDeConfianza")
  private Double gradoDeConfianza;

  @Getter
  @ManyToMany(fetch = FetchType.LAZY)
  private List<PrestacionDeServicio> serviciosParticularesObservados;

  @Getter @Setter
  @Column(name = "estaActiva")
  private Boolean estaActiva  = true;

  public Comunidad(String nombre) {
    this.nombre = nombre;
    this.miembros = new ArrayList<>();
    this.incidentesReportados = new ArrayList<>();
    this.serviciosParticularesObservados = new ArrayList<>();
  }

  public Comunidad(String nombre, Double gradoDeConfianza, List<PrestacionDeServicio> serviciosParticularesObservados){
    this.nombre = nombre;
    this.gradoDeConfianza = gradoDeConfianza;
    this.miembros = new ArrayList<>();
    this.incidentesReportados = new ArrayList<>();
    this.serviciosParticularesObservados = serviciosParticularesObservados;
  }

  public Comunidad(String nombre, Double gradoDeConfianza){
    this.nombre = nombre;
    this.gradoDeConfianza = gradoDeConfianza;
    this.miembros = new ArrayList<>();
    this.incidentesReportados = new ArrayList<>();
    this.serviciosParticularesObservados = new ArrayList<>();
  }

  public Comunidad(){
    this.miembros = new ArrayList<>();
    this.incidentesReportados = new ArrayList<>();
    this.serviciosParticularesObservados = new ArrayList<>();
  }

  public void agregarMiembro(Miembro miembro) {
    this.miembros.add(miembro);
    miembro.setComunidad(this);
  }

  public void removerMiembro(Miembro miembro) {
    this.miembros.remove(miembro);
  }

  public void agregarIncidenteReportado(Incidente incidente, Notificador notificador) {
    this.incidentesReportados.add(incidente);
    incidente.setComunidad(this);

    NuevoIncidente nuevoIncidente = new NuevoIncidente();
    for (Miembro miembro: this.miembros){
      notificador.notificarAMiembro(miembro, nuevoIncidente, incidente);
    }
  }

  public List<Incidente> incidentesSegunEstado(Estado estado){
    return incidentesReportados.stream().filter(incidente -> estado == incidente.getEstado()).toList();
  }

  public void agregarServicioObservado(PrestacionDeServicio prestacionDeServicio) {
  this.serviciosParticularesObservados.add(prestacionDeServicio);
    
  }

}
