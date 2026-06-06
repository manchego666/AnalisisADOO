package AnalisisADOO.Clases;

/**
 * Representa un atributo completo dentro de una clase POO o SQL.
 * Ya no es un simple String, sino un objeto con metadatos.
 */
public class ClaseAtributo
{
    //===========================================
    // CAMPOS BASE
    //===========================================
    public String nombre;          // nombre del atributo
    public String tipoDato;        // string, int, bool, etc.
    public String encapsulacion;   // public, private, protected (solo POO)
    public String tipoAtributo;    // readonly, const, var, static (solo POO)

    // SQL
    public boolean esPK = false;
    public boolean esFK = false;
    public boolean esNullable = true;

    // Valor por defecto (opcional)
    public String valorDefault = "";

    //===========================================
    // CONSTRUCTOR
    //===========================================
    public ClaseAtributo(String nombre)
    {
        this.nombre = nombre;
        this.tipoDato = "string";       // por defecto
        this.encapsulacion = "private"; // por defecto
        this.tipoAtributo = "var";      // por defecto
    }

    //===========================================
    // MÉTODOS DE UTILIDAD
    //===========================================

    /**
     * Representación para diagramas POO.
     */
    public String toPOOString()
    {
        return encapsulacion + " " + tipoAtributo + " " + tipoDato + " " + nombre;
    }

    /**
     * Representación para diagramas SQL.
     */
    public String toSQLString()
    {
        String flags = "";

        if(esPK) flags += " PK";
        if(esFK) flags += " FK";
        if(!esNullable) flags += " NOT NULL";

        return nombre + " " + tipoDato + flags;
    }

    /**
     * Representación genérica (TXT/JSON).
     */
    @Override
    public String toString()
    {
        return "Atributo{" +
                "nombre='" + nombre + '\'' +
                ", tipoDato='" + tipoDato + '\'' +
                ", encapsulacion='" + encapsulacion + '\'' +
                ", tipoAtributo='" + tipoAtributo + '\'' +
                ", esPK=" + esPK +
                ", esFK=" + esFK +
                ", esNullable=" + esNullable +
                ", valorDefault='" + valorDefault + '\'' +
                '}';
    }
}
