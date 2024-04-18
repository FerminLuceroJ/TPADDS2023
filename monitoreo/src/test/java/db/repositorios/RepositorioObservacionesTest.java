package db.repositorios;

import models.entidades.incidente.Observacion;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import models.repositorios.RepositorioObservaciones;

import java.util.List;

public class RepositorioObservacionesTest {

    private Observacion unaObservacion;

    private RepositorioObservaciones repositorioObservaciones;

    @BeforeEach
    void init(){
        this.unaObservacion = new Observacion();
        unaObservacion.setDescripcion("unaDescripcion");

        this.repositorioObservaciones = new RepositorioObservaciones();
    }

    @Test
    public void laObservacionSePersiste(){
        repositorioObservaciones.persistir(unaObservacion);
        Observacion otraObs = repositorioObservaciones.observacionSegunId(1);
        Assertions.assertEquals("unaDescripcion",otraObs.getDescripcion());
    }

    @Test
    public void traeLaObservacionSegunID(){
        Observacion notificacion = repositorioObservaciones.observacionSegunId(1);
        Assertions.assertEquals(1, notificacion.getId());
    }

    @Test
    public void traeTodasLasObservaciones(){
        List<Observacion> observaciones = repositorioObservaciones.observaciones();
        Assertions.assertEquals(1, observaciones.size());
    }

    @Test
    public void laObservacionSeModifica(){
        Observacion observacionAModificar = repositorioObservaciones.observacionSegunId(1);
        observacionAModificar.setDescripcion("OtraDescripcion");
        repositorioObservaciones.modificar(observacionAModificar);
        Observacion observacionMdificada = repositorioObservaciones.observacionSegunId(1);
        Assertions.assertEquals("OtraDescripcion",observacionMdificada.getDescripcion());
    }

    @Test
    public void laObservacionSeElimina(){
        Observacion observacionAEliminar = repositorioObservaciones.observacionSegunId(1);
        repositorioObservaciones.eliminarObservacion(observacionAEliminar);
        List<Observacion> observaciones = repositorioObservaciones.observaciones();
        Assertions.assertEquals(0, observaciones.size());
    }

}