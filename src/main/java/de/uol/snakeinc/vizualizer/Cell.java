package de.uol.snakeinc.vizualizer;

import javafx.scene.paint.Paint;
import javafx.scene.shape.Rectangle;

public class Cell extends Rectangle {

    public  Cell () {
        super.setHeight(13);
        super.setWidth(13);
        this.setFill(Paint.valueOf("BLACK"));
    }

    public void changeTo (Paint colour) {
        this.setFill(colour);
    }

}
