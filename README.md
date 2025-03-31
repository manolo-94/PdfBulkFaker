# Generador de PDFs Falsos

Este programa en Java genera archivos PDF vacíos basados en una lista de nombres proporcionada por el usuario. La lista de nombres se extrae de un archivo de texto, y los archivos PDF generados se guardan en una carpeta seleccionada por el usuario.

## Flujo del Programa

1. **Seleccionar la carpeta de salida**: El usuario elige la carpeta donde se guardarán los PDFs.
2. **Seleccionar el archivo de lista**: El usuario selecciona el archivo de texto que contiene los nombres de los PDFs.
3. **Leer la lista de nombres**: El programa procesa los nombres de los archivos desde el archivo de texto.
4. **Generar PDFs falsos**: Se crean archivos PDF vacíos con los nombres indicados en la carpeta seleccionada.
5. **Mostrar un mensaje de éxito**: El programa informa al usuario que la operación ha sido completada.

## Requisitos

- **Java 17 o superior**
- **iText 7**: Librería para generar los PDFs. Puedes agregarla al proyecto utilizando Maven o Gradle.

### Maven

Agrega la siguiente dependencia en tu archivo `pom.xml`:

```xml
<dependency>
    <groupId>com.itextpdf</groupId>
    <artifactId>itext7-core</artifactId>
    <version>7.2.4</version> <!-- Asegúrate de usar la última versión -->
    <scope>compile</scope>
</dependency>
