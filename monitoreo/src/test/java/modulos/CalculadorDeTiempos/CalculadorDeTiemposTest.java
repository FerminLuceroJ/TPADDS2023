package modulos.CalculadorDeTiempos;


import models.modulos.calculadorDeTiempos.CalculadorDeTiempos;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.time.Duration;
import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;

public class CalculadorDeTiemposTest {

  private LocalDateTime hoy, ayer, fechaAntigua;

  @BeforeEach
  public void init(){
    this.hoy = LocalDateTime.now();
    this.ayer = hoy.minus(1, ChronoUnit.DAYS);
    this.fechaAntigua = hoy.minusWeeks(1);
  }

  @Test
  public void seCalculaBienElTiempoEntre2Fechas() {
    Duration tiempoEntreFechas = CalculadorDeTiempos.calcularTiempoEntreFechas(ayer, hoy);
    Assertions.assertEquals(24, tiempoEntreFechas.toHours());
  }

  @Test
  public void unaFechaNoPerteneceAEstaSemana(){
    Assertions.assertFalse(CalculadorDeTiempos.estaEnSemanaActual(fechaAntigua));
  }

  @Test
  public void unaFechaSiPerteneceAEstaSemana(){
    Assertions.assertTrue(CalculadorDeTiempos.estaEnSemanaActual(hoy));
  }
}
