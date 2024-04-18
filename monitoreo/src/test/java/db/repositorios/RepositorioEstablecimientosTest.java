package db.repositorios;

import models.entidades.organizaciones.Establecimiento;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import models.repositorios.RepositorioEstablecimientos;
import java.util.List;

public class RepositorioEstablecimientosTest {

    private Establecimiento unEstablecimiento;

    private RepositorioEstablecimientos repositorioEstablecimientos;

    @BeforeEach
    void init(){
        this.unEstablecimiento = new Establecimiento();
        unEstablecimiento.setNombre("unNombre");

        this.repositorioEstablecimientos = new RepositorioEstablecimientos();
    }

    @Test
    public void elEstablecimientoSePersiste(){
        repositorioEstablecimientos.persistir(unEstablecimiento);
        Establecimiento otroEstablecimiento = repositorioEstablecimientos.establecimientoSegunId(1);
        Assertions.assertEquals("unNombre", otroEstablecimiento.getNombre());
    }

    @Test
    public void traeElEstablecimientoSegunID(){
        Establecimiento establecimiento = repositorioEstablecimientos.establecimientoSegunId(1);
        Assertions.assertEquals(1, establecimiento.getId());
    }

    @Test
    public void traeTodosLosEstablecimientos(){
        List<Establecimiento> establecimientos = repositorioEstablecimientos.establecimientos();
        Assertions.assertEquals(1, establecimientos.size());
    }

    @Test
    public void unEstablecimientoSeModifica(){
        Establecimiento establecimientoAModificar = repositorioEstablecimientos.establecimientoSegunId(1);
        establecimientoAModificar.setNombre("otroNombre");
        repositorioEstablecimientos.modificar(establecimientoAModificar);
        Establecimiento establecimientoModificado = repositorioEstablecimientos.establecimientoSegunId(1);
        Assertions.assertEquals(establecimientoModificado.getNombre(),"otroNombre");
    }

    @Test
    public void unEstablecimientoSeElimina(){
        Establecimiento establecimientoAEliminar = repositorioEstablecimientos.establecimientoSegunId(1);
        repositorioEstablecimientos.eliminar(establecimientoAEliminar);
        List<Establecimiento> establecimientos = repositorioEstablecimientos.establecimientos();
        Assertions.assertEquals(0, establecimientos.size());
    }
}
