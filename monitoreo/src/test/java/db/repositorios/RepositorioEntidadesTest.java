package db.repositorios;

import org.junit.jupiter.api.Assertions;
import models.repositorios.RepositorioEntidades;
import models.entidades.organizaciones.entidad.Entidad;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.List;

public class RepositorioEntidadesTest {

  private Entidad unaEntidad;

  private RepositorioEntidades repositorioEntidades;

  @BeforeEach
  void init(){
    this.unaEntidad = new Entidad();
    unaEntidad.setNombre("UnNombre");


    this.repositorioEntidades = new RepositorioEntidades();
  }

  @Test
  public void laEntidadSePersiste(){
    repositorioEntidades.persistir(unaEntidad);
    Entidad otraEntidad = repositorioEntidades.entidadSegunId(1);
    Assertions.assertEquals("UnNombre",otraEntidad.getNombre());
  }

  @Test
  public void traeLaEntidadSegunID(){
    Entidad unaEntidad = repositorioEntidades.entidadSegunId(1);
    Assertions.assertEquals(unaEntidad.getId(),1);
  }

  @Test
  public void traeTodasLasEntidades(){
    List<Entidad> entidades = repositorioEntidades.entidades();
    Assertions.assertEquals(entidades.size(),1);
  }

  @Test
  public void unaEntidadSeModifica(){
    Entidad entidadAmodificar = repositorioEntidades.entidadSegunId(1);
    entidadAmodificar.setNombre("otroNombre");
    repositorioEntidades.modificar(entidadAmodificar);
    Entidad entidadModificada = repositorioEntidades.entidadSegunId(1);
    Assertions.assertEquals(entidadModificada.getNombre(),"otroNombre");
  }

  @Test
  public void unaEntidadSeElimina(){
    Entidad entidadAEliminar = repositorioEntidades.entidadSegunId(1);
    repositorioEntidades.eliminar(entidadAEliminar);
    List<Entidad> entidades = repositorioEntidades.entidades();
    Assertions.assertEquals(entidades.size(),0);
  }
}
