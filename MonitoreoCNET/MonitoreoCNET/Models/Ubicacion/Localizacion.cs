using System.ComponentModel.DataAnnotations;
using System.ComponentModel.DataAnnotations.Schema;

namespace Dominio.Entidades.Ubicacion
{
    [Table("localizacion")]
    //[Inheritance(strategy: InheritanceType.SingleTable)]
    //[DiscriminatorColumn("discriminador")]
    public abstract class Localizacion
    {
        [Key]
        [DatabaseGenerated(DatabaseGeneratedOption.Identity)]
        public int Id { get; set; }

        [Column("nombre")]
        public string Nombre { get; set; }

        [Column("es_provincia")]
        public bool EsProvincia { get; set; }

        [Column("provincia")]
        public string Provincia { get; set; }
        
        [Column("municipio")] 
        public string Municipio { get; set; }
        
        [Column("departamento")]
        public string Departamento { get; set; }
        
        [Column("calle")]
        public string Calle { get; set; }
        
        [Column("numero")]
        public string Numero { get; set; }
        
        public abstract Localizacion GetProvincia();
    }
}