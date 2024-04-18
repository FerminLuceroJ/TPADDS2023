package models.repositorios;

import models.entidades.incidente.Observacion;
import io.github.flbulgarelli.jpa.extras.simple.WithSimplePersistenceUnit;

import javax.persistence.EntityTransaction;
import java.util.List;

public class RepositorioObservaciones implements WithSimplePersistenceUnit {

  public void persistir(Observacion observacion){
    EntityTransaction tx = entityManager().getTransaction();
    tx.begin();
    entityManager().persist(observacion);
    tx.commit();
  }

  public void modificar(Observacion observacion){
    EntityTransaction tx = entityManager().getTransaction();
    tx.begin();
    entityManager().merge(observacion);
    tx.commit();
  }

  public void eliminarObservacion(Observacion observacion) {
    EntityTransaction tx = entityManager().getTransaction();
    tx.begin();
    entityManager().remove(observacion);
    tx.commit();
  }

  @SuppressWarnings("unchecked")
  public List<Observacion> observaciones() {
    return entityManager()
        .createQuery("from Observacion")
        .getResultList();
  }


  @SuppressWarnings("unchecked")
  public Observacion observacionSegunId(int id) {
    return (Observacion) entityManager().find(Observacion.class, id);
  }

  @SuppressWarnings("unchecked")
  public List<Observacion> observacionSegunIdMiembro(int idMiembro) {
    return entityManager().createQuery("from Observacion where id_miembro = :idMiembro")
        .setParameter("idMiembro",idMiembro)
        .getResultList();
  }
}