//=====================================
//Autor : Christopher Diaz Gastelum
//Firma : ZORRO DEVELOPER (＾▽＾)
//Proyecto : AnalisisADOO
//Clase : ClasePOO.java
//Descripción de la clase:
//Entidad POO: permite atributos, métodos, herencia, tipo de clase y sellado lógico.
//=====================================

package AnalisisADOO.SubClases;

import AnalisisADOO.Clases.ClaseEntidad;
import AnalisisADOO.Clases.ClaseEnums;
import AnalisisADOO.Program;

import java.util.List;
import java.util.Scanner;

// en c# public sealed class ClasePOO : ClaseEntidad  (≧ω≦) ES MI MANERA DE DECIR NADIE HEREDA A ESTA CLASE (ง'̀-'́)ง ZORRODEV 2026
public class ClasePOO extends ClaseEntidad
{
    private boolean esSealed;

    public ClasePOO(String nombreClase, String namespace)
    {
        super(nombreClase, namespace);
        this.tipoClase    = ClaseEnums.TipoClase.Normal;
        this.colorClase   = "Naranja";
        this.colorAtributos = "Verde";
        this.colorMetodos   = "Rojo";
        this.esSealed     = false;
    }

    //=================================
    // MENU PRINCIPAL DE LA ENTIDAD POO  BY: ZORRODEV 2026 (⁀ᗢ⁀)
    //=================================
    @Override
    public void ListarDatosEntidad()
    {
        Scanner sc = new Scanner(System.in);
        boolean salir = false;

        while(!salir)
        {
            Program.clearScreen();
            System.out.println("\n===== ENTIDAD POO: " + nombreClase + " =====" + Program.RESET);
            System.out.println("TipoClase: " + (tipoClase != null ? tipoClase : "null"));
            System.out.println("Sealed   : " + (esSealed ? "SI" : "NO"));
            System.out.println("Orden    : " + ordenDecimal);
            System.out.println("1. Agregar atributo");
            System.out.println("2. Editar atributo");
            System.out.println("3. Borrar atributo");
            System.out.println("4. Agregar método");
            System.out.println("5. Editar método");
            System.out.println("6. Borrar método");
            System.out.println("7. Cambiar tipo de clase");
            System.out.println("8. Heredar");
            System.out.println(esSealed ? "9. Desellar clase" : "9. Sellar clase");
            System.out.println("10. Agregar enumeración");
            System.out.println("11. Listar enumeraciones");
            System.out.println("12. Agregar Análisis ADOO");
            System.out.println("13. Mostrar Análisis ADOO");
            System.out.println("14. Mostrar propiedades");
            System.out.println("0. Regresar");
            System.out.println("=====================================");
            System.out.print("Opción: ");

            int op = Integer.parseInt(sc.nextLine());

            switch(op)
            {
                case 1: menuAgregarAtributo(sc); break;
                case 2: menuEditarAtributo(sc); break;
                case 3: menuBorrarAtributo(sc); break;
                case 4: menuAgregarMetodo(sc); break;
                case 5: menuEditarMetodo(sc); break;
                case 6: menuBorrarMetodo(sc); break;
                case 7: menuCambiarTipoClase(sc); break;
                case 8: menuHeredar(sc); break;
                case 9: toggleSealed(); break;
                case 10: menuAgregarEnumeracion(sc); break;
                case 11: menuListarEnumeraciones(sc); break;
                case 12: menuAgregarAnalisisADOO(sc); break;
                case 13: menuMostrarAnalisisADOO(); break;
                case 14: mostrarPropiedades(); break;
                case 0: salir = true; break;
            }
        }
    }

    //========================
    // SUBMENUS BY: ZORRODEV 2026 (⁀ᗢ⁀)
    //========================
    private void menuAgregarAtributo(Scanner sc)
    {
        Program.clearScreen();
        System.out.print("Nuevo atributo: ");
        String atr = sc.nextLine();
        agregarAtributo(atr);
        Program.pause(sc);
    }

