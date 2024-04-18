package models.modulos.fusionador;

import models.dtos.servicioPropio.ComunidadFusionableDTO;
import models.dtos.servicioPropio.PrestacionDeServicioFusionableDTO;
import models.dtos.servicioPropio.PropuestaDeFusionDTO;
import models.entidades.comunidad.Comunidad;
import models.entidades.servicios.servicio.PrestacionDeServicio;
import models.repositorios.RepositorioComunidades;
import models.repositorios.RepositorioPrestacionesDeServicios;

import java.util.ArrayList;
import java.util.List;

public class ConverterDTOaEntidad {
    public PropuestaDeFusion convertirDTOaEntidad(PropuestaDeFusionDTO propuestaDTO) {
        RepositorioComunidades repositorioComunidades = new RepositorioComunidades();
        Comunidad unaComunidad = repositorioComunidades.comunidadSegunId(propuestaDTO.getUnaComunidad().getId());
        Comunidad otraComunidad = repositorioComunidades.comunidadSegunId(propuestaDTO.getOtraComunidad().getId());

        return new PropuestaDeFusion(
                unaComunidad,
                otraComunidad,
                propuestaDTO.getFechaDePropuesta(),
                propuestaDTO.getFueAceptada()
        );
    }

    public Comunidad convertirDTOaEntidad(ComunidadFusionableDTO comunidadDTO){
        List<PrestacionDeServicio> serviciosParticularesObservados = convertirDTOaEntidad(comunidadDTO.getServiciosParticularesObservados());

        return new Comunidad(
                comunidadDTO.getNombre(),
                comunidadDTO.getGradoDeConfianza(),
                serviciosParticularesObservados
        );
    }

    public List<PrestacionDeServicio> convertirDTOaEntidad(List<PrestacionDeServicioFusionableDTO> prestacionesDTO){
        List<PrestacionDeServicio> prestaciones = new ArrayList<>();

        for (PrestacionDeServicioFusionableDTO prestacionDTO : prestacionesDTO) {

            RepositorioPrestacionesDeServicios repositorioPrestaciones = new RepositorioPrestacionesDeServicios();
            PrestacionDeServicio prestacion = repositorioPrestaciones.prestacionDeServicioSegunID(prestacionDTO.getId());

            if (prestacion != null) {
                prestaciones.add(prestacion);
            }
        }
        return prestaciones;
    }
}
