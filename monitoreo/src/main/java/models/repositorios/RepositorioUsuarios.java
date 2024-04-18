package models.repositorios;

import models.entidades.persona.Miembro;
import models.entidades.persona.autentificacion.Rol;
import models.entidades.persona.autentificacion.Usuario;
import io.github.flbulgarelli.jpa.extras.simple.WithSimplePersistenceUnit;

import javax.persistence.EntityTransaction;
import java.util.ArrayList;
import java.util.List;

public class RepositorioUsuarios implements WithSimplePersistenceUnit {

  public void persistir(Usuario usuario){
    EntityTransaction tx = entityManager().getTransaction();
    tx.begin();
    entityManager().persist(usuario);
    tx.commit();
  }

  public void eliminar(Usuario usuario){
    EntityTransaction tx = entityManager().getTransaction();
    tx.begin();
    entityManager().remove(usuario);
    tx.commit();
  }

  public void modificar(Usuario usuario){
    EntityTransaction tx = entityManager().getTransaction();
    tx.begin();
    entityManager().merge(usuario);
    tx.commit();
  }

  @SuppressWarnings("unchecked")
  public List<Usuario> usuarios(){
    return entityManager()
        .createQuery("from Usuario")
        .getResultList();
  }

  public Usuario usuarioSegunId(int id){
    return (Usuario) entityManager().find(Usuario.class, id);
  }

  public Usuario usuarioSegunCorreoElectronica(String correoElectronico){
    List<Usuario> usuarios = entityManager()
        .createQuery("from Usuario where mail = :mail")
        .setParameter("mail", correoElectronico)
        .getResultList();

    if(usuarios.isEmpty()){
      return null;
    }
    return usuarios.get(0);
  }

  @SuppressWarnings("unchecked")
  public List<Usuario> usuariosSegunRol(Rol unRol){
    return entityManager()
        .createQuery("from Usuario where rol = :rol")
        .setParameter("rol", unRol)
        .getResultList();
  }

  @SuppressWarnings("unchecked")
  public List<Usuario> usuarioSegunNombre(String nombre){
    return entityManager()
        .createQuery("from Usuario where usuario = :nombre")
        .setParameter("usuario", nombre)
        .getResultList();
  }

  public List<Usuario> usuarioSegunComunidad(int id_comunidad){

    RepositorioMiembros repositorioMiembros = new RepositorioMiembros();

    List<Miembro> miembros = repositorioMiembros.miembrosSegunComunidad(id_comunidad);

    List<Usuario> usuarios = new ArrayList<>();

    for (Miembro miembro: miembros) {
      usuarios.add(miembro.getUsuario());
    }

    return usuarios;
  }
}
