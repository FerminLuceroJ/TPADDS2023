package models.entidades.rankings.ValueObjects;

import models.entidades.incidente.Incidente;
import models.entidades.servicios.servicio.PrestacionDeServicio;
import lombok.Getter;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;

public class IncidentesPorPrestacionDeServicio {

    @Getter
    private PrestacionDeServicio prestacionDeServicio;
    @Setter @Getter
    private List<Incidente> incidentes;

    public IncidentesPorPrestacionDeServicio(PrestacionDeServicio prestacion){
        this.prestacionDeServicio = prestacion;
        this.incidentes = new ArrayList<>();
    }
}
