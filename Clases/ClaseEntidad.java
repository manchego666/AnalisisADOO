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
public class ClaseEntidad extends ClasePlantilla
{
    public ClaseEntidad(String nombreClase, String namespace)
    {
        super(nombreClase, namespace);
    }

    //===========================================
    // MÉTODOS DEL CONTRATO (ya vienen de ClasePlantilla)
    // Solo implementamos lo que ClasePlantilla dejó abstracto
    //===========================================

    @Override
    public String AnalisisADOO()
    {
        return "Analisis de la clase " + nombreClase + ":\n" + analisisADOO;
    }

    @Override
    public String ElijirTonoParaRelacion()
    {
        return "TonoBajo"; // lógica simple por ahora
    }

    //===========================================
    // POLIMORFISMO VISUAL (por defecto)
    // Las subclases (POO, SQL, Sealed) lo sobrescriben
    //===========================================
    @Override
    public void Colorearse()
    {
        System.out.print("\u001B[37m"); // blanco por defecto
    }

    @Override
    public void ColorearseAtributos()
    {
        System.out.print("\u001B[32m"); // verde por defecto
    }

    @Override
    public void ColorearseMetodos()
    {
        System.out.print("\u001B[31m"); // rojo por defecto
    }

    //===========================================
    // MENÚ PRINCIPAL (abstracto)
    // Las subclases lo implementan
    //===========================================
    @Override
    public void ListarDatosEntidad()
    {
        // ClaseEntidad no tiene menú propio
        // ClasePOO y ClaseSQL implementan este método
    }
}
