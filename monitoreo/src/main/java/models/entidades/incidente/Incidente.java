package models.entidades.incidente;

import models.entidades.comunidad.Comunidad;
import models.entidades.organizaciones.Establecimiento;
import models.entidades.persona.Miembro;
import models.entidades.servicios.servicio.PrestacionDeServicio;
import java.time.LocalDateTime;

import models.entidades.ubicacion.Localizacion;
import lombok.Getter;
import lombok.Setter;
import models.modulos.Notificador.Notificaciones.Notificacion.CierreIncidente;
import models.modulos.Notificador.Notificador;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "incidente")
public class Incidente {

    @Id
    @Setter@Getter
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Getter @Setter
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id_miembro",referencedColumnName = "id")
    private Miembro notificante;

    @Getter @Setter
    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "id_prestacion_de_servicio", referencedColumnName = "id")
    private PrestacionDeServicio servicioAfectado;

    @Getter @Setter
    @Column(name = "fecha_alta", columnDefinition = "TIMESTAMP")
    private LocalDateTime fechaAlta;

    @Getter @Setter
    @Column(name="descripcion", columnDefinition = "TEXT")
    private String descripcion;

    @Getter @Setter
    @Column(name="estado")
    @Enumerated(EnumType.STRING)
    private Estado estado;

    @Getter @Setter
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id_establecimiento",referencedColumnName = "id")
    private Establecimiento establecimientoAfectado;

    @Getter
    @OneToMany(mappedBy = "incidente", cascade = {CascadeType.PERSIST, CascadeType.MERGE, CascadeType.REMOVE}, fetch = FetchType.LAZY)
    private List<Observacion> observaciones;

    @Getter@Setter
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id_comunidad", referencedColumnName = "id")
    private Comunidad comunidad;

    public Incidente(Miembro notificante, PrestacionDeServicio servicioAfectado, Establecimiento establecimientoAfectado, String descripcion){
    this.notificante = notificante;
    this.servicioAfectado = servicioAfectado;
    this.establecimientoAfectado = establecimientoAfectado;
    this.fechaAlta = LocalDateTime.now();
    this.descripcion = descripcion;
    this.estado = Estado.ABIERTO;
    this.observaciones = new ArrayList<>();
    }

    public Incidente(){
        this.observaciones = new ArrayList<>();
        this.estado = Estado.ABIERTO;
    }

    public void agregarObservacion(Observacion observacion){
        this.observaciones.add(observacion);
        observacion.setIncidente(this);
    }

    public void cerrarIncidente(Miembro notificante, String descripcion, Notificador notificador){
        this.estado = Estado.CERRADO;
        Observacion observacion = new Observacion(notificante, descripcion);
        agregarObservacion(observacion);
        for(Miembro miembro: notificante.getComunidad().getMiembros()){
            notificador.notificarAMiembro(miembro, new CierreIncidente(), this);
        }
    }

    public LocalDateTime getFechaDeCierre(){
        return this.estado == Estado.CERRADO ? observaciones.get(observaciones.size() - 1).getFecha() : null;
    }

    public Localizacion getLocalizacion(){
        return establecimientoAfectado.getLocalizacion();
    }


}
