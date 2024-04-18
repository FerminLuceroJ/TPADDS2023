using System;
using System.ComponentModel.DataAnnotations;
using System.ComponentModel.DataAnnotations.Schema;
using MonitoreoCNET.Models.Persona;

namespace MonitoreoCNET.Models.Incidentes
{
    [Table("observacion")]
    public class Observacion
    {
        [Key]
        [DatabaseGenerated(DatabaseGeneratedOption.Identity)]
        public int Id { get; set; }

        [ForeignKey("id_miembro")]
        public virtual Miembro Notificante { get; set; }

        [Column("descripcion", TypeName = "TEXT")]
        public string Descripcion { get; set; }

        [Column("fecha", TypeName = "TIMESTAMP")]
        public DateTime Fecha { get; set; }

        [ForeignKey("id_incidente")]
        public Incidente Incidente { get; set; }

        public Observacion(Miembro notificante, string descripcion)
        {
            Notificante = notificante;
            Descripcion = descripcion;
            Fecha = DateTime.Now;
        }

        public Observacion()
        {
            Fecha = DateTime.Now;
        }
    }
}