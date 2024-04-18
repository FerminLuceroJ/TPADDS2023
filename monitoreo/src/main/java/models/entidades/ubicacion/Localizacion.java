package models.entidades.ubicacion;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

@Entity
@Inheritance(strategy = InheritanceType.SINGLE_TABLE)
@DiscriminatorColumn(name = "discriminador")
public abstract class Localizacion {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Getter@Setter
    public Integer id;

    @Column(name = "nombre")
    @Getter@Setter
    public String nombre;

    @Column(name = "es_provincia")
    @Getter@Setter
    private Boolean esProvincia;

    public abstract Localizacion getProvincia();
}
