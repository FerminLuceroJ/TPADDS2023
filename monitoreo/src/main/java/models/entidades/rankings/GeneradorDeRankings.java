package models.entidades.rankings;

import models.entidades.incidente.Estado;
import models.entidades.incidente.Incidente;
import models.entidades.organizaciones.entidad.Entidad;
import models.entidades.rankings.ValueObjects.*;
import models.entidades.rankings.reportes.Reporte;
import models.entidades.rankings.reportes.ReporteWeb;
import models.modulos.calculadorDeTiempos.CalculadorDeTiempos;
import models.modulos.exportador.Exportador;
import models.repositorios.RepositorioEntidades;
import models.repositorios.RepositorioIncidentes;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.Objects;

public class GeneradorDeRankings {

  private RepositorioIncidentes repositorioIncidentes;
  private RepositorioEntidades repositorioEntidades;

  public GeneradorDeRankings() {
    this.repositorioEntidades = new models.repositorios.RepositorioEntidades();
    this.repositorioIncidentes = new models.repositorios.RepositorioIncidentes();
  }

  private Double calcularPromediosDeHorarios(List<Long> horarios){
    Double suma = 0.0;
    Double promedio;
    for(Long horario : horarios){
      suma += horario;
    }
    promedio = suma/horarios.size();
    return promedio;
  }

  private List<Long> horariosDeIncidentesCerradosEnEntidad(Entidad entidad){
    return repositorioIncidentes.
        incidentes()
        .stream().filter(incidente -> incidente.getEstado() == Estado.CERRADO).toList()
        .stream().filter(incidente -> Objects.equals(incidente.getEstablecimientoAfectado().getNombreEntidad(), entidad.getNombre())).toList()
        .stream()
        .map(incidente -> CalculadorDeTiempos.calcularTiempoEntreFechas(incidente.getFechaAlta(), incidente.getFechaDeCierre()).toHours())
        .toList();
  }

  public List<PromedioDuracionIncidente> generarPromedioDuracionIncidenteDeMayorPromedioDeCierre(){

    List<Entidad> entidades = repositorioEntidades.entidades();

    List<PromedioDuracionIncidente> promediosPorEntidad = new ArrayList<>();

    for (Entidad entidad : entidades) {
      List<Long> horariosDeEntidad = this.horariosDeIncidentesCerradosEnEntidad(entidad);

      Double promedioDeEntidad = this.calcularPromediosDeHorarios(horariosDeEntidad);

      PromedioDuracionIncidente promedioDeIncidentesParaEntidad = new PromedioDuracionIncidente(entidad, promedioDeEntidad);
      promediosPorEntidad.add(promedioDeIncidentesParaEntidad);
    }
    promediosPorEntidad.sort(new Comparator<PromedioDuracionIncidente>() {
      @Override
      public int compare(PromedioDuracionIncidente promedio1, PromedioDuracionIncidente promedio2) {
        return Double.compare(promedio2.getPromedio(), promedio1.getPromedio());
      }
    });

    return promediosPorEntidad;
  }

  public void exportarRankingDeMayorPromedioDeCierre(Exportador exportador, String formato) {

    List<PromedioDuracionIncidente> promediosPorEntidad = generarPromedioDuracionIncidenteDeMayorPromedioDeCierre();

    Reporte reporte = new Reporte();

    for (PromedioDuracionIncidente promedio : promediosPorEntidad) {
      reporte.agregarDato("El promedio de cierre de incidentes en " + promedio.getEntidad().getNombre() + " fue de: " + promedio.getPromedio() + " horas");
    }

    exportador.exportar(reporte, formato, "RankingDeMayorPromedioDeCierre");
  }

  public ReporteWeb generarRankingDeMayorPromedioDeCierreWeb(){

    ReporteWeb reporteWeb = new ReporteWeb();

    List<PromedioDuracionIncidente> promediosPorEntidad = generarPromedioDuracionIncidenteDeMayorPromedioDeCierre();

    for (PromedioDuracionIncidente promedio : promediosPorEntidad) {
      Registro registro = new Registro();
      registro.setNombreEntidad(promedio.getEntidad().getNombre());
      registro.setValor(promedio.getPromedio().toString());
      reporteWeb.addRegistro(registro);
    }

    return  reporteWeb;

  }

