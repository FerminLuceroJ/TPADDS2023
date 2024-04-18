 package models.modulos.Notificador.MailSender;

import lombok.Setter;
import models.modulos.Notificador.MailSender.AdapterMailSender.AdapterMailSender;

 public class MailSender {
  @Setter
  private AdapterMailSender adapterMailSender;
  //@Getter
  //private List<String> notificaciones;

   /*public void agregarNotificacion(String... notificaciones){
     Collections.addAll(this.notificaciones, notificaciones);
   }*/

  public MailSender(AdapterMailSender adapter){
    this.adapterMailSender = adapter;
    //this.notificaciones = new ArrayList<>();
  }

  public void notificar(String correoDestino, String mensaje, String asunto){
      this.enviarNotificacion(correoDestino, mensaje, asunto);


      /*switch(estrategia)
      case CUANDOSUCEDEN ->
        this.enviarNotificacion(correoDestino, mensaje, asunto);
      case SINAPUROS ->
      // Esto esta agregando dos strings, el mensaje y el asunto, como si fueran dos notificaciones separadas.
      // Además, esta lógica no es propia del MailSender. Es algo del "Notificador" del sistema.
        this.agregarNotificacion(mensaje, asunto);
      */
      // La lógica del cron no debe estar acá. No es responsabilidad de un mail sender ejecutar un proceso mediante cron
      /*
      *
      * TODO
      * Agregar logica para el cron job/task
      *
      * */
     //this.adapterMailSender.notificar(correoDestino, mensaje);
  }

  private void enviarNotificacion(String correoDestino, String mensaje, String asunto){
     this.adapterMailSender.notificar(correoDestino, mensaje, asunto);
  }
}
