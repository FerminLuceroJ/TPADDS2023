package models.repositorios;

import models.entidades.ubicacion.Localizacion;
import io.github.flbulgarelli.jpa.extras.simple.WithSimplePersistenceUnit;

import javax.persistence.EntityTransaction;
import java.util.List;

public class RepositorioLocalizaciones implements WithSimplePersistenceUnit {

    public void persistir(Localizacion unaLocalizacion) {
        EntityTransaction tx = entityManager().getTransaction();
        tx.begin();
        entityManager().persist(unaLocalizacion);
        tx.commit();
    }

    public void eliminar(Localizacion unaLocalizacion) {
        EntityTransaction tx = entityManager().getTransaction();
        tx.begin();
        entityManager().remove(unaLocalizacion);
        tx.commit();
    }

    @SuppressWarnings("unchecked")
    public List<Localizacion> localizaciones() {
        return entityManager()
            .createQuery("from Localizacion")
            .getResultList();
    }

    public Localizacion localizacionSegunId(int idLocalizacion) {
        return entityManager().find(Localizacion.class, idLocalizacion);
    }

    public void modificar(Localizacion localizacion) {
        EntityTransaction tx = entityManager().getTransaction();

        tx.begin();
        entityManager().merge(localizacion);
        tx.commit();
    }

}
