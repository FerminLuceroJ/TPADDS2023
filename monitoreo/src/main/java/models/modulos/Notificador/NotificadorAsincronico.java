package models.modulos.Notificador;


import lombok.Getter;
import lombok.Setter;
import models.entidades.notificacion.Notificacion;

import java.util.List;

public class NotificadorAsincronico {
    @Getter
    @Setter
    Notificador notificador;
    public NotificadorAsincronico(Notificador notificador) {
        this.notificador = notificador;
    }

    private void enviarNotificacionesAsincronicas() {
        List<Notificacion> notificaciones = notificador.getRepositorioNotificaciones().notificacionesSinEnviar();

        for (Notificacion notificacion : notificaciones) {
            notificador.notificarMiembroSegunMedio(
                    notificacion.getDestinatario(),
                    notificacion.getDestinatario().getMedioDeNotificacion(),
                    notificacion.getMensaje(),
                    notificacion.getAsunto());

            notificacion.setEnviado(true);

        }
    }
}