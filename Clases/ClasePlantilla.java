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
 * Clase base REAL del sistema.
 * Implementa el contrato y provee toda la lógica común.
 */
public abstract class ClasePlantilla implements InterfaceContrato
{
    //===========================================
    // CAMPOS BASE (✿◠‿◠)
    //===========================================
    protected String nombreClase;
    protected String namespace;

    protected ClaseEnums.TipoClase tipoClase;   // POO usa esto, SQL no (＾▽＾)
    protected String clasePadre;               // nombre de la clase padre (✿◠‿◠)
    protected double ordenDecimal;            // para diagramas (✿◠‿◠)

    protected List<ClaseAtributo> atributos;
    protected List<String> metodos;

    protected String analisisADOO = "";

     // Para herencia ZORRODEV 2026 (≧◡≦)
    protected List<String> clasesHijas = new ArrayList<>();
     
    // Colores (polimorfismo visual) ZORRODEV 2026 (≧◡≦)
    protected String colorClase;
    protected String colorAtributos;
    protected String colorMetodos;

    //===========================================
    // CONSTRUCTOR ZORRODEV 2026 (≧◡≦)
    //===========================================
    public ClasePlantilla(String nombreClase, String namespace)
    {
        this.nombreClase = nombreClase;
        this.namespace = namespace;

        this.atributos = new ArrayList<>();
        this.metodos = new ArrayList<>();
        this.clasePadre = null;
        this.ordenDecimal = 0.0;

        this.colorClase     = "\u001B[37m"; // blanco ZORRODEV 2026 (≧◡≦)
        this.colorAtributos = "\u001B[32m"; // verde ZORRODEV 2026 (≧◡≦)
        this.colorMetodos   = "\u001B[31m"; // rojo ZORRODEV 2026 (≧◡≦)
    }

    
    //===========================================
    // GETTERS / SETTERS ZORRODEV 2026 (≧◡≦)
    //===========================================

    public ClaseEnums.TipoClase getTipoClase() {
        return tipoClase;
    }

    public void setTipoClase(ClaseEnums.TipoClase tipo) {
        this.tipoClase = tipo;
    }

    public String getAnalisisADOO() {
        return analisisADOO;
    }

    public String getNombreClase() {
        return nombreClase;
    }

    public String getNamespace() {
        return namespace;
    }

    public String getClasePadre() {
        return clasePadre;
    }

    public void setClasePadre(String padre) {
        this.clasePadre = padre;
    }

    public double getOrdenDecimal() {
        return ordenDecimal;
    }

    public void setOrdenDecimal(double valor) {
        this.ordenDecimal = valor;
    }

    public List<ClaseAtributo> getAtributos() {
        return atributos;
    }

    public List<String> getMetodos() {
        return metodos;
    }

    public List<String> getClasesHijas() {
        return clasesHijas;
    }

    @Override
    public String AnalisisADOO() {
        return analisisADOO;
    }

    public void setAnalisisADOO(String txt) {
        this.analisisADOO = txt;
    }

    // ATRIBUTOS

    @Override
    public void agregarAtributo(String nombre)
    {
        if(nombre != null && !nombre.trim().isEmpty())
            atributos.add(new ClaseAtributo(nombre));
    }

    public void agregarAtributo(ClaseAtributo a)
    {
        if(a != null)
            atributos.add(a);
    }

    @Override
    public void editarAtributo(String viejo, String nuevo)
    {
        for(ClaseAtributo a : atributos)
        {
            if(a.nombre.equals(viejo))
            {
                a.nombre = nuevo;
                return;
            }
        }
    }

    @Override
    public void borrarAtributo(String nombre)
    {
        atributos.removeIf(a -> a.nombre.equals(nombre));
    }

    // MÉTODOS

    @Override
    public void agregarMetodo(String m)
    {
        if(m != null && !m.trim().isEmpty())
            metodos.add(m);
    }

    @Override
    public void editarMetodo(String viejo, String nuevo)
    {
        int idx = metodos.indexOf(viejo);
        if(idx >= 0)
            metodos.set(idx, nuevo);
    }

    @Override
    public void borrarMetodo(String m)
    {
        metodos.remove(m);
    }


    //===========================================
    // HERENCIA ZORRODEV 2026 (≧◡≦)
    //===========================================

    public boolean puedeSerPadre() {
        return true;
    }

    //===========================================
    // CONTRATO: ANALISIS ADOO ZORRODEV 2026 (≧◡≦)
    //===========================================

    @Override
    public String AnalisisADOO(String extra)
    {
        return "Analisis de la clase " + nombreClase + ":\n" + analisisADOO + "\n" + extra;
    }

    @Override
    public String ElijirTonoParaRelacion()
    {
        return "TonoBajo";
    }

    //===========================================
    // POLIMORFISMO VISUAL (BASE) ZORRODEV 2026 (≧◡≦)
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

    // MENÚ

    @Override
    public abstract void ListarDatosEntidad();
}
