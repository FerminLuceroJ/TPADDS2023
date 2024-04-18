package models.modulos.contrasenias;

import models.entidades.persona.autentificacion.Usuario;
import models.modulos.contrasenias.restricciones.CaducidadValida;
import models.modulos.contrasenias.restricciones.ListaDeContraseniasDebiles;
import models.modulos.contrasenias.restricciones.Restriccion;
import models.modulos.contrasenias.restricciones.longitud.LongitudMaxima;
import models.modulos.contrasenias.restricciones.longitud.LongitudMinima;
import models.modulos.contrasenias.excepciones.ContraseniaInvalidaException;
import models.modulos.contrasenias.restricciones.expresionregular.ContieneMayusculaExpresionRegular;
import models.modulos.contrasenias.restricciones.expresionregular.ContieneMinusculaExpresionRegular;
import models.modulos.contrasenias.restricciones.expresionregular.ContieneNumeroExpresionRegular;
import models.modulos.contrasenias.restricciones.expresionregular.ContieneSignoDePuntuacionExpresionRegular;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.Collections;


public class ValidadorDeContrasenias {

  private final Set<Restriccion> restricciones;

  // En este momento, cuando se instancia, ya se le cargan automaticamente las restricciones que tiene nuestro sistema
  // Podria modificarse el constructor y cargarlas en el momento de instanciacion
  public ValidadorDeContrasenias() {
    this.restricciones = new HashSet<>();
    this.cargarRestriccionesActuales();
  }
  
  public boolean esValidaContrasenia(Usuario usuario) {
    List<String> errores = new ArrayList<>();
    for (Restriccion restriccion : this.restricciones) {
      if (!restriccion.esValidaContrasenia(usuario)) {
        errores.add(restriccion.getMensajeError());
      }
    }
    if (!errores.isEmpty()) {
      throw new ContraseniaInvalidaException(String.join(", ", errores));
    } else {
      return true;
    }
  }

  // Quizá estaria bueno tener una clase constructor para que esta logica no esté acá
  private void cargarRestriccionesActuales() {
    Restriccion caducidadValidad = new CaducidadValida();
    Restriccion listaContraseniasDebiles = new ListaDeContraseniasDebiles();
    Restriccion longitudMaxima = new LongitudMaxima();
    Restriccion longitudMinima = new LongitudMinima();
    Restriccion contieneMayusculas = new ContieneMayusculaExpresionRegular();
    Restriccion contieneMinuscula = new ContieneMinusculaExpresionRegular();
    Restriccion contieneNumero = new ContieneNumeroExpresionRegular();
    Restriccion contieneSignoPuntuacion = new ContieneSignoDePuntuacionExpresionRegular();
    Collections.addAll(this.restricciones,
        caducidadValidad,
        listaContraseniasDebiles,
        longitudMaxima,
        longitudMinima,
        contieneMayusculas,
        contieneMinuscula,
        contieneNumero,
        contieneSignoPuntuacion
    );
  }
}
