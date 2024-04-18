package models.modulos.Notificador.MailSender.AdapterMailSender;


public interface AdapterMailSender {
  public void notificar(String correo, String mensaje, String asunto);
}
