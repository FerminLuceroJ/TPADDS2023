package controllers;

import io.javalin.http.Context;
import models.dtos.servicioPropio.ComunidadFusionableDTO;
import models.dtos.servicioPropio.EstablecimientoFusionableDTO;
import models.dtos.servicioPropio.PrestacionDeServicioFusionableDTO;
import models.dtos.servicioPropio.ServicioFusionableDTO;
import models.entidades.comunidad.Comunidad;
import models.entidades.organizaciones.Establecimiento;
import models.entidades.servicios.servicio.PrestacionDeServicio;
import models.entidades.servicios.servicio.Servicio;
import models.repositorios.RepositorioComunidades;

import java.util.ArrayList;
import java.util.List;

public class ComunidadController {
    private RepositorioComunidades repositorioComunidades;

    public ComunidadController(){
        this.repositorioComunidades = new RepositorioComunidades();
    }

    public void obtenerComunidadesParaFusion(Context ctx) throws Exception {
        try {
            List<Comunidad> comunidades = repositorioComunidades.comunidadesSegunEstado(true);
            List<ComunidadFusionableDTO> comunidadesFusionablesDTO = convertirAComunidadesFusionableDTO(comunidades);
            ctx.json(comunidadesFusionablesDTO);
        } catch (Exception e) {
            ctx.status(500); // error interno del servidor
            ctx.result("Error al obtener las comunidades para propuestas de fusión" + e.getMessage());
            e.printStackTrace();
        }
    }

    public List<ComunidadFusionableDTO> convertirAComunidadesFusionableDTO(List<Comunidad> comunidades) {
        List<ComunidadFusionableDTO> comunidadesFusionablesDTOS = new ArrayList<>();

        for (Comunidad comunidad : comunidades) {
            ComunidadFusionableDTO comunidadFusionableDTO = new ComunidadFusionableDTO(
                    comunidad.getId(),
                    comunidad.getNombre(),
                    convertirAPrestacionesDeServicioFusionableDTO(comunidad.getServiciosParticularesObservados()),
                    comunidad.getGradoDeConfianza()
            );

            comunidadesFusionablesDTOS.add(comunidadFusionableDTO);
        }

        return comunidadesFusionablesDTOS;
    }

    public List<PrestacionDeServicioFusionableDTO> convertirAPrestacionesDeServicioFusionableDTO(List<PrestacionDeServicio> prestaciones){
        List<PrestacionDeServicioFusionableDTO> prestacionesFusionablesDTOS = new ArrayList<>();

        for(PrestacionDeServicio prestacionDeServicio : prestaciones){
            PrestacionDeServicioFusionableDTO prestacionDeServicioFusionableDTO = new PrestacionDeServicioFusionableDTO(
                    prestacionDeServicio.getId(),
                    convertirAServicioFusionableDTO(prestacionDeServicio.getServicio()),
                    convertirAEstablecimientoFusionableDTO(prestacionDeServicio.getEstablecimiento())
            );

            prestacionesFusionablesDTOS.add(prestacionDeServicioFusionableDTO);
        }

        return prestacionesFusionablesDTOS;
    }

    private EstablecimientoFusionableDTO convertirAEstablecimientoFusionableDTO(Establecimiento establecimiento) {
        return new EstablecimientoFusionableDTO(establecimiento.getId(), establecimiento.getNombre());
    }

    private ServicioFusionableDTO convertirAServicioFusionableDTO(Servicio servicio) {
        return new ServicioFusionableDTO(servicio.getId(), servicio.getNombre());
    }


}
