package db.repositorios;

import models.entidades.organizaciones.entidad.Entidad;
import models.entidades.persona.Interes;
import models.entidades.persona.Miembro;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import models.repositorios.RepositorioIntereses;

import java.util.List;

public class RepositorioInteresesTest {
    private Interes interes;
    private Interes unInteres;
    private Interes otroInteres;

    private RepositorioIntereses repositorio;

    @BeforeEach
    void init(){
        Miembro miembro = new Miembro();
        Miembro otroMiembro = new Miembro();

        Entidad entidad = new Entidad();
        entidad.setNombre("hola");
        Entidad otraEntidad = new Entidad();
        otraEntidad.setNombre("chau");


        this.interes = new Interes();
        this.interes.setMiembro(miembro);
        this.interes.setEntidad(entidad);

        this.otroInteres = new Interes();
        this.interes.setMiembro(otroMiembro);
        this.interes.setEntidad(otraEntidad);

        this.unInteres = new Interes();
        this.unInteres.setMiembro(otroMiembro);


        this.repositorio = new RepositorioIntereses();
    }

    @Test
    public void elInteresSePersiste(){
        repositorio.persistir(interes);
        repositorio.persistir(otroInteres);
        repositorio.persistir(unInteres);
        Assertions.assertEquals(1, repositorio.interesSegunId(1).getId());
    }

    @Test
    public void traeElInteresSegunId(){
        Interes interesTraido = repositorio.interesSegunId(2);
        Assertions.assertEquals(2, interesTraido.getId());
    }

    @Test
    public void traeTodosLosIntereses(){
        List<Interes> intereses = repositorio.intereses();
        Assertions.assertEquals(2, intereses.size());
    }

    @Test
    public void seModificaElInteres(){
        Interes interesAModificar = repositorio.interesSegunId(1);
        interesAModificar.getEntidad().setNombre("holaModificado");
        repositorio.modificar(interesAModificar);
        Interes interesModificado = repositorio.interesSegunId(1);
        Assertions.assertEquals("holaModificado", interesModificado.getEntidad().getNombre());
    }

    @Test
    public void traeLosInteresesSegunIdMiembro(){
        List<Interes> interesesTraidos = repositorio.interesesSegunIdMiembro(2);
        Assertions.assertEquals(2, interesesTraidos.size());
    }

    @Test
    public void seEliminaElInteres(){
        int cantidadDeInteresesAntesDeEliminar = repositorio.intereses().size();
        repositorio.eliminar(repositorio.interesSegunId(1));
        int cantidadDeInteresesDespuesDeEliminar = repositorio.intereses().size();
        Assertions.assertEquals(3, cantidadDeInteresesAntesDeEliminar);
        Assertions.assertEquals(2, cantidadDeInteresesDespuesDeEliminar);
    }

}
