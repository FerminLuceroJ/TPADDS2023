package models.repositorios;

import models.entidades.comunidad.Comunidad;
import io.github.flbulgarelli.jpa.extras.simple.WithSimplePersistenceUnit;

import javax.persistence.EntityTransaction;
import java.util.List;

public class RepositorioComunidades implements WithSimplePersistenceUnit {

  public void persistir(Comunidad unaComunidad){
    EntityTransaction tx = entityManager().getTransaction();
    tx.begin();
    entityManager().persist(unaComunidad);
    tx.commit();
  }

  public void modificar(Comunidad unaComunidad){
    EntityTransaction tx = entityManager().getTransaction();
    tx.begin();
    entityManager().merge(unaComunidad);
    tx.commit();
  }

  public void eliminar(Comunidad unaComunidad) {
    EntityTransaction tx = entityManager().getTransaction();
    tx.begin();
    entityManager().remove(unaComunidad);
    tx.commit();
  }

  @SuppressWarnings("unchecked")
  public List<Comunidad> comunidades() {
    return entityManager()
        .createQuery("from Comunidad")
        .getResultList();
  }

  @SuppressWarnings("unchecked")
  public List<Comunidad> comunidadesSegunEstado(Boolean estado){
    return entityManager()
            .createQuery("from Comunidad where estaActiva = :estado")
            .setParameter("estado", estado)
            .getResultList();
  }

  @SuppressWarnings("unchecked")
  public Comunidad comunidadSegunId(int id) {
    return (Comunidad) entityManager().find(Comunidad.class, id);
  }

  @SuppressWarnings("unchecked")
  public List<Comunidad> comunidadSegunNombre(String nombre) {
    return entityManager()
        .createQuery("from Comunidad where nombre = :nombre")
        .setParameter("nombre", nombre)
        .getResultList();
  }
}