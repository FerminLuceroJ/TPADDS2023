package models.modulos.Notificador.WhatsappSender.AdapterWhatsappSender;

public interface AdapterWhatsappSender {
  public void notificar(String numero, String mensaje, String asunto);
}
