package modulos.lectorCSV;

import models.entidades.organizaciones.EmpresaPrestadoraDeServicios;
import models.entidades.organizaciones.OrganismoDeControl;
import models.modulos.lectorCSV.LectorDeArchivosCSV;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.net.URISyntaxException;
import java.util.List;

public class LectorDeArchivosCSVTest {

  private LectorDeArchivosCSV lector;
  @BeforeEach
  public void init() throws URISyntaxException, IOException {
    lector = new LectorDeArchivosCSV();
  }

  @Test
  public void instanciaTodasLasEmpresasYOrganismos() throws IOException {
    Assertions.assertEquals(2, lector.cargarEmpresas().size());
    Assertions.assertEquals(2, lector.cargarOrganismos().size());
  }
  @Test
  public void lasLineasSeLeenBien() throws IOException {
    List<String[]> empresas = lector.leerArchivoDeEmpresasCSV();
    List<String[]> organismos = lector.leerArchivoDeOrganismosCSV();

    Assertions.assertEquals("NombreE1", empresas.get(0)[0]);
    Assertions.assertEquals("NombreE2", empresas.get(1)[0]);
    Assertions.assertEquals("NombreO1", organismos.get(0)[0]);
    Assertions.assertEquals("NombreO2", organismos.get(1)[0]);
  }

  @Test
  public void laPrimerEmpresaEstaBien() throws IOException {
    List<EmpresaPrestadoraDeServicios> empresas = lector.cargarEmpresas();
    Assertions.assertEquals("NombreE1",empresas.get(0).getNombre());
  }
  @Test
  public void elSegundoOrganismoEstaBien() throws IOException{
    List<OrganismoDeControl> organismos = lector.cargarOrganismos();
    Assertions.assertEquals("NombreO2", organismos.get(1).getNombre());
  }
}
