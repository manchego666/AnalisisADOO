//=====================================
//Autor : Christopher Diaz Gastelum
//Firma : ZORRO DEVELOPER (＾▽＾)
//Proyecto : AnalisisADOO
//Clase : ClasePlantilla.java
//Descripción de la clase:
//Clase abstracta base que representa una clase documentable en el sistema.
//=====================================

package AnalisisADOO.Clases;

import java.util.ArrayList;
import java.util.List;

/**
 * ClasePlantilla
 * Clase abstracta que representa una clase documentable.
 * Implementa la mayor parte del contrato y deja el comportamiento
 * específico a las subclases (POO, SQL, Sealed, etc.).
 */
public abstract class ClasePlantilla implements InterfaceContrato
{
    // DATOS BASICOS
    protected String nombreClase;
    protected String namespace;

    // LISTAS DE MIEMBROS
    protected List<String> atributos;
    protected List<String> metodos;

    // HERENCIA / RELACIONES
    protected String clasePadre;
    protected List<String> clasesHijas;

    // ORDEN PARA DIAGRAMA (1.00, 1.01, etc.)
    protected double ordenDecimal;

    // COLORES
    protected String colorClase;
    protected String colorAtributos;
    protected String colorMetodos;

    // TIPO DE CLASE (solo aplica para POO)
    protected ClaseEnums.TipoClase tipoClase;

    // ANALISIS / DESCRIPCION ADOO
    protected String analisisADOO;

    public ClasePlantilla(String nombreClase, String namespace)
    {
        this.nombreClase   = nombreClase;
        this.namespace     = namespace;

        this.atributos     = new ArrayList<>();
        this.metodos       = new ArrayList<>();
        this.clasesHijas   = new ArrayList<>();

        this.clasePadre    = null;
        this.ordenDecimal  = 0.0;

        // Colores por defecto (podrán sobreescribirse en subclases).
        this.colorClase      = "Blanco";
        this.colorAtributos  = "Verde";
        this.colorMetodos    = "Rojo";

        this.tipoClase     = null; // POO la usará, SQL/Sealed pueden dejarla null.
        this.analisisADOO  = "";
    }

    //========================
    // IMPLEMENTACION BASE DEL CONTRATO
    //========================

    @Override
    public String AnalisisADOO()
    {
        return analisisADOO;
    }

    @Override
    public void setAnalisisADOO(String txt)
    {
        this.analisisADOO = txt;
    }

    // ATRIBUTOS
    @Override
    public List<String> getAtributos()
    {
        return atributos;
    }

    @Override
    public void agregarAtributo(String atributo)
    {
        if(atributo != null && !atributo.trim().isEmpty())
            atributos.add(atributo);
    }

    @Override
    public void editarAtributo(String viejo, String nuevo)
    {
        int idx = atributos.indexOf(viejo);
        if(idx >= 0 && nuevo != null && !nuevo.trim().isEmpty())
        {
            atributos.set(idx, nuevo);
        }
    }

    @Override
    public void borrarAtributo(String atributo)
    {
        atributos.remove(atributo);
    }

    // METODOS
    @Override
    public List<String> getMetodos()
    {
        return metodos;
    }

    @Override
    public void agregarMetodo(String metodo)
    {
        if(metodo != null && !metodo.trim().isEmpty())
            metodos.add(metodo);
    }

    @Override
    public void editarMetodo(String viejo, String nuevo)
    {
        int idx = metodos.indexOf(viejo);
        if(idx >= 0 && nuevo != null && !nuevo.trim().isEmpty())
        {
            metodos.set(idx, nuevo);
        }
    }

    @Override
    public void borrarMetodo(String metodo)
    {
        metodos.remove(metodo);
    }

    //========================
    // METODOS ABSTRACTOS / POLIMORFISMO
    //========================

    @Override
    public abstract void ListarDatosEntidad();

    @Override
    public abstract void Colorearse();

    @Override
    public abstract void ColorearseAtributos();

    @Override
    public abstract void ColorearseMetodos();

    //========================
    // GETTERS/SETTERS DE APOYO
    //========================

    public String getNombreClase()
    {
        return nombreClase;
    }

    public String getNamespace()
    {
        return namespace;
    }

    public double getOrdenDecimal()
    {
        return ordenDecimal;
    }

    public void setOrdenDecimal(double ordenDecimal)
    {
        this.ordenDecimal = ordenDecimal;
    }

    public String getClasePadre()
    {
        return clasePadre;
    }

    public void setClasePadre(String clasePadre)
    {
        this.clasePadre = clasePadre;
    }

    public List<String> getClasesHijas()
    {
        return clasesHijas;
    }

    public ClaseEnums.TipoClase getTipoClase()
    {
        return tipoClase;
    }

    public void setTipoClase(ClaseEnums.TipoClase tipoClase)
    {
        this.tipoClase = tipoClase;
    }
}
