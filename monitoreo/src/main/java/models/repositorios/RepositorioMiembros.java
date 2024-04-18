package models.repositorios;

import models.entidades.persona.Miembro;
import io.github.flbulgarelli.jpa.extras.simple.WithSimplePersistenceUnit;

import javax.persistence.EntityTransaction;
import java.util.List;

public class RepositorioMiembros implements WithSimplePersistenceUnit {

  public void persistir(Miembro miembro){
    EntityTransaction tx = entityManager().getTransaction();
    tx.begin();
    entityManager().persist(miembro);
    tx.commit();
  }
  
  public void modificar(Miembro miembro){
    EntityTransaction tx = entityManager().getTransaction();
    tx.begin();
    entityManager().merge(miembro);
    tx.commit();
  }

  public void eliminar(Miembro miembro){
    EntityTransaction tx = entityManager().getTransaction();
    tx.begin();
    entityManager().remove(miembro);
    tx.commit();
  }

  @SuppressWarnings("unchecked")
  public List<Miembro> miembros(){
    return entityManager()
        .createQuery("from Miembro")
        .getResultList();
  }

  public List<Miembro> miembrosSegunComunidad(int id_comunidad){
    return entityManager()
        .createQuery("from Miembro where miembro = :id_comunidad")
        .setParameter("miembro", id_comunidad)
        .getResultList();
  }

  public Miembro miembrosSegunUsuario(int usuario_id){
    return (Miembro) entityManager()
        .createQuery("from Miembro where usuario_id = :usuario_id")
        .setParameter("usuario_id", usuario_id)
        .getSingleResult();
  }

  public Miembro miembroSegunId(int id){
    return  (Miembro) entityManager()
        .createQuery("from Miembro where id = :id")
        .setParameter("id", id)
        .getSingleResult();
  }
}
