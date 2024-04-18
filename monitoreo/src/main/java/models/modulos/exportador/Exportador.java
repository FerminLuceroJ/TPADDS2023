package models.modulos.exportador;

import models.entidades.rankings.reportes.Reporte;
import models.modulos.exportador.factoryExportador.FactoryFormatoDeExportacion;

public class Exportador {

  public String exportar(Reporte reporte, String formatoDeExportacion, String nombreDelArchivo){
    String extension = ".pdf";
    switch(formatoDeExportacion){
      case "PDF" ->
          extension = ".pdf";
      case "EXCEL" ->
          extension = ".xlsx";
    }
    EstrategiaDeExportacion estrategia = FactoryFormatoDeExportacion.crear(formatoDeExportacion, nombreDelArchivo + extension);
    return estrategia.exportar(reporte);
  }
}
