package models.modulos.exportador.factoryExportador;

import models.modulos.exportador.EstrategiaDeExportacion;
import models.modulos.exportador.formatosExportacion.excel.ExportarAExcel;
import models.modulos.exportador.NoExisteFormatoException;
import models.modulos.exportador.formatosExportacion.pdf.AdapterApachePDFBox;
import models.modulos.exportador.formatosExportacion.pdf.ExportarAPDF;

public class FactoryFormatoDeExportacion {

  public static EstrategiaDeExportacion crear(String formato, String nombreDelArchivo) {
    EstrategiaDeExportacion estrategiaDeExportacion;
    switch (formato) {
      case "PDF": estrategiaDeExportacion = crearExportarAPDF(nombreDelArchivo); break;
      case "EXCEL": estrategiaDeExportacion = new ExportarAExcel(nombreDelArchivo); break;
      default: throw new NoExisteFormatoException();
    }

    return estrategiaDeExportacion;
  }

  private static ExportarAPDF crearExportarAPDF(String nombreDelArchivo) {
    return new ExportarAPDF(new AdapterApachePDFBox(nombreDelArchivo));
  }
}
