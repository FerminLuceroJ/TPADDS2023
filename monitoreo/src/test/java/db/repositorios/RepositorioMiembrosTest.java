package db.repositorios;

import models.entidades.persona.EstrategiaDeNotificacion;
import models.entidades.persona.MedioDeNotificacion;
import models.entidades.persona.Miembro;
import models.entidades.persona.TipoMiembro;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import models.repositorios.RepositorioMiembros;

import java.util.List;

public class RepositorioMiembrosTest {

    private Miembro unMiembro;
    private RepositorioMiembros repositorioMiembros;

    @BeforeEach
    public void init(){
        this.unMiembro = new Miembro();
        unMiembro.setApellido("Rodriguez");
        unMiembro.setNombre("Fabian");
        unMiembro.setEstrategiaDeNotificacion(EstrategiaDeNotificacion.CUANDOSUCEDEN);
        unMiembro.setMedioDeNotificacion(MedioDeNotificacion.MAIL);
        unMiembro.setTipo(TipoMiembro.AFECTADO);

        this.repositorioMiembros = new RepositorioMiembros();
    }

    @Test
    public void elMiembroSePersiste(){
        repositorioMiembros.persistir(unMiembro);
        Miembro unMiembro = repositorioMiembros.miembroSegunId(1);
        Assertions.assertEquals("Fabian", unMiembro.getNombre());
    }

    @Test
    public void traeElMiembroSegunID(){
        Miembro miembro = repositorioMiembros.miembroSegunId(1);
        System.out.println(miembro.getNombre());
    }

    @Test
    public void traeTodosLosMiembros(){
        List<Miembro> miembros = repositorioMiembros.miembros();
        System.out.println(miembros.get(0).getNombre());
    }

    @Test
    public void elMiembroSeModifica(){
        Miembro miembroAModificar = repositorioMiembros.miembroSegunId(1);
        miembroAModificar.setNombre("juancito");
        repositorioMiembros.modificar(miembroAModificar);
        Miembro miembroModificado = repositorioMiembros.miembroSegunId(1);
        Assertions.assertEquals("juancito", miembroModificado.getNombre());
    }

    @Test
    public void elMiembroSeElimina(){
        Miembro miembroAEliminar = repositorioMiembros.miembroSegunId(1);
        repositorioMiembros.eliminar(miembroAEliminar);
        Assertions.assertEquals(0, repositorioMiembros.miembros().size());
    }
}
