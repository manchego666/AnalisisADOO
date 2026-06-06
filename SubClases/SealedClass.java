//=====================================
//Autor : Christopher Diaz Gastelum
//Firma : ZORRO DEVELOPER (＾▽＾)
//Proyecto : AnalisisADOO
//Clase : SealedClass.java
//Descripción de la clase:
//Variante de ClasePOO que puede heredar pero no puede ser heredada.
//=====================================

package AnalisisADOO.SubClases;

public class SealedClass extends ClasePOO
{
    public SealedClass(String nombreClase, String namespace)
    {
        super(nombreClase, namespace);
        // TipoClase puede quedar null para marcarla como especial.
        this.tipoClase    = null;
        this.colorClase   = "Dorado"; // Ejemplo de color especial.
    }
}
