module com.lpcode.pdfbulk {
    requires javafx.controls;
    requires javafx.fxml;     // Módulo de JavaFX FXML (si lo usas)
    requires org.apache.pdfbox; // Módulo de Apache PDFBox
    requires com.opencsv;
    exports com.lpcode.pdfbulk;
}
