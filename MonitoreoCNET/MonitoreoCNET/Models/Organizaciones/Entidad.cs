using System.Collections.Generic;
using System.ComponentModel.DataAnnotations;
using System.ComponentModel.DataAnnotations.Schema;

namespace MonitoreoCNET.Models.Organizaciones
{
    
    [Table("entidad")]
    public class Entidad
    {
        [Key]
        [DatabaseGenerated(DatabaseGeneratedOption.Identity)]
        public int Id { get; set; }

        [Column("nombre")]
        public string Nombre { get; set; }

        //[OneToMany(nameof(Establecimiento.Entidad))]
        public virtual List<Establecimiento> Establecimientos { get; set; }

        [ForeignKey("id_tipo_entidad")]
        public virtual TipoEntidad Tipo { get; set; }

        public Entidad(string nombre, TipoEntidad tipo)
        {
            Nombre = nombre;
            Establecimientos = new List<Establecimiento>();
            Tipo = tipo;
        }

        public Entidad()
        {
            Establecimientos = new List<Establecimiento>();
        }

        public void AgregarEstablecimiento(Establecimiento establecimiento)
        {
            Establecimientos.Add(establecimiento);
            establecimiento.Entidad = this;
        }

        public void RemoverEstablecimiento(Establecimiento establecimiento)
        {
            Establecimientos.Remove(establecimiento);
        }

        public string GetNombreCompleto()
        {
            return Tipo.GetNombreCompletoEntidad(this);
        }
    }
}