package models.dtos.servicioPropio;

import lombok.Getter;
import lombok.Setter;
import models.dtos.servicioPropio.ComunidadFusionableDTO;

import java.time.LocalDate;


public class PropuestaDeFusionDTO {

    @Getter
    private ComunidadFusionableDTO unaComunidad;

    @Getter
    private ComunidadFusionableDTO otraComunidad;

    @Getter
    private LocalDate fechaDePropuesta;

    @Getter @Setter
    private Boolean fueAceptada;

    public PropuestaDeFusionDTO(ComunidadFusionableDTO unaComunidad, ComunidadFusionableDTO otraComunidad, LocalDate fechaDePropuesta) {
        this.unaComunidad = unaComunidad;
        this.otraComunidad = otraComunidad;
        this.fechaDePropuesta = fechaDePropuesta;
    }

    public PropuestaDeFusionDTO(){
    }
}
