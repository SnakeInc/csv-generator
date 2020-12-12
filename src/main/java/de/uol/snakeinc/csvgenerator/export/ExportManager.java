package de.uol.snakeinc.csvgenerator.export;

import de.uol.snakeinc.csvgenerator.csv.CsvGenerator;
import de.uol.snakeinc.csvgenerator.csv.PlayerData;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class ExportManager {

    private File exportFile;
    private File logFolder;

    public ExportManager(File exportFile, File logFolder) {
        this.exportFile = exportFile;
        this.logFolder = logFolder;
    }

    public void collect() {
        HashMap<String, PlayerData> players = new HashMap<String, PlayerData>();
        List<File> files = new ArrayList<File>();
        listFiles(logFolder, files);
        for (File file : files) {
            try {
                GameParser parser = new GameParser(file);
                players = parser.addPlayerData(players);
            } catch (FileNotFoundException e) {
                System.out.println("Found a file, that was not readable");
            }
        }
        List<PlayerData> data = new ArrayList<PlayerData>();
        for (String key : players.keySet()) {
            data.add(players.get(key));
        }

        CsvGenerator generator = new CsvGenerator(exportFile, data);
        generator.generate();
    }

    private void listFiles(File directory, List<File> files) {

        // Get all files from a directory.
        File[] fList = directory.listFiles();
        if(fList != null) {
            for (File file : fList) {
                if (file.isFile()) {
                    files.add(file);
                } else if (file.isDirectory()) {
                    listFiles(file, files);
                }
            }
        }
    }
}
