package models.dtos.servicioPropio;

import lombok.Getter;

public class ServicioFusionableDTO {
    @Getter
    private int id;

    @Getter
    private String nombre;

    public ServicioFusionableDTO(int id, String nombre) {
        this.id = id;
        this.nombre = nombre;
    }

    public ServicioFusionableDTO(){
    }
}
