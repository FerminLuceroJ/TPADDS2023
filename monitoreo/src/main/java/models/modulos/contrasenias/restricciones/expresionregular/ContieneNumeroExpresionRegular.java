package models.modulos.contrasenias.restricciones.expresionregular;

public class ContieneNumeroExpresionRegular extends ExpresionRegular {
  private static final String expresionRegular = ".*\\d.*";

  @Override
  protected String obtenerExpresionRegular() {
    return expresionRegular;
  }

  @Override
  public String getMensajeError() {
    return super.getMensajeError() + " No contiene numero.";
  }
}
