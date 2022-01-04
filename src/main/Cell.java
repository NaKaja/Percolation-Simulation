package main;

import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;

/**
 * Class that represents a single tile/square in the simulation grid
 * Can be either empty, filled (blocked), or flooded
 */
public class Cell extends Rectangle {

    //TODO: add slider to change scale of grid to add more control over simulation
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

    /**
     * Set the color of the cell according to its status - empty, filled, or flooded
     */
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

    /**
     * Fill the cell with fluid
     */
    public void flood(){
        flooded = true;
        updateColor();
    }

    /**
     * Remove fluid from the cell
     */
    public void unflood(){
        flooded = false;
        updateColor();
    }

    /**
     * Returns whether the cell is completely empty
     */
    public boolean isNotFilledOrFlooded(){
        return !filled && !flooded;
    }

    /**
     * Toggling the status of a clicked cell.
     * Filled cells become empty, empty cells become filled - flooded cells become filled
     */
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
