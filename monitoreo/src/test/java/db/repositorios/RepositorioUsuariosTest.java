package db.repositorios;

import models.entidades.persona.autentificacion.Rol;
import models.entidades.persona.autentificacion.Usuario;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import models.repositorios.RepositorioUsuarios;

import java.util.List;

public class RepositorioUsuariosTest {

    private Usuario unUsuario;

    private RepositorioUsuarios repositorioUsuarios;

    @BeforeEach
    public void init(){
        this.unUsuario = new Usuario("1234", "nombreUsuario");
        unUsuario.setCorreoElectronico("correoReal@gmail.com");
        unUsuario.setRol(Rol.ADMIN_COMUNIDAD);

        repositorioUsuarios = new RepositorioUsuarios();
    }

    @Test
    public void elUsuarioSePersiste(){
        repositorioUsuarios.persistir(unUsuario);
        Usuario otroUsuario = repositorioUsuarios.usuarioSegunId(1);
        Assertions.assertEquals("nombreUsuario", otroUsuario.getUsuario());
    }

    @Test
    public void seTraeElUsuarioPorID(){
        Usuario otroUsuario = repositorioUsuarios.usuarioSegunId(1);
        Assertions.assertEquals("nombreUsuario", otroUsuario.getUsuario());
    }

    @Test
    public void seTraeTodosLosUsuarios(){
        List<Usuario> usuarios = repositorioUsuarios.usuarios();
        Assertions.assertEquals(1, usuarios.size());
    }

    @Test
    public void elUsuarioSeModifica(){
        Usuario usuarioAModificar = repositorioUsuarios.usuarioSegunId(1);
        usuarioAModificar.setUsuario("otroNombre");
        repositorioUsuarios.modificar(usuarioAModificar);
        Usuario usuarioModificado = repositorioUsuarios.usuarioSegunId(1);
        Assertions.assertEquals("otroNombre", usuarioModificado.getUsuario());
    }

    @Test
    public void elUsuarioEsDelRolBuscado(){
        Usuario otroUsuario = repositorioUsuarios.usuariosSegunRol(Rol.ADMIN_COMUNIDAD).get(0);
        Assertions.assertEquals("1234", otroUsuario.getContrasenia());
    }

    @Test
    public void elUsuarioSeElimina(){
        Usuario usuarioAEliminar = repositorioUsuarios.usuarioSegunId(1);
        repositorioUsuarios.eliminar(usuarioAEliminar);
        Assertions.assertEquals(0, repositorioUsuarios.usuarios().size());
    }

}
