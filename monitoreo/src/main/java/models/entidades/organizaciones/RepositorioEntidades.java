package models.entidades.organizaciones;

import models.entidades.organizaciones.entidad.Entidad;
import lombok.Getter;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class RepositorioEntidades {
  @Getter
  List<Entidad> entidades;

  public RepositorioEntidades(){
    this.entidades = new ArrayList<>();
  }

  public void agregarEntidad(Entidad... entidades){
    Collections.addAll(this.entidades, entidades);
  }
}
