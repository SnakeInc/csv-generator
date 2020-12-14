package de.uol.snakeinc.csvgenerator.export;

import de.uol.snakeinc.csvgenerator.csv.DataHandler;
import de.uol.snakeinc.csvgenerator.csv.GameData;
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

    public void addPlayerData(DataHandler dataHandler) {
        GameData gameData = new GameData();
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
                        this.handlePlayer(player, currentPlayers.size() + 1, game.getPlayers().size(), dataHandler, gameData);
                    }
                }
            }
            lastRoundplayers = currentPlayers;
        }
        if (!lastRoundplayers.isEmpty()) {
            this.handlePlayer(lastRoundplayers.get(0), 1, game.getPlayers().size(), dataHandler, gameData);
        }

        dataHandler.getGames().add(gameData);
    }

    private void handlePlayer(String player, int position, int players, DataHandler dataHandler, GameData gameData) {
        PlayerData dataForPlayer = null;
        if (dataHandler.getPlayers().containsKey(player)) {
            dataForPlayer = dataHandler.getPlayers().get(player);
        } else {
            dataForPlayer = new PlayerData(player);
        }
        dataForPlayer.addGame();
        dataForPlayer.addPosition(position);
        dataForPlayer.addTrustValue(position, players);
        dataForPlayer.addPlayers(players);
        dataHandler.getPlayers().put(player, dataForPlayer);
        gameData.addPlayer(position, dataForPlayer);
    }

    private List<String> getPlayersByBoard(Integer[][] currentBoard, boolean[][] blackBoard) {
        List<Integer> players = new ArrayList<Integer>();
        for (int i = 0; i < blackBoard.length; i++) {
            for (int j = 0; j < blackBoard[i].length; j++) {
                boolean check = blackBoard[i][j];
                if (check) {
                    Integer player = currentBoard[i][j];
                    if (player != 0) {
                        blackBoard[i][j] = false;
                        if (!players.contains(player) && player != -1) {
                            players.add(player);
                        }
                    }
                }
            }
        }
        List<String> playerNames = new ArrayList<String>();
        for (Integer player : players) {
            String playerName = game.getPlayers().get(player);
            playerNames.add(playerName);
        }
        return playerNames;
    }

}
