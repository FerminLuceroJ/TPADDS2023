package models.modulos.Notificador;

import models.entidades.incidente.Incidente;
import models.entidades.persona.EstrategiaDeNotificacion;
import models.entidades.persona.MedioDeNotificacion;
import models.entidades.persona.Miembro;
import lombok.Getter;
import models.modulos.Notificador.Notificaciones.Notificable;
import models.modulos.Notificador.WhatsappSender.WhatsappSender;
import models.modulos.Notificador.MailSender.AdapterMailSender.AdapterJavaxMail;
import models.modulos.Notificador.MailSender.MailSender;
import models.modulos.Notificador.WhatsappSender.AdapterWhatsappSender.AdapterWhatsappTwillio;
import models.entidades.notificacion.Notificacion;
import models.entidades.notificacion.RepositorioNotificaciones;

public class Notificador {
  @Getter
  private RepositorioNotificaciones repositorioNotificaciones;
  public Notificador(){
    this.repositorioNotificaciones = new RepositorioNotificaciones();
  }

  public void notificarAMiembro(Miembro miembro, String mensaje, String asunto){
    MedioDeNotificacion medio = miembro.getMedioDeNotificacion();
    // Antes de llamar a notificarMiembroSegunMedio, se debe evaluar si la notificación es sincrónica o no
    // Si es sincrónica, avanzar y llamar al método. Si no es sincrónica, crear la notificación, agregarla al repositorio
    // de notificaciones, y hacer return.

    if(miembro.getEstrategiaDeNotificacion() == EstrategiaDeNotificacion.SINAPUROS){
      Notificacion notificacion = new Notificacion(miembro, mensaje, asunto);
      this.repositorioNotificaciones.agregarNotificacion(notificacion);
      return;
    }

    this.notificarMiembroSegunMedio(miembro, medio, mensaje, asunto);
  }

  public void notificarAMiembro(Miembro miembro, Notificable notificacion, Incidente incidente){
    this.notificarAMiembro(miembro, notificacion.getMentsajeDeNotificacion(incidente), notificacion.getAsuntoDeNotificacion());
  }

  public void notificarMiembroSegunMedio(Miembro miembro, MedioDeNotificacion medio, String mensaje, String asunto){
    switch(medio){
      case MAIL ->
        this.notificarPorMail(miembro, mensaje, asunto);
      case WHATSAPP ->
        this.notificarPorWhatsapp(miembro, mensaje, asunto);
    }
  }

  private void notificarPorMail(Miembro miembro, String mensaje, String asunto){
    //EstrategiaDeNotificacion estrategia = miembro.getEstrategiaDeNotificacion();
    MailSender mailSender = new MailSender(new AdapterJavaxMail());
    String correoDestino = miembro.getUsuario().getCorreoElectronico();
    mailSender.notificar(correoDestino, mensaje, asunto);
  }

  private void notificarPorWhatsapp(Miembro miembro, String mensaje, String asunto){
    //EstrategiaDeNotificacion estrategia = miembro.getEstrategiaDeNotificacion();
    WhatsappSender whatsappSender = new WhatsappSender(new AdapterWhatsappTwillio());
    String numeroDestino = miembro.getUsuario().getCorreoElectronico();
    whatsappSender.notificar(numeroDestino, mensaje, asunto);
  }

}
