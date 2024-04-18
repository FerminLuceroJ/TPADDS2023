package models.modulos.calculadorDeTiempos;

import java.time.DayOfWeek;
import java.time.Duration;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.temporal.TemporalAdjusters;
import java.time.temporal.WeekFields;
import java.util.Locale;

public final class CalculadorDeTiempos {

  private CalculadorDeTiempos() {
  }

  public static Duration calcularTiempoEntreFechas(LocalDateTime unaFecha, LocalDateTime otraFecha) {
    return Duration.between(unaFecha, otraFecha);
  }

  public static boolean estaEnSemanaActual(LocalDateTime fecha) {

    //DayOfWeek diaSemana = fecha.getDayOfWeek();

    //LocalDateTime inicioSemana = fecha.minusDays(diaSemana.getValue() - 1).with(LocalTime.MIDNIGHT);
    //LocalDateTime finSemana = inicioSemana.plusDays(6).with(LocalTime.of(23, 59, 59));

    //return fecha.isAfter(inicioSemana) && fecha.isBefore(finSemana);
    //}

    LocalDateTime lunesDeLaSemana = LocalDateTime.now().with(TemporalAdjusters.previousOrSame(DayOfWeek.MONDAY));

    WeekFields weekFields = WeekFields.of(Locale.getDefault());

    int numeroDeLaSemanaAComparar = fecha.get(weekFields.weekOfWeekBasedYear());
    int numeroDeLaSemanaActual = lunesDeLaSemana.get(weekFields.weekOfWeekBasedYear());

    // Si el numero de lo semana que estamos comparando la fecha, es igual al de la semana actual, quiere decir que esa fecha esta en la misma semana

    return numeroDeLaSemanaAComparar == numeroDeLaSemanaActual;

  }
}

