package models.modulos.serviciogeoref.georef;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;
import models.modulos.serviciogeoref.georef.entidades.ListadoDeMunicipios;
import models.modulos.serviciogeoref.georef.entidades.ListadoDeProvincias;
import models.modulos.serviciogeoref.georef.entidades.ListadoDepartamentos;

public interface GeorefService {
  @GET("provincias")
  Call<ListadoDeProvincias> getListadoProvincias();
  @GET("municipios")
  Call<ListadoDeMunicipios> getListadoMunicipios(@Query("provincia") int idProvincia, @Query("max") int cantidad);

  @GET("departamentos")
  Call<ListadoDepartamentos> getListadoDepartamentos(@Query("provincia") int idProvincia, @Query("max") int cantidad);

}
