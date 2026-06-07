//=====================================
//Autor : Christopher Diaz Gastelum
//Firma : ZORRO DEVELOPER (＾▽＾) (ง'̀-'́)ง
//Proyecto : AnalisisADOO
//Descripción del proyecto:
//  Documentador basado en ADOO que ayuda a generar análisis,
//  descripciones y diagramas de clases en consola. Este proyecto
//  es educativo y libre para descargar, modificar y estudiar.
//
//Licencia (ZORRODEV 2026):
//  - Puedes descargarlo, copiarlo, modificarlo y usarlo libremente.
//  - NO puedes venderlo.
//  - NO puedes apropiarte del código ni remover esta licencia.
//  - Los derechos reservados pertenecen a ZORRODEV.
//  - El repositorio puede pasar de público a privado en el futuro.
//  - Si deseas crear un documentador profesional, deberás hacerlo
//    como un proyecto nuevo con tu propia arquitectura.
//Fecha : 06/06/2026
//Clase : Program.java
//Descripción de la clase:
//Punto de entrada del sistema Documentador. Controla el menú principal,
//la lista de entidades y las operaciones generales.
//=====================================

package AnalisisADOO;

import AnalisisADOO.Clases.ClaseEntidad;
import AnalisisADOO.Clases.ClaseAtributo;
import AnalisisADOO.SubClases.ClasePOO;
import AnalisisADOO.SubClases.ClaseSQL;
import AnalisisADOO.SubClases.SealedClass;
import AnalisisADOO.Data.DataJSON;
import AnalisisADOO.Data.DataTEXT;

