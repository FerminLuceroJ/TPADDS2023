// /Data/ApplicationDbContext.cs

using Microsoft.EntityFrameworkCore;
using MonitoreoCNET.Models.Incidentes;
using System.Collections.Generic;

namespace MonitoreoCNET.Data
{
    public class ApplicationDbContext : DbContext
    {
        public DbSet<Incidente> Incidentes { get; set; }
        // Otros DbSet para otras entidades

        public ApplicationDbContext(DbContextOptions<ApplicationDbContext> options)
            : base(options)
        {
        }

        // Otros métodos y configuraciones específicos del contexto
    }
}
