package db.repositorios;

import models.entidades.organizaciones.entidad.TipoEntidad;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import models.repositorios.RepositorioTipoEntidades;

import java.util.List;

public class RepositorioTipoEntidadesTest {

    private TipoEntidad unTipoEntidad;

    private RepositorioTipoEntidades repositorioTipoEntidades;

    @BeforeEach
    void init(){
        this.unTipoEntidad = new TipoEntidad();
        unTipoEntidad.setNombre("unNombre");
        unTipoEntidad.setPrefijoEntidad("Banco");
        unTipoEntidad.setPrefijoEstablecimiento("Ciudad");

        this.repositorioTipoEntidades = new RepositorioTipoEntidades();
    }

    @Test
    public void elTipoEntidadSePersiste(){
        repositorioTipoEntidades.persistir(unTipoEntidad);
        TipoEntidad otroTipo = repositorioTipoEntidades.tipoSegunId(1);
        Assertions.assertEquals("unNombre", otroTipo.getNombre());
    }

    @Test
    public void traeElTipoEntidadSegunID(){
        TipoEntidad unTipoEntidad = repositorioTipoEntidades.tipoSegunId(1);
        Assertions.assertEquals(1, unTipoEntidad.getId());
    }
    @Test
    public void traeTodosLosTiposEntidad(){
        List<TipoEntidad> tiposEntidades = repositorioTipoEntidades.tipoDeEntidades();
        Assertions.assertEquals(1, tiposEntidades.size());
    }

    @Test
    public void unTipoEntidadSeModifica(){
        TipoEntidad tipoEntidadAModificar = repositorioTipoEntidades.tipoSegunId(1);
        tipoEntidadAModificar.setNombre("otroNombre");
        repositorioTipoEntidades.modificar(tipoEntidadAModificar);
        TipoEntidad unTipoEntidadModificado = repositorioTipoEntidades.tipoSegunId(1);
        Assertions.assertEquals(unTipoEntidadModificado.getNombre(),"otroNombre");
    }

    @Test
    public void unTipoEntidadSeElimina(){
        TipoEntidad tipoEntidadAEliminar = repositorioTipoEntidades.tipoSegunId(1);
        repositorioTipoEntidades.eliminar(tipoEntidadAEliminar);
        Assertions.assertEquals(0, repositorioTipoEntidades.tipoDeEntidades().size());
    }

}
