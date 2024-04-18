using System;
using System.Collections.Generic;
using System.ComponentModel.DataAnnotations;
using System.ComponentModel.DataAnnotations.Schema;
using Dominio.Entidades.Ubicacion;
using MonitoreoCNET.Models.Comunidades;
using MonitoreoCNET.Models.Organizaciones;
using MonitoreoCNET.Models.Persona;
using MonitoreoCNET.Models.Servicios;

namespace MonitoreoCNET.Models.Incidentes
{
    public enum Estado
    {
        ABIERTO,
        CERRADO
    }
    
    [Table("incidente")]
    public class Incidente
    {
        [Key]
        [DatabaseGenerated(DatabaseGeneratedOption.Identity)]
        public int Id { get; set; }

        [ForeignKey("id_miembro")]
        public virtual Miembro Notificante { get; set; }

        [ForeignKey("id_prestacion_de_servicio")]
        public virtual PrestacionDeServicio ServicioAfectado { get; set; }

        [Column("fecha_alta", TypeName = "TIMESTAMP")]
        public DateTime FechaAlta { get; set; }

        [Column("descripcion", TypeName = "TEXT")]
        public string Descripcion { get; set; }

        [Column("estado")]
        public Estado Estado { get; set; }

        [ForeignKey("id_establecimiento")]
        public virtual Establecimiento EstablecimientoAfectado { get; set; }

        //[OneToMany(nameof(Observacion.Incidente))]
        public List<Observacion> Observaciones { get; set; }

        [ForeignKey("id_comunidad")]
        public Comunidad Comunidad { get; set; }

        public Incidente(Miembro notificante, PrestacionDeServicio servicioAfectado, Establecimiento establecimientoAfectado, string descripcion)
        {
            Notificante = notificante;
            ServicioAfectado = servicioAfectado;
            EstablecimientoAfectado = establecimientoAfectado;
            FechaAlta = DateTime.Now;
            Descripcion = descripcion;
            Estado = Estado.ABIERTO;
            Observaciones = new List<Observacion>();
        }

        public Incidente()
        {
            Observaciones = new List<Observacion>();
            Estado = Estado.ABIERTO;
        }

        public void AgregarObservacion(Observacion observacion)
        {
            Observaciones.Add(observacion);
            observacion.Incidente = this;
        }

        public void CerrarIncidente(Miembro notificante, string descripcion/*, Notificador notificador*/)
        {
            Estado = Estado.CERRADO;
            var observacion = new Observacion(notificante, descripcion);
            AgregarObservacion(observacion);
            
            /*
            foreach (var miembro in notificante.Comunidad.Miembros)
            {
                notificador.NotificarAMiembro(miembro, new CierreIncidente(), this);
            }*/
        }

        public DateTime? GetFechaDeCierre()
        {
            return Estado == Estado.CERRADO ? Observaciones[^1].Fecha : null;
        }

        public Localizacion GetLocalizacion()
        {
            return EstablecimientoAfectado.Localizacion;
        }
    }
}