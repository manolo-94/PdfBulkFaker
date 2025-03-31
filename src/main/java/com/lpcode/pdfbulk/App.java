package com.lpcode.pdfbulk;

import com.opencsv.CSVReader;
import org.apache.pdfbox.pdmodel.PDDocument;
import org.apache.pdfbox.pdmodel.PDPage;
import org.apache.pdfbox.pdmodel.PDPageContentStream;
import javafx.application.Application;
import javafx.stage.FileChooser;
import javafx.stage.DirectoryChooser;
import javafx.stage.Stage;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.VBox;

import java.io.*;
import java.nio.file.*;
import java.util.List;

public class App extends Application {

    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage primaryStage) {
        primaryStage.setTitle("Generador de PDFs Falsos");

        Button btnSelectCSV = new Button("Seleccionar archivo CSV");
        Button btnSelectDirectory = new Button("Seleccionar directorio de salida");
        Button btnGeneratePDFs = new Button("Generar PDFs");

        VBox vbox = new VBox(10, btnSelectCSV, btnSelectDirectory, btnGeneratePDFs);
        Scene scene = new Scene(vbox, 300, 200);

        String[] csvFilePath = {null};  // Para almacenar la ruta del CSV
        String[] outputDirectory = {null};  // Para almacenar la ruta del directorio de salida

        // Seleccionar archivo CSV
        btnSelectCSV.setOnAction(event -> {
            FileChooser fileChooser = new FileChooser();
            fileChooser.getExtensionFilters().add(new FileChooser.ExtensionFilter("CSV Files", "*.csv"));
            File file = fileChooser.showOpenDialog(primaryStage);
            if (file != null) {
                csvFilePath[0] = file.getAbsolutePath();
            }
        });

        // Seleccionar directorio de salida
        btnSelectDirectory.setOnAction(event -> {
            DirectoryChooser directoryChooser = new DirectoryChooser();
            File directory = directoryChooser.showDialog(primaryStage);
            if (directory != null) {
                outputDirectory[0] = directory.getAbsolutePath();
            }
        });

        // Generar PDFs a partir del CSV
        btnGeneratePDFs.setOnAction(event -> {
            if (csvFilePath[0] != null && outputDirectory[0] != null) {
                generatePDFsFromCSV(csvFilePath[0], outputDirectory[0]);
            } else {
                System.out.println("Por favor, seleccione el archivo CSV y el directorio de salida.");
            }
        });

        primaryStage.setScene(scene);
        primaryStage.show();
    }

    private void generatePDFsFromCSV(String csvFilePath, String outputDirectory) {
        try {
            CSVReader reader = new CSVReader(new FileReader(csvFilePath));
            List<String[]> rows = reader.readAll();

            // Crear directorio de salida si no existe
            Path path = Paths.get(outputDirectory);
            if (!Files.exists(path)) {
                Files.createDirectory(path);
            }

            // Generar un PDF por cada fila del CSV
            for (String[] row : rows) {
                if (row.length > 0) {
                    String pdfFileName = row[0] + ".pdf";  // Nombre del archivo PDF
                    createFakePDF(Paths.get(outputDirectory, pdfFileName).toString());
                }
            }
            System.out.println("PDFs generados exitosamente en: " + outputDirectory);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void createFakePDF(String filePath) {
        try {
            // Crear un nuevo documento PDF
            PDDocument document = new PDDocument();
            PDPage page = new PDPage();
            document.addPage(page);

            // Escribir contenido al PDF
            PDPageContentStream contentStream = new PDPageContentStream(document, page);
            contentStream.beginText();
            contentStream.setFont(org.apache.pdfbox.pdmodel.font.PDType1Font.HELVETICA_BOLD, 12);
            contentStream.newLineAtOffset(100, 700);
            contentStream.showText("Archivo falso generado");
            contentStream.endText();
            contentStream.close();

            // Guardar el PDF en el sistema de archivos
            document.save(filePath);
            document.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}