package sample;

import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Line;
import java.util.ArrayList;
import java.util.List;

public class Grid {

    private final List<List<Cell>> cells;
    private int cellsWide;
    private int cellsHigh;

    public Grid(Pane pane, double density){
        this(pane, (int) pane.getPrefWidth(), (int) pane.getPrefHeight(),density);
    }

    public Grid(Pane pane, int width, int height, double density){
        this.cellsWide = width / Cell.SCALE;
        this.cellsHigh = height / Cell.SCALE;
        cells = new ArrayList<>();

        //Initialize 2D list
        for(int i = 0; i < cellsHigh; i++){
            cells.add(new ArrayList<>());
        }

        //Create cells
        for(int i = 0; i < cellsHigh; i++){
            for(int j = 0; j < cellsWide; j++){
                cells.get(i).add(new Cell(j, i, Math.random() < density));
            }
        }

        initialize(pane);
    }

    public void floodCell(int row, int col){
        cells.get(row).get(col).flood();
    }

    public Cell getCell(int row, int col){
        return cells.get(row).get(col);
    }

    public int getCellsWide(){
        return cellsWide;
    }

    public int getCellsHigh(){
        return cellsHigh;
    }

    private void initialize(Pane pane){
        for(List<Cell> row : cells){
            for(Cell cell : row){
                pane.getChildren().add(cell);
            }
        }

        //Making GridLines
        for(int i = 0; i < cellsWide; i++){
            Line l = new Line(i*Cell.SCALE, 0, i*Cell.SCALE, cellsHigh*Cell.SCALE);
            pane.getChildren().add(l);
            l.setFill(Color.BLACK);
        }
        for(int i = 0; i < cellsHigh; i++){
            Line l = new Line(0, i*Cell.SCALE, cellsWide*Cell.SCALE, i*Cell.SCALE);
            pane.getChildren().add(l);
            l.setFill(Color.BLACK);
        }
    }

    public void unflood() {
        for(int i = 0; i < cellsHigh; i++){
            for(int j = 0; j < cellsWide; j++){
                cells.get(i).get(j).unflood();
            }
        }
    }

    public void clickedCell(double x, double y) {
        Cell clicked = cells.get((int)y/Cell.SCALE).get((int)x/Cell.SCALE);
        clicked.clicked();
    }
}
