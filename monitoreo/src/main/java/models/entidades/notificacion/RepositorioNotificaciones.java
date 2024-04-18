package models.entidades.notificacion;

import lombok.Getter;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;


public class RepositorioNotificaciones {
    @Getter
    private List<Notificacion> listaDeNotificaciones;

    public RepositorioNotificaciones() {
        this.listaDeNotificaciones = new ArrayList<>();
    }

    public void agregarNotificacion(Notificacion ... notificaciones) {
        Collections.addAll(this.listaDeNotificaciones, notificaciones);
    }

    public void removerNotificacion(Notificacion notificacion){
        this.listaDeNotificaciones.remove(notificacion);
    }

    public List<Notificacion> notificacionesEnviadas() {
        return this.listaDeNotificaciones.stream().filter(notificacion -> notificacion.getEnviado()).toList();
    }

    public List<Notificacion> notificacionesSinEnviar() {
        return this.listaDeNotificaciones.stream().filter(notificacion -> !notificacion.getEnviado()).toList();
    }
}