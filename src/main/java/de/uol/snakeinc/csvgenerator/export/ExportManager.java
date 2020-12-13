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
        double counter = 100.0D / files.size();
        double currentPercent = 0;
        double oldPercent = 0;
        System.out.println("0%");
        for (File file : files) {
            currentPercent += counter;
            int percent = percentCounter(oldPercent, currentPercent);
            if (percent != 0) {
                System.out.println(percent + "%");
                oldPercent = percent;
            }
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
        System.out.println("100%");
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

    private int percentCounter(double oldPercent, double currentPercent) {
        int[] percents = new int[]{5,10,15,20,25,30,35,40,45,50,55,60,65,70,75,80,85,90,95};
        for (int option : percents) {
            if (option > oldPercent && currentPercent > option) {
                return option;
            }
        }
        return 0;
    }
}
