package de.uol.snakeinc.csvgenerator.csv;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class DataHandler {

    private List<GameData> games;
    private HashMap<String, PlayerData> players;

    public DataHandler() {
        this.games = new ArrayList<GameData>();
        this.players = new HashMap<String, PlayerData>();
    }

    public HashMap<String, PlayerData> getPlayers() {
        return this.players;
    }

    public List<GameData> getGames() {
        return this.games;
    }

    public void scoreGames() {
        for (GameData gameData : games) {
            gameData.calculateScoring();
        }
    }

}
