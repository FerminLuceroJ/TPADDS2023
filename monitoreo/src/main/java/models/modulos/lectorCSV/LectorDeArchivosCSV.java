package models.modulos.lectorCSV;

import com.opencsv.CSVReader;
import models.entidades.organizaciones.EmpresaPrestadoraDeServicios;
import models.entidades.organizaciones.OrganismoDeControl;
import models.modulos.lectorCSV.excepciones.ErrorLeerCSVException;

import java.io.*;
import java.net.URISyntaxException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;

public class LectorDeArchivosCSV {
  private static final String nombreArchivoOrganismos = "OrganismosDeControl.csv";
  private static final String nombreArchivoEmpresas = "EmpresasPrestadorasDeServicios.csv";
  private final CSVReader lectorCSVOrganismos;
  private final CSVReader lectorCSVEmpresas;

  public LectorDeArchivosCSV() throws URISyntaxException, IOException {
    Path rutaOrganismos = Paths.get(ClassLoader.getSystemResource(nombreArchivoOrganismos).toURI());
    Path rutaEmpresas = Paths.get(ClassLoader.getSystemResource(nombreArchivoEmpresas).toURI());
    Reader lectorOrganismos = Files.newBufferedReader(rutaOrganismos);
    Reader lectorEmpresas = Files.newBufferedReader(rutaEmpresas);
    this.lectorCSVOrganismos = new CSVReader(lectorOrganismos);
    this.lectorCSVEmpresas = new CSVReader(lectorEmpresas);
  }

  public List<String> LeerArchivoCsvWeb(InputStream archivoEmpresas){
    InputStream inputStream = archivoEmpresas;
    List<String> cadenas = new ArrayList<>();

    // Crear un lector para leer el contenido del archivo
    try (BufferedReader reader = new BufferedReader(new InputStreamReader(inputStream))) {
      String linea;
      while ((linea = reader.readLine()) != null) {
        cadenas.add(linea);
        System.out.println(linea);
      }
    } catch (IOException e) {
      e.printStackTrace();
    }

    return cadenas;
  }

  public List<String[]> leerArchivoDeOrganismosCSV() throws IOException {
    return lectorCSVOrganismos.readAll();
  }

  public List<String[]> leerArchivoDeEmpresasCSV() throws IOException {
    return lectorCSVEmpresas.readAll();
  }

  // Por el momento del archivo CSV solo cargamos 1 atributo "nombre" de las organizaciones, podrían cargarse más atributos sin problema
  public List<EmpresaPrestadoraDeServicios> cargarEmpresas() {
    List<String[]> lista;

    try{
      lista = this.leerArchivoDeEmpresasCSV();
    }
    catch (IOException e) {
      throw new ErrorLeerCSVException("Error al leer el archivo CSV de empresas");
    }

    List<EmpresaPrestadoraDeServicios> empresas = new ArrayList<>();
    for (String[] prestadorDeServicio : lista){
        EmpresaPrestadoraDeServicios empresa = new EmpresaPrestadoraDeServicios(prestadorDeServicio[0]);
        empresas.add(empresa);
    }
    return empresas;
  }

  public List<OrganismoDeControl> cargarOrganismos() {
  List<String[]> lista;

    try {
      lista = this.leerArchivoDeOrganismosCSV();
    }
     catch (IOException e) {
      throw new ErrorLeerCSVException("Error al leer el archivo CSV de organismos");
     }

    List<OrganismoDeControl> organismos = new ArrayList<>();
    for (String[] prestadorDeServicio : lista){
        OrganismoDeControl organismo = new OrganismoDeControl(prestadorDeServicio[0]);
        organismos.add(organismo);
    }
    return organismos;
  }

}
