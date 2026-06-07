//=====================================
//Autor : Christopher Diaz Gastelum
//Firma : ZORRO DEVELOPER (＾▽＾) (ง'̀-'́)ง
//Proyecto : AnalisisADOO
//Clase : DataJSON.java
//Descripción:
//Guardado y carga COMPLETA en JSON de:
//- AnalisisGeneral
//- Entidades POO / SQL / Sealed
//- Atributos completos (POO y SQL)
//- Métodos
//- Herencia
//- TipoClase
//- Colores
//- Orden
//=====================================

package AnalisisADOO.Data;

import AnalisisADOO.Clases.*;
import AnalisisADOO.SubClases.*;

import java.io.*;
import java.util.*;

public class DataJSON
{
    // Resultado de carga  (✿◠‿◠)
    public static class ResultadoCarga
    {
        public String analisisGeneral;
        public List<ClaseEntidad> entidades;
    }

    //=====================================
    // GUARDAR JSON ZORRODEV 2026 (ง'̀-'́)ง
    //=====================================
    public static boolean Guardar(String archivo, String analisisGeneral, List<ClaseEntidad> entidades)
    {
        try(BufferedWriter bw = new BufferedWriter(new FileWriter(archivo)))
        {
            bw.write("{\n");
            bw.write("  \"AnalisisGeneral\": " + toJson(analisisGeneral) + ",\n");
            bw.write("  \"Entidades\": [\n");

            for(int i=0; i<entidades.size(); i++)
            {
                ClaseEntidad ce = entidades.get(i);

                String tipo = "POO";
                if(ce instanceof ClaseSQL) tipo = "SQL";
                if(ce instanceof SealedClass) tipo = "SEALED";

                bw.write("    {\n");
                bw.write("      \"Nombre\": " + toJson(ce.getNombreClase()) + ",\n");
                bw.write("      \"Namespace\": " + toJson(ce.getNamespace()) + ",\n");
                bw.write("      \"Tipo\": " + toJson(tipo) + ",\n");
                bw.write("      \"TipoClase\": " + toJson(ce.getTipoClase() == null ? "null" : ce.getTipoClase().toString()) + ",\n");
                bw.write("      \"Padre\": " + toJson(ce.getClasePadre()) + ",\n");
                bw.write("      \"Orden\": " + ce.getOrdenDecimal() + ",\n");
                bw.write("      \"Analisis\": " + toJson(ce.getAnalisisADOO()) + ",\n");

                // Atributos COMPLETOS
                bw.write("      \"Atributos\": [\n");
                for(int j=0; j<ce.getAtributos().size(); j++)
                {
                    ClaseAtributo a = ce.getAtributos().get(j);
                    bw.write("        {\n");
                    bw.write("          \"Nombre\": " + toJson(a.nombre) + ",\n");
                    bw.write("          \"Encapsulacion\": " + toJson(a.encapsulacion) + ",\n");
                    bw.write("          \"TipoAtributo\": " + toJson(a.tipoAtributo) + ",\n");
                    bw.write("          \"TipoDato\": " + toJson(a.tipoDato) + ",\n");
                    bw.write("          \"PK\": " + a.esPK + ",\n");
                    bw.write("          \"FK\": " + a.esFK + ",\n");
                    bw.write("          \"Nullable\": " + a.esNullable + "\n");
                    bw.write("        }");
                    if(j < ce.getAtributos().size()-1) bw.write(",");
                    bw.write("\n");
                }
                bw.write("      ],\n");

                // Métodos
                bw.write("      \"Metodos\": [");
                for(int j=0; j<ce.getMetodos().size(); j++)
                {
                    bw.write(toJson(ce.getMetodos().get(j)));
                    if(j < ce.getMetodos().size()-1) bw.write(", ");
                }
                bw.write("],\n");

                // Clases hijas
                bw.write("      \"ClasesHijas\": [");
                for(int j=0; j<ce.getClasesHijas().size(); j++)
                {
                    bw.write(toJson(ce.getClasesHijas().get(j)));
                    if(j < ce.getClasesHijas().size()-1) bw.write(", ");
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
            e.printStackTrace();
            return false;
        }
    }

    //=====================================
    // CARGAR JSON ZORRODEV 2026 (ノಠ益ಠ)ノ
    //=====================================
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

            String[] objs = dividirObjetos(bloqueEntidades);

            for(String obj : objs)
            {
                String nombre   = extraerCampo(obj, "Nombre");
                String ns       = extraerCampo(obj, "Namespace");
                String tipo     = extraerCampo(obj, "Tipo");
                String analisis = extraerCampo(obj, "Analisis");
                String padre    = extraerCampo(obj, "Padre");
                String tipoClase= extraerCampo(obj, "TipoClase");
                int orden       = extraerEntero(obj, "Orden");

                ClaseEntidad ce;

                if("SQL".equalsIgnoreCase(tipo))
                    ce = new ClaseSQL(nombre, ns);
                else if("SEALED".equalsIgnoreCase(tipo))
                    ce = new SealedClass(nombre, ns);
                else
                    ce = new ClasePOO(nombre, ns);

                ce.setAnalisisADOO(analisis);
                ce.setClasePadre(padre);
                ce.setOrdenDecimal(orden);

                // TipoClase (¬_¬)
                if(!"null".equals(tipoClase))
                    ce.setTipoClase(ClaseEnums.TipoClase.valueOf(tipoClase));

                // Atributos COMPLETOS (¬_¬)
                List<String> bloquesA = extraerObjetos(obj, "Atributos");
                for(String ba : bloquesA)
                {
                    ClaseAtributo a = new ClaseAtributo(extraerCampo(ba, "Nombre"));
                    a.encapsulacion = extraerCampo(ba, "Encapsulacion");
                    a.tipoAtributo  = extraerCampo(ba, "TipoAtributo");
                    a.tipoDato      = extraerCampo(ba, "TipoDato");
                    a.esPK          = extraerBoolean(ba, "PK");
                    a.esFK          = extraerBoolean(ba, "FK");
                    a.esNullable    = extraerBoolean(ba, "Nullable");

                    ce.getAtributos().add(a);
                }

                // Métodos (¬_¬)
                for(String m : extraerLista(obj, "Metodos"))
                    ce.agregarMetodo(m);

                // Clases hijas (¬_¬)
                for(String h : extraerLista(obj, "ClasesHijas"))
                    ce.getClasesHijas().add(h);

                res.entidades.add(ce);
            }

            return res;
        }
        catch(Exception e)
        {
            e.printStackTrace();
            return null;
        }
    }

