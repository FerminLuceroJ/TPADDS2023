using System.ComponentModel.DataAnnotations;
using System.ComponentModel.DataAnnotations.Schema;
using MonitoreoCNET.Models.Organizaciones;
using MonitoreoCNET.Models.Servicios;

namespace MonitoreoCNET.Models.Persona
{
    [Table("interes")]
    public class Interes
    {
        [Key]
        [DatabaseGenerated(DatabaseGeneratedOption.Identity)]
        public int Id { get; set; }

        [ForeignKey("id_entidad")]
        public virtual Entidad Entidad { get; set; }

        [ForeignKey("id_servicio")]
        public virtual Servicio Servicio { get; set; }

        [ForeignKey("id_miembro")]
        public virtual Miembro Miembro { get; set; }

        public Interes()
        {
        }
    }
}