  public List<IncidentePorEntidad> generarIncidentesPorEntidad(){
    List<Entidad> entidades = repositorioEntidades.entidades();
    List<IncidentePorEntidad> incidentesPorEntidad = new ArrayList<>();

    for(Entidad entidad : entidades){
      List<Incidente> incidentesEnEntidad = repositorioIncidentes.incidentes()
          .stream().filter(incidente ->
              Objects.equals(incidente.getEstablecimientoAfectado().getNombreEntidad(), entidad.getNombre())).toList();
      List<Incidente> incidentesEnEntidadDeLaSemana = incidentesEnEntidad.stream().filter(i -> CalculadorDeTiempos.estaEnSemanaActual(i.getFechaAlta())).toList();

      IncidentePorEntidad incidentesDeEntidad = new IncidentePorEntidad(entidad, incidentesEnEntidadDeLaSemana.size());

      incidentesPorEntidad.add(incidentesDeEntidad);
    }

    incidentesPorEntidad.sort(new Comparator<IncidentePorEntidad>() {
      @Override
      public int compare(IncidentePorEntidad incidentesPorEntidad1, IncidentePorEntidad incidentesPorEntidad2) {
        return Integer.compare(incidentesPorEntidad2.getCantidadDeIncidentes(), incidentesPorEntidad1.getCantidadDeIncidentes());
      }
    });

    return incidentesPorEntidad;
  }

  public void exportarRankingDeMayorCantidadDeIncidentesEnSemana(Exportador exportador, String formato){
    Reporte reporte = new Reporte();

    List<IncidentePorEntidad> incidentesPorEntidad = generarIncidentesPorEntidad();

    for(IncidentePorEntidad incidentes: incidentesPorEntidad){
      reporte.agregarDato("La cantidad de incidentes reportados en " + incidentes.getEntidad().getNombre() + " fue de: " + incidentes.getCantidadDeIncidentes());
    }

    exportador.exportar(reporte, formato, "cantidadDeIncidentesDeSemana");
  }

  public ReporteWeb generarRankingDeMayorCantidadDeIncidentesEnSemanaWeb(){

    ReporteWeb reporte = new ReporteWeb();

    List<IncidentePorEntidad> incidentesPorEntidad = generarIncidentesPorEntidad();

    for(IncidentePorEntidad incidentes: incidentesPorEntidad){

      Registro registro = new Registro();

      registro.setNombreEntidad(incidentes.getEntidad().getNombre());
      registro.setValor(incidentes.getCantidadDeIncidentes().toString());

     reporte.addRegistro(registro);
    }

    return reporte;

  }

  public void exportarRankingMayorCantidadDeIncidentesEnSemana(Exportador exportador, String formato) {

    Reporte reporte = new Reporte();

    List<Entidad> entidades = repositorioEntidades.entidades();
    List<DatosParaRankingDos> datosParaRankingDos = new ArrayList<>();

    for (Entidad entidad : entidades) {

      List<Incidente> incidentes = repositorioIncidentes.incidentes().stream().filter(incidente ->
          Objects.equals(incidente.getEstablecimientoAfectado().getNombreEntidad(), entidad.getNombre())).toList().
          stream().filter(incidente -> CalculadorDeTiempos.estaEnSemanaActual(incidente.getFechaAlta())).toList();
      List<IncidenteRepetidoPorFecha> incidentesRepetidos = new ArrayList<>();

      int suma = 0;
      for (Incidente incidente : incidentes) {
        if (incidentesRepetidos.size() == 0) {
          IncidenteRepetidoPorFecha incidenteRepetidoPorFecha = new IncidenteRepetidoPorFecha(incidente, incidente.getFechaAlta());
          incidentesRepetidos.add(incidenteRepetidoPorFecha);
        }
        if (elIncidenteFueRegistrado(incidentesRepetidos, incidente)) {
          continue;
        } else {
          IncidenteRepetidoPorFecha incidenteRepetidoPorFecha = new IncidenteRepetidoPorFecha(incidente, incidente.getFechaAlta());
          incidentesRepetidos.add(incidenteRepetidoPorFecha);
        }
      }

      suma = incidentesRepetidos.size();
      DatosParaRankingDos datos = new DatosParaRankingDos(entidad, suma);
      datosParaRankingDos.add(datos);
    }

    datosParaRankingDos.sort(new Comparator<DatosParaRankingDos>() {
      @Override
      public int compare(DatosParaRankingDos datos1, DatosParaRankingDos datos2) {
        return Integer.compare(datos2.getSuma(), datos1.getSuma());
      }
    });

    for(DatosParaRankingDos datos: datosParaRankingDos){
      reporte.agregarDato(datos.getDatos());
    }

    exportador.exportar(reporte, formato, "cantidadDeIncidentesDeSemana");
  }

  private Boolean elIncidenteFueRegistrado(List<IncidenteRepetidoPorFecha> incidentesRepetidosPorFecha, Incidente incidente){
    return incidentesRepetidosPorFecha.stream().
        anyMatch((incidenteRepetidoPorFecha ->
            incidenteRepetidoPorFecha.getIncidente().getServicioAfectado().getServicio().getNombre().equals(incidente.getServicioAfectado().getServicio().getNombre())
                &&
                incidenteRepetidoPorFecha.getFecha().toLocalDate().equals(incidente.getFechaAlta().toLocalDate())));
  }
}
