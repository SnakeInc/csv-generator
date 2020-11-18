package de.uol.snakeinc.vizualizer;

import de.uol.snakeinc.vizualizer.parser.Game;
import javafx.collections.ObservableList;
import javafx.scene.Node;
import javafx.scene.layout.GridPane;
import javafx.scene.paint.Paint;
import javafx.scene.shape.Rectangle;

import java.io.File;
import java.io.FileNotFoundException;

public class Board extends GridPane {

     Game game;
     int rounds;
     RoundTimer roundTimer;

    public Board (File file) {
         this.setMinWidth(1000);
         this.setMinHeight(1000);
         this.setVgap(3);
         this.setHgap(3);
         try {
              game = Game.getGame(file);
              System.out.println("Rounds: " + game.getRounds());
              System.out.println("Width: " + game.getWidth());System.out.println("Height: " + game.getHeight());
              for(int player : game.getPlayers().keySet()) {
              System.out.println("Player " + player + ": " + game.getPlayers().get(player));
              }
         } catch (FileNotFoundException e) {
              e.printStackTrace();
         }
         initializeCells();
    }

     public void initializeCells() {
          for (int i=0; i< game.getWidth(); i++) {
               for (int j=0; j< game.getHeight(); j++) {
                    Cell cell = new Cell();
                    this.add(cell,i,j);
               }
          }
     }

     public void setSpeed (double speed) {
        if (roundTimer != null) {
            roundTimer.end();
        }
        roundTimer = new RoundTimer(speed, this);
     }

     public void nextRound () {
         ObservableList<Node> childrens = this.getChildren();

         for (int i = 0; i < game.getWidth() ; i++) {
             for (int j = 0; i < game.getHeight(); j++) {
                 for (Node node : childrens) {
                     if (this.getRowIndex(node) == i && this.getColumnIndex(node) == j) {
                         ((Cell) node).setFill(Paint.valueOf("RED"));
                     }
                 }
             }
         }
         if (rounds == 0) {
             roundTimer.end();
         } else {
             rounds--;
         }
     }

     public void startGame() {
         ObservableList<Node> childrens = this.getChildren();
         this.nextRound();
         rounds--;
         roundTimer.run();
     }

     public Game getGame () {
        return game;
     }

}
