package db.repositorios;

import models.entidades.ubicacion.Departamento;
import models.entidades.ubicacion.Localizacion;
import models.entidades.ubicacion.Municipio;
import models.entidades.ubicacion.Provincia;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import models.repositorios.RepositorioLocalizaciones;

import java.util.List;

public class RepositorioLocalizacionesTest {

    private Departamento unDepartamento;
    private Municipio unMunicipio;
    private Provincia unaProvicia;

    private RepositorioLocalizaciones repositorioLocalizaciones;

    @BeforeEach
    void init(){
        this.unDepartamento = new Departamento();
        this.unMunicipio = new Municipio();
        this.unaProvicia = new Provincia();

        this.repositorioLocalizaciones = new RepositorioLocalizaciones();
    }

    @Test
    public void laUbicacionSePersiste(){
        repositorioLocalizaciones.persistir(unDepartamento);
        repositorioLocalizaciones.persistir(unMunicipio);
        repositorioLocalizaciones.persistir(unaProvicia);
        Localizacion unaLocalizacion = repositorioLocalizaciones.localizacionSegunId(1);
        Assertions.assertEquals(1,unaLocalizacion.getId());
    }

    @Test
    public void traeLaUbicacionSegunID(){
        Localizacion unaLocalizacion = repositorioLocalizaciones.localizacionSegunId(1);
        Assertions.assertEquals(1, unaLocalizacion.getId());
    }

    @Test
    public void traeTodasLasUbicaciones(){
        List<Localizacion> localizaciones = repositorioLocalizaciones.localizaciones();
        Assertions.assertEquals(3, localizaciones.size());
    }

    @Test
    public void lasUbicacionesSeEliminan(){
        Localizacion localizacionAEliminar1 = repositorioLocalizaciones.localizacionSegunId(1);
        Localizacion localizacionAEliminar2 = repositorioLocalizaciones.localizacionSegunId(2);
        Localizacion localizacionAEliminar3 = repositorioLocalizaciones.localizacionSegunId(3);
        repositorioLocalizaciones.eliminar(localizacionAEliminar1);
        repositorioLocalizaciones.eliminar(localizacionAEliminar2);
        repositorioLocalizaciones.eliminar(localizacionAEliminar3);
        List<Localizacion> localizaciones = repositorioLocalizaciones.localizaciones();
        Assertions.assertEquals(0, localizaciones.size());
    }
}
