package sample;

import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;

public class Cell extends Rectangle {

    public static final int SCALE = 10;
    private boolean filled;
    private boolean flooded;
    private final Color fill = Color.FIREBRICK;
    private final Color empty = Color.BEIGE;
    private final Color flood = Color.LIGHTBLUE;

    public Cell(int x, int y, boolean filled){
        this.setLayoutX(x*SCALE);
        this.setLayoutY(y*SCALE);
        this.setWidth(SCALE);
        this.setHeight(SCALE);
        this.filled = filled;
        updateColor();
    }

    public void updateColor(){
        if(filled){
            setFill(fill);
        }
        else{
            if(flooded){
                setFill(flood);
            }
            else{
                setFill(empty);
            }
        }
    }

    public void flood(){
        flooded = true;
        updateColor();
    }

    public void unflood(){
        flooded = false;
        updateColor();
    }

    public boolean isNotFilledOrFlooded(){
        return !filled && !flooded;
    }

    public void clicked(){
        if(filled){
            filled = false;
        }
        else{
            if(flooded){
                flooded = false;
            }
            filled = true;
        }
        updateColor();
    }
}
