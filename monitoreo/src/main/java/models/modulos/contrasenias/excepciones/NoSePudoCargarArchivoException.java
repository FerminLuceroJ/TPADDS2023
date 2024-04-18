package models.modulos.contrasenias.excepciones;

public class NoSePudoCargarArchivoException extends RuntimeException {
    public NoSePudoCargarArchivoException(String mensaje) {
        super(mensaje);
    }
}
