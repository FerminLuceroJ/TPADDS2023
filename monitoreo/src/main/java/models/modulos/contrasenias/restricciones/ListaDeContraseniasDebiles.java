package models.modulos.contrasenias.restricciones;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

import models.entidades.persona.autentificacion.Usuario;
import models.modulos.contrasenias.excepciones.NoSePudoCargarArchivoException;


public class ListaDeContraseniasDebiles implements Restriccion {
  private static final String nombreDeArchivo = "ListaPeoresContrasenias.txt";
  private final List<String> peoresContrasenias;

  @Override
  public boolean esValidaContrasenia(Usuario usuario) {
    return !this.peoresContrasenias.contains(usuario.getContrasenia());
  }

  @Override
  public String getMensajeError() {
    return "La contraseña se encuentra en la lista de las 'Diez mil peores contraseñas'.";
  }

  public ListaDeContraseniasDebiles() {
    this.peoresContrasenias = new ArrayList<String>();
    this.cargarPeoresContraseniasDesdeArchivo();
  }

  // Quizá estaria bueno tener una clase constructor para que esta logica no esté acá
  private void cargarPeoresContraseniasDesdeArchivo() {
    try {
      InputStream inputStream = getClass().getClassLoader().getResourceAsStream(nombreDeArchivo);
      BufferedReader lector = new BufferedReader(new InputStreamReader(inputStream));
      String linea;
      while ((linea = lector.readLine()) != null) {
        this.peoresContrasenias.add(linea.trim());
      }
      lector.close();
    } catch (IOException e) {
        throw new NoSePudoCargarArchivoException("Error al cargar las peores contraseñas desde el archivo");
      }
  }

}
