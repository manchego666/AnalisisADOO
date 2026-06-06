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
    // ANALISIS / DESCRIPCION. => ANALISIS DISEÑO ORIENTADO A OBJETOS: aunque en clase aprendi esto y me gusto
    //ahora aprendi que puede ser ANALISIS DescripciónDiseño/DescripcionContexto Orientado a objetos (＾▽＾) ZORRO DEVELOPER 2026!
    //y el analisis y la descripsión entera de las entidades orientada a objetos se pueden guardar en este documentado
    //comodamente
    String AnalisisADOO();              // Devuelve el análisis/descripción ADOO de la entidad. 
    void   setAnalisisADOO(String txt); // Permite establecer el análisis/descripción ADOO.

    // ATRIBUTOS
    List<String> getAtributos();
    void agregarAtributo(String atributo);
    void editarAtributo(String viejo, String nuevo);
    void borrarAtributo(String atributo);

    // METODOS
    List<String> getMetodos();
    void agregarMetodo(String metodo);
    void editarMetodo(String viejo, String nuevo);
    void borrarMetodo(String metodo);

    // MENU / VISTA DETALLADA
    void ListarDatosEntidad(); // Muestra propiedades, atributos, métodos, etc. (flujo de menú interno).

    // COLORES / POLIMORFISMO VISUAL
    void Colorearse();             // Aplica color de la clase.
    void ColorearseAtributos();    // Aplica color de atributos.
    void ColorearseMetodos();      // Aplica color de métodos.
}
