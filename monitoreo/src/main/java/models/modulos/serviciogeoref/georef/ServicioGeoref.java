package models.modulos.serviciogeoref.georef;

import models.modulos.serviciogeoref.georef.adapter.AdapterLocalizacionAPI;
import retrofit2.Call;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;
import models.modulos.serviciogeoref.georef.entidades.ListadoDeMunicipios;
import models.modulos.serviciogeoref.georef.entidades.ListadoDeProvincias;
import models.modulos.serviciogeoref.georef.entidades.ListadoDepartamentos;

import java.io.IOException;

public class ServicioGeoref implements AdapterLocalizacionAPI {


  private static ServicioGeoref instancia = null;
  private Retrofit retrofit;
  private static final String urlApi = "https://apis.datos.gob.ar/georef/api/";
  private ServicioGeoref(){
    this.retrofit = new Retrofit.Builder()
        .baseUrl(urlApi)
        .addConverterFactory(GsonConverterFactory.create())
        .build();
  }

  public static ServicioGeoref getInstancia(){
    if(instancia == null){
      instancia = new ServicioGeoref();
    }
    return instancia;
  }

  public ListadoDeProvincias listadoDeProvincias() throws IOException {
    GeorefService georefService = this.retrofit.create(GeorefService.class);
    Call<ListadoDeProvincias> requestProvincias = georefService.getListadoProvincias();
    Response<ListadoDeProvincias> responseProvincias = requestProvincias.execute();
  return responseProvincias.body();
  }

  public ListadoDeMunicipios listadoDeMunicipios(int id, int cantidad) throws IOException {
    GeorefService georefService = this.retrofit.create(GeorefService.class);
    Call<ListadoDeMunicipios> requestMunicipios = georefService.getListadoMunicipios(id, cantidad);
    Response<ListadoDeMunicipios> responseMunicipios = requestMunicipios.execute();
    return responseMunicipios.body();
  }

  public ListadoDepartamentos listadoDeDepartamentos(int id, int cantidad) throws IOException {
    GeorefService georefService = this.retrofit.create(GeorefService.class);
    Call<ListadoDepartamentos> requestDepartamentos = georefService.getListadoDepartamentos(id, cantidad);
    Response<ListadoDepartamentos> responseDepartamentos = requestDepartamentos.execute();
    return responseDepartamentos.body();
  }
}
