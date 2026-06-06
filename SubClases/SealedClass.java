//=====================================
//Autor : Christopher Diaz Gastelum
//Firma : ZORRO DEVELOPER (＾▽＾)
//Proyecto : AnalisisADOO
//Clase : SealedClass.java
//Descripción de la clase:
//Variante de ClasePOO que puede heredar pero no puede ser heredada.
//=====================================

package AnalisisADOO.SubClases;

import AnalisisADOO.Clases.ClaseEnums;
import AnalisisADOO.Program;

public class SealedClass extends ClasePOO
{
    public SealedClass(String nombreClase, String namespace)
    {
        super(nombreClase, namespace);

        // Una clase sellada NO tiene tipoClase (ノಠ益ಠ)ノ ZORRODEV 2026
        this.tipoClase = null;

        // Colores especiales
        this.colorClase = Program.DORADO;
        this.colorAtributos = Program.VERDE;
        this.colorMetodos = Program.ROJO;
    }

    //===========================================
    // POLIMORFISMO VISUAL ZORRODEV 2026 (⁄ ⁄•⁄ω⁄•⁄ ⁄)
    //===========================================
    @Override
    public void Colorearse()
    {
        System.out.print(Program.DORADO);
    }

    @Override
    public void ColorearseAtributos()
    {
        System.out.print(Program.VERDE);
    }

    @Override
    public void ColorearseMetodos()
    {
        System.out.print(Program.ROJO);
    }

    //===========================================
    // MOSTRAR PROPIEDADES (VERSIÓN SELLADA) ZORRODEV 2026 (⁄ ⁄•⁄ω⁄•⁄ ⁄)
    //===========================================
    @Override
    protected void mostrarPropiedades(java.util.Scanner sc)
    {
        Program.clearScreen();

        Colorearse();
        System.out.println("===== PROPIEDADES DE " + nombreClase + " (SEALED) =====");
        System.out.print(Program.RESET);

        System.out.println("Namespace : " + namespace);
        System.out.println("TipoClase : SEALED (null)");
        System.out.println("Padre     : " + (clasePadre != null ? clasePadre : "Ninguno"));
        System.out.println("Orden     : " + ordenDecimal);

        System.out.println();

        ColorearseAtributos();
        System.out.println("Atributos:");
        System.out.print(Program.RESET);
        for(String a : atributos)
            System.out.println("- " + a);

        System.out.println();

        ColorearseMetodos();
        System.out.println("Métodos:");
        System.out.print(Program.RESET);
        for(String m : metodos)
            System.out.println("- " + m);

        System.out.println("\nAnalisis ADOO:");
        System.out.println(Program.wrapText(analisisADOO, 80));

        Program.pause(sc);
    }

    //===========================================
    // BLOQUEAR HERENCIA    ZORRODEV 2026 (⁄ ⁄•⁄ω⁄•⁄ ⁄)
    //===========================================
    @Override
    public boolean puedeSerPadre()
    {
        return false; // ninguna clase puede heredar de esta
    }

    //===========================================
    // DESSELLAR (convertir a ClasePOO normal) ZORRODEV 2026 (⁄ ⁄•⁄ω⁄•⁄ ⁄)
    //===========================================
    public void desellar()
    {
        this.tipoClase = ClaseEnums.TipoClase.Normal;
        this.colorClase = Program.NARANJA;
    }
}
