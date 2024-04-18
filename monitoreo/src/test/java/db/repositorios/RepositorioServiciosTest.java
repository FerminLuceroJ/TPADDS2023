package db.repositorios;

import models.entidades.servicios.servicio.Servicio;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import models.repositorios.RepositorioServicios;

import java.util.List;

public class RepositorioServiciosTest {

    private Servicio unServicio;
    private RepositorioServicios repositorioServicios;

    @BeforeEach
    public void init(){
        this.unServicio = new Servicio("NombreDelServicio");

        repositorioServicios = new RepositorioServicios();
    }

    @Test
    public void elServicioSePersiste(){
        repositorioServicios.persistir(unServicio);
        Servicio otroServicio = repositorioServicios.servicioSegunId(1);
        Assertions.assertEquals("NombreDelServicio", otroServicio.getNombre());
    }

    @Test
    public void seTraeElServicioPorId(){
        Servicio otroServicio = repositorioServicios.servicioSegunId(1);
        Assertions.assertEquals("NombreDelServicio", otroServicio.getNombre());
    }

    @Test
    public void seTraeTodosLosServicios(){
        List<Servicio> servicios = repositorioServicios.servicios();
        Assertions.assertEquals(1,servicios.size());
    }

    @Test
    public void elServicioSeModifica(){
        Servicio servicioAModificar = repositorioServicios.servicioSegunId(1);
        servicioAModificar.setNombre("otroNombre");
        repositorioServicios.modificar(servicioAModificar);
        Servicio servicioModificado = repositorioServicios.servicioSegunId(1);
        Assertions.assertEquals("otroNombre", servicioModificado.getNombre());

    }

    @Test
    public void elServicioSeElimina(){
        Servicio prestacionDeServicioAEliminar = repositorioServicios.servicioSegunId(1);
        repositorioServicios.eliminar(prestacionDeServicioAEliminar);
        Assertions.assertEquals(0,repositorioServicios.servicios().size());
    }
}
