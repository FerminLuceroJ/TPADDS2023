package db.repositorios;

import models.entidades.servicios.servicio.PrestacionDeServicio;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import models.repositorios.RepositorioPrestacionesDeServicios;

import java.util.List;

public class RepositorioPrestacionesDeServiciosTest {

    private PrestacionDeServicio unaPrestacionDeServicio;

    private RepositorioPrestacionesDeServicios repositorioPrestacionesDeServicios;

    @BeforeEach
    void init(){
        this.unaPrestacionDeServicio = new PrestacionDeServicio();

        this.repositorioPrestacionesDeServicios = new RepositorioPrestacionesDeServicios();
    }

    @Test
    public void laPrestacionDeServicioSePersiste(){
        repositorioPrestacionesDeServicios.persistir(unaPrestacionDeServicio);
        PrestacionDeServicio otraPrestacion = repositorioPrestacionesDeServicios.prestacionDeServicioSegunID(1);
        Assertions.assertEquals(1,otraPrestacion.getId());
    }

    @Test
    public void traeLaPrestacionDeServicioSegunID(){
        PrestacionDeServicio unaEntidad = repositorioPrestacionesDeServicios.prestacionDeServicioSegunID(1);
        Assertions.assertEquals(unaEntidad.getId(),1);
    }

    @Test
    public void traeTodasLasPrestacionDeServicio(){
        List<PrestacionDeServicio> entidades = repositorioPrestacionesDeServicios.prestacionesDeServicio();
        Assertions.assertEquals(entidades.size(),1);
    }
    @Test
    public void laPrestacionDeServicioSeElimina(){
        PrestacionDeServicio prestacionDeServicioAEliminar = repositorioPrestacionesDeServicios.prestacionDeServicioSegunID(1);
        repositorioPrestacionesDeServicios.eliminar(prestacionDeServicioAEliminar);
        List<PrestacionDeServicio> prestaciones = repositorioPrestacionesDeServicios.prestacionesDeServicio();
        Assertions.assertEquals(prestaciones.size(),0);
    }

    @Test
    public void traeLaPrestacionPorEstablecimientoYServicio(){
        // Este test solo funciona si tenes levantado una prestacion,
        // que referencie al establecimiento de id 1 con nombre ="establecimiento"
        // y que referencie al servicio de id 1
        PrestacionDeServicio prestacionDeServicio = repositorioPrestacionesDeServicios.prestacionSegunEstablecimientoYServicio(1,1);
        Assertions.assertEquals("establecimiento", prestacionDeServicio.getEstablecimiento().getNombre());
    }

}
