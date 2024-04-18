package controllers;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import io.javalin.http.Context;
import io.javalin.http.HttpStatus;
import models.dtos.servicio3.*;
import models.dtos.servicioPropio.ComunidadFusionableDTO;
import models.dtos.servicioPropio.PropuestaDeFusionDTO;
import models.entidades.comunidad.Comunidad;
import models.entidades.organizaciones.entidad.Entidad;
import models.repositorios.RepositorioComunidades;
import models.repositorios.RepositorioEntidades;
import models.repositorios.RepositorioIncidentes;
import org.apache.poi.ss.formula.functions.Rank;

import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.util.List;

public class RankingExternoController {

  private RepositorioComunidades repositorioComunidades;

  private RepositorioIncidentes repositorioIncidentes;

  private RepositorioEntidades repositorioEntidades;

  public RankingExternoController(){
    this.repositorioEntidades = new RepositorioEntidades();
    this.repositorioComunidades = new RepositorioComunidades();
    this.repositorioIncidentes = new RepositorioIncidentes();
  }

  public void obtenerSolicitudDeRanking(Context ctx) throws Exception {
    ConverterEntidadADTO converter = new ConverterEntidadADTO();
    try {
      List<Comunidad> comunidades = repositorioComunidades.comunidadesSegunEstado(true);
      List<ComunidadParaRankingDTO> comunidadesParaRankingDTO = converter.convertirAComunidadParaRankingDTO(comunidades);

      List<Entidad> entidades = repositorioEntidades.entidades();
      List<EntidadParaRankingDTO> entidadesParaRankingDTO = converter.convertirAEntidadesParaRankingDTO(entidades);

      RankingDTO rankingDTORequest = new RankingDTO(1F, comunidadesParaRankingDTO, entidadesParaRankingDTO);
      ctx.json(rankingDTORequest);
    } catch (Exception e) {
      ctx.status(500); // error interno del servidor
      ctx.result("Error al obtener la solicitud para la request" + e.getMessage());
      e.printStackTrace();
    }
  }

  public void obtenerRankingExterno(Context ctx){
    ObjectMapper objectMapper = new ObjectMapper();
    objectMapper.registerModule(new JavaTimeModule());
    String generarRankingEndpoint = "http://localhost:8081/rankings/ranking";

    ConverterEntidadADTO converter = new ConverterEntidadADTO();

    try {
      List<Comunidad> comunidades = repositorioComunidades.comunidadesSegunEstado(true);
      List<ComunidadParaRankingDTO> comunidadesParaRankingDTO = converter.convertirAComunidadParaRankingDTO(comunidades);

      List<Entidad> entidades = repositorioEntidades.entidades();
      List<EntidadParaRankingDTO> entidadesParaRankingDTO = converter.convertirAEntidadesParaRankingDTO(entidades);

      RankingDTO rankingDTORequest = new RankingDTO(1F, comunidadesParaRankingDTO, entidadesParaRankingDTO);

      String requestValue = objectMapper.writeValueAsString(rankingDTORequest);

      HttpClient httpClient = HttpClient.newHttpClient();
      HttpRequest request = HttpRequest.newBuilder()
          .uri(URI.create(generarRankingEndpoint))
          .header("Content-Type", "application/json")
          .POST(HttpRequest.BodyPublishers.ofString(requestValue))
          .build();


      HttpResponse<String> response = httpClient.send(request, HttpResponse.BodyHandlers.ofString());

      if (response.statusCode() == HttpStatus.OK.getCode()) {
        String rankingRespuesta = response.body();
        RankingGenerado ranking = objectMapper.readValue(rankingRespuesta, new TypeReference<RankingGenerado>() {});
        System.out.println(ranking);
        ctx.json(ranking);
      } else {
        ctx.status(response.statusCode());
        ctx.result("Error al obtener el ranking generado por el servicio del grupo 12");
      }
    } catch (Exception e) {
      e.printStackTrace();
      ctx.status(HttpStatus.INTERNAL_SERVER_ERROR);
      ctx.result("Error interno al procesar la solicitud: " + e.getMessage());
    }

  }
}
