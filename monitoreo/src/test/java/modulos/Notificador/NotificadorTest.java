package modulos.Notificador;

import models.entidades.incidente.Incidente;
import models.entidades.organizaciones.Establecimiento;
import models.entidades.persona.EstrategiaDeNotificacion;
import models.entidades.persona.MedioDeNotificacion;
import models.entidades.persona.Miembro;
import models.entidades.persona.autentificacion.Usuario;
import models.entidades.servicios.servicio.PrestacionDeServicio;
import models.entidades.servicios.servicio.Servicio;
import models.entidades.ubicacion.Localizacion;
import models.entidades.ubicacion.Provincia;
import models.modulos.Notificador.Notificaciones.Notificable;
import models.modulos.Notificador.Notificaciones.Notificacion.CierreIncidente;
import models.modulos.Notificador.Notificaciones.Notificacion.NuevoIncidente;
import models.modulos.Notificador.Notificaciones.Notificacion.RevisionIncidente;
import models.modulos.Notificador.Notificador;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;


public class NotificadorTest {
  private Miembro miembro;
  private Notificador notificador;

  private Incidente incidente;
  private Establecimiento establecimiento;
  private Servicio servicio;
  private PrestacionDeServicio prestacionDeServicio;
  private Notificable creacionIncidente;
  private Notificable cierreIncidente;
  private Notificable revisionIncidente;


  /*
   *
   *   IMPORTANTE DESACTIVAR EL ANTIVIRUS ANTES DE PROBAR.
   *   Si no lo desactivo arroja una excepcion que creo que tiene que ver con la red y el antivirus bloqueando actividad sospechosa etc
   *
   * */
  @BeforeEach
  public void init(){
    Localizacion localizacion = new Provincia();
    localizacion.nombre = "Buenos Aires";
    this.miembro = new Miembro("facundo", "piaggio", localizacion);
    miembro.setMedioDeNotificacion(MedioDeNotificacion.MAIL);
    miembro.setEstrategiaDeNotificacion(EstrategiaDeNotificacion.CUANDOSUCEDEN);
    Usuario usuario = new Usuario("contraseniaSegura123", "Facu");
    usuario.setCorreoElectronico("fpiaggio@frba.utn.edu.ar");
    miembro.setUsuario(usuario);

    establecimiento = new Establecimiento("Estacion Carabobo", localizacion, null);
    servicio = new Servicio("Baños");
    prestacionDeServicio = new PrestacionDeServicio(servicio, establecimiento);

    creacionIncidente = new NuevoIncidente();
    cierreIncidente = new CierreIncidente();
    revisionIncidente = new RevisionIncidente();

    incidente = new Incidente(null, prestacionDeServicio, establecimiento, "Los baños estan clausurados");

    notificador = new Notificador();
  }

  @Test
  public void seNotificaAlMiembroConMensajeYAsunto(){
    notificador.notificarAMiembro(miembro, "Testeando ando", "Prueba");
  }

  @Test
  public void seNotificaCreacionDeIncidente(){
    notificador.notificarAMiembro(miembro, creacionIncidente, incidente);
  }

  @Test
  public void seNotificaCierreDeIncidente(){
    notificador.notificarAMiembro(miembro, cierreIncidente, incidente);
  }
  @Test
  public void seNotificaRevisionDeIncidente(){
    notificador.notificarAMiembro(miembro, revisionIncidente, incidente);
  }

}
