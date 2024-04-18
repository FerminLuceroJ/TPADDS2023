package models.dtos.servicioPropio;

import lombok.Getter;

public class PrestacionDeServicioFusionableDTO {
    @Getter
    private int id;

    @Getter
    private ServicioFusionableDTO servicio;

    @Getter
    private EstablecimientoFusionableDTO establecimiento;

    public PrestacionDeServicioFusionableDTO(int id, ServicioFusionableDTO servicio, EstablecimientoFusionableDTO establecimiento){
        this.id = id;
        this.servicio = servicio;
        this.establecimiento = establecimiento;
    }

    public PrestacionDeServicioFusionableDTO() {
    }
}
