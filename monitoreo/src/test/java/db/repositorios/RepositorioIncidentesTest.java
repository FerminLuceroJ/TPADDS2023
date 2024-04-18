package db.repositorios;

import models.entidades.incidente.Estado;
import models.entidades.incidente.Incidente;
import models.entidades.organizaciones.entidad.Entidad;
import org.junit.jupiter.api.Assertions;
import models.repositorios.RepositorioIncidentes;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.List;

public class RepositorioIncidentesTest {

    private Incidente unIncidente;
    private RepositorioIncidentes repositorioIncidentes;


    @BeforeEach
    void init() {
        this.unIncidente = new Incidente();
        unIncidente.setEstado(Estado.ABIERTO);
        unIncidente.setDescripcion("No hay papel en el baño");

        this.repositorioIncidentes = new RepositorioIncidentes();
    }

    @Test
    public void elIncidenteSePersiste() {
        repositorioIncidentes.persistir(unIncidente);
        Incidente unIncidente = repositorioIncidentes.incidenteSegunId(1);
        Assertions.assertEquals(unIncidente.getDescripcion(),"No hay papel en el baño");
    }

    @Test
    public void traeElIncidenteSegunId() {
        Incidente unIncidente = repositorioIncidentes.incidenteSegunId(1);
        Assertions.assertEquals(unIncidente.getId(), 1);
    }

    @Test
    public void unIncidenteSeModifica() {
       Incidente incidenteAModificar = repositorioIncidentes.incidenteSegunId(1);
        incidenteAModificar.setDescripcion("El piso del baño esta mojado");
        incidenteAModificar.setEstado(Estado.CERRADO);
        repositorioIncidentes.modificar(incidenteAModificar);
        Incidente incidenteModificado = repositorioIncidentes.incidenteSegunId(1);
        Assertions.assertEquals(incidenteModificado.getDescripcion(), "El piso del baño esta mojado");
        Assertions.assertEquals(incidenteModificado.getEstado(), Estado.CERRADO);
    }

    @Test
    public void traerTodosLosIncidentes() {
        List<Incidente> incidentes = repositorioIncidentes.incidentes();
        Assertions.assertEquals(1, incidentes.size());
    }

    @Test
    public void unIncidenteSeElimina() {
        Incidente incidenteAEliminar = repositorioIncidentes.incidenteSegunId(1);
        repositorioIncidentes.eliminar(incidenteAEliminar);
        Assertions.assertEquals(repositorioIncidentes.incidentes().size(), 0);
    }

    @Test
    public void meTraeLosIncidentesPorEstado(){
        Incidente incidente1 = new Incidente();
        incidente1.setEstado(Estado.CERRADO);
        Incidente incidente2 = new Incidente();
        Incidente incidente3 = new Incidente();

        repositorioIncidentes.persistir(incidente1);
        repositorioIncidentes.persistir(incidente2);
        repositorioIncidentes.persistir(incidente3);

        List<Incidente> incidentesPorEstado = repositorioIncidentes.incidentesSegunEstado(Estado.ABIERTO);
        Assertions.assertEquals(2, incidentesPorEstado.size());

        repositorioIncidentes.eliminar(incidente1);
        repositorioIncidentes.eliminar(incidente2);
        repositorioIncidentes.eliminar(incidente3);
    }
}
