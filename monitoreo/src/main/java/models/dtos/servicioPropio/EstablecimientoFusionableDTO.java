package models.dtos.servicioPropio;

import lombok.Getter;
import models.entidades.organizaciones.Establecimiento;

public class EstablecimientoFusionableDTO {
    @Getter
    private int id;
    @Getter
    private String nombre;

    public EstablecimientoFusionableDTO(int id, String nombre) {
        this.id = id;
        this.nombre = nombre;
    }

    public EstablecimientoFusionableDTO(){
    }
}
