package modulos.contrasenias;

import models.entidades.persona.autentificacion.Usuario;
import models.modulos.contrasenias.ValidadorDeContrasenias;
import models.modulos.contrasenias.excepciones.ContraseniaInvalidaException;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;
import java.util.Arrays;

public class ValidadorDeContraseniasTest {

  @Test
  public void contraseniasValidasTest() {
    ValidadorDeContrasenias validador = new ValidadorDeContrasenias();

    Usuario usuarioContraseniaSeguraUno = new Usuario("un4contraS3gur4!", "rodri");
    Usuario usuarioContraseniaSeguraDos = new Usuario("LoSd1@bl0sRoJos!", "rodri");
    Usuario usuarioContraseniaSeguraTres = new Usuario("UtnFRB4Medrano?", "rodri");
    Usuario usuarioContraseniaSeguraCuatro = new Usuario("QuEG4444naSd3ReCiBIrme!!!!!", "rodri");

    Arrays.stream(new Usuario[]{
                    usuarioContraseniaSeguraUno,
                    usuarioContraseniaSeguraDos,
                    usuarioContraseniaSeguraTres,
                    usuarioContraseniaSeguraCuatro
        })
        .forEach(usuario -> {
            Assertions.assertTrue(validador.esValidaContrasenia(usuario));
        });
  }

  @Test
  public void contraseniaInvalidaSinMayusculaTest() {
    ValidadorDeContrasenias validador = new ValidadorDeContrasenias();
    Usuario usuarioConContrasenia = new Usuario("un4passs3gur4!", "rodri");
    String mensajeEsperado = "La contraseña debe cumplir la expresión regular: .*[A-Z].* . No contiene mayuscula.";

    ContraseniaInvalidaException e = Assertions.assertThrows(ContraseniaInvalidaException.class, () -> validador.esValidaContrasenia(usuarioConContrasenia));

    Assertions.assertEquals(mensajeEsperado, e.getMessage());
  }

  @Test
  public void contraseniaInvalidaSinMinusculaTest() {
    ValidadorDeContrasenias validador = new ValidadorDeContrasenias();
    Usuario usuarioConContrasenia = new Usuario("UN4PASSS3GUR4!", "rodri");
    String mensajeEsperado = "La contraseña debe cumplir la expresión regular: .*[a-z].* . No contiene minuscula.";

    ContraseniaInvalidaException e = Assertions.assertThrows(ContraseniaInvalidaException.class, () -> validador.esValidaContrasenia(usuarioConContrasenia));

    Assertions.assertEquals(mensajeEsperado, e.getMessage());
  }

  @Test
  public void contraseniaInvalidaSinNumeroTest() {
    ValidadorDeContrasenias validador = new ValidadorDeContrasenias();
    Usuario usuarioConContrasenia = new Usuario("UNPaSSSGUR!", "rodri");
    String mensajeEsperado = "La contraseña debe cumplir la expresión regular: .*\\d.* . No contiene numero.";

    ContraseniaInvalidaException e = Assertions.assertThrows(ContraseniaInvalidaException.class, () -> validador.esValidaContrasenia(usuarioConContrasenia));

    Assertions.assertEquals(mensajeEsperado, e.getMessage());
  }

  @Test
  public void contraseniaInvalidaSinSignoDePuntuacionTest() {
    ValidadorDeContrasenias validador = new ValidadorDeContrasenias();
    Usuario usuarioConContrasenia = new Usuario("uNP4SSSGUR", "facu");
    String mensajeEsperado = "La contraseña debe cumplir la expresión regular: .*[\\p{Punct}].* . No contiene signo de puntuacion.";

    ContraseniaInvalidaException e = Assertions.assertThrows(ContraseniaInvalidaException.class, () -> validador.esValidaContrasenia(usuarioConContrasenia));

    Assertions.assertEquals(mensajeEsperado, e.getMessage());
  }

  @Test
  public void contraseniaInvalidaMuyCortaTest() {
    ValidadorDeContrasenias validador = new ValidadorDeContrasenias();
    Usuario usuarioConContrasenia = new Usuario("uNP4s!", "fermin");
    String mensajeEsperado = "La contraseña debe tener al menos 8 caracteres.";

    ContraseniaInvalidaException e = Assertions.assertThrows(ContraseniaInvalidaException.class, () -> validador.esValidaContrasenia(usuarioConContrasenia));

    Assertions.assertEquals(mensajeEsperado, e.getMessage());
  }

  @Test
  public void contraseniaInvalidaMuyLargaTest() {
    ValidadorDeContrasenias validador = new ValidadorDeContrasenias();
    Usuario usuarioConContrasenia = new Usuario("AgUanT3E!RojoAgUanT3E!RojoAgUanT3E!RojoAgUanT3E!RojoAgUanT3E!RojoAgUanT3E!Rojo", "carlos");
    String mensajeEsperado = "La contraseña debe tener como maximo 64 caracteres.";

    ContraseniaInvalidaException e = Assertions.assertThrows(ContraseniaInvalidaException.class, () -> validador.esValidaContrasenia(usuarioConContrasenia));

    Assertions.assertEquals(mensajeEsperado, e.getMessage());
  }

  @Test
  public void contraseniaInvalidaMuyViejaTest() {
    ValidadorDeContrasenias validador = new ValidadorDeContrasenias();
    Usuario usuarioConContrasenia = new Usuario("AgUanT3E!", "feli");
    usuarioConContrasenia.setFechaContrasenia(LocalDate.of(2021, 4, 21));
    String mensajeEsperado = "La contraseña no puede tener más de 90 días desde su creacion.";

    ContraseniaInvalidaException e = Assertions.assertThrows(ContraseniaInvalidaException.class, () -> validador.esValidaContrasenia(usuarioConContrasenia));

    Assertions.assertEquals(mensajeEsperado, e.getMessage());
  }

  @Test
  public void contraseniaInvalidaEstaEnListaPeoresTest() {
    ValidadorDeContrasenias validador = new ValidadorDeContrasenias();
    Usuario usuarioConContrasenia = new Usuario("master", "rodri");
    String mensajeEsperado = "La contraseña se encuentra en la lista de las 'Diez mil peores contraseñas'.";

    ContraseniaInvalidaException e = Assertions.assertThrows(ContraseniaInvalidaException.class, () -> validador.esValidaContrasenia(usuarioConContrasenia));
    //la contraseña no cumple varios requisitos, por ende el mensaje de excepcion va a contener más cosas, por eso uso contains
    Assertions.assertTrue(e.getMessage().contains(mensajeEsperado));
  }

  @Test
  public void contraseniaInvalidaPorMasDeUnaRestriccionTest() {
    // Se testea que informe ambos incumplimientos
    ValidadorDeContrasenias validador = new ValidadorDeContrasenias();
    Usuario usuarioConContrasenia = new Usuario("aguante!", "rodri");
    String mensajeEsperadoUno = "La contraseña debe cumplir la expresión regular: .*[A-Z].* . No contiene mayuscula.";
    String mensajeEsperadoDos = "La contraseña debe cumplir la expresión regular: .*\\d.* .";

    ContraseniaInvalidaException e = Assertions.assertThrows(ContraseniaInvalidaException.class, () -> validador.esValidaContrasenia(usuarioConContrasenia));

    Assertions.assertTrue(e.getMessage().contains(mensajeEsperadoUno));
    Assertions.assertTrue(e.getMessage().contains(mensajeEsperadoDos));
  }

}