    private void menuEditarAtributo(Scanner sc)
    {
        Program.clearScreen();
        if(atributos.isEmpty())
        {
            System.out.println("No hay atributos.");
            Program.pause(sc);
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

        Program.pause(sc);
    }

    private void menuBorrarAtributo(Scanner sc)
    {
        Program.clearScreen();
        if(atributos.isEmpty())
        {
            System.out.println("No hay atributos.");
            Program.pause(sc);
            return;
        }

        for(int i=0; i<atributos.size(); i++)
            System.out.println(i + " - " + atributos.get(i));

        System.out.print("Índice a borrar: ");
        int idx = Integer.parseInt(sc.nextLine());
        if(idx < 0 || idx >= atributos.size()) return;

        borrarAtributo(atributos.get(idx));
        Program.pause(sc);
    }

    private void menuAgregarMetodo(Scanner sc)
    {
        System.out.print("Nuevo método: ");
        String m = sc.nextLine();
        agregarMetodo(m);
        Program.pause(sc);
    }

    private void menuEditarMetodo(Scanner sc)
    {
        Program.clearScreen();
        if(metodos.isEmpty())
        {
            System.out.println("No hay métodos.");
            Program.pause(sc);
            return;
        }

        for(int i=0; i<metodos.size(); i++)
            System.out.println(i + " - " + metodos.get(i));

        System.out.print("Índice a editar: ");
        int idx = Integer.parseInt(sc.nextLine());
        if(idx < 0 || idx >= metodos.size()) return;

        String viejo = metodos.get(idx);
        System.out.print("Nuevo valor: ");
        String nuevo = sc.nextLine();
        editarMetodo(viejo, nuevo);

        Program.pause(sc);
    }

    private void menuBorrarMetodo(Scanner sc)
    {
        Program.clearScreen();
        if(metodos.isEmpty())
        {
            System.out.println("No hay métodos.");
            Program.pause(sc);
            return;
        }

        for(int i=0; i<metodos.size(); i++)
            System.out.println(i + " - " + metodos.get(i));

        System.out.print("Índice a borrar: ");
        int idx = Integer.parseInt(sc.nextLine());
        if(idx < 0 || idx >= metodos.size()) return;

        borrarMetodo(metodos.get(idx));
        Program.pause(sc);
    }

    private void menuCambiarTipoClase(Scanner sc)
    {
        Program.clearScreen();
        System.out.println("Tipo actual: " + (tipoClase != null ? tipoClase : "null"));
        System.out.println("0. Normal");
        System.out.println("1. Interface");
        System.out.println("2. Abstracta");
        System.out.print("Opción: ");
        int op = Integer.parseInt(sc.nextLine());

        switch(op)
        {
            case 0: tipoClase = ClaseEnums.TipoClase.Normal; break;
            case 1: tipoClase = ClaseEnums.TipoClase.Interface; break;
            case 2: tipoClase = ClaseEnums.TipoClase.Abstracta; break;
        }

        Program.pause(sc);
    }

    //====================================
    // HEREDAR BY: ZORRODEV 2026 (⁀ᗢ⁀)
    //====================================
   private void menuHeredar(Scanner sc) 
    {
        Program.clearScreen();
        System.out.println("===== HEREDAR =====");

        // Listar clases compatibles (solo POO y no sealed) BY: ZORRODEV 2026 (⁀ᗢ⁀)
        List<ClaseEntidad> lista = Program.entidades;

        int index = 0;
        for (ClaseEntidad ce : lista) 
        {
            if (ce instanceof ClasePOO && ce != this)
                System.out.println(index + " - " + ce.getNombreClase());
            index++;
         }

        System.out.println("X - Cancelar");
        System.out.print("Seleccione clase padre: ");
        String linea = sc.nextLine();

        int idx;
        try { idx = Integer.parseInt(linea); }
        catch(Exception e) { return; }

        if (idx < 0 || idx >= lista.size()) return;

        ClaseEntidad padre = lista.get(idx);

        if (!(padre instanceof ClasePOO)) 
        {
            System.out.println("No puedes heredar de SQL o de una clase no POO.");
            Program.pause(sc);
            return;
        }

        if (padre instanceof SealedClass) 
        {
            System.out.println("No puedes heredar de una clase sellada.");
            Program.pause(sc);
            return;
        }

        // Aplicar herencia (°o°)
        this.clasePadre = padre.getNombreClase();
        padre.getClasesHijas().add(this.getNombreClase());

        // Reordenar decimales (๑°o°๑)
        Program.ReordenarEntidades();

        System.out.println("Herencia aplicada correctamente.");
        Program.pause(sc);
    }

    //============================================
    //SELLAR / DESELLAR ZORRODEV 2026 (๑°o°๑)
    //============================================
    private void toggleSealed()
    {
        esSealed = !esSealed;
        if(esSealed)
        {
            this.tipoClase = null;
            System.out.println("Clase sellada (no podrá ser heredada).");
        }
        else
        {
            // Al desellar, vuelve a ser POO normal. (๑°o°๑)
            this.tipoClase = ClaseEnums.TipoClase.Normal;
            System.out.println("Clase desellada (puede ser heredada de nuevo).");
        }
    }

    //================================
    //ENUMERACIONES ZORRODEV 2026 (๑°o°๑)
    //================================
    private void menuAgregarEnumeracion(Scanner sc)
    {
        Program.clearScreen();
        System.out.print("Nombre de la enumeración: ");
        String nombreEnum = sc.nextLine();
        
        if(nombreEnum.trim().isEmpty())
        {
            Program.pause(sc);
            return;
        }

        if(!ClaseEnums.EnumeracionesCreadas.containsKey(nombreEnum))
        {
            int id = ClaseEnums.EnumeracionesCreadas.size() + 1;
            ClaseEnums.EnumeracionesCreadas.put(nombreEnum, id);
            System.out.println("Enumeración creada: " + nombreEnum);
        }
        else
        {
            System.out.println("Ya existe una enumeración con ese nombre.");
        }
        Program.pause(sc);
    }

    private void menuListarEnumeraciones(Scanner sc)
    {
        Program.clearScreen();
        if(ClaseEnums.EnumeracionesCreadas.isEmpty())
        {
            System.out.println("No hay enumeraciones creadas.");
            Program.pause(sc);
            return;
        }

        System.out.println("===== ENUMERACIONES =====");
        for(String key : ClaseEnums.EnumeracionesCreadas.keySet())
            System.out.println("- " + key);

        System.out.print("Nombre de la enumeración para editar (o vacío para regresar): ");
        String nombre = sc.nextLine();
        if(nombre.trim().isEmpty()) return;

        ClaseEnums.ListaEnumeraciones.putIfAbsent(nombre, new java.util.ArrayList<>());

        boolean salir = false;
        while(!salir)
        {
            Program.clearScreen();
            System.out.println("\n===== ENUM: " + nombre + " =====");
            
            java.util.List<String> vals = ClaseEnums.ListaEnumeraciones.get(nombre);
            for(int i=0; i<vals.size(); i++)
                System.out.println(i + " - " + vals.get(i));

            System.out.println("1. Agregar valor");
            System.out.println("0. Regresar");
            System.out.print("Opción: ");
            
            int op = Integer.parseInt(sc.nextLine());

            switch(op)
            {
                case 1:
                    System.out.print("Nuevo valor: ");
                    String v = sc.nextLine();
                    if(v != null && !v.trim().isEmpty())
                        vals.add(v);
                    break;
                case 0:
                    salir = true;
                    break;
            }
        }
    }


    //============================
    //ANALISIS ADOO ZORRODEV (⁀ᗢ⁀)
    //============================
    private void menuAgregarAnalisisADOO(Scanner sc)
    {
        Program.clearScreen();
        System.out.println("\n===== AGREGAR / EDITAR ANALISIS ADOO =====");
        System.out.println("Actual:");
        System.out.println(Program.wrapText(analisisADOO, 80));
        
        System.out.println("\nNuevo análisis:");
        String txt = sc.nextLine();
        
        if(!txt.trim().isEmpty())
            setAnalisisADOO(txt);

        Program.pause(sc);
    }

    private void menuMostrarAnalisisADOO()
    {
        Program.clearScreen();
        System.out.println("===== ANALISIS ADOO DE " + nombreClase + " =====");
        System.out.println(Program.wrapText(analisisADOO, 80));
        System.out.println("=====================================");
        Program.pause(sc);
    }


    //=========================
    //MOSTRAR PROPIEDADES ZORRODEV 2026 (⁄ ⁄>⁄◡⁄<⁄ ⁄)
    //=========================
    private void mostrarPropiedades()
    {
       Program.clearScreen();

       Colorearse();
       System.out.println("===== PROPIEDADES DE " + nombreClase + " =====");
       System.out.print(Program.RESET);

       System.out.println("Namespace = " + namespace);
       System.out.println("TipoClase: " + tipoClase);
       System.out.println("Sealed = " + (esSealed ? "SI" : "NO"));
       System.out.println("Padre = " + (clasePadre == null ? "Ninguno" : clasePadre));
       System.out.println("Orden = " + ordenDecimal);
        
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

    //========================
    // POLIMORFISMO VISUAL ZORRODEV 2026 (๑•́‧̫•̀๑)
    //========================
    @Override
    public void Colorearse()
    {
        System.out.print(Program.NARANJA);
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
}
