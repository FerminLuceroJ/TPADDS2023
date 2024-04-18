package models.entidades.persona.autentificacion;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.time.LocalDate;

@Entity
@Table(name="usuario")
public class Usuario {

  @Getter @Setter
  @Column(name = "id")
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private int id;

  @Getter @Setter
  @Column(name = "usuario")
  private String usuario;

  @Setter @Getter
  @Column(name = "contrasenia")
  private String contrasenia;

  @Getter@Setter
  @Column(name = "rol")
  @Enumerated(EnumType.STRING)
  private Rol rol;

  @Setter @Getter
  @Column(name="fecha_contraseña",columnDefinition = "date")
  private LocalDate fechaContrasenia;

  @Setter @Getter
  @Column(name = "mail")
  private String correoElectronico;

  public Usuario(String contrasenia, String correoElectronico)
  {
    this.setCorreoElectronico(correoElectronico);
    this.setContrasenia(contrasenia);
    this.setFechaContrasenia(LocalDate.now());
  }

  public Usuario(){}
}
