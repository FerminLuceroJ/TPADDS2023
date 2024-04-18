package models.repositorios;

import models.entidades.organizaciones.entidad.Entidad;
import io.github.flbulgarelli.jpa.extras.simple.WithSimplePersistenceUnit;

import javax.persistence.EntityTransaction;
import java.util.List;

public class RepositorioEntidades implements WithSimplePersistenceUnit {

  public void persistir(Entidad unaEntidad) {
    EntityTransaction tx = entityManager().getTransaction();
    tx.begin();
    entityManager().persist(unaEntidad);
    tx.commit();
  }

  @SuppressWarnings("unchecked")
  public List<Entidad> entidades() {
    return entityManager()
        .createQuery("from Entidad")
        .getResultList();
  }

  public void eliminar(Entidad unaEntidad) {
    EntityTransaction tx = entityManager().getTransaction();
    tx.begin();
    entityManager().remove(unaEntidad);
    tx.commit();
  }

  public Entidad entidadSegunId(int idEntidad) {
    return entityManager().find(Entidad.class, idEntidad);
  }

  @SuppressWarnings("unchecked")
  public List<Entidad> entidadSegunNombre(String nombre) {
    return entityManager()
        .createQuery("from Entidad where nombre = :nombre")
        .setParameter("nombre", nombre)
        .getResultList();
  }

  public void modificar(Entidad unaEntidad) {
    EntityTransaction tx = entityManager().getTransaction();

    tx.begin();
    entityManager().merge(unaEntidad);
    tx.commit();
  }

}