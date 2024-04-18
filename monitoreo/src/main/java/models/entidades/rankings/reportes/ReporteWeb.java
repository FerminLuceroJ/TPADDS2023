package models.entidades.rankings.reportes;

import models.entidades.rankings.ValueObjects.Registro;

import java.util.ArrayList;
import java.util.List;

public class ReporteWeb {
    List<Registro> registro;

    public ReporteWeb() {
        this.registro = new ArrayList<>();
    }

    public List<Registro> getRegistro() {
        return registro;
    }

    public void addRegistro(Registro registro){
        this.registro.add(registro);
    }
}
