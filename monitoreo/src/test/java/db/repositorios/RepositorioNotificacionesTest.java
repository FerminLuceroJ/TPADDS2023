package db.repositorios;

import models.entidades.notificacion.Notificacion;
import org.junit.jupiter.api.Assertions;
import models.repositorios.RepositorioNotificaciones;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.List;

public class RepositorioNotificacionesTest {

  private Notificacion unaNotificacion;

  private RepositorioNotificaciones repositorioNotificaciones;

  @BeforeEach
  void init(){
    this.unaNotificacion = new Notificacion();
    unaNotificacion.setAsunto("asunto");
    unaNotificacion.setMensaje("mensaje");
    unaNotificacion.setEnviado(false);

    this.repositorioNotificaciones = new RepositorioNotificaciones();
  }

  @Test
  public void laNotificacionSePersiste(){
    repositorioNotificaciones.persistir(unaNotificacion);
    Notificacion otraNotificacion = repositorioNotificaciones.notificacionSegunId(1);
    Assertions.assertEquals("mensaje", otraNotificacion.getMensaje());
  }

  @Test
  public void traeLaNotificacionSegunID(){
    Notificacion otraNotificacion = repositorioNotificaciones.notificacionSegunId(1);
    Assertions.assertEquals("mensaje", otraNotificacion.getMensaje());
  }

  @Test
  public void traeTodasLasNotificaciones(){
    List<Notificacion> notificaciones = repositorioNotificaciones.notificaciones();
    Assertions.assertEquals(1, notificaciones.size());
  }

  @Test
  public void laNotificacionSeModifica(){
    Notificacion notificacionAModificar = repositorioNotificaciones.notificacionSegunId(1);
    notificacionAModificar.setMensaje("OtroMensaje");
    repositorioNotificaciones.modificar(notificacionAModificar);
    Notificacion notificacionModificada = repositorioNotificaciones.notificacionSegunId(1);
    Assertions.assertEquals("OtroMensaje", notificacionModificada.getMensaje());
  }

  @Test
  public void laNotificacionSeElimina(){
    Notificacion noitifacionAEliminar = repositorioNotificaciones.notificacionSegunId(1);
    repositorioNotificaciones.eliminar(noitifacionAEliminar);
    Assertions.assertEquals(0, repositorioNotificaciones.notificaciones().size());
  }

}
