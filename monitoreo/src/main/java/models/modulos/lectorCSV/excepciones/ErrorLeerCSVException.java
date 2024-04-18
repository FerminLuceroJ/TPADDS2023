package models.modulos.lectorCSV.excepciones;

public class ErrorLeerCSVException extends RuntimeException {

    public ErrorLeerCSVException(String mensaje) {
        super(mensaje);
    }

}
