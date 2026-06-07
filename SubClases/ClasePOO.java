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
import AnalisisADOO.Clases.ClaseAtributo;
import AnalisisADOO.Clases.ClaseTipoAtributo;
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
        this.tipoClase      = ClaseEnums.TipoClase.Normal;
        this.colorClase     = "Naranja";
        this.colorAtributos = "Verde";
        this.colorMetodos   = "Rojo";
        this.esSealed       = false;
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
                case 14: mostrarPropiedades(sc); break;
                case 0: salir = true; break;
            }
        }
    }

    //===========================================
    // SUBMENÚ PROFESIONAL PARA AGREGAR ATRIBUTO
    //===========================================
    private void menuAgregarAtributo(Scanner sc)
    {
        Program.clearScreen();
        System.out.println("===== NUEVO ATRIBUTO POO =====");

        // Nombre
        System.out.print("Nombre del atributo: ");
        String nombre = sc.nextLine();
        if(nombre.trim().isEmpty()) return;

        ClaseAtributo atr = new ClaseAtributo(nombre);

        // Encapsulación
        System.out.println("\nEncapsulación:");
        for(int i=0; i<ClaseTipoAtributo.Encapsulaciones.size(); i++)
            System.out.println((i+1) + ". " + ClaseTipoAtributo.Encapsulaciones.get(i));
        System.out.print("Opción: ");
        int enc = Integer.parseInt(sc.nextLine()) - 1;
        if(enc >= 0 && enc < ClaseTipoAtributo.Encapsulaciones.size())
            atr.encapsulacion = ClaseTipoAtributo.Encapsulaciones.get(enc);

        // Tipo de atributo
        System.out.println("\nTipo de atributo:");
        for(int i=0; i<ClaseTipoAtributo.TiposAtributo.size(); i++)
            System.out.println((i+1) + ". " + ClaseTipoAtributo.TiposAtributo.get(i));
        System.out.print("Opción: ");
        int ta = Integer.parseInt(sc.nextLine()) - 1;
        if(ta >= 0 && ta < ClaseTipoAtributo.TiposAtributo.size())
            atr.tipoAtributo = ClaseTipoAtributo.TiposAtributo.get(ta);

        // Tipo de dato
        atr.tipoDato = elegirTipoDato(sc);

        atributos.add(atr);

        System.out.println("\nAtributo agregado correctamente.");
        Program.pause(sc);
    }

    //===========================================
    // SUBMENÚ PARA EDITAR ATRIBUTO
    //===========================================
    private void menuEditarAtributo(Scanner sc)
    {
        Program.clearScreen();
        if(atributos.isEmpty())
        {
            System.out.println("No hay atributos.");
            Program.pause(sc);
            return;
        }

        System.out.println("===== EDITAR ATRIBUTO =====");
        for(int i=0; i<atributos.size(); i++)
            System.out.println(i + " - " + atributos.get(i).toPOOString());

        System.out.print("Índice a editar: ");
        int idx = Integer.parseInt(sc.nextLine());
        if(idx < 0 || idx >= atributos.size()) return;

        ClaseAtributo atr = atributos.get(idx);

        boolean salir = false;
        while(!salir)
        {
            Program.clearScreen();
            System.out.println("Editando: " + atr.toPOOString());
            System.out.println("1. Cambiar nombre");
            System.out.println("2. Cambiar encapsulación");
            System.out.println("3. Cambiar tipo de atributo");
            System.out.println("4. Cambiar tipo de dato");
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
                    System.out.println("\nEncapsulación:");
                    for(int i=0; i<ClaseTipoAtributo.Encapsulaciones.size(); i++)
                        System.out.println((i+1) + ". " + ClaseTipoAtributo.Encapsulaciones.get(i));
                    System.out.print("Opción: ");
                    int enc = Integer.parseInt(sc.nextLine()) - 1;
                    if(enc >= 0 && enc < ClaseTipoAtributo.Encapsulaciones.size())
                        atr.encapsulacion = ClaseTipoAtributo.Encapsulaciones.get(enc);
                    break;

                case 3:
                    System.out.println("\nTipo de atributo:");
                    for(int i=0; i<ClaseTipoAtributo.TiposAtributo.size(); i++)
                        System.out.println((i+1) + ". " + ClaseTipoAtributo.TiposAtributo.get(i));
                    System.out.print("Opción: ");
                    int ta = Integer.parseInt(sc.nextLine()) - 1;
                    if(ta >= 0 && ta < ClaseTipoAtributo.TiposAtributo.size())
                        atr.tipoAtributo = ClaseTipoAtributo.TiposAtributo.get(ta);
                    break;

                case 4:
                    atr.tipoDato = elegirTipoDato(sc);
                    break;

                case 0:
                    salir = true;
                    break;
            }
        }
    }

    //===========================================
    // BORRAR ATRIBUTO
    //===========================================
    private void menuBorrarAtributo(Scanner sc)
    {
        Program.clearScreen();
        if(atributos.isEmpty())
        {
            System.out.println("No hay atributos.");
            Program.pause(sc);
            return;
        }

        System.out.println("===== BORRAR ATRIBUTO =====");
        for(int i=0; i<atributos.size(); i++)
            System.out.println(i + " - " + atributos.get(i).toPOOString());

        System.out.print("Índice a borrar: ");
        int idx = Integer.parseInt(sc.nextLine());
        if(idx < 0 || idx >= atributos.size()) return;

        atributos.remove(idx);
        System.out.println("Atributo eliminado.");
        Program.pause(sc);
    }

    //===========================================
    // MENÚ PARA ELEGIR TIPO DE DATO
    //===========================================
    private String elegirTipoDato(Scanner sc)
    {
        System.out.println("\n===== TIPO DE DATO =====");
        System.out.println("1. string");
        System.out.println("2. int");
        System.out.println("3. bool");
        System.out.println("4. double");
        System.out.println("5. float");
        System.out.println("6. char");
        System.out.println("7. DateTime");
        System.out.println("8. List<T>");
        System.out.println("9. Dictionary<K,V>");
        System.out.println("10. object");
        System.out.println("11. dynamic");
        System.out.println("12. decimal");
        System.out.println("13. long");
        System.out.println("14. short");
        System.out.println("15. byte");
        System.out.println("16. Enumeración creada");
        System.out.println("17. Clase POO del proyecto");
        System.out.print("Opción: ");

        int op = Integer.parseInt(sc.nextLine());

        switch(op)
        {
            case 1: return "string";
            case 2: return "int";
            case 3: return "bool";
            case 4: return "double";
            case 5: return "float";
            case 6: return "char";
            case 7: return "DateTime";

            case 8:
                System.out.print("Tipo interno T: ");
                return "List<" + sc.nextLine() + ">";

            case 9:
                System.out.print("Tipo clave K: ");
                String k = sc.nextLine();
                System.out.print("Tipo valor V: ");
                String v = sc.nextLine();
                return "Dictionary<" + k + "," + v + ">";

            case 10: return "object";
            case 11: return "dynamic";
            case 12: return "decimal";
            case 13: return "long";
            case 14: return "short";
            case 15: return "byte";

            case 16:
                System.out.println("Enumeraciones disponibles:");
                for(String key : ClaseEnums.EnumeracionesCreadas.keySet())
                    System.out.println("- " + key);
                System.out.print("Nombre: ");
                return sc.nextLine();

            case 17:
                System.out.println("Clases POO disponibles:");
                for(ClaseEntidad ce : Program.entidades)
                    if(ce instanceof ClasePOO)
                        System.out.println("- " + ce.getNombreClase());
                System.out.print("Nombre: ");
                return sc.nextLine();
        }

        return "string";
    }

    //===========================================
    // MÉTODOS (IGUAL QUE ANTES)
    //===========================================
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

        this.clasePadre = padre.getNombreClase();
        padre.getClasesHijas().add(this.getNombreClase());

        Program.ReordenarEntidades();

        System.out.println("Herencia aplicada correctamente.");
        Program.pause(sc);
    }

    //============================================
    // SELLAR / DESELLAR ZORRODEV 2026 (๑°o°๑)
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
            this.tipoClase = ClaseEnums.TipoClase.Normal;
            System.out.println("Clase desellada (puede ser heredada de nuevo).");
        }
    }

    //================================
    // ENUMERACIONES ZORRODEV 2026
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
    // ANALISIS ADOO ZORRODEV (⁀ᗢ⁀)
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
        Program.pause(new Scanner(System.in));
    }

    //=========================
    // MOSTRAR PROPIEDADES ZORRODEV 2026 (⁄ ⁄>⁄◡⁄<⁄ ⁄)
    //=========================
    private void mostrarPropiedades(Scanner sc)
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
        for(ClaseAtributo a : atributos)
            System.out.println("- " + a.toPOOString());

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
