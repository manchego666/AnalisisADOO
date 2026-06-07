//=====================================
//Autor : Christopher Diaz Gastelum
//Firma : ZORRO DEVELOPER (＾▽＾) (⁄ ⁄•⁄ω⁄•⁄ ⁄)
//Proyecto : AnalisisADOO
//Clase : ClaseEntidad.java
//Descripción de la clase:
//Clase base que actúa como puente para las entidades POO, SQL y Sealed.
//=====================================

package AnalisisADOO.Clases;

import java.util.ArrayList;
import java.util.List;

/**
 * ClaseEntidad
 * Representa una entidad documentable genérica.
 * Sirve como base para subclases especializadas (POO, SQL, Sealed).
 * ZORRODEV 2026 (⁄ ⁄>⁄◡⁄<⁄ ⁄)
 */
public class ClaseEntidad extends ClasePlantilla
{
    //===========================================
    // CAMPOS BASE (heredados de ClasePlantilla)
    // nombreClase
    // namespace
    // tipoClase
    // clasePadre
    // ordenDecimal
    // atributos (List<ClaseAtributo>)
    // metodos   (List<String>)
    // clasesHijas (List<String>)
    // analisisADOO
    //===========================================

    public ClaseEntidad(String nombreClase, String namespace)
    {
        super(nombreClase, namespace);

        // Asegurar listas inicializadas (por si ClasePlantilla cambia)
        if(this.atributos == null) this.atributos = new ArrayList<>();
        if(this.metodos == null)   this.metodos   = new ArrayList<>();
        if(this.clasesHijas == null) this.clasesHijas = new ArrayList<>();
    }

    //===========================================
    // MÉTODOS DEL CONTRATO (de ClasePlantilla) DONDE ANALIZAREMOS PUES (ノಠ益ಠ)ノ (ಠ_ಠ) ZORRODEV 2026
    //===========================================

    @Override
    public String AnalisisADOO()
    {
        return "Analisis de la clase " + nombreClase + ":\n" + analisisADOO;
    }

    @Override
    public String ElijirTonoParaRelacion()
    {
        return "TonoBajo"; // lógica simple por ahora (ಥ﹏ಥ)
    }

    //===========================================
    // ATRIBUTOS (puente para POO y SQL)
    //===========================================

    @Override
    public void agregarAtributo(String a)
    {
        // Compatibilidad con versiones antiguas (ಥ﹏ಥ)
        if(a != null && !a.trim().isEmpty())
            atributos.add(new ClaseAtributo(a));
    }

    @Override
    public void editarAtributo(String viejo, String nuevo)
    {
        for(ClaseAtributo atr : atributos)
        {
            if(atr.nombre.equals(viejo))
            {
                atr.nombre = nuevo;
                return;
            }
        }
    }

    @Override
    public void borrarAtributo(String a)
    {
        atributos.removeIf(x -> x.nombre.equals(a));
    }

    //===========================================
    // MÉTODOS (solo POO los usa) SQL POR EL MOMENTO NO (ಥ﹏ಥ) ZORRODEV 2026
    //===========================================

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
        if(idx >= 0)
            metodos.set(idx, nuevo);
    }

    @Override
    public void borrarMetodo(String metodo)
    {
        metodos.remove(metodo);
    }

    //===========================================
    // HERENCIA (puede ser sobrescrito por Sealed)
    //===========================================
    public boolean puedeSerPadre()
    {
        return true; // SealedClass lo sobrescribe (ง'̀-'́)ง
    }

    //===========================================
    // POLIMORFISMO VISUAL (por defecto)
    //===========================================
    @Override
    public void Colorearse()
    {
        System.out.print("\u001B[37m"); // blanco por defecto (ZORRODEV 2026)
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
        System.out.println("ClaseEntidad no tiene menú propio (ಥ﹏ಥ)");
    }
}
