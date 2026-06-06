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
    //===========================================
    // CAMPOS BASE
    //===========================================
    protected String nombreClase;
    protected String namespace;

    protected ClaseEnums.TipoClase tipoClase; // POO usa esto, SQL no
    protected String clasePadre;              // nombre de la clase padre
    protected double ordenDecimal;            // para diagramas

    protected List<String> atributos;
    protected List<String> metodos;

    protected String analisisADOO = "";

    // Para herencia
    protected List<String> clasesHijas = new ArrayList<>();

    // Colores (polimorfismo visual)
    protected String colorClase;
    protected String colorAtributos;
    protected String colorMetodos;

    //===========================================
    // CONSTRUCTOR
    //===========================================
    public ClasePlantilla(String nombreClase, String namespace)
    {
        this.nombreClase = nombreClase;
        this.namespace = namespace;

        this.atributos = new ArrayList<>();
        this.metodos = new ArrayList<>();
        this.clasePadre = null;
        this.ordenDecimal = 0.0;

        // Colores por defecto (subclases los sobrescriben)
        this.colorClase = "\u001B[37m";     // blanco
        this.colorAtributos = "\u001B[32m"; // verde
        this.colorMetodos = "\u001B[31m";   // rojo
    }

    //===========================================
    // GETTERS / SETTERS
    //===========================================
    public String getNombreClase() 
    {
        return nombreClase;
    }

    public String getNamespace()
    {
        return namespace;
    }

    public String getClasePadre()
    {
        return clasePadre;
    }

    public void setClasePadre(String padre) 
    {
        this.clasePadre = padre;
    }

    public double getOrdenDecimal() 
    {
        return ordenDecimal;
    }

    public void setOrdenDecimal(double valor)
    {
        this.ordenDecimal = valor;
    }

    public List<String> getAtributos() 
    {
        return atributos;
    }

    public List<String> getMetodos()
    {
        return metodos;
    }

    public List<String> getClasesHijas() 
    {
        return clasesHijas;
    }

    public String AnalisisADOO()
    {
        return analisisADOO;
    }

    public void setAnalisisADOO(String txt) 
    {
        this.analisisADOO = txt;
    }

    //===========================================
    // ATRIBUTOS
    //===========================================
    public void agregarAtributo(String a) 
    {
        if(a != null && !a.trim().isEmpty())
            atributos.add(a);
    }

    public void editarAtributo(String viejo, String nuevo) 
    {
        int idx = atributos.indexOf(viejo);
        if(idx >= 0)
            atributos.set(idx, nuevo);
    }

    public void borrarAtributo(String a)
    {
        atributos.remove(a);
    }

    //===========================================
    // METODOS
    //===========================================
    public void agregarMetodo(String m) 
    {
        if(m != null && !m.trim().isEmpty())
            metodos.add(m);
    }

    public void editarMetodo(String viejo, String nuevo)
    {
        int idx = metodos.indexOf(viejo);
        if(idx >= 0)
            metodos.set(idx, nuevo);
    }

    public void borrarMetodo(String m) 
    {
        metodos.remove(m);
    }

    //===========================================
    // HERENCIA
    //===========================================
    public boolean puedeSerPadre() 
    {
        return true; // SealedClass lo sobrescribe
    }

    //===========================================
    // CONTRATO: ANALISIS ADOO
    //===========================================
    @Override
    public String AnalisisADOO(String extra)
    {
        return "Analisis de la clase " + nombreClase + ":\n" + analisisADOO + "\n" + extra;
    }

    @Override
    public String ElijirTonoParaRelacion() 
    {
        return "TonoBajo"; // lógica simple por ahora
    }

    //===========================================
    // POLIMORFISMO VISUAL (BASE)
    // Subclases sobrescriben estos métodos
    //===========================================
    @Override
    public void Colorearse()
    {
        System.out.print(colorClase);
    }

    @Override
    public void ColorearseAtributos()
    {
        System.out.print(colorAtributos);
    }

    @Override
    public void ColorearseMetodos()
    {
        System.out.print(colorMetodos);
    }

    //===========================================
    // MENÚ PRINCIPAL (ABSTRACTO)
    //===========================================
    @Override
    public abstract void ListarDatosEntidad();
}
