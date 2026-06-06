//=====================================
//Autor : Christopher Diaz Gastelum
//Firma : ZORRO DEVELOPER (＾▽＾)
//Proyecto : AnalisisADOO
//Descripción del proyecto:
//  Documentador basado en ADOO que ayuda a generar análisis,
//  descripciones y diagramas de clases en consola. Este proyecto
//  es una versión libre, pequeña y educativa. Si en el futuro
//  creo un documentador profesional, será otro proyecto con una
//  arquitectura distinta, posiblemente orientada a datos o en inglés.
//
//Licencia:
//  Este programa es público y puede modificarse, copiarse,
//  compartirse y venderse libremente. Sin embargo, en caso de
//  vender o redistribuir, es obligatorio remover mi nombre y firma
//  para liberarme de cualquier mal uso.
//  Si yo me dedico a hacer un documentador seria otro proyecto el cual no es compartido
//  libremente como este.Sin embargo la estructura seria en otra 
//  orientación talvez en ingles o datadriven.
//Fecha : 06/06/2026
//Clase : Program.java
//Descripción de la clase:
//Punto de entrada del sistema Documentador. Controla el menú principal,
//la lista de entidades y las operaciones generales.
//=====================================

package AnalisisADOO;

import AnalisisADOO.Clases.ClaseEntidad;
import AnalisisADOO.SubClases.ClasePOO;
import AnalisisADOO.SubClases.ClaseSQL;
import AnalisisADOO.Data.DataJSON;
import AnalisisADOO.Data.DataTEXT;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.Scanner;

public class Program
{

//#region DATOS (ATRIBUTOS/LISTAS/STRING/INT/ETC)
    ///<sumary>
    ///es static porque esta variable SOLO ESTA CLASE LO APLICA Y AQUI TE QUEDAS QUIETA NO TE MUEVAS.  (^‿^) ZORRO DEVELOPER 2026
    ///"Analisis general del sistema Documentador de Clases POO/SQL."  (≧◡≦) ZORRO DEVELOPER 2026 
    /// <summary/>
   public static String AnalisisGeneral = "";
    ///<sumary>
    /// tenemos que guardar en una lista todas las entidades que
    /// vayamos agregando en la ejecución del programa.
    /// El cual nos ayudara en el analisis y documentación estilo ADOO. (≧◡≦) ZORRO DEVELOPER 2026
    /// LISTA GLOBAL DE ENTIDADES
    /// <sumary/>
    public static List<ClaseEntidad> entidades = new ArrayList<>();
    //ZORRODEV 2026 (≧◡≦) ni para que comento que es este boleano
    public static boolean cambiosSinGuardar = false;
    //ZORRODEV 2026 (≧◡≦) ni para que comento que son estos sets(ಠ_ಠ)(¬_¬)
    public static final String RESET  = "\u001B[0m";
    public static final String NARANJA = "\u001B[38;5;208m";
    public static final String AZUL    = "\u001B[34m";
    public static final String VERDE   = "\u001B[32m";
    public static final String ROJO    = "\u001B[31m";
    public static final String ROSA    = "\u001B[35m";
    public static final String DORADO  = "\u001B[33m"; 
//#endregion DATOS (ATRIBUTOS/LISTAS/STRING/INT/ETC)
//#region METODO MAIN
    public static void main(String[] ZORRODEVELOPER) //Firmado por Zorro developer (≧◡≦) ♡ cambien a args si gustan.
    {
        Scanner sc = new Scanner(System.in);
        boolean salir = false;

        while(!salir)
        {
            clearScreen();
            System.out.println("=====================================");
            System.out.println("===== Analisis Diseñado a Objetos ===");
            System.out.println("=====================================");
            System.out.println("1. Mostrar Análisis ADOO General");
            System.out.println("2. Crear Entidad");
            System.out.println("3. Seleccionar Entidad");
            System.out.println("4. Mostrar Diagrama de Clases");
            System.out.println("5. Agregar / Editar Análisis ADOO General");
            System.out.println("6. Guardar en JSON");
            System.out.println("7. Cargar desde JSON");
            System.out.println("8. Exportar a TXT");
            System.out.println("0. Salir");
            System.out.println("=====================================");
            System.out.print("Opción: ");

            String linea = sc.nextLine();
            int op = 0;
            try { op = Integer.parseInt(linea); } catch(Exception e) {}

            switch(op)
            {
                case 1: MostrarAnalisisGeneral(sc); break;
                case 2: CrearEntidad(sc); break;
                case 3: SeleccionarEntidad(sc); break;
                case 4: MostrarDiagramas(sc); break;
                case 5: AgregarAnalisisGeneral(sc); break;
                case 6: GuardarJSON(sc); break;
                case 7: CargarJSON(sc); break;
                case 8: ExportarTXT(sc); break;
                case 0: salir = ConfirmarSalida(sc); break;
            }
        }
    }
//#endregion METODO MAIN


//#region METODOS/TAREAS

