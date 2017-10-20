package PointOfView.View.Stastics;

import PointOfView.MainApp;
import PointOfView.View.Stastics.Menu.MenuStasticsLayoutController;
import PointOfView.View.Stastics.Sale.SaleStasticsLayoutController;
import PointOfView.View.Stastics.Time.TimeStasticsLayoutController;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.Tab;
import javafx.scene.layout.BorderPane;
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
    void handleSaleTabSelected(ActionEvent event) {
    	try {
    		FXMLLoader loader = new FXMLLoader(getClass().getResource("Sale/SaleStasticsLayout.fxml"));
    		BorderPane pane = loader.load();
    		
    		SaleStasticsLayoutController controller = loader.getController();
    		controller.setStastics(mainApp.getStasticsModel());
    		
    		saleStasticsTab.setContent(pane);
    	}catch (Exception e) {
    		//ignore
    	}
    }
    
    @FXML
    void handleMenuTabSelected(ActionEvent event) {
    	try {
    		FXMLLoader loader = new FXMLLoader(getClass().getResource("Menu/MenuStasticsLayout.fxml"));
    		BorderPane pane = loader.load();
    		
    		MenuStasticsLayoutController controller = loader.getController();
    		
    		menuStasticsTab.setContent(pane);
    	}catch (Exception e) {
    		//ignore
    	}
    }

    @FXML
    void handleTimeTabSelected(ActionEvent event) {
    	try {
    		FXMLLoader loader = new FXMLLoader(getClass().getResource("Time/TimeStasticsLayout.fxml"));
    		BorderPane pane = loader.load();
    		
    		TimeStasticsLayoutController controller = loader.getController();
    		
    		timeStasticsPane.setContent(pane);
    	}catch (Exception e) {
    		//ignore
    	}
    }
    
    private MainApp mainApp;
    private Stage stage;
    
    public void setMainApp(Stage stage, MainApp mainApp) {
    	this.mainApp = mainApp;
    	this.stage = stage;
    	
    	handleSaleTabSelected(null);
    	handleTimeTabSelected(null);
    	handleMenuTabSelected(null);
    	
    	
    }

}
