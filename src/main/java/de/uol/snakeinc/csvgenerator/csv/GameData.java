package de.uol.snakeinc.csvgenerator.csv;

import java.util.HashMap;

public class GameData {

    private HashMap<Place, PlayerData> players;

    public GameData() {
        this.players = new HashMap<Place, PlayerData>();
    }

    public void addPlayer(int position, PlayerData player) {
        players.put(new Place(position), player);
    }

    public void calculateScoring() {
        int playerAmount = players.size() - 1;
        if (playerAmount > 0) {
            double division = 1.0D / playerAmount;
            double score = 0.0D;
            for (Place position : players.keySet()) {
                if (position.getPosition() != 1) {
                    score += players.get(position).getTrustValue() * division;
                }
            }
            for (Place position : players.keySet()) {
                if (position.getPosition() == 1) {
                    players.get(position).addTrustScoreEnemies(score);
                }
            }
        }
    }

}