    //========================
    // UTILIDADES
    //========================
    public static void clearScreen()
    {
        try
        {
            System.out.print("\033[H\033[2J");
            System.out.flush();
        }
        catch(Exception e)
        {
            // Si no soporta ANSI, no pasa nada.
        }
    }

    public static void pause(Scanner sc)
    {
        System.out.println("\nPresione ENTER para regresar...");
        sc.nextLine();
    }

    public static String wrapText(String texto, int ancho)
    {
        if(texto == null) return "";
        String[] palabras = texto.split(" ");
        StringBuilder sb = new StringBuilder();
        int col = 0;

        for(String p : palabras)
        {
            if(col + p.length() + 1 > ancho)
            {
                sb.append("\n");
                col = 0;
            }
            sb.append(p).append(" ");
            col += p.length() + 1;
        }
        return sb.toString();
    }

    //========================
    // OPCION 1: ANALISIS GENERAL
    //========================
    public static void MostrarAnalisisGeneral(Scanner sc)
    {
        clearScreen();
        System.out.println("===== ANALISIS ADOO GENERAL =====");
        if(AnalisisGeneral == null || AnalisisGeneral.trim().isEmpty())
        {
            System.out.println("Aún no tienes el análisis general para este proyecto.");
            System.out.println("Se recomienda agregar uno desde el menú.");
        }
        else
        {
            System.out.println(wrapText(AnalisisGeneral, 80));
        }
        System.out.println("=====================================");
        pause(sc);
    }

    public static void AgregarAnalisisGeneral(Scanner sc)
    {
        clearScreen();
        System.out.println("===== AGREGAR / EDITAR ANALISIS ADOO GENERAL =====");
        System.out.println("Actual:");
        System.out.println(wrapText(AnalisisGeneral, 80));
        System.out.println("\nNuevo análisis general:");
        String txt = sc.nextLine();
        if(!txt.trim().isEmpty())
        {
            AnalisisGeneral = txt;
            cambiosSinGuardar = true;
            System.out.println("Análisis general actualizado.");
        }
        else
        {
            System.out.println("No se actualizó porque el texto está vacío.");
        }
        System.out.println("=====================================");
        pause(sc);
    }

    //========================
    // OPCION 2: CREAR ENTIDAD
    //========================
    public static void CrearEntidad(Scanner sc)
    {
        clearScreen();
        System.out.println("===== CREAR ENTIDAD =====");
        System.out.print("Nombre de la entidad: ");
        String nombre = sc.nextLine();
        if(nombre.trim().isEmpty())
        {
            System.out.println("Nombre vacío, se cancela la creación.");
            pause(sc);
            return;
        }

        System.out.print("Namespace: ");
        String ns = sc.nextLine();
        if(ns.trim().isEmpty())
            ns = "DefaultNamespace";

        System.out.println("Tipo de entidad:");
        System.out.println("1. POO");
        System.out.println("2. SQL");
        System.out.println("0. Cancelar");
        System.out.print("Opción: ");
        String linea = sc.nextLine();
        int tipo = 0;
        try { tipo = Integer.parseInt(linea); } catch(Exception e) {}

        ClaseEntidad nueva = null;

        switch(tipo)
        {
            case 1:
                nueva = new ClasePOO(nombre, ns);
                break;
            case 2:
                nueva = new ClaseSQL(nombre, ns);
                break;
            default:
                System.out.println("Creación cancelada.");
                pause(sc);
                return;
        }

        double orden = entidades.size() + 1;
        nueva.setOrdenDecimal(orden);

        entidades.add(nueva);
        ReordenarEntidades();
        cambiosSinGuardar = true;
        System.out.println("Entidad creada y agregada a la lista.");
        System.out.println("=====================================");
        pause(sc);
    }

    //========================
    // OPCION 3: SELECCIONAR ENTIDAD
    //========================
    public static void SeleccionarEntidad(Scanner sc)
    {
        if(entidades.isEmpty())
        {
            clearScreen();
            System.out.println("No hay entidades creadas.");
            System.out.println("=====================================");
            pause(sc);
            return;
        }

        clearScreen();
        System.out.println("===== SELECCIONAR ENTIDAD =====");
        for(int i=0; i<entidades.size(); i++)
        {
            ClaseEntidad ce = entidades.get(i);
            System.out.println(i + " - " + ce.getNombreClase() + " (Orden: " + ce.getOrdenDecimal() + ")");
        }
        System.out.println("X - Cancelar");
        System.out.println("=====================================");
        System.out.print("Seleccione índice: ");
        String linea = sc.nextLine();
        int idx;
        try { idx = Integer.parseInt(linea); } catch(Exception e) { return; }

        if(idx < 0 || idx >= entidades.size())
            return;

        ClaseEntidad seleccionada = entidades.get(idx);
        seleccionada.ListarDatosEntidad(); // dentro de la entidad se ve su análisis, etc.

        ReordenarEntidades();
        cambiosSinGuardar = true;
    }

