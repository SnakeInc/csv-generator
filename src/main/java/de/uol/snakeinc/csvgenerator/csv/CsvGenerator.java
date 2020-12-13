package de.uol.snakeinc.csvgenerator.csv;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.nio.charset.StandardCharsets;
import java.util.List;

public class CsvGenerator {

    private File file;
    private List<PlayerData> playerData;

    public CsvGenerator(File file, List<PlayerData> playerData) {
        this.file = file;
        this.playerData = playerData;
    }

    public void generate() {
        String data = "Name;Games;First;Second;Third;Fourth;Fifth;Sixth;TrustedValue;MedianPlayers";
        for (PlayerData player : this.playerData) {
            data = data + "\n" + player.getCSVString();
        }
        try {
            if (file.exists()) {
                file.delete();
            }
            if (!file.exists() && file.getParentFile() != null) {
                file.getParentFile().mkdirs();
                file.createNewFile();
            }
            BufferedWriter fileWriter = new BufferedWriter(new OutputStreamWriter(new FileOutputStream(file, true), StandardCharsets.UTF_8));
            fileWriter.write(data);
            fileWriter.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}
