//=====================================
//Autor : Christopher Diaz Gastelum
//Firma : ZORRO DEVELOPER (＾▽＾)
//Proyecto : AnalisisADOO
//Clase : ClaseSQL.java
//Descripción de la clase:
//Entidad SQL: solo atributos, sin métodos, con posibilidad de marcar dependencia (PK/FK).
//=====================================

package AnalisisADOO.SubClases;

import AnalisisADOO.Clases.ClaseEntidad;
import java.util.Scanner;

public class ClaseSQL extends ClaseEntidad
{
    private boolean esDependiente; // true = tiene FK hacia otra tabla

    public ClaseSQL(String nombreClase, String namespace)
    {
        super(nombreClase, namespace);
        this.tipoClase      = null;       // No aplica TipoClase POO.
        this.colorClase     = "Azul";
        this.colorAtributos = "Rosa";     // Atributos SQL.
        this.colorMetodos   = "Gris";     // No se usan, pero se deja por contrato.
        this.esDependiente  = false;
    }

    @Override
    public void agregarMetodo(String metodo)
    {
        // SQL no permite métodos.
        System.out.println("Las entidades SQL no pueden tener métodos.");
    }

    @Override
    public void editarMetodo(String viejo, String nuevo)
    {
        System.out.println("Las entidades SQL no pueden tener métodos.");
    }

    @Override
    public void borrarMetodo(String metodo)
    {
        System.out.println("Las entidades SQL no pueden tener métodos.");
    }

    //========================
    // MENU INTERNO SQL
    //========================
    @Override
    public void ListarDatosEntidad()
    {
        Scanner sc = new Scanner(System.in);
        boolean salir = false;

        while(!salir)
        {
            System.out.println("\n===== ENTIDAD SQL: " + nombreClase + " =====");
            System.out.println("Dependiente (FK): " + (esDependiente ? "SI" : "NO"));
            System.out.println("Orden           : " + ordenDecimal);
            System.out.println("1. Agregar atributo");
            System.out.println("2. Editar atributo");
            System.out.println("3. Borrar atributo");
            System.out.println("4. Enlazar (PK/FK)");
            System.out.println("5. Marcar como independiente");
            System.out.println("6. Mostrar propiedades");
            System.out.println("7. Agregar Análisis ADOO");
            System.out.println("8. Mostrar Análisis ADOO");
            System.out.println("0. Regresar");
            System.out.print("Opción: ");

            int op = Integer.parseInt(sc.nextLine());

            switch(op)
            {
                case 1: menuAgregarAtributo(sc); break;
                case 2: menuEditarAtributo(sc); break;
                case 3: menuBorrarAtributo(sc); break;
                case 4: menuEnlazarPKFK(sc); break;
                case 5: esDependiente = false; break;
                case 6: mostrarPropiedades(); break;
                case 7: menuAgregarAnalisisADOO(sc); break;
                case 8: menuMostrarAnalisisADOO(); break;
                case 0: salir = true; break;
            }
        }
    }

    private void menuAgregarAtributo(Scanner sc)
    {
        System.out.print("Nuevo atributo (SQL): ");
        String atr = sc.nextLine();
        agregarAtributo(atr);
    }

    private void menuEditarAtributo(Scanner sc)
    {
        if(atributos.isEmpty())
        {
            System.out.println("No hay atributos.");
            return;
        }

        for(int i=0; i<atributos.size(); i++)
            System.out.println(i + " - " + atributos.get(i));

        System.out.print("Índice a editar: ");
        int idx = Integer.parseInt(sc.nextLine());
        if(idx < 0 || idx >= atributos.size()) return;

        String viejo = atributos.get(idx);
        System.out.print("Nuevo valor: ");
        String nuevo = sc.nextLine();
        editarAtributo(viejo, nuevo);
    }

    private void menuBorrarAtributo(Scanner sc)
    {
        if(atributos.isEmpty())
        {
            System.out.println("No hay atributos.");
            return;
        }

        for(int i=0; i<atributos.size(); i++)
            System.out.println(i + " - " + atributos.get(i));

        System.out.print("Índice a borrar: ");
        int idx = Integer.parseInt(sc.nextLine());
        if(idx < 0 || idx >= atributos.size()) return;

        borrarAtributo(atributos.get(idx));
    }

    private void menuEnlazarPKFK(Scanner sc)
    {
        // Aquí se integrará con Program.entidades para elegir otra entidad,
        // decidir quién es mayor/menor y marcar PK/FK.
        System.out.println("Lógica de enlace PK/FK se implementa desde Program (lista de entidades).");
        esDependiente = true;
    }

    private void menuAgregarAnalisisADOO(Scanner sc)
    {
        System.out.println("\n===== AGREGAR / EDITAR ANALISIS ADOO (SQL) =====");
        System.out.println("Actual:");
        System.out.println(analisisADOO);
        System.out.println("\nNuevo análisis:");
        String txt = sc.nextLine();
        if(!txt.trim().isEmpty())
            setAnalisisADOO(txt);
    }

    private void menuMostrarAnalisisADOO()
    {
        System.out.println("\n===== ANALISIS ADOO DE " + nombreClase + " (SQL) =====");
        System.out.println(analisisADOO);
        System.out.println("\nPresione una tecla para regresar...");
        try { System.in.read(); } catch(Exception e) {}
    }

    private void mostrarPropiedades()
    {
        System.out.println("\n===== PROPIEDADES SQL DE " + nombreClase + " =====");
        System.out.println("Namespace   : " + namespace);
        System.out.println("Dependiente : " + (esDependiente ? "SI" : "NO"));
        System.out.println("Orden       : " + ordenDecimal);

        System.out.println("\nAtributos:");
        for(String a : atributos) System.out.println(" - " + a);

        System.out.println("\nPresione una tecla para regresar...");
        try { System.in.read(); } catch(Exception e) {}
    }

    //========================
    // COLORES (POLIMORFISMO)
    //========================
    @Override
    public void Colorearse()
    {
        if(esDependiente)
        System.out.print("\u001B[94m"); // azul claro ZORRO DEVELOPER 2026 (´∀｀)♡
    else
        System.out.print(Program.AZUL); // azul normal ZORRO DEVELOPER 2026 (´∀｀)♡
    }

    @Override
    public void ColorearseAtributos()
    {
        System.out.print(Program.ROSA); 
    }

    @Override
    public void ColorearseMetodos()
    {
        // SQL no usa métodos por el momento, pero se deja por contrato. EN UN FUTURO SERIA BUENO AGREGAR SP , FUNC , DISPARADORES
        // ESPERANDO ACTUALIZACION ZORRO DEVELOPER 2026 (ಠ_ಠ) VAMOS ES SOLO ELEJIR COLOR Y PERMITIR ADD METODOS (¬_¬)
    }
}