    //========================
    // OPCION 4: DIAGRAMA DE CLASES
    //========================
    public static void MostrarDiagramas(Scanner sc)
    {
        if(entidades.isEmpty())
        {
            clearScreen();
            System.out.println("No hay entidades para diagramar. Crea alguna primero.");
            System.out.println("=====================================");
            pause(sc);
            return;
        }

        clearScreen();
        System.out.println("===== DIAGRAMA DE CLASES (TEXTO) =====");

        Collections.sort(entidades, Comparator.comparingDouble(ClaseEntidad::getOrdenDecimal));

        for(ClaseEntidad ce : entidades)
        {
            String nombre = ce.getNombreClase();
            String padre  = ce.getClasePadre();
            String encabezado;

            if(padre != null && !padre.trim().isEmpty())
                encabezado = "=== " + nombre + " : " + padre + " ===";
            else
                encabezado = "=== " + nombre + " ===";

            // Color según tipo (muy simple, puedes refinarlo con getters específicos)
            String colorClase = NARANJA;
            if(ce instanceof ClaseSQL) colorClase = AZUL;

            System.out.println(colorClase + encabezado + RESET);
            System.out.println("Namespace: " + ce.getNamespace());
            System.out.println("Orden   : " + ce.getOrdenDecimal());

            System.out.println(VERDE + "Atributos:" + RESET);
            for(String a : ce.getAtributos())
                System.out.println(" - " + a);

            System.out.println(ROJO + "Métodos:" + RESET);
            for(String m : ce.getMetodos())
                System.out.println(" - " + m);

            System.out.println("=====================================\n");
        }

        pause(sc);
    }

    //========================
    // OPCION 6/7/8: GUARDAR / CARGAR / EXPORTAR
    //========================
    public static void GuardarJSON(Scanner sc)
    {
        clearScreen();
        System.out.print("Nombre de archivo JSON (ej: proyecto.json): ");
        String nombre = sc.nextLine();
        if(nombre.trim().isEmpty())
        {
            System.out.println("Nombre vacío, se cancela.");
            pause(sc);
            return;
        }

        boolean ok = DataJSON.Guardar(nombre, AnalisisGeneral, entidades);
        if(ok)
        {
            cambiosSinGuardar = false;
            System.out.println("Datos guardados en JSON correctamente.");
        }
        else
        {
            System.out.println("Ocurrió un error al guardar JSON.");
        }
        System.out.println("=====================================");
        pause(sc);
    }

    public static void CargarJSON(Scanner sc)
    {
        clearScreen();
        System.out.print("Nombre de archivo JSON a cargar: ");
        String nombre = sc.nextLine();
        if(nombre.trim().isEmpty())
        {
            System.out.println("Nombre vacío, se cancela.");
            pause(sc);
            return;
        }

        DataJSON.ResultadoCarga res = DataJSON.Cargar(nombre);
        if(res != null)
        {
            AnalisisGeneral = res.analisisGeneral;
            entidades       = res.entidades;
            ReordenarEntidades();
            cambiosSinGuardar = false;
            System.out.println("Datos cargados correctamente desde JSON.");
        }
        else
        {
            System.out.println("No se pudo cargar el archivo JSON.");
        }
        System.out.println("=====================================");
        pause(sc);
    }

    public static void ExportarTXT(Scanner sc)
    {
        clearScreen();
        System.out.print("Nombre de archivo TXT (ej: proyecto.txt): ");
        String nombre = sc.nextLine();
        if(nombre.trim().isEmpty())
        {
            System.out.println("Nombre vacío, se cancela.");
            pause(sc);
            return;
        }

        boolean ok = DataTEXT.Exportar(nombre, AnalisisGeneral, entidades);
        if(ok)
            System.out.println("Exportado correctamente a TXT.");
        else
            System.out.println("Ocurrió un error al exportar TXT.");

        System.out.println("=====================================");
        pause(sc);
    }

    //========================
    // LOGICA INTERNA: REORDENAR ENTIDADES
    //========================
    public static void ReordenarEntidades()
    {
        Collections.sort(entidades, Comparator.comparingDouble(ClaseEntidad::getOrdenDecimal));

        double base = 1.0;
        for(ClaseEntidad ce : entidades)
        {
            ce.setOrdenDecimal(base);
            base += 1.0;
        }
    }

    //========================
    // METODO DE APOYO
    //========================
    public static ClaseEntidad BuscarEntidadPorNombre(String nombre)
    {
        for(ClaseEntidad ce : entidades)
        {
            if(ce.getNombreClase().equalsIgnoreCase(nombre))
                return ce;
        }
        return null;
    }

    //========================
    // CONFIRMAR SALIDA
    //========================
    public static boolean ConfirmarSalida(Scanner sc)
    {
        if(cambiosSinGuardar)
        {
            System.out.println("\nNo has guardado los datos aún. ¿Quieres guardar antes de salir? (S/N)");
            String r = sc.nextLine();
            if(r.equalsIgnoreCase("S"))
            {
                GuardarJSON(sc);
            }
        }
        return true;
    }
//#endregion METODOS/TAREAS
}













