//=====================================
//Autor : Christopher Diaz Gastelum
//Firma : ZORRO DEVELOPER (＾▽＾)
//Proyecto : AnalisisADOO
//Clase : ClaseSQL.java
//Descripción de la clase:
//Entidad SQL: solo atributos, sin métodos, con PK/FK/NULL y dependencia.
//=====================================

package AnalisisADOO.SubClases;

import AnalisisADOO.Clases.ClaseEntidad;
import AnalisisADOO.Clases.ClaseAtributo;
import AnalisisADOO.Program;

import java.util.Scanner;

public class ClaseSQL extends ClaseEntidad
{
    private boolean esDependiente; // true = tiene FK hacia otra tabla (ಥ﹏ಥ)

    public ClaseSQL(String nombreClase, String namespace)
    {
        super(nombreClase, namespace);
        this.tipoClase      = null;
        this.colorClase     = Program.AZUL;
        this.colorAtributos = Program.ROSA;
        this.colorMetodos   = Program.GRIS;
        this.esDependiente  = false;
    }



    //===========================================
    // SQL NO PERMITE MÉTODOS (ಥ﹏ಥ) ( POR EL MOMENTO DESPUES TALVEZ SP,VW,TG) ZORRODEV2026 (｡•́︿•̀｡)
    //===========================================
    @Override
    public void agregarMetodo(String metodo)
    {
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

    //===========================================
    // MENÚ PRINCIPAL SQL ZORRODEV 2026 (≧ω≦)
    //===========================================
    @Override
    public void ListarDatosEntidad()
    {
        Scanner sc = new Scanner(System.in);
        boolean salir = false;

        while(!salir)
        {
            Program.clearScreen();
            Colorearse();
            System.out.println("===== ENTIDAD SQL: " + nombreClase + " =====");
            System.out.print(Program.RESET);

            System.out.println("Dependiente (FK): " + (esDependiente ? "SI" : "NO"));
            System.out.println("Orden           : " + ordenDecimal);
            System.out.println("=====================================");
            System.out.println("1. Agregar atributo");
            System.out.println("2. Editar atributo");
            System.out.println("3. Borrar atributo");
            System.out.println("4. Enlazar PK/FK");
            System.out.println("5. Marcar como independiente");
            System.out.println("6. Mostrar propiedades");
            System.out.println("7. Agregar Análisis ADOO");
            System.out.println("8. Mostrar Análisis ADOO");
            System.out.println("0. Regresar");
            System.out.println("=====================================");
            System.out.print("Opción: ");

            int op = Integer.parseInt(sc.nextLine());

            switch(op)
            {
                case 1: menuAgregarAtributo(sc); break;
                case 2: menuEditarAtributo(sc); break;
                case 3: menuBorrarAtributo(sc); break;
                case 4: menuEnlazarPKFK(sc); break;
                case 5: esDependiente = false; Program.pause(sc); break;
                case 6: mostrarPropiedades(sc); break;
                case 7: menuAgregarAnalisisADOO(sc); break;
                case 8: menuMostrarAnalisisADOO(sc); break;
                case 0: salir = true; break;
            }
        }
    }


    //===========================================
    // AGREGAR ATRIBUTO SQL PROFESIONAL (ಥ﹏ಥ)
    //===========================================

    private void menuAgregarAtributo(Scanner sc)
    {
        Program.clearScreen();
        System.out.println("===== NUEVO ATRIBUTO SQL =====");

        System.out.print("Nombre del atributo: ");
        String nombre = sc.nextLine();
        if(nombre.trim().isEmpty()) return;

        ClaseAtributo atr = new ClaseAtributo(nombre);

        atr.tipoDato = elegirTipoDatoSQL(sc);

        System.out.print("¿Es PK? (s/n): ");
        atr.esPK = sc.nextLine().trim().equalsIgnoreCase("s");

        System.out.print("¿Es FK? (s/n): ");
        atr.esFK = sc.nextLine().trim().equalsIgnoreCase("s");
        if(atr.esFK) esDependiente = true;

        System.out.print("¿Permite NULL? (s/n): ");
        atr.esNullable = sc.nextLine().trim().equalsIgnoreCase("s");

        atributos.add(atr);

        System.out.println("\nAtributo SQL agregado correctamente.");
        Program.pause(sc);
    }

    //===========================================
    // EDITAR ATRIBUTO SQL PROFESIONAL
    //===========================================   

    private void menuEditarAtributo(Scanner sc)
    {
        Program.clearScreen();
        if(atributos.isEmpty())
        {
            System.out.println("No hay atributos SQL.");
            Program.pause(sc);
            return;
        }

        System.out.println("===== EDITAR ATRIBUTO SQL =====");
        for(int i=0; i<atributos.size(); i++)
            System.out.println(i + " - " + atributos.get(i).toSQLString());

        System.out.print("Índice a editar: ");
        int idx = Integer.parseInt(sc.nextLine());
        if(idx < 0 || idx >= atributos.size()) return;

        ClaseAtributo atr = atributos.get(idx);

        boolean salir = false;
        while(!salir)
        {
            Program.clearScreen();
            System.out.println("Editando: " + atr.toSQLString());
            System.out.println("1. Cambiar nombre");
            System.out.println("2. Cambiar tipo SQL");
            System.out.println("3. Cambiar PK");
            System.out.println("4. Cambiar FK");
            System.out.println("5. Cambiar NULL");
            System.out.println("0. Regresar");
            System.out.print("Opción: ");

            int op = Integer.parseInt(sc.nextLine());
            switch(op)
            {
                case 1:
                    System.out.print("Nuevo nombre: ");
                    atr.nombre = sc.nextLine();
                    break;

                case 2:
                    atr.tipoDato = elegirTipoDatoSQL(sc);
                    break;

                case 3:
                    System.out.print("¿Es PK? (s/n): ");
                    atr.esPK = sc.nextLine().trim().equalsIgnoreCase("s");
                    break;

                case 4:
                    System.out.print("¿Es FK? (s/n): ");
                    atr.esFK = sc.nextLine().trim().equalsIgnoreCase("s");
                    esDependiente = atr.esFK;
                    break;

                case 5:
                    System.out.print("¿Permite NULL? (s/n): ");
                    atr.esNullable = sc.nextLine().trim().equalsIgnoreCase("s");
                    break;

                case 0:
                    salir = true;
                    break;
            }
        }
    }

    
    private void menuBorrarAtributo(Scanner sc)
    {
        Program.clearScreen();
        if(atributos.isEmpty())
        {
            System.out.println("No hay atributos SQL.");
            Program.pause(sc);
            return;
        }

        System.out.println("===== BORRAR ATRIBUTO SQL =====");
        for(int i=0; i<atributos.size(); i++)
            System.out.println(i + " - " + atributos.get(i).toSQLString());

        System.out.print("Índice a borrar: ");
        int idx = Integer.parseInt(sc.nextLine());
        if(idx < 0 || idx >= atributos.size()) return;

        atributos.remove(idx);

        esDependiente = atributos.stream().anyMatch(a -> a.esFK);

        System.out.println("Atributo eliminado.");
        Program.pause(sc);
    }

    private void menuEnlazarPKFK(Scanner sc)
    {
        Program.clearScreen();
        System.out.println("===== ENLAZAR PK/FK =====");
        System.out.println("Esta tabla ahora será dependiente (FK).");
        esDependiente = true;
        Program.pause(sc);
    }

    private String elegirTipoDatoSQL(Scanner sc)
    {
        System.out.println("\n===== TIPO DE DATO SQL =====");
        System.out.println("1. INT");
        System.out.println("2. BIGINT");
        System.out.println("3. SMALLINT");
        System.out.println("4. TINYINT");
        System.out.println("5. DECIMAL");
        System.out.println("6. FLOAT");
        System.out.println("7. DOUBLE");
        System.out.println("8. VARCHAR(n)");
        System.out.println("9. CHAR(n)");
        System.out.println("10. TEXT");
        System.out.println("11. DATE");
        System.out.println("12. DATETIME");
        System.out.println("13. BIT");
        System.out.println("14. BOOLEAN");
        System.out.print("Opción: ");

        int op = Integer.parseInt(sc.nextLine());

        switch(op)
        {
            case 1: return "INT";
            case 2: return "BIGINT";
            case 3: return "SMALLINT";
            case 4: return "TINYINT";
            case 5: return "DECIMAL";
            case 6: return "FLOAT";
            case 7: return "DOUBLE";
            case 8:
                System.out.print("Tamaño VARCHAR: ");
                return "VARCHAR(" + sc.nextLine() + ")";
            case 9:
                System.out.print("Tamaño CHAR: ");
                return "CHAR(" + sc.nextLine() + ")";
            case 10: return "TEXT";
            case 11: return "DATE";
            case 12: return "DATETIME";
            case 13: return "BIT";
            case 14: return "BOOLEAN";
        }

        return "INT";
    }

    private void menuAgregarAnalisisADOO(Scanner sc)
    {
        Program.clearScreen();
        System.out.println("===== AGREGAR / EDITAR ANALISIS ADOO (SQL) =====");
        System.out.println("Actual:");
        System.out.println(Program.wrapText(analisisADOO, 80));

        System.out.println("\nNuevo análisis:");
        String txt = sc.nextLine();

        if(!txt.trim().isEmpty())
            setAnalisisADOO(txt);

        Program.pause(sc);
    }

    private void menuMostrarAnalisisADOO(Scanner sc)
    {
        Program.clearScreen();
        System.out.println("===== ANALISIS ADOO DE " + nombreClase + " (SQL) =====");
        System.out.println(Program.wrapText(analisisADOO, 80));
        Program.pause(sc);
    }

    private void mostrarPropiedades(Scanner sc)
    {
        Program.clearScreen();

        Colorearse();
        System.out.println("===== PROPIEDADES SQL DE " + nombreClase + " =====");
        System.out.print(Program.RESET);

        System.out.println("Namespace   : " + namespace);
        System.out.println("Dependiente : " + (esDependiente ? "SI" : "NO"));
        System.out.println("Orden       : " + ordenDecimal);

        System.out.println();

        ColorearseAtributos();
        System.out.println("Atributos SQL:");
        System.out.print(Program.RESET);
        for(ClaseAtributo a : atributos)
            System.out.println("- " + a.toSQLString());

        System.out.println("\nAnalisis ADOO:");
        System.out.println(Program.wrapText(analisisADOO, 80));

        Program.pause(sc);
    }

    @Override
    public void Colorearse()
    {
        if(esDependiente)
            System.out.print("\u001B[94m");
        else
            System.out.print(Program.AZUL);
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
