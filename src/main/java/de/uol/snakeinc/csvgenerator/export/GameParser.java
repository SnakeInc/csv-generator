package de.uol.snakeinc.csvgenerator.export;

import de.uol.snakeinc.csvgenerator.csv.PlayerData;
import de.uol.snakeinc.csvgenerator.parser.Game;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class GameParser {

    private Game game;

    public GameParser(File file) throws FileNotFoundException {
        this.game = Game.getGame(file);
    }

    public HashMap<String, PlayerData> addPlayerData(HashMap<String, PlayerData> playerData) {
        List<String> lastRoundplayers = new ArrayList<String>();
        boolean[][] blackBoard = new boolean[game.getBoards().get(1).length][game.getBoards().get(1)[0].length];
        for (int i = 0; i < blackBoard.length; i++) {
            for (int j = 0; j < blackBoard[i].length; j++) {
                blackBoard[i][j] = true;
            }
        }
        for (Integer round : game.getBoards().keySet()) {
            Integer[][] currentBoard = game.getBoards().get(round);
            List<String> currentPlayers = getPlayersByBoard(currentBoard, blackBoard);
            if (currentPlayers.size() < lastRoundplayers.size() && !lastRoundplayers.isEmpty()) {
                for (String player : lastRoundplayers) {
                    if (!currentPlayers.contains(player)) {
                        this.handlePlayer(player, lastRoundplayers.size(), game.getPlayers().size(), playerData);
                    }
                }
            }
            lastRoundplayers = currentPlayers;
        }
        if (!lastRoundplayers.isEmpty()) {
            this.handlePlayer(lastRoundplayers.get(0), 1, game.getPlayers().size(), playerData);
        }

        return playerData;
    }

    private void handlePlayer(String player, int position, int players, HashMap<String, PlayerData> playerData) {
        PlayerData dataForPlayer = null;
        if (playerData.containsKey(player)) {
            dataForPlayer = playerData.get(player);
        } else {
            dataForPlayer = new PlayerData(player);
        }
        dataForPlayer.addGame();
        dataForPlayer.addPosition(position);
        dataForPlayer.addTrustValue(position, players);
        playerData.put(player, dataForPlayer);
    }

    private List<String> getPlayersByBoard(Integer[][] currentBoard, boolean[][] blackBoard) {
        List<Integer> players = new ArrayList<Integer>();
        for (int i = 0; i < blackBoard.length; i++) {
            for (int j = 0; j < blackBoard[i].length; j++) {
                boolean check = blackBoard[i][j];
                if (check) {
                    Integer player = currentBoard[i][j];
                    if(player != 0) {
                        blackBoard[i][j] = false;
                        if (!players.contains(player)) {
                            players.add(player);
                        }
                    }
                }
            }
        }
        List<String> playerNames = new ArrayList<String>();
        for (Integer player : players) {
            playerNames.add(game.getPlayers().get(player));
        }
        return playerNames;
    }

}
