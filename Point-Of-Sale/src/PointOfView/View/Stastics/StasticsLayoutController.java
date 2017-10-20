package PointOfView.View.Stastics;

import PointOfView.MainApp;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Tab;
import javafx.stage.Stage;

public class StasticsLayoutController {

    @FXML
    private Tab saleStasticsTab;

    @FXML
    private Tab menuStasticsTab;

    @FXML
    private Tab timeStasticsPane;

    @FXML
    void handleClose(ActionEvent event) {
    	stage.close();
    }

    @FXML
    void handleMenuTabSelected(ActionEvent event) {

    }

    @FXML
    void handleSaleTabSelected(ActionEvent event) {

    }

    @FXML
    void handleTimeTabSelected(ActionEvent event) {

    }
    
    private MainApp mainApp;
    private Stage stage;
    
    public void setMainApp(Stage stage, MainApp mainApp) {
    	this.mainApp = mainApp;
    	this.stage = stage;
    }

}
