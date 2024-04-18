package controllers;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import io.javalin.http.Context;
import io.javalin.http.HttpStatus;
import models.dtos.servicioPropio.ComunidadFusionableDTO;
import models.dtos.servicioPropio.PropuestaDeFusionDTO;
import models.modulos.fusionador.ConverterDTOaEntidad;
import models.modulos.fusionador.PropuestaDeFusion;
import models.repositorios.RepositorioComunidades;
import models.repositorios.RepositorioPropuestasDeFusion;

import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.util.List;

public class FusionarComunidadesController {

    private static List<PropuestaDeFusionDTO> propuestas;
    private final ConverterDTOaEntidad converter;
    private final RepositorioPropuestasDeFusion repositorioPropuestasDeFusion;
    private final RepositorioComunidades repositorioComunidades;

    public FusionarComunidadesController(){
        this.repositorioPropuestasDeFusion = new RepositorioPropuestasDeFusion();
        this.repositorioComunidades = new RepositorioComunidades();
        this.converter = new ConverterDTOaEntidad();
    }

    public void obtenerPropuestasDeFusion(Context ctx) {
        ObjectMapper objectMapper = new ObjectMapper();
        objectMapper.registerModule(new JavaTimeModule());
        String posiblesFusionesEndpoint = "http://localhost:8081/posiblesfusiones";

        try {
            //solicitud HTTP get al servicio de fusión
            HttpClient httpClient = HttpClient.newHttpClient();
            HttpRequest request = HttpRequest.newBuilder()
                    .uri(URI.create(posiblesFusionesEndpoint))
                    .build();

            HttpResponse<String> response = httpClient.send(request, HttpResponse.BodyHandlers.ofString());

            if (response.statusCode() == HttpStatus.OK.getCode()) {
                String propuestasDeFusionJson = response.body();
                propuestas = objectMapper.readValue(propuestasDeFusionJson, new TypeReference<List<PropuestaDeFusionDTO>>() {});
                ctx.json(propuestas);
            } else {
                ctx.status(response.statusCode());
                ctx.result("Error al obtener las propuestas de fusión desde el servicio de fusión");
            }
        } catch (Exception e) {
            e.printStackTrace();
            ctx.status(HttpStatus.INTERNAL_SERVER_ERROR);
            ctx.result("Error interno al procesar la solicitud: " + e.getMessage());
        }
    }

    public void aceptarPropuestaDeFusion(Context ctx) {
        ObjectMapper objectMapper = new ObjectMapper();
        objectMapper.registerModule(new JavaTimeModule());
        String fusionarComunidadesEndpoint = "http://localhost:8081/fusionarcomunidades";

        try {
            int propuestaIndice = Integer.parseInt(ctx.pathParam("propuestaIndice"));

            PropuestaDeFusionDTO propuestaDTO = encontrarPropuestaPorIndice(propuestaIndice);

            if (propuestaDTO != null) {

                String propuestaJson = objectMapper.writeValueAsString(propuestaDTO);

                // Realiza solicitud HTTP post al servicio de fusión
                HttpClient httpClient = HttpClient.newHttpClient();
                HttpRequest request = HttpRequest.newBuilder()
                        .uri(URI.create(fusionarComunidadesEndpoint))
                        .header("Content-Type", "application/json")
                        .POST(HttpRequest.BodyPublishers.ofString(propuestaJson))
                        .build();

                HttpResponse<String> response = httpClient.send(request, HttpResponse.BodyHandlers.ofString());

                if (response.statusCode() == HttpStatus.OK.getCode()) {
                    ComunidadFusionableDTO comunidadFusionada = objectMapper.readValue(response.body(), ComunidadFusionableDTO.class);
                    repositorioComunidades.persistir(converter.convertirDTOaEntidad(comunidadFusionada));

                    PropuestaDeFusion propuesta = converter.convertirDTOaEntidad(propuestaDTO);

                    propuesta.getUnaComunidad().setEstaActiva(false);
                    propuesta.getOtraComunidad().setEstaActiva(false);
                    repositorioComunidades.modificar(propuesta.getUnaComunidad());
                    repositorioComunidades.modificar(propuesta.getOtraComunidad());

                    propuesta.setFueAceptada(true);
                    repositorioPropuestasDeFusion.persistir(propuesta);

                    ctx.status(HttpStatus.OK);
                    ctx.result("Propuesta de fusión aceptada con éxito.");
                } else {
                    ctx.status(response.statusCode());
                    ctx.result("Error al aceptar la propuesta de fusión desde el servicio externo");
                }
            } else {
                ctx.status(HttpStatus.NOT_FOUND);
                ctx.result("Propuesta de fusión no encontrada.");
            }
        } catch (Exception e) {
            e.printStackTrace();
            ctx.status(HttpStatus.INTERNAL_SERVER_ERROR);
            ctx.result("Error interno al procesar la solicitud: " + e.getMessage());
        }
    }

    public void rechazarPropuestaDeFusion(Context ctx) {
        try {
            int propuestaIndice = Integer.parseInt((ctx.pathParam("propuestaIndice")));

            PropuestaDeFusionDTO propuestaDTO = encontrarPropuestaPorIndice(propuestaIndice);

            if (propuestaDTO != null) {

                propuestaDTO.setFueAceptada(false);
                repositorioPropuestasDeFusion.persistir(converter.convertirDTOaEntidad(propuestaDTO));

                ctx.status(HttpStatus.OK);
                ctx.result("Propuesta de fusión rechazada con éxito.");
            } else {
                ctx.status(HttpStatus.NOT_FOUND);
                ctx.result("Propuesta de fusión no encontrada.");
            }
        } catch (Exception e) {
            e.printStackTrace();
            ctx.status(HttpStatus.INTERNAL_SERVER_ERROR);
            ctx.result("Error interno al procesar la solicitud: " + e.getMessage());
        }
    }

    private PropuestaDeFusionDTO encontrarPropuestaPorIndice(int propuestaIndice) {
        return propuestas.get(propuestaIndice);
    }
}


