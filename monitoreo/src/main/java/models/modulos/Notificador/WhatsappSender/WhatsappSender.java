package models.modulos.Notificador.WhatsappSender;

import lombok.Getter;
import lombok.Setter;
import models.modulos.Notificador.WhatsappSender.AdapterWhatsappSender.AdapterWhatsappSender;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class WhatsappSender {
  @Setter
  private AdapterWhatsappSender adapterWhatsappSender;
  @Getter
  private List<String> notificaciones;

  public void agregarNotificacion(String... notificaciones){
    Collections.addAll(this.notificaciones, notificaciones);
  }

  public WhatsappSender(AdapterWhatsappSender adapter){
    this.adapterWhatsappSender = adapter;
    this.notificaciones = new ArrayList<>();
  }

  public void notificar(String numeroDestino, String mensaje, String asunto){
    //TODO
  }
}
