using System.Collections.Generic;
using System.ComponentModel.DataAnnotations;
using System.ComponentModel.DataAnnotations.Schema;
using Dominio.Entidades.Ubicacion;
using MonitoreoCNET.Models.Servicios;

namespace MonitoreoCNET.Models.Organizaciones
{
    [Table("establecimiento")]
    public class Establecimiento
    {
        [Key]
        [DatabaseGenerated(DatabaseGeneratedOption.Identity)]
        public int Id { get; set; }

        [Column("nombre")]
        public string Nombre { get; set; }

        [ForeignKey("id_localizacion")]
        public virtual Localizacion Localizacion { get; set; }

        //[OneToMany(nameof(PrestacionDeServicio.Establecimiento))]
        public virtual List<PrestacionDeServicio> ServiciosPrestados { get; set; }

        [ForeignKey("id_entidad")]
        public virtual Entidad Entidad { get; set; }

        public Establecimiento(string nombre, Localizacion localizacion, Entidad entidad)
        {
            Nombre = nombre;
            Localizacion = localizacion;
            ServiciosPrestados = new List<PrestacionDeServicio>();
            Entidad = entidad;
        }

        public Establecimiento()
        {
            ServiciosPrestados = new List<PrestacionDeServicio>();
        }

        public void AgregarPrestacionDeServicio(PrestacionDeServicio servicioPrestado)
        {
            ServiciosPrestados.Add(servicioPrestado);
            servicioPrestado.Establecimiento = this;
        }

        public void RemoverPrestacionDeServicio(PrestacionDeServicio servicioPrestado)
        {
            ServiciosPrestados.Remove(servicioPrestado);
        }

        public string GetNombreEntidad()
        {
            return Entidad.Nombre;
        }

        public string GetNombreCompleto()
        {
            return Entidad.GetNombreCompleto();
        }
    }
}