import java.util.*;

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

    public static boolean cambiosSinGuardar = false;
    //ZORRODEV 2026 (≧◡≦) ni para que comento que son estos sets(ಠ_ಠ)(¬_¬)
    public static final String RESET  = "\u001B[0m";
    public static final String NARANJA = "\u001B[38;5;208m";
    public static final String AZUL    = "\u001B[34m";
    public static final String VERDE   = "\u001B[32m";
    public static final String ROJO    = "\u001B[31m";
    public static final String ROSA    = "\u001B[35m";
    public static final String DORADO  = "\u001B[33m";

    //#endregion DATOS

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

            int op = 0;
            try { op = Integer.parseInt(sc.nextLine()); } catch(Exception e) {}

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
    //#endregion MAIN

    //#region UTILIDADES
    public static void clearScreen()
    {
        try
        {
            System.out.print("\033[H\033[2J");
            System.out.flush();
        }
        catch(Exception e) {}
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
    //#endregion UTILIDADES

    //#region OPCION 1: ANALISIS GENERAL
    public static void MostrarAnalisisGeneral(Scanner sc)
    {
        clearScreen();
        System.out.println("===== ANALISIS ADOO GENERAL =====");
        if(AnalisisGeneral == null || AnalisisGeneral.trim().isEmpty())
        {
            System.out.println("Aún no tienes el análisis general.");
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
    //#endregion OPCION 1

    //#region OPCION 2: CREAR ENTIDAD
    public static void CrearEntidad(Scanner sc)
    {
        clearScreen();
        System.out.println("===== CREAR ENTIDAD =====");
        System.out.print("Nombre de la entidad: ");
        String nombre = sc.nextLine();
        if(nombre.trim().isEmpty())
        {
            System.out.println("Nombre vacío, se cancela.");
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
        System.out.println("3. SEALED");
        System.out.println("0. Cancelar");
        System.out.print("Opción: ");

        int tipo = 0;
        try { tipo = Integer.parseInt(sc.nextLine()); } catch(Exception e) {}

        ClaseEntidad nueva = null;

        switch(tipo)
        {
            case 1: nueva = new ClasePOO(nombre, ns); break;
            case 2: nueva = new ClaseSQL(nombre, ns); break;
            case 3: nueva = new SealedClass(nombre, ns); break;
            default:
                System.out.println("Creación cancelada.");
                pause(sc);
                return;
        }

        nueva.setOrdenDecimal(entidades.size() + 1);
        entidades.add(nueva);
        ReordenarEntidades();
        cambiosSinGuardar = true;

        System.out.println("Entidad creada correctamente.");
        pause(sc);
    }
    //#endregion OPCION 2

    //#region OPCION 3: SELECCIONAR ENTIDAD
    public static void SeleccionarEntidad(Scanner sc)
    {
        if(entidades.isEmpty())
        {
            clearScreen();
            System.out.println("No hay entidades creadas.");
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
        System.out.print("Seleccione índice: ");

        String linea = sc.nextLine();
        int idx;
        try { idx = Integer.parseInt(linea); } catch(Exception e) { return; }

        if(idx < 0 || idx >= entidades.size())
            return;

        entidades.get(idx).ListarDatosEntidad();
        ReordenarEntidades();
        cambiosSinGuardar = true;
    }
    //#endregion OPCION 3

    //#region OPCION 4: DIAGRAMA DE CLASES
    public static void MostrarDiagramas(Scanner sc)
    {
        if(entidades.isEmpty())
        {
            clearScreen();
            System.out.println("No hay entidades para diagramar.");
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

            String encabezado = (padre != null && !padre.trim().isEmpty())
                    ? "=== " + nombre + " : " + padre + " ==="
                    : "=== " + nombre + " ===";

            String color = NARANJA;
            if(ce instanceof ClaseSQL) color = AZUL;
            if(ce instanceof SealedClass) color = DORADO;

            System.out.println(color + encabezado + RESET);
            System.out.println("Namespace: " + ce.getNamespace());
            System.out.println("Orden   : " + ce.getOrdenDecimal());

            System.out.println(VERDE + "Atributos:" + RESET);
            for(ClaseAtributo a : ce.getAtributos())
                System.out.println(" - " + a.toPOOString());

            System.out.println(ROJO + "Métodos:" + RESET);
            for(String m : ce.getMetodos())
                System.out.println(" - " + m);

            System.out.println("=====================================\n");
        }

        pause(sc);
    }
    //#endregion OPCION 4

    //#region OPCION 6/7/8: GUARDAR / CARGAR / EXPORTAR
    public static void GuardarJSON(Scanner sc)
    {
        clearScreen();
        System.out.print("Nombre de archivo JSON: ");
        String nombre = sc.nextLine();
        if(nombre.trim().isEmpty())
        {
            System.out.println("Nombre vacío.");
            pause(sc);
            return;
        }

        boolean ok = DataJSON.Guardar(nombre, AnalisisGeneral, entidades);
        if(ok)
        {
            cambiosSinGuardar = false;
            System.out.println("Guardado correctamente.");
        }
        else
        {
            System.out.println("Error al guardar JSON.");
        }
        pause(sc);
    }

    public static void CargarJSON(Scanner sc)
    {
        clearScreen();
        System.out.print("Nombre de archivo JSON a cargar: ");
        String nombre = sc.nextLine();
        if(nombre.trim().isEmpty())
        {
            System.out.println("Nombre vacío.");
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
            System.out.println("Datos cargados correctamente.");
        }
        else
        {
            System.out.println("Error al cargar JSON.");
        }
        pause(sc);
    }

    public static void ExportarTXT(Scanner sc)
    {
        clearScreen();
        System.out.print("Nombre de archivo TXT: ");
        String nombre = sc.nextLine();
        if(nombre.trim().isEmpty())
        {
            System.out.println("Nombre vacío.");
            pause(sc);
            return;
        }

        boolean ok = DataTEXT.Exportar(nombre, AnalisisGeneral, entidades);
        if(ok)
            System.out.println("Exportado correctamente.");
        else
            System.out.println("Error al exportar TXT.");

        pause(sc);
    }
    //#endregion OPCION 6/7/8

    //#region LOGICA INTERNA
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

    public static ClaseEntidad BuscarEntidadPorNombre(String nombre)
    {
        for(ClaseEntidad ce : entidades)
            if(ce.getNombreClase().equalsIgnoreCase(nombre))
                return ce;
        return null;
    }
    //#endregion LOGICA INTERNA

    //#region CONFIRMAR SALIDA
    public static boolean ConfirmarSalida(Scanner sc)
    {
        if(cambiosSinGuardar)
        {
            System.out.println("\nNo has guardado los datos. ¿Guardar antes de salir? (S/N)");
            String r = sc.nextLine();
            if(r.equalsIgnoreCase("S"))
                GuardarJSON(sc);
        }
        return true;
    }
    //#endregion CONFIRMAR SALIDA
}
