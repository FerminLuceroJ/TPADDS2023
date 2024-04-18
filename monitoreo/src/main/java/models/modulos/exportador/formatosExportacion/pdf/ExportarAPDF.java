package models.modulos.exportador.formatosExportacion.pdf;

import models.entidades.rankings.reportes.Reporte;
import models.modulos.exportador.EstrategiaDeExportacion;

public class ExportarAPDF implements EstrategiaDeExportacion {
  private AdapterExportadorPDF adapter;

  public ExportarAPDF(AdapterExportadorPDF adapter){
    this.adapter = adapter;
  }

  public String exportar(Reporte reporte) {
    return this.adapter.exportar(reporte);
  }
}
