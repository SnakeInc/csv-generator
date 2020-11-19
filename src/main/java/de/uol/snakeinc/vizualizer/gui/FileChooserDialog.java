package de.uol.snakeinc.vizualizer.gui;

import java.io.File;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.scene.control.Button;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.VBox;
import javafx.stage.FileChooser;
import javafx.stage.Stage;

public class FileChooserDialog extends VBox {

    File actualFile;

    public FileChooserDialog(Stage stage) {
        final FileChooser fileChooser = new FileChooser();

        final Button openButton = new Button("Open a Game...");

        openButton.setOnAction(
            new EventHandler<ActionEvent>() {
                @Override
                public void handle(final ActionEvent e) {
                    File file = fileChooser.showOpenDialog(stage);
                    if (file != null) {
                        openFile(file);
                    }
                }
            });

        final GridPane inputGridPane = new GridPane();

        GridPane.setConstraints(openButton, 0, 0);
        inputGridPane.setHgap(6);
        inputGridPane.setVgap(6);
        inputGridPane.getChildren().addAll(openButton);
        this.getChildren().addAll(inputGridPane);
        this.setPadding(new Insets(12, 12, 12, 12));
    }

    private void openFile(File file) {
            actualFile = file;
    }

    public File getActualFile() {
        return actualFile;
    }

    public Boolean checkFile() {
        return actualFile != null;
    }
}
