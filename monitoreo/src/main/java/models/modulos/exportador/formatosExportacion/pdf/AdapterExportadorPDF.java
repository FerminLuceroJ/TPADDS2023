package models.modulos.exportador.formatosExportacion.pdf;

import models.entidades.rankings.reportes.Reporte;

public interface AdapterExportadorPDF {
  public String exportar(Reporte reporte);
}
