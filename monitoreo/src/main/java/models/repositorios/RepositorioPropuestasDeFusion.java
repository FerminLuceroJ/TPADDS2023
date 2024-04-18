package models.repositorios;

import io.github.flbulgarelli.jpa.extras.simple.WithSimplePersistenceUnit;
import models.modulos.fusionador.PropuestaDeFusion;

import javax.persistence.EntityTransaction;

public class RepositorioPropuestasDeFusion implements WithSimplePersistenceUnit {

    public void persistir(PropuestaDeFusion propuesta){
        EntityTransaction tx = entityManager().getTransaction();
        tx.begin();
        entityManager().persist(propuesta);
        tx.commit();
    }
}
