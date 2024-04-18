
using System.ComponentModel.DataAnnotations;
using System.ComponentModel.DataAnnotations.Schema;
using Dominio.Entidades.Ubicacion;
using MonitoreoCNET.Models.Comunidades;

namespace MonitoreoCNET.Models.Persona
{
    public enum TipoMiembro
    {
        OBSERVADOR,
        AFECTADO
    }

    public enum MedioDeNotificacion
    {
        MAIL,
        WHATSAPP
    }

    public enum EstrategiaDeNotificacion
    {
        CUANDOSUCEDEN,
        SINAPUROS
    }

[Table("miembro")]
    public class Miembro
    {
        [Key]
        [DatabaseGenerated(DatabaseGeneratedOption.Identity)]
        public int Id { get; set; }

        [Column("nombre")]
        public string Nombre { get; set; }

        [Column("apellido")]
        public string Apellido { get; set; }

        //[OneToOne]
        public virtual Usuario Usuario { get; set; }

        [ForeignKey("id_localizacion")]
        public virtual Localizacion LocalizacionDeInteres { get; set; }

        //[OneToMany(nameof(Interes.Miembro))]
        public virtual List<Interes> Intereses { get; set; }

        [ForeignKey("id_comunidad")]
        public virtual Comunidad Comunidad { get; set; }

        [Column("medio_notificacion")]
        public MedioDeNotificacion MedioDeNotificacion { get; set; }

        [Column("estrategia_notificacion")]
        public EstrategiaDeNotificacion EstrategiaDeNotificacion { get; set; }

        [Column("telefono")]
        public string Telefono { get; set; }

        [Column("tipo_miembro")]
        public TipoMiembro Tipo { get; set; }

        public Miembro(string nombre, string apellido, Localizacion localizacion)
        {
            Nombre = nombre;
            Apellido = apellido;
            LocalizacionDeInteres = localizacion;
            Intereses = new List<Interes>();
        }

        public Miembro()
        {
            Intereses = new List<Interes>();
        }

        public void AgregarInteres(Interes interes)
        {
            Intereses.Add(interes);
            interes.Miembro = this;
        }

        public void RemoverInteres(Interes interes)
        {
            Intereses.Remove(interes);
        }
        /*
        public bool EstaCercaDeIncidente(Localizacion localizacion)
        {
            if ((localizacion.EsProvincia && LocalizacionDeInteres.EsProvincia) || (!localizacion.EsProvincia && !LocalizacionDeInteres.EsProvincia))
            {
                return LocalizacionDeInteres.Id == localizacion.Id;
            }
            else if (localizacion.EsProvincia)
            {
                return LocalizacionDeInteres.Provincia.Id == localizacion.Id;
            }
            else
            {
                return LocalizacionDeInteres.Id == localizacion.Provincia.Id;
            }
        }*/

        public void SetLocalizacionDeInteres(Localizacion localizacion/*, Notificador notificador*/)
        {
            LocalizacionDeInteres = localizacion;

            if (Comunidad == null)
                return;
            /*
            foreach (var incidente in Comunidad.IncidentesSegunEstado(Estado.ABIERTO))
            {
                if (EstaCercaDeIncidente(incidente.Localizacion))
                {
                    notificador.NotificarAMiembro(this, new RevisionIncidente(), incidente);
                }
            }*/
        }
    }
}