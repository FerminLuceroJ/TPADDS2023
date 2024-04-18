package models.dtos.servicioPropio;

import lombok.Getter;

import java.util.ArrayList;
import java.util.List;

public class ComunidadFusionableDTO {
    @Getter
    private int id;
    @Getter
    private String nombre;
    @Getter
    private List<PrestacionDeServicioFusionableDTO> serviciosParticularesObservados;
    @Getter
    private Double gradoDeConfianza;

    public ComunidadFusionableDTO(int id, String nombre, List<PrestacionDeServicioFusionableDTO> serviciosParticularesObservados, Double gradoDeConfianza) {
        this.id = id;
        this.nombre = nombre;
        this.serviciosParticularesObservados = serviciosParticularesObservados;
        this.gradoDeConfianza = gradoDeConfianza;
    }

    public ComunidadFusionableDTO(){
        this.serviciosParticularesObservados = new ArrayList<>();
    }

}