    //=====================================
    // UTILIDADES JSON ZORRODEV 2026
    //=====================================
    private static String toJson(String s)
    {
        if(s == null) s = "";
        return "\"" + s.replace("\\", "\\\\").replace("\"", "\\\"") + "\"";
    }

    private static int extraerEntero(String json, String campo)
    {
        try { return Integer.parseInt(extraerCampo(json, campo)); }
        catch(Exception e) { return 0; }
    }

    private static boolean extraerBoolean(String json, String campo)
    {
        return json.contains("\"" + campo + "\": true");
    }

    private static String[] dividirObjetos(String array)
    {
        String contenido = array.substring(1, array.length()-1).trim();
        if(contenido.isEmpty()) return new String[0];
        return contenido.split("\\},\\s*\\{");
    }

    private static List<String> extraerObjetos(String json, String campo)
    {
        List<String> lista = new ArrayList<>();
        String arr = extraerArray(json, campo);
        if(arr == null) return lista;

        String contenido = arr.substring(1, arr.length()-1).trim();
        if(contenido.isEmpty()) return lista;

        String[] objs = contenido.split("\\},\\s*\\{");
        for(String o : objs)
        {
            String x = o.trim();
            if(!x.startsWith("{")) x = "{" + x;
            if(!x.endsWith("}"))   x = x + "}";
            lista.add(x);
        }
        return lista;
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
                    return json.substring(bracket, i+1);
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
