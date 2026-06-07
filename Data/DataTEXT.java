//=====================================
//Autor : Christopher Diaz Gastelum
//Firma : ZORRO DEVELOPER (＾▽＾) (✿◠‿◠)
//Proyecto : AnalisisADOO
//Clase : DataTEXT.java
//Descripción:
//Exporta el proyecto COMPLETO a un archivo de texto legible.
//Incluye:
//- AnalisisGeneral
//- Entidades POO / SQL / SEALED
//- Atributos completos (POO y SQL)
//- Métodos
//- Herencia
//- TipoClase
//- PK / FK / NULL
//- Encapsulación
//- Tipo de atributo
//- Tipo de dato
//=====================================

package AnalisisADOO.Data;

import AnalisisADOO.Clases.ClaseEntidad;
import AnalisisADOO.Clases.ClaseAtributo;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.util.List;

public class DataTEXT
{
    public static boolean Exportar(String archivo, String analisisGeneral, List<ClaseEntidad> entidades)
    {
        try(BufferedWriter bw = new BufferedWriter(new FileWriter(archivo)))
        {
            bw.write("PROYECTO: AnalisisADOO\n");
            bw.write("=====================================\n\n");

            bw.write("ANALISIS ADOO GENERAL:\n");
            bw.write(analisisGeneral + "\n\n");
            bw.write("=====================================\n\n");

            for(ClaseEntidad ce : entidades)
            {
                bw.write("CLASE: " + ce.getNombreClase() + "\n");
                bw.write("Namespace : " + ce.getNamespace() + "\n");
                bw.write("TipoClase : " + (ce.getTipoClase() == null ? "SEALED / SQL" : ce.getTipoClase()) + "\n");
                bw.write("Padre     : " + (ce.getClasePadre() == null ? "Ninguno" : ce.getClasePadre()) + "\n");
                bw.write("Orden     : " + ce.getOrdenDecimal() + "\n\n");

                bw.write("Analisis ADOO de la clase:\n");
                bw.write(ce.getAnalisisADOO() + "\n\n");

                //=====================================
                // ATRIBUTOS COMPLETOS
                //=====================================
                bw.write("Atributos:\n");
                for(ClaseAtributo a : ce.getAtributos())
                {
                    bw.write(" - Nombre        : " + a.nombre + "\n");
                    bw.write("   Encapsulación : " + a.encapsulacion + "\n");
                    bw.write("   TipoAtributo  : " + a.tipoAtributo + "\n");
                    bw.write("   TipoDato      : " + a.tipoDato + "\n");
                    bw.write("   PK            : " + a.esPK + "\n");
                    bw.write("   FK            : " + a.esFK + "\n");
                    bw.write("   Nullable      : " + a.esNullable + "\n");
                    bw.write("\n");
                }

                //=====================================
                // MÉTODOS
                //=====================================
                bw.write("Metodos:\n");
                for(String m : ce.getMetodos())
                    bw.write(" - " + m + "\n");

                //=====================================
                // CLASES HIJAS
                //=====================================
                bw.write("\nClases Hijas:\n");
                if(ce.getClasesHijas().isEmpty())
                    bw.write(" - Ninguna\n");
                else
                    for(String h : ce.getClasesHijas())
                        bw.write(" - " + h + "\n");

                bw.write("\n-------------------------------------\n\n");
            }

            bw.write("Gracias por usar Documentador ADOO de ZORRODEV 2026 (✿◠‿◠)\n");
            return true;
        }
        catch(Exception e)
        {
            return false;
        }
    }
}
