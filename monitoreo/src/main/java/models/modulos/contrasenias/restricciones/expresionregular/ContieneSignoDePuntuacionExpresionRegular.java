package models.modulos.contrasenias.restricciones.expresionregular;

public class ContieneSignoDePuntuacionExpresionRegular extends ExpresionRegular {
  private static final String expresionRegular = ".*[\\p{Punct}].*";

  @Override
  protected String obtenerExpresionRegular() {
    return expresionRegular;
  }

  @Override
  public String getMensajeError() {
    return super.getMensajeError() + " No contiene signo de puntuacion.";
  }
}
