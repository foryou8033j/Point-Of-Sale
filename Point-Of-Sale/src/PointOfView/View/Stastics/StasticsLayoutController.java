package PointOfView.View.Stastics;

import PointOfView.MainApp;
import PointOfView.View.Stastics.Menu.MenuStasticsLayoutController;
import PointOfView.View.Stastics.Sale.SaleStasticsLayoutController;
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
    		controller.setStastics(mainApp.getDataManagement().getMenues(), stage, mainApp.getStasticsModel());
    		
    		menuStasticsTab.setContent(pane);
    	}catch (Exception e) {
    		//ignore
    	}
    }
    
    private MainApp mainApp;
    private Stage stage;
    
    public void setMainApp(Stage stage, MainApp mainApp) {
    	this.mainApp = mainApp;
    	this.stage = stage;
    	
    	mainApp.getReceipts().loadDataFromFile();
    	
    	handleSaleTabSelected(null);
    	handleMenuTabSelected(null);
    	
    	
    }

}
