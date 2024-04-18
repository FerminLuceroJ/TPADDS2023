package models.modulos.contrasenias.restricciones.expresionregular;

public class ContieneMinusculaExpresionRegular extends ExpresionRegular {

  private static final String expresionRegular = ".*[a-z].*";

  @Override
  protected String obtenerExpresionRegular() {
    return expresionRegular;
  }

  @Override
  public String getMensajeError() {
    return super.getMensajeError() + " No contiene minuscula.";
  }
}
