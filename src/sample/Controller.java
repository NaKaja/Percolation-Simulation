package sample;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.Pane;

import java.net.URL;
import java.util.ResourceBundle;

public class Controller implements Initializable {

    @FXML
    private Pane simPane;
    @FXML
    private TextField densityField;
    @FXML
    private Button vButton, sButton, fButton, percButton;
    @FXML
    private CheckBox autoUpdate;
    private Grid grid;
    private int fluidType;


    /**
     * Default settings is Standard flow and 0.5 density grid
     */
    @Override
    public void initialize(URL location, ResourceBundle resources) {
        grid = new Grid(simPane, 0.5);
        fluidType = 2;
        densityField.setText("0.5");
        sButton.setStyle("-fx-background-color: #94d6f7");
        percButton.setDefaultButton(true);
    }

    public void percolate(ActionEvent actionEvent) {
        grid.unflood(); //Clear the grid of previous simulation

        if(fluidType == 1){ //Begin Viscous fluid percolation
            for(int col = 0; col < grid.getCellsWide(); col++){
                if(grid.getCell(0, col).isNotFilledOrFlooded()){
                    grid.floodCell(0, col);
                    viscPerc(0, col);
                }
            }
        }

        if(fluidType == 2){ //Begin Standard fluid percolation
            for(int col = 0; col < grid.getCellsWide(); col++){
                if(grid.getCell(0, col).isNotFilledOrFlooded()){
                    grid.floodCell(0, col);
                    standardPerc(0, col);
                }
            }
        }

        if(fluidType == 3){ //Begin Standard fluid percolation
            for(int col = 0; col < grid.getCellsWide(); col++){
                if(grid.getCell(0, col).isNotFilledOrFlooded()){
                    grid.floodCell(0, col);
                    fluidPerc(0, col);
                }
            }
        }
    }

    public void viscPerc(int row, int col){
        if(row < grid.getCellsHigh() - 1){
            if(grid.getCell(row + 1, col).isNotFilledOrFlooded()){ //check space below
                grid.floodCell(row + 1, col);
                viscPerc(row + 1, col);
            }
        }
    }

    public void standardPerc(int row, int col){
        if(row > 0){
            if(grid.getCell(row - 1, col).isNotFilledOrFlooded()){ //check space above
                grid.floodCell(row - 1, col);
                standardPerc(row - 1, col);
            }
        }

        if(row < grid.getCellsHigh() - 1){
            if(grid.getCell(row + 1, col).isNotFilledOrFlooded()){ //check space below
                grid.floodCell(row + 1, col);
                standardPerc(row + 1, col);
            }
        }

        if(col < grid.getCellsWide() - 1){
            if(grid.getCell(row, col + 1).isNotFilledOrFlooded()){ //check space to right
                grid.floodCell(row, col + 1);
                standardPerc(row, col + 1);
            }
        }

        if(col > 0){
            if(grid.getCell(row, col - 1).isNotFilledOrFlooded()){ //check space to left
                grid.floodCell(row, col - 1);
                standardPerc(row, col - 1);
            }
        }
    }

    public void fluidPerc(int row, int col){
        if(row > 0){
            if(grid.getCell(row - 1, col).isNotFilledOrFlooded()){ //check space above
                grid.floodCell(row - 1, col);
                fluidPerc(row - 1, col);
            }
        }

        if(row < grid.getCellsHigh() - 1){
            if(grid.getCell(row + 1, col).isNotFilledOrFlooded()){ //check space below
                grid.floodCell(row + 1, col);
                fluidPerc(row + 1, col);
            }
        }

        if(col < grid.getCellsWide() - 1){
            if(grid.getCell(row, col + 1).isNotFilledOrFlooded()){ //check space to right
                grid.floodCell(row, col + 1);
                fluidPerc(row, col + 1);
            }
        }

        if(col > 0){
            if(grid.getCell(row, col - 1).isNotFilledOrFlooded()){ //check space to left
                grid.floodCell(row, col - 1);
                fluidPerc(row, col - 1);
            }
        }

        if(row > 0 && col > 0){
            if(grid.getCell(row - 1, col - 1).isNotFilledOrFlooded()){ //check space up left
                grid.floodCell(row - 1, col - 1);
                fluidPerc(row - 1, col - 1);
            }
        }

        if(row > 0 && col < grid.getCellsWide() - 1){
            if(grid.getCell(row - 1, col + 1).isNotFilledOrFlooded()){ //check space up right
                grid.floodCell(row - 1, col + 1);
                fluidPerc(row - 1, col + 1);
            }
        }

        if(row < grid.getCellsHigh() - 1 && col > 0){
            if(grid.getCell(row + 1, col - 1).isNotFilledOrFlooded()){ //check space down left
                grid.floodCell(row + 1, col - 1);
                fluidPerc(row + 1, col - 1);
            }
        }

        if(row < grid.getCellsHigh() - 1 && col < grid.getCellsWide() - 1){
            if(grid.getCell(row + 1, col + 1).isNotFilledOrFlooded()){ //check space down right
                grid.floodCell(row + 1, col + 1);
                fluidPerc(row + 1, col + 1);
            }
        }
    }

    public void newGrid(ActionEvent actionEvent) {
        try{
            grid = new Grid(simPane, Double.parseDouble(densityField.getText() + "0"));
        }
        catch(NumberFormatException e){
            System.out.println("Non-number in density field");
        }
    }

    public void vPress(ActionEvent actionEvent) {
        vButton.setStyle("-fx-background-color: #94d6f7");
        sButton.setStyle("");
        fButton.setStyle("");
        fluidType = 1;
    }

    public void sPress(ActionEvent actionEvent) {
        vButton.setStyle("");
        sButton.setStyle("-fx-background-color:#94d6f7;");
        fButton.setStyle("");
        fluidType = 2;
    }

    public void fPress(ActionEvent actionEvent) {
        vButton.setStyle("");
        sButton.setStyle("");
        fButton.setStyle("-fx-background-color:#94d6f7;");
        fluidType = 3;
    }

    public void gridClicked(MouseEvent mouseEvent) {
        grid.clickedCell(mouseEvent.getX(), mouseEvent.getY());
        if(autoUpdate.isSelected()){
            percolate(null);
        }
    }
}
