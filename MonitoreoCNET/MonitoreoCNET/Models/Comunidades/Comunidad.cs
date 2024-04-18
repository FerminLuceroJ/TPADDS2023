using System.Collections.Generic;
using System.ComponentModel.DataAnnotations;
using System.ComponentModel.DataAnnotations.Schema;
using MonitoreoCNET.Models.Incidentes;
using MonitoreoCNET.Models.Persona;
using MonitoreoCNET.Models.Servicios;


namespace MonitoreoCNET.Models.Comunidades
{
    [Table("comunidad")]
    public class Comunidad
    {
        [Key]
        [DatabaseGenerated(DatabaseGeneratedOption.Identity)]
        public int Id { get; set; }

        [Column("nombre")]
        public string Nombre { get; set; }

        //[OneToMany(nameof(Miembro.Comunidad))]
        public virtual List<Miembro> Miembros { get; set; }

//[OneToMany(nameof(Incidente.Comunidad))]
        public virtual List<Incidente> IncidentesReportados { get; set; }

        [Column("gradoDeConfianza")]
        public double GradoDeConfianza { get; set; }

        //[ManyToMany]
        public virtual List<PrestacionDeServicio> ServiciosParticularesObservados { get; set; }

        public Comunidad(string nombre)
        {
            Nombre = nombre;
            Miembros = new List<Miembro>();
            IncidentesReportados = new List<Incidente>();
            ServiciosParticularesObservados = new List<PrestacionDeServicio>();
        }

        public Comunidad()
        {
            Miembros = new List<Miembro>();
            IncidentesReportados = new List<Incidente>();
            ServiciosParticularesObservados = new List<PrestacionDeServicio>();
        }

        public void AgregarMiembro(Miembro miembro)
        {
            Miembros.Add(miembro);
            miembro.Comunidad = this;
        }

        public void RemoverMiembro(Miembro miembro)
        {
            Miembros.Remove(miembro);
        }

        public void AgregarIncidenteReportado(Incidente incidente/* Notificador notificador*/)
        {
            IncidentesReportados.Add(incidente);
            incidente.Comunidad = this;
            /*
            Incidente.Incidente nuevoIncidente = new Incidente.Incidente();
            foreach (var miembro in Miembros)
            {
                notificador.NotificarAMiembro(miembro, nuevoIncidente, incidente);
            }+
            */
        }

        public List<Incidente> IncidentesSegunEstado(Estado estado)
        {
            return IncidentesReportados.FindAll(incidente => estado == incidente.Estado);
        }

        public void AgregarServicioObservado(PrestacionDeServicio prestacionDeServicio)
        {
            ServiciosParticularesObservados.Add(prestacionDeServicio);
        }
    }
}