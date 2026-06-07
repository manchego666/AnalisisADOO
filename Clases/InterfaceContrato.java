//=====================================
//Autor : Christopher Diaz Gastelum
//Firma : ZORRO DEVELOPER (＾▽＾)
//Proyecto : AnalisisADOO
//Clase : InterfaceContrato.java
//Descripción de la clase:
//Contrato obligatorio para todas las clases documentables del sistema.
//=====================================

package AnalisisADOO.Clases;

import java.util.List;

/**
 * InterfaceContrato
 * Define el contrato obligatorio para todas las clases documentables.
 */
public interface InterfaceContrato
{
    // ATRIBUTOS
    void agregarAtributo(String nombre);
    void editarAtributo(String viejo, String nuevo);
    void borrarAtributo(String nombre);
    List<ClaseAtributo> getAtributos();

    // MÉTODOS
    void agregarMetodo(String nombre);
    void editarMetodo(String viejo, String nuevo);
    void borrarMetodo(String nombre);
    List<String> getMetodos();


    //===========================================
    // ANALISIS ADOO DESCRIPCION. => ANALISIS DISEÑO ORIENTADO A OBJETOS.  (≧◡≦) ZORRO DEVEL
    //===========================================
    String AnalisisADOO();              // versión simple
    String AnalisisADOO(String extra);  // versión extendida

     // TONO PARA RELACIONES (HERENCIA) ZORRODEV 2026 (≧◡≦)
    String ElijirTonoParaRelacion();

    // COLORES / POLIMORFISMO VISUALZORRODEV 2026 (≧◡≦)
    void Colorearse();
    void ColorearseAtributos();
    void ColorearseMetodos();

    // MENÚ
    void ListarDatosEntidad(); // Muestra propiedades, atributos, métodos, etc. (flujo de menú interno). ZORRODEV 2026 (≧◡≦)
}
