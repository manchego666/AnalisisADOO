//=====================================
//Autor : Christopher Diaz Gastelum
//Firma : ZORRO DEVELOPER (＾▽＾)
//Proyecto : AnalisisADOO
//Clase : ClaseEntidad.java
//Descripción de la clase:
//Clase base que actúa como puente para las entidades POO, SQL y Sealed.
//=====================================

package AnalisisADOO.Clases;

/**
 * ClaseEntidad
 * Representa una entidad documentable genérica.
 * Sirve como base para subclases especializadas (POO, SQL, Sealed).
 */
public abstract class ClaseEntidad extends ClasePlantilla
{
    public ClaseEntidad(String nombreClase, String namespace)
    {
        super(nombreClase, namespace);
        // Por defecto, una entidad se considera "Normal" (POO la usará).
        // SQL y Sealed podrán dejar tipoClase en null o sobreescribir comportamiento.
    }

    /**
     * ListarDatosEntidad
     * La implementación concreta se hará en las subclases (POO, SQL),
     * ya que el menú y las opciones dependen del tipo de entidad.
     */
    @Override
    public abstract void ListarDatosEntidad();

    /**
     * Colorearse / ColorearseAtributos / ColorearseMetodos
     * Se implementan en subclases para demostrar polimorfismo visual.
     */
    @Override
    public abstract void Colorearse();

    @Override
    public abstract void ColorearseAtributos();

    @Override
    public abstract void ColorearseMetodos();
}
