//=====================================
//Clase : DataJSON.java
//Descripción:
//Maneja guardado/carga muy simple en JSON de:
//- AnalisisGeneral
//- Lista de entidades (nombre, namespace, tipo simple, atributos, métodos, análisis)
//=====================================

package AnalisisADOO.Data;

import AnalisisADOO.Clases.ClaseEntidad;
import AnalisisADOO.SubClases.ClasePOO;
import AnalisisADOO.SubClases.ClaseSQL;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class DataJSON
{
    public static class ResultadoCarga
    {
        public String analisisGeneral;
        public List<ClaseEntidad> entidades;
    }

    public static boolean Guardar(String archivo, String analisisGeneral, List<ClaseEntidad> entidades)
    {
        try(BufferedWriter bw = new BufferedWriter(new FileWriter(archivo)))
        {
            bw.write("{\n");
            bw.write("  \"AnalisisGeneral\": " + toJsonString(analisisGeneral) + ",\n");
            bw.write("  \"Entidades\": [\n");

            for(int i=0; i<entidades.size(); i++)
            {
                ClaseEntidad ce = entidades.get(i);
                String tipo = "POO";
                if(ce instanceof ClaseSQL) tipo = "SQL";

                bw.write("    {\n");
                bw.write("      \"Nombre\": " + toJsonString(ce.getNombreClase()) + ",\n");
                bw.write("      \"Namespace\": " + toJsonString(ce.getNamespace()) + ",\n");
                bw.write("      \"Tipo\": " + toJsonString(tipo) + ",\n");
                bw.write("      \"Analisis\": " + toJsonString(ce.AnalisisADOO()) + ",\n");

                // Atributos
                bw.write("      \"Atributos\": [");
                for(int j=0; j<ce.getAtributos().size(); j++)
                {
                    bw.write(toJsonString(ce.getAtributos().get(j)));
                    if(j < ce.getAtributos().size()-1) bw.write(", ");
                }
                bw.write("],\n");

                // Métodos
                bw.write("      \"Metodos\": [");
                for(int j=0; j<ce.getMetodos().size(); j++)
                {
                    bw.write(toJsonString(ce.getMetodos().get(j)));
                    if(j < ce.getMetodos().size()-1) bw.write(", ");
                }
                bw.write("]\n");

                bw.write("    }");
                if(i < entidades.size()-1) bw.write(",");
                bw.write("\n");
            }

            bw.write("  ]\n");
            bw.write("}\n");
            return true;
        }
        catch(Exception e)
        {
            return false;
        }
    }

    private static String toJsonString(String s)
    {
        if(s == null) s = "";
        return "\"" + s.replace("\\", "\\\\").replace("\"", "\\\"") + "\"";
    }

    public static ResultadoCarga Cargar(String archivo)
    {
        try(BufferedReader br = new BufferedReader(new FileReader(archivo)))
        {
            StringBuilder sb = new StringBuilder();
            String line;
            while((line = br.readLine()) != null)
                sb.append(line).append("\n");

            String json = sb.toString();

            ResultadoCarga res = new ResultadoCarga();
            res.analisisGeneral = extraerCampo(json, "AnalisisGeneral");
            res.entidades = new ArrayList<>();

            String bloqueEntidades = extraerArray(json, "Entidades");
            if(bloqueEntidades == null) return res;

            // Parseo muy simple por objetos separados por "},{"
            String[] objs = bloqueEntidades.split("\\},\\s*\\{");
            for(String obj : objs)
            {
                String o = obj.trim();
                if(!o.startsWith("{")) o = "{" + o;
                if(!o.endsWith("}")) o = o + "}";

                String nombre    = extraerCampo(o, "Nombre");
                String ns        = extraerCampo(o, "Namespace");
                String tipo      = extraerCampo(o, "Tipo");
                String analisis  = extraerCampo(o, "Analisis");

                ClaseEntidad ce;
                if("SQL".equalsIgnoreCase(tipo))
                    ce = new ClaseSQL(nombre, ns);
                else
                    ce = new ClasePOO(nombre, ns);

                ce.setAnalisisADOO(analisis);

                // Atributos
                List<String> attrs = extraerLista(o, "Atributos");
                for(String a : attrs) ce.agregarAtributo(a);

                // Métodos
                List<String> mets = extraerLista(o, "Metodos");
                for(String m : mets) ce.agregarMetodo(m);

                res.entidades.add(ce);
            }

            return res;
        }
        catch(Exception e)
        {
            return null;
        }
    }

    private static String extraerCampo(String json, String campo)
    {
        String key = "\"" + campo + "\"";
        int idx = json.indexOf(key);
        if(idx < 0) return "";
        int colon = json.indexOf(":", idx);
        int start = json.indexOf("\"", colon+1) + 1;
        int end   = json.indexOf("\"", start);
        if(start < 0 || end < 0) return "";
        return json.substring(start, end).replace("\\\"", "\"").replace("\\\\", "\\");
    }

    private static String extraerArray(String json, String campo)
    {
        String key = "\"" + campo + "\"";
        int idx = json.indexOf(key);
        if(idx < 0) return null;
        int bracket = json.indexOf("[", idx);
        int level = 0;
        int i = bracket;
        for(; i<json.length(); i++)
        {
            char c = json.charAt(i);
            if(c == '[') level++;
            else if(c == ']')
            {
                level--;
                if(level == 0)
                {
                    return json.substring(bracket, i+1);
                }
            }
        }
        return null;
    }

    private static List<String> extraerLista(String json, String campo)
    {
        List<String> lista = new ArrayList<>();
        String arr = extraerArray(json, campo);
        if(arr == null) return lista;

        String contenido = arr.substring(1, arr.length()-1).trim();
        if(contenido.isEmpty()) return lista;

        // separar por comas simples
        String[] partes = contenido.split(",");
        for(String p : partes)
        {
            String s = p.trim();
            if(s.startsWith("\"")) s = s.substring(1);
            if(s.endsWith("\""))   s = s.substring(0, s.length()-1);
            s = s.replace("\\\"", "\"").replace("\\\\", "\\");
            if(!s.isEmpty()) lista.add(s);
        }
        return lista;
    }
}
