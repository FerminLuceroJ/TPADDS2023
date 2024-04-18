package models.repositorios;

import models.entidades.servicios.servicio.Servicio;
import io.github.flbulgarelli.jpa.extras.simple.WithSimplePersistenceUnit;

import javax.persistence.EntityTransaction;
import java.util.List;

public class RepositorioServicios implements WithSimplePersistenceUnit {

    public void persistir(Servicio servicio){
        EntityTransaction tx = entityManager().getTransaction();
        tx.begin();
        entityManager().persist(servicio);
        tx.commit();
    }

    public void eliminar(Servicio servicio){
        EntityTransaction tx = entityManager().getTransaction();
        tx.begin();
        entityManager().remove(servicio);
        tx.commit();
    }

    public void modificar(Servicio servicio){
        EntityTransaction tx = entityManager().getTransaction();
        tx.begin();
        entityManager().merge(servicio);
        tx.commit();
    }

    @SuppressWarnings("unchecked")
    public List<Servicio> servicios(){
        return entityManager()
            .createQuery("from Servicio")
            .getResultList();
    }

    public Servicio servicioSegunNombre(String nombre){
        return (Servicio) entityManager()
            .createQuery("from Servicio where nombre = :nombre")
            .setParameter("nombre", nombre)
            .getSingleResult();
    }

    public Servicio servicioSegunId(int id){
        return (Servicio) entityManager().find(Servicio.class, id);
    }
}
