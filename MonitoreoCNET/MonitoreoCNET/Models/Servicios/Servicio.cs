using System.ComponentModel.DataAnnotations;
using System.ComponentModel.DataAnnotations.Schema;

namespace MonitoreoCNET.Models.Servicios
{
    [Table("servicio")]
    public class Servicio
    {
        [Key]
        [DatabaseGenerated(DatabaseGeneratedOption.Identity)]
        public int Id { get; set; }

        [Column("nombre")]
        public string Nombre { get; set; }

        public Servicio(string nombre)
        {
            Nombre = nombre;
        }

        public Servicio()
        {
        }
    }
}