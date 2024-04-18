package models.entidades.persona;

import models.entidades.organizaciones.entidad.Entidad;
import models.entidades.servicios.servicio.Servicio;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

@Entity
@Table(name="interes")
public class Interes {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Getter @Setter
    private int id;

    @Getter @Setter
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id_entidad", referencedColumnName = "id")
    private Entidad entidad;

    @Getter @Setter
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id_servicio", referencedColumnName = "id")
    private Servicio servicio;

    @Getter @Setter
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id_miembro",referencedColumnName = "id")
    private Miembro miembro;

    public Interes() {
    }
}
