package models.entidades.organizaciones;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import models.entidades.organizaciones.entidad.Entidad;
import models.entidades.servicios.servicio.PrestacionDeServicio;
import models.entidades.ubicacion.Localizacion;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "establecimiento")
public class Establecimiento {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Getter @Setter
    private int id;

    @Getter @Setter
    @Column(name = "nombre")
    private String nombre;

    @Setter @Getter
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id_localizacion", referencedColumnName = "id")
    private Localizacion localizacion;

    @Getter
    @JsonBackReference
    @OneToMany(mappedBy = "establecimiento")
    private List<PrestacionDeServicio> serviciosPrestados;

    @Getter @Setter
    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "id_entidad", referencedColumnName = "id")
    private Entidad entidad;

    public Establecimiento(String nombre, Localizacion localizacion, Entidad entidad) {
        this.setNombre(nombre);
        this.setLocalizacion(localizacion);
        this.serviciosPrestados = new ArrayList<>();
        this.entidad = entidad;
    }

    public Establecimiento(){
        this.serviciosPrestados = new ArrayList<>();
    }

    public void agregarPrestacionDeServicio(PrestacionDeServicio servicioPrestado) {
        this.serviciosPrestados.add(servicioPrestado);
        servicioPrestado.setEstablecimiento(this);
    }

    public void removerPrestacionDeServicio(PrestacionDeServicio servicioPrestado){
        this.serviciosPrestados.remove(servicioPrestado);
    }

    public String getNombreEntidad(){
        return this.entidad.getNombre();
    }

    public String getNombreCompleto() {

        return this.entidad.getNombreCompleto();
    }


}
