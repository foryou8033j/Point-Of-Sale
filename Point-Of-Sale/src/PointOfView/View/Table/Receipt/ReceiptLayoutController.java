package PointOfView.View.Table.Receipt;

import java.net.URL;
import java.util.ResourceBundle;

import PointOfView.MainApp;
import PointOfView.Models.Receipt.ReceiptModel;
import PointOfView.View.Table.Receipt.Card.CardReceiptLayoutController;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;

public class ReceiptLayoutController implements Initializable{

    @FXML
    private TableView<ReceiptModel> table;

    @FXML
    private TableColumn<ReceiptModel, String> dateColumn;

    @FXML
    private TableColumn<ReceiptModel, String> wayColumn;

    @FXML
    private TableColumn<ReceiptModel, String> payColumn;

    
    @Override
	public void initialize(URL location, ResourceBundle resources) {
		
    	dateColumn.setCellValueFactory(cellData -> cellData.getValue().dateProperty());
    	wayColumn.setCellValueFactory(cellData -> cellData.getValue().payProperty());
    	payColumn.setCellValueFactory(cellData -> cellData.getValue().moneyProperty());
    	
    	
    	table.getSelectionModel().selectedItemProperty().addListener(
                (observable, oldValue, newValue) -> drawReceipt(newValue));
    	
	}
    
    private void drawReceipt(ReceiptModel receipt) {
    	if(receipt == null)
    		pane.setCenter(null);
    	
    	pane.setCenter(drawCardReceipt(receipt));
    }
    
    
    private AnchorPane drawCardReceipt(ReceiptModel receipt) {
    	try {
			FXMLLoader loader = new FXMLLoader(getClass().getResource("Card/CardReceiptLayout.fxml"));
			AnchorPane pane = loader.load();
			
			CardReceiptLayoutController controller = loader.getController();
			controller.setReceipt(receipt);
			
			//controller.setMainApp(this, pane, mainApp);
			
			return pane;
			
		}catch (Exception e) {
			e.printStackTrace();
		}
    	return null;
    }
    
    /*private AnchorPane drawCashReceipt(ReceiptModel receipt) {
    	
    }*/
    
    
    
    @FXML
    void handleClose(ActionEvent event) {
    	stage.close();
    }

	private Stage stage;
    private MainApp mainApp;
    private BorderPane pane;
    
    public void setMainApp(Stage stage, BorderPane pane, MainApp mainApp) {
    	this.mainApp = mainApp;
    	this.stage = stage;
    	this.pane = pane;
    	
    	table.setItems(mainApp.getReceipts().getReceiptList());
    	
    	pane.setCenter(null);
    }

	

}
