package models.modulos.fusionador;

import lombok.Getter;
import lombok.Setter;
import models.entidades.comunidad.Comunidad;

import javax.persistence.*;
import java.time.LocalDate;

@Entity(name="propuestas_de_fusion")
public class PropuestaDeFusion {
    @Id
    @Setter@Getter
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id_una_comunidad", referencedColumnName = "id")
    @Getter
    private Comunidad unaComunidad;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id_otra_comunidad", referencedColumnName = "id")
    @Getter
    private Comunidad otraComunidad;

    @Column(name="fecha_de_propuesta",columnDefinition = "date")
    @Getter
    private LocalDate fechaDePropuesta;

    @Column(name="fue_aceptada")
    @Getter @Setter
    private Boolean fueAceptada;

    public PropuestaDeFusion(Comunidad unaComunidad, Comunidad otraComunidad, LocalDate fechaDePropuesta, Boolean fueAceptada) {
        this.unaComunidad = unaComunidad;
        this.otraComunidad = otraComunidad;
        this.fechaDePropuesta = fechaDePropuesta;
        this.fueAceptada = fueAceptada;
    }

    public PropuestaDeFusion(){
    }

}
