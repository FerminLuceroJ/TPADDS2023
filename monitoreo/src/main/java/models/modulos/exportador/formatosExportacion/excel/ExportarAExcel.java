package models.modulos.exportador.formatosExportacion.excel;

import models.modulos.exportador.EstrategiaDeExportacion;
import models.modulos.exportador.config.Config;
import models.entidades.rankings.reportes.Reporte;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Arrays;
import java.util.List;


public class ExportarAExcel implements EstrategiaDeExportacion {
  private XSSFWorkbook workbook;
  private String nombreDeArchivo;

  public ExportarAExcel(String nombreDeArchivo) {
    this.nombreDeArchivo = nombreDeArchivo;
  }

  public String exportar(Reporte reporte) {
    crearLibroDeTrabajo();
    XSSFSheet hoja = crearHoja("Hoja1");
    agregarDatos(hoja, reporte.getDatos());
    guardar();
    return rutaCompletaDelArchivo();
  }

  private String rutaCompletaDelArchivo(){
    return Config.RUTA_EXPORTACION + this.nombreDeArchivo;
  }

  private void guardar(){
    try {
      FileOutputStream outputStream = new FileOutputStream(rutaCompletaDelArchivo());
      workbook.write(outputStream);
      workbook.close();
    } catch (FileNotFoundException e) {
      // Ojo con imprimir las excepciones. Esto va a traer dolores de cabeza más adelante. Si no sabemos qué hacer con
      // la excepción, además de imprimirla hay que hace throw new RuntimeException(e)
      e.printStackTrace();
      throw new RuntimeException(e);
    } catch (IOException e) {
      e.printStackTrace();
      throw new RuntimeException(e);
    }
  }

  private void crearLibroDeTrabajo(){
    this.workbook = new XSSFWorkbook();
  }

  private XSSFSheet crearHoja(String nombre){
    return workbook.createSheet(nombre);
  }

  private void agregarDatos(XSSFSheet hoja, List<String> datos){
    int numeroDeFila = 0;

    for (String dato : datos) {
      List<String> valores = Arrays.stream(dato.split(" ")).toList();
      Row fila = crearFila(hoja, numeroDeFila);
      numeroDeFila++;
      agregarColumna(valores, fila, hoja);
    }
  }
  private Row crearFila(XSSFSheet hoja, int numeroDeFila){
    return hoja.createRow(numeroDeFila);
  }

  private void agregarColumna(List<String> valores, Row fila, XSSFSheet hoja){
    int numeroDeColumna = 0;
    for (String v : valores) {
      Cell celda = fila.createCell(numeroDeColumna);
      numeroDeColumna++;
      celda.setCellValue(v);
    }
  }
}
