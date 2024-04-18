package models.repositorios;

import models.entidades.servicios.servicio.PrestacionDeServicio;
import io.github.flbulgarelli.jpa.extras.simple.WithSimplePersistenceUnit;

import javax.persistence.EntityTransaction;
import java.util.List;

public class RepositorioPrestacionesDeServicios implements WithSimplePersistenceUnit {

    public void persistir(PrestacionDeServicio prestacionDeServicio){
        EntityTransaction tx = entityManager().getTransaction();
        tx.begin();
        entityManager().persist(prestacionDeServicio);
        tx.commit();
    }

    public void modificar(PrestacionDeServicio prestacionDeServicio){
        EntityTransaction tx = entityManager().getTransaction();
        tx.begin();
        entityManager().merge(prestacionDeServicio);
        tx.commit();
    }

    public void eliminar(PrestacionDeServicio prestacionDeServicio){
        EntityTransaction tx = entityManager().getTransaction();
        tx.begin();
        entityManager().remove(prestacionDeServicio);
        tx.commit();
    }

    public List<PrestacionDeServicio> prestacionesDeServicio(){
        return entityManager()
            .createQuery("from PrestacionDeServicio")
            .getResultList();
    }

    public PrestacionDeServicio prestacionSegunEstablecimientoYServicio(int idEstablecimiento, int idServicio){
        return (PrestacionDeServicio) entityManager()
            .createQuery("from PrestacionDeServicio where id_establecimiento = :idEstablecimiento AND id_servicio = :idServicio")
            .setParameter("idEstablecimiento", idEstablecimiento)
            .setParameter("idServicio", idServicio)
            .getSingleResult();
    }

    public PrestacionDeServicio prestacionDeServicioSegunID(int id){
        return (PrestacionDeServicio) entityManager().find(PrestacionDeServicio.class, id);
    }
}
