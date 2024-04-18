package models.modulos.exportador.formatosExportacion.pdf;
import models.modulos.exportador.config.Config;

import models.entidades.rankings.reportes.Reporte;
import org.apache.pdfbox.pdmodel.PDDocument;
import org.apache.pdfbox.pdmodel.PDPage;
import org.apache.pdfbox.pdmodel.PDPageContentStream;
import org.apache.pdfbox.pdmodel.font.PDType1Font;

import java.io.IOException;
import java.util.List;

public class AdapterApachePDFBox implements AdapterExportadorPDF{

  private String nombreDeArchivo;

  public AdapterApachePDFBox(String nombreDeArchivo) {
    this.nombreDeArchivo = nombreDeArchivo;
  }

  public String exportar(Reporte reporte) {
    PDDocument doc = new PDDocument();
    PDPage myPage = new PDPage();
    doc.addPage(myPage);
    try {
      PDPageContentStream cont = new PDPageContentStream(doc, myPage);
      cont.beginText();
      cont.setFont(PDType1Font.TIMES_ROMAN, 12);
      cont.setLeading(14.5f);
      cont.newLineAtOffset(25, 700);
      agregarDatos(cont, reporte.getDatos());

      cont.endText();
      cont.close();
      doc.save(rutaCompletaDelArchivo());
      doc.close();
    } catch (IOException e) {
      e.printStackTrace();
    }
    return rutaCompletaDelArchivo();
  }

  private String rutaCompletaDelArchivo(){
    return Config.RUTA_EXPORTACION + this.nombreDeArchivo;
  }

  private void agregarDatos(PDPageContentStream pagina, List<String> datos) throws IOException {
    for (String dato : datos) {
      pagina.newLine();
      //String datosDeLaFila = String.join(", ", datos);
      pagina.showText(dato);
    }
  }

}
