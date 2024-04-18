package models.entidades.rankings.ValueObjects;

import models.entidades.organizaciones.entidad.Entidad;
import lombok.Getter;
import lombok.Setter;

public class IncidentePorEntidad {

    @Setter@Getter
    private Entidad entidad;
    @Setter @Getter
    private Integer cantidadDeIncidentes;

    public IncidentePorEntidad(Entidad entidad, Integer cantidadDeIncidentes){
        this.entidad = entidad;
        this.cantidadDeIncidentes = cantidadDeIncidentes;
    }
}
