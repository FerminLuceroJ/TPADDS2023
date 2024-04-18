package models.entidades.organizaciones.entidad;

import models.entidades.organizaciones.Establecimiento;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "entidad")
public class Entidad {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Getter @Setter
    private int id;

    @Getter @Setter
    @Column(name = "nombre")
    private String nombre;

    @Getter
    @OneToMany(mappedBy = "entidad", cascade = {CascadeType.PERSIST, CascadeType.MERGE, CascadeType.REMOVE}, fetch = FetchType.LAZY)
    private List<Establecimiento> establecimientos;

    @Getter @Setter
    @ManyToOne(cascade = {CascadeType.PERSIST, CascadeType.MERGE}, fetch = FetchType.LAZY)
    @JoinColumn(name = "id_tipo_entidad", referencedColumnName = "id")
    private TipoEntidad tipo;

    public Entidad(String nombre, TipoEntidad tipo) {
        this.setNombre(nombre);
        this.establecimientos = new ArrayList<>();
        this.tipo = tipo;
    }

    public Entidad(){
        this.establecimientos = new ArrayList<>();
    }

    public void agregarEstablecimientos(Establecimiento establecimiento) {
        this.establecimientos.add(establecimiento);
        establecimiento.setEntidad(this);
    }

    public void removerEstablecimiento(Establecimiento establecimiento) {
        this.establecimientos.remove(establecimiento);
    }

    public String getNombreCompleto() {
        return this.tipo.getNombreCompletoEntidad(this);
    }
}
