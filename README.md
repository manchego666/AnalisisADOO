# 🦊 AnalisisADOO — Documentador ADOO en Consola  
**By: ZORRO DEVELOPER (＾▽＾) 2026**

AnalisisADOO es un **documentador educativo** basado en la metodología  
**Análisis y Diseño Orientado a Objetos (ADOO)**.  
Permite crear entidades **POO**, **SQL** y **SEALED**, generar análisis,  
diagramas de clases en texto, y exportar documentación en **JSON** y **TXT**.

Este proyecto es una versión **libre, pequeña y educativa**, creada para  
aprender arquitectura, diseño y documentación de clases en consola.

---

## ✨ Características Principales

- Crear entidades:
  - 🟧 **POO**
  - 🟦 **SQL**
  - 🟨 **SEALED**
- Atributos completos:
  - Encapsulación  
  - Tipo de atributo  
  - Tipo de dato  
  - PK / FK / Nullable  
- Métodos (solo POO y SEALED)
- Herencia entre clases POO
- Análisis ADOO por clase y general
- Diagrama de clases en texto
- Exportación:
  - 📄 JSON (estructura completa)
  - 📄 TXT (documentación legible)
- Colores ANSI para polimorfismo visual
- Sistema de ordenamiento automático
- Guardado y carga del proyecto

---

## 📦 Instalación

Clona el repositorio:

```bash
git clone https://github.com/manchego666/AnalisisADOO.git
```


Compila con tu JDK favorito:
```
javac -encoding UTF-8 -d bin $(find . -name "*.java")
```

Ejecuta:
```
java -cp bin AnalisisADOO.Program
```

Estructura del Proyecto (≧◡≦)ノ



AnalisisADOO/
│
├── Clases/
│   ├── ClaseEntidad.java
│   ├── ClaseAtributo.java
│   ├── ClaseEnums.java
│   ├── ClasePlantilla.java
│   ├── ClaseTipoAtributo.java
│   └── InterfaceContrato.java
│
├── SubClases/
│   ├── ClasePOO.java
│   ├── ClaseSQL.java
│   └── SealedClass.java
│
├── Data/
│   ├── DataJSON.java
│   └── DataTEXT.java
│
├── Program.java
└── README.md
```

Uso General (ﾉ≧ڡ≦)
Al ejecutar el programa verás un menú principal:

``` 
1. Mostrar Análisis ADOO General
2. Crear Entidad
3. Seleccionar Entidad
4. Mostrar Diagrama de Clases
5. Agregar / Editar Análisis ADOO General
6. Guardar en JSON
7. Cargar desde JSON
8. Exportar a TXT
0. Salir
```

Cada entidad tiene su propio menú interno para:

``` 
Agregar/editar/borrar atributos

Agregar/editar/borrar métodos (POO/SEALED)

Cambiar tipo de clase

Heredar

Ver análisis

Ver propiedades

Exportación
JSON
Guarda absolutamente todo el proyecto:

Atributos completos

Métodos

Herencia

TipoClase

PK/FK/NULL

Análisis

Orden

Clases hijas

TXT
Genera un documento legible con:

Análisis general

Análisis por clase

Atributos

Métodos

Herencia
```

``` 
🛡️ Licencia ZORRODEV 2026
Este proyecto es libre para descargar, estudiar y modificar, pero:


❌ NO se permite:
Vender el software

Apropiarse del código

Remover la firma o licencia

✔ Sí se permite:
Descargar

Modificar

Usar para aprender

Compartir siempre que se mantenga esta licencia

Derechos reservados:
ZORRODEV mantiene todos los derechos sobre este proyecto.  
El repositorio puede pasar de público a privado en cualquier momento.
```


🦊 Autor
Christopher Díaz Gastélum  
ZORRO DEVELOPER (＾▽＾)
Creador del Documentador ADOO en consola
2026

❤️ Agradecimientos
Gracias por usar este proyecto educativo.
Si en el futuro creo un documentador profesional, será un proyecto
completamente nuevo, posiblemente orientado a datos o en inglés.

(⁄ ⁄•⁄ω⁄•⁄ ⁄)
