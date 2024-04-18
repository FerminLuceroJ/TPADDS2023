package models.repositorios;

import models.entidades.persona.Interes;
import io.github.flbulgarelli.jpa.extras.simple.WithSimplePersistenceUnit;

import javax.persistence.EntityTransaction;
import java.util.List;

public class RepositorioIntereses implements WithSimplePersistenceUnit {

    public void persistir(Interes interes){
        EntityTransaction tx = entityManager().getTransaction();
        tx.begin();
        entityManager().persist(interes);
        tx.commit();
    }

    public void eliminar(Interes interes){
        EntityTransaction tx = entityManager().getTransaction();
        tx.begin();
        entityManager().remove(interes);
        tx.commit();
    }

    public void modificar(Interes interes){
        EntityTransaction tx = entityManager().getTransaction();
        tx.begin();
        entityManager().merge(interes);
        tx.commit();
    }

    public Interes interesSegunId(int idInteres) {
        return entityManager().find(Interes.class, idInteres);
    }

    public List<Interes> interesesSegunIdMiembro(int IdMiembro) {
        return entityManager()
            .createQuery("from Interes where id_miembro = :IdMiembro")
            .setParameter("IdMiembro", IdMiembro)
            .getResultList();
    }

    public List<Interes> intereses() {
        return entityManager()
            .createQuery("from Interes")
            .getResultList();
    }

}
