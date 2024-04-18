package models.entidades.notificacion;

import models.entidades.persona.Miembro;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.time.LocalDateTime;

@Entity
@Table(name = "notificacion")
public class Notificacion {

    @Getter @Setter
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Getter @Setter
    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "id_miembro", referencedColumnName = "id")
    private Miembro destinatario;

    @Getter @Setter
    @Column(name = "fecha_creacion", columnDefinition = "TIMESTAMP")
    private LocalDateTime fechaDeCreacion;

    @Getter @Setter
    @Column(name = "enviado")
    private Boolean enviado;

    @Getter @Setter
    @Column(name = "mensaje", columnDefinition = "TEXT")
    private String mensaje;

    @Getter @Setter
    @Column(name = "asunto")
    private String asunto;

    public Notificacion(Miembro destinatario, String mensaje, String asunto) {
        this.destinatario = destinatario;
        this.fechaDeCreacion = LocalDateTime.now();
        this.enviado = false;
        this.mensaje = mensaje;
        this.asunto = asunto;
    }

    public Notificacion(){
        this.fechaDeCreacion = LocalDateTime.now();
    }
}