package models.repositorios;

import models.entidades.organizaciones.entidad.TipoEntidad;
import io.github.flbulgarelli.jpa.extras.simple.WithSimplePersistenceUnit;

import javax.persistence.EntityTransaction;
import java.util.List;

public class RepositorioTipoEntidades implements WithSimplePersistenceUnit {

  public void persistir(TipoEntidad tipoEntidad){
    EntityTransaction tx = entityManager().getTransaction();
    tx.begin();
    entityManager().persist(tipoEntidad);
    tx.commit();
  }

  public void modificar(TipoEntidad tipoEntidad) {
    EntityTransaction tx = entityManager().getTransaction();

    tx.begin();
    entityManager().merge(tipoEntidad);
    tx.commit();
  }

  public void eliminar(TipoEntidad tipoEntidad){
    EntityTransaction tx = entityManager().getTransaction();
    tx.begin();
    entityManager().remove(tipoEntidad);
    tx.commit();
  }

  @SuppressWarnings("unchecked")
  public List<TipoEntidad> tipoDeEntidades(){
    return entityManager()
        .createQuery("from TipoEntidad")
        .getResultList();
  }

  public TipoEntidad tipoSegunId(int id){
    return (TipoEntidad) entityManager().find(TipoEntidad.class, id);
  }

}
