//=====================================
//Autor : Christopher Diaz Gastelum
//Firma : ZORRO DEVELOPER (＾▽＾)
//Proyecto : AnalisisADOO
//Clase : ClaseEnums.java
//Descripción de la clase:
//Contiene enumeraciones globales y estructuras de apoyo para el sistema.
//=====================================

package AnalisisADOO.Clases;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * ClaseEnums
 * Contiene enumeraciones y diccionarios globales del sistema.
 */
public class ClaseEnums 
{
    /**
     * TipoClase
     * Representa el tipo de clase POO.
     * - Interface
     * - Abstracta
     * - Normal
     */
    public enum TipoClase { Interface, Abstracta, Normal }

    ///<summary>
    /// EnumeracionesCreadas:
    ///  Diccionario que guarda las enumeraciones definidas por el usuario.
    ///  Clave: Nombre de la enumeración (ej: "TipoUsuario").
    ///  Valor: Un identificador entero para esa enumeración.
    ///</summary>
    public static Map<String, Integer> EnumeracionesCreadas = new HashMap<>();

    ///<summary>
    /// ListaEnumeraciones:
    ///  Diccionario que guarda los valores de cada enumeración.
    ///  Clave: Nombre de la enumeración (ej: "TipoUsuario").
    ///  Valor: Lista de valores (ej: ["Empleado", "Jefe", "Invitado"]).
    ///</summary>
    public static Map<String, List<String>> ListaEnumeraciones = new HashMap<>();
}
