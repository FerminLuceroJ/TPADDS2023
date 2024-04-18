package models.modulos.contrasenias.restricciones.expresionregular;

public class ContieneMayusculaExpresionRegular extends ExpresionRegular {
  private static final String expresionRegular = ".*[A-Z].*";

  @Override
  protected String obtenerExpresionRegular() {
    return expresionRegular;
  }

  @Override
  public String getMensajeError() {
    return super.getMensajeError() + " No contiene mayuscula.";
  }
}
