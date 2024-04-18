package models.repositorios;

import models.entidades.incidente.Estado;
import models.entidades.incidente.Incidente;
import io.github.flbulgarelli.jpa.extras.simple.WithSimplePersistenceUnit;

import javax.persistence.EntityTransaction;
import java.util.List;

public class RepositorioIncidentes implements WithSimplePersistenceUnit {

  public void persistir(Incidente incidente){
    EntityTransaction tx = entityManager().getTransaction();
    tx.begin();
    entityManager().persist(incidente);
    tx.commit();
  }

  public void eliminar(Incidente incidente){
    EntityTransaction tx = entityManager().getTransaction();
    tx.begin();
    entityManager().remove(incidente);
    tx.commit();
  }

  public void modificar(Incidente incidente){
    EntityTransaction tx = entityManager().getTransaction();
    tx.begin();
    entityManager().merge(incidente);
    tx.commit();
  }
  @SuppressWarnings("unchecked")
  public List<Incidente> incidentes(){
    return entityManager()
        .createQuery("from Incidente")
        .getResultList();
  }

  public Incidente incidenteSegunId(int id){
    return (Incidente) entityManager().find(Incidente.class, id);
  }

  @SuppressWarnings("unchecked")
  public List<Incidente> incidentesSegunEstado(Estado unEstado){
    return entityManager()
        .createQuery("from Incidente where estado = :estado")
        .setParameter("estado",unEstado)
        .getResultList();
  }

  @SuppressWarnings("unchecked")
  public List<Incidente> incidentesSegunNotificante(int notificanteId){
    return entityManager()
        .createQuery("from Incidente where id_miembro = :notificanteId")
        .setParameter("notificanteId",notificanteId)
        .getResultList();
  }

}
