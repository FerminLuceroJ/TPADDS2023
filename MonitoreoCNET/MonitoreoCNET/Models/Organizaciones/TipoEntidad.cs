using System.ComponentModel.DataAnnotations;
using System.ComponentModel.DataAnnotations.Schema;

namespace MonitoreoCNET.Models.Organizaciones
{
    [Table("tipo_entidad")]
    public class TipoEntidad
    {
        [Key]
        [DatabaseGenerated(DatabaseGeneratedOption.Identity)]
        public int Id { get; set; }

        [Column("nombre")]
        public string Nombre { get; set; }

        [Column("prefijo_entidad")]
        public string PrefijoEntidad { get; set; }

        [Column("prefijo_establecimiento")]
        public string PrefijoEstablecimiento { get; set; }

        public TipoEntidad(string nombre, string prefijoEntidad, string prefijoEstablecimiento)
        {
            Nombre = nombre;
            PrefijoEntidad = prefijoEntidad;
            PrefijoEstablecimiento = prefijoEstablecimiento;
        }

        public TipoEntidad()
        {
        }

        public string GetNombreCompletoEntidad(Entidad entidad)
        {
            return PrefijoEntidad + " " + entidad.Nombre;
        }

        public string GetNombreCompletoEstablecimiento(Establecimiento establecimiento)
        {
            return PrefijoEstablecimiento + " " + establecimiento.Nombre;
        }
    }
}