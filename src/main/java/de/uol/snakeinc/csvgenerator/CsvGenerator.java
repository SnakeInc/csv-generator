package de.uol.snakeinc.csvgenerator;

import de.uol.snakeinc.csvgenerator.export.ExportManager;

import java.io.File;
import java.io.IOException;

public class CsvGenerator {

    public static void main(String[] args) {
        File exportFile = new File("data.csv");
        File exportsFolder = new File("exports");
        if (!exportsFolder.exists()) {
            exportsFolder.mkdirs();
        }
        System.out.println("Bitte stellen Sie sicher, dass alle Datein für die CSV im Ordner exports liegen.");
        System.out.println("Bestätigen Sie mit Enter.");
        try {
            System.in.read();
        } catch (IOException e) {
            e.printStackTrace();
        }

        ExportManager exportManager = new ExportManager(exportFile, exportsFolder);
        exportManager.collect();
        System.out.println("Die Datein wurden geparst und als csv exportiert. Viel Spaß beim analysieren :D");
    }
}
