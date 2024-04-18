package models.repositorios;

import models.entidades.notificacion.Notificacion;
import io.github.flbulgarelli.jpa.extras.simple.WithSimplePersistenceUnit;

import javax.persistence.EntityTransaction;
import java.util.List;

public class RepositorioNotificaciones implements WithSimplePersistenceUnit {

  public void persistir(Notificacion notificacion){
    EntityTransaction tx = entityManager().getTransaction();
    tx.begin();
    entityManager().persist(notificacion);
    tx.commit();
  }

  public void modificar(Notificacion notificacion){
    EntityTransaction tx = entityManager().getTransaction();
    tx.begin();
    entityManager().merge(notificacion);
    tx.commit();
  }

  public void eliminar(Notificacion notificacion){
    EntityTransaction tx = entityManager().getTransaction();
    tx.begin();
    entityManager().remove(notificacion);
    tx.commit();
  }

  @SuppressWarnings("unchecked")
  public List<Notificacion> notificaciones() {
    return entityManager()
        .createQuery("from Notificacion")
        .getResultList();
  }

  @SuppressWarnings("unchecked")
  public Notificacion notificacionSegunId(int id) {
    return (Notificacion) entityManager()
        .createQuery("from Notificacion where id = :id")
        .setParameter("id", id)
        .getSingleResult();
  }

  public Notificacion notificacionSegunEstado(Boolean enviada) {
    Integer estado;
    if(enviada){
      estado = 1;
    }
    else {
      estado = 0;
    }
    return (Notificacion) entityManager()
        .createQuery("from Notificacion where estado = :estado")
        .setParameter("estado",estado)
        .getResultList();
  }
}
