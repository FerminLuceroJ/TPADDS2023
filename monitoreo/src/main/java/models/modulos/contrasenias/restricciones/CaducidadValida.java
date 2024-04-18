package models.modulos.contrasenias.restricciones;

import java.time.LocalDate;
import java.time.temporal.ChronoUnit;

import models.entidades.persona.autentificacion.Usuario;

public class CaducidadValida implements Restriccion {

  private static final Integer diasMaximos = 90;

  @Override
  public boolean esValidaContrasenia(Usuario usuario) {
    return ChronoUnit.DAYS.between(usuario.getFechaContrasenia(), this.fechaActual()) < diasMaximos;
  }

  @Override
  public String getMensajeError() {
    return "La contraseña no puede tener más de " + diasMaximos + " días desde su creacion.";
  }

  private LocalDate fechaActual() {
    return LocalDate.now();
  }
}
