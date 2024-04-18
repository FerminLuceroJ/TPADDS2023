package models.modulos.serviciogeoref.georef.adapter;

import models.modulos.serviciogeoref.georef.entidades.ListadoDeMunicipios;
import models.modulos.serviciogeoref.georef.entidades.ListadoDeProvincias;
import models.modulos.serviciogeoref.georef.entidades.ListadoDepartamentos;

import java.io.IOException;
public interface AdapterLocalizacionAPI {
  public ListadoDeProvincias listadoDeProvincias() throws IOException;
  public ListadoDeMunicipios listadoDeMunicipios(int id, int cantidad) throws IOException;
  public ListadoDepartamentos listadoDeDepartamentos(int id, int cantidad) throws IOException;
}
