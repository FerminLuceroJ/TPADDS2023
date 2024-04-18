using System.ComponentModel.DataAnnotations;
using System.ComponentModel.DataAnnotations.Schema;
using MonitoreoCNET.Models.Organizaciones;
using MonitoreoCNET.Models.Servicios;

namespace MonitoreoCNET.Models.Servicios
{
    [Table("prestacion_de_servicio")]
    public class PrestacionDeServicio
    {
        [Key]
        [DatabaseGenerated(DatabaseGeneratedOption.Identity)]
        public int Id { get; set; }

        [ForeignKey("id_servicio")]
        public virtual Servicio Servicio { get; set; }

        [ForeignKey("id_establecimiento")]
        public virtual Establecimiento Establecimiento { get; set; }

        public PrestacionDeServicio(Servicio servicio, Establecimiento establecimiento)
        {
            Servicio = servicio;
            Establecimiento = establecimiento;
        }

        public PrestacionDeServicio()
        {
        }
    }
}