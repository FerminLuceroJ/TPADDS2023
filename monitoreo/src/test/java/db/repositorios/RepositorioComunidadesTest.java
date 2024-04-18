package db.repositorios;

import models.entidades.comunidad.Comunidad;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import models.repositorios.RepositorioComunidades;


import java.util.List;

public class RepositorioComunidadesTest {

    private Comunidad unaComunidad;

    private RepositorioComunidades repositorioComunidades;

    @BeforeEach
    void init(){
        this.unaComunidad = new Comunidad();
        unaComunidad.setNombre("grupo10");
        unaComunidad.setGradoDeConfianza(10.0);
        this.repositorioComunidades = new RepositorioComunidades();
    }

    @Test
    public void laComunidadSePersiste(){
        repositorioComunidades.persistir(unaComunidad);
        Comunidad otraComunidad = repositorioComunidades.comunidadSegunId(1);
        Assertions.assertEquals("grupo10", otraComunidad.getNombre());
    }

    @Test
    public void traeLaNotificacionSegunID(){
        Comunidad comunidad = repositorioComunidades.comunidadSegunId(1);
        System.out.println(comunidad.getNombre());
        Assertions.assertEquals("grupo10", comunidad.getNombre());
    }

    @Test
    public void traeTodasLasComunidades(){
        List<Comunidad> comunidades = repositorioComunidades.comunidades();
        System.out.println(comunidades.get(0).getNombre());
        Assertions.assertEquals("grupo10", comunidades.get(0).getNombre());
    }

    @Test
    public void laComunidadSeModifica(){
        Comunidad comunidadAModificar = repositorioComunidades.comunidadSegunId(1);
        comunidadAModificar.setGradoDeConfianza(5.0);
        repositorioComunidades.modificar(comunidadAModificar);
        Comunidad comunidadModificada = repositorioComunidades.comunidadSegunId(1);
        Assertions.assertEquals(5.0, comunidadModificada.getGradoDeConfianza());
    }

    @Test
    public void laComunidadSeElimina(){
        Comunidad comunidadAEliminar = repositorioComunidades.comunidadSegunId(1);
        repositorioComunidades.eliminar(comunidadAEliminar);
        List<Comunidad> comunidades = repositorioComunidades.comunidades();
        Assertions.assertEquals(comunidades.size(),0);
    }
}
