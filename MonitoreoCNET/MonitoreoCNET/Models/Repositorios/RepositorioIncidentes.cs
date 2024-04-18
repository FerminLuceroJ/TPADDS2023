// /Models/Repositorios/RepositorioIncidentes.cs

using System.Collections.Generic;
using System.Linq;
using MonitoreoCNET.Data;
using MonitoreoCNET.Models.Incidentes;

namespace MonitoreoCNET.Models.Repositorios
{
    public class RepositorioIncidentes
    {
        private readonly ApplicationDbContext _contexto;

        public RepositorioIncidentes(ApplicationDbContext contexto)
        {
            _contexto = contexto;
        }

        public List<Incidente> ObtenerTodosLosIncidentes()
        {
            return _contexto.Incidentes.ToList();
        }

        // Otros métodos del repositorio según sea necesario
    }
}
