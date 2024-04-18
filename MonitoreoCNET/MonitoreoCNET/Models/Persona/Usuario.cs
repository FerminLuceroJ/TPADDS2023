using System;
using System.ComponentModel.DataAnnotations;
using System.ComponentModel.DataAnnotations.Schema;

namespace MonitoreoCNET.Models.Persona
{
    public enum Rol
    {
        MIEMBRO,
        ADMIN_COMUNIDAD,
        ADMIN_PLATAFORMA,
        RESPONSABLE
    }
    
    [Table("usuario")]
    public class Usuario
    {
        [Key]
        [Column("id")]
        [DatabaseGenerated(DatabaseGeneratedOption.Identity)]
        public int Id { get; set; }

        [Column("usuario")]
        public string UserName { get; set; }

        [Column("contrasenia")]
        public string Password { get; set; }

        [Column("rol")]
        public Rol Rol { get; set; }

        [Column("fecha_contraseña", TypeName = "date")]
        public DateTime FechaContrasenia { get; set; }

        [Column("mail")]
        public string CorreoElectronico { get; set; }

        public Usuario(string contrasenia, string usuario)
        {
            UserName = usuario;
            Password = contrasenia;
            FechaContrasenia = DateTime.Now;
        }

        private Usuario()
        {
        }
    }
}