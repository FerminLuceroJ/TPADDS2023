package models.modulos.exportador;

import models.entidades.rankings.reportes.Reporte;

public interface EstrategiaDeExportacion {
  public String exportar(Reporte reporte);
}
