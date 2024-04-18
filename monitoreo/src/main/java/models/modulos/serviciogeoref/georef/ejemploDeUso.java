package models.modulos.serviciogeoref.georef;

import models.modulos.serviciogeoref.georef.adapter.AdapterLocalizacionAPI;
import models.modulos.serviciogeoref.georef.entidades.ListadoDeMunicipios;
import models.entidades.ubicacion.Municipio;

import java.io.IOException;

public class ejemploDeUso {
  public static void main(String[] args) throws IOException {
    AdapterLocalizacionAPI servicioGeoref = ServicioGeoref.getInstancia();

    //Prueba Provincias
    /*
     System.out.println("Listado de Provincias:");

    ListadoDeProvincias listadoDeProvincias = servicioGeoref.listadoDeProvincias();

    listadoDeProvincias.provincias.sort((p1,p2) -> p1.id >= p2.id? 1 : -1);

    for (Provincia provincia: listadoDeProvincias.provincias) {
      System.out.println(provincia.id + " " + provincia.nombre);
    }
    */


    //Prueba Municipios

    ListadoDeMunicipios listadoDeMunicipios = servicioGeoref.listadoDeMunicipios(6, 100);
    for (Municipio municipio: listadoDeMunicipios.municipios) {
      System.out.println(municipio.id + " " + municipio.nombre);
    }

/*
    //Prueba Departamentos
    ListadoDepartamentos listadoDepartamentos = servicioGeoref.listadoDeDepartamentos(6, 100);
    for (Departamento municipio: listadoDepartamentos.departamentos) {
      System.out.println(municipio.id + " " + municipio.nombre);
    }*/
  }
}
