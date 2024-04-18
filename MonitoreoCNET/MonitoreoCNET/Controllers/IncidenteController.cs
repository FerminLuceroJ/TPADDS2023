using Microsoft.AspNetCore.Mvc;
using MonitoreoCNET.Models.Incidentes;
using MonitoreoCNET.Models.Organizaciones;
using MonitoreoCNET.Models.Servicios;

namespace MonitoreoCNET.Controllers;

public class IncidenteController : Controller
{
    public IActionResult Incidentes()
    {
        Servicio servicio1 = new Servicio();
        servicio1.Nombre = "Baños";
        Servicio servicio2 = new Servicio();
        servicio2.Nombre = "Escalera mecanica";

        Entidad entidad1 = new Entidad();
        entidad1.Nombre = "Banco Nacion";

        Entidad entidad2 = new Entidad();
        entidad2.Nombre = "Linea A de subtes";

        Establecimiento establecimiento1 = new Establecimiento();
        establecimiento1.Nombre = "Sucursal Flores";
        establecimiento1.Entidad = entidad1;
        
        Establecimiento establecimiento2 = new Establecimiento();
        establecimiento2.Nombre = "Estacion Carabobo";
        establecimiento2.Entidad = entidad2;
        
        PrestacionDeServicio prestacionDeServicio1 = new PrestacionDeServicio();
        prestacionDeServicio1.Servicio = servicio1;
        prestacionDeServicio1.Establecimiento = establecimiento1;

        PrestacionDeServicio prestacionDeServicio2 = new PrestacionDeServicio();
        prestacionDeServicio2.Servicio = servicio2;
        prestacionDeServicio2.Establecimiento = establecimiento2;
        
        Incidente inc1 = new Incidente();
        inc1.Descripcion = "No hay mas papel en el baño";
        inc1.Estado = Estado.ABIERTO;
        inc1.ServicioAfectado = prestacionDeServicio1;
        inc1.Id = 1;
        
        Incidente inc2 = new Incidente();
        inc2.Descripcion = "No andan las escaleraaaaas";
        inc2.Estado = Estado.CERRADO;
        inc2.ServicioAfectado = prestacionDeServicio2;
        inc2.Id = 2;
        
        List<Incidente> incidentes = new List<Incidente>();
        incidentes.Add(inc1);
        incidentes.Add(inc2);
        

        return View(incidentes);
    }
}