package modulos.exportador;


import models.entidades.rankings.reportes.Reporte;
import models.modulos.exportador.Exportador;
import models.modulos.exportador.config.Config;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class ExportadorTests {
  private Reporte reporte;
  private Exportador exportador;
  @BeforeEach
  public void init(){
    this.reporte = new Reporte();
    reporte.agregarDato("Esta es una prueba de exportacion");

    this.exportador = new Exportador();
  }

  @Test
  public void seExportaElReporteAExcel() {
    reporte.agregarDato("Agrego un segundo dato de prueba");
    reporte.agregarDato("Ahora un tercero");
    Assertions.assertEquals(Config.RUTA_EXPORTACION + "datos.xlsx", this.exportador.exportar(this.reporte,"EXCEL", "datos"));
  }
  @Test
  public void seExportaElReporteAPDF(){
    reporte.agregarDato("Agrego un segundo dato de prueba");
    reporte.agregarDato("Ahora un tercero");
    Assertions.assertEquals(Config.RUTA_EXPORTACION + "datos.pdf", this.exportador.exportar(this.reporte,"PDF", "datos"));
  }
}
