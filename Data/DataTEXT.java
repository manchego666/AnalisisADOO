//=====================================
//Clase : DataTEXT.java
//Descripción:
//Exporta el proyecto a un archivo de texto legible.
//=====================================

package AnalisisADOO.Data;

import AnalisisADOO.Clases.ClaseEntidad;

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
                bw.write("Namespace: " + ce.getNamespace() + "\n");
                bw.write("Orden    : " + ce.getOrdenDecimal() + "\n\n");

                bw.write("Analisis ADOO de la clase:\n");
                bw.write(ce.AnalisisADOO() + "\n\n");

                bw.write("Atributos:\n");
                for(String a : ce.getAtributos())
                    bw.write(" - " + a + "\n");

                bw.write("\nMetodos:\n");
                for(String m : ce.getMetodos())
                    bw.write(" - " + m + "\n");

                bw.write("\n-------------------------------------\n\n");
            }

            bw.write("Gracias por usar Documentador ADOO de ZORRODEV 2026\n");
            return true;
        }
        catch(Exception e)
        {
            return false;
        }
    }
}
