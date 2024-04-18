package models.repositorios;

import models.entidades.organizaciones.Establecimiento;
import io.github.flbulgarelli.jpa.extras.simple.WithSimplePersistenceUnit;

import javax.persistence.EntityTransaction;
import java.util.List;

public class RepositorioEstablecimientos implements WithSimplePersistenceUnit {

    public void persistir(Establecimiento unEstablecimiento) {
        EntityTransaction tx = entityManager().getTransaction();
        tx.begin();
        entityManager().persist(unEstablecimiento);
        tx.commit();
    }

    @SuppressWarnings("unchecked")
    public List<Establecimiento> establecimientos() {
        return entityManager()
            .createQuery("from Establecimiento")
            .getResultList();
    }

    public void eliminar(Establecimiento unEstablecimiento) {
        EntityTransaction tx = entityManager().getTransaction();
        tx.begin();
        entityManager().remove(unEstablecimiento);
        tx.commit();
    }

    public Establecimiento establecimientoSegunId(int idEstablecimiento) {
        return entityManager().find(Establecimiento.class, idEstablecimiento);
    }

    public Establecimiento establecimientoSegunNombre(String nombre){
        return (Establecimiento) entityManager()
            .createQuery("from Establecimiento where nombre = :nombre")
            .setParameter("nombre",nombre)
            .getSingleResult();
    }

    public void modificar(Establecimiento unEstablecimiento) {
        EntityTransaction tx = entityManager().getTransaction();

        tx.begin();
        entityManager().merge(unEstablecimiento);
        tx.commit();
    }


}
