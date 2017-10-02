package PointOfView.Order.Table.View.Payment.Card;

import javax.swing.text.PlainDocument;

import PointOfView.MainApp;
import PointOfView.Order.Table.Model.TableData;
import PointOfView.Order.Table.View.Payment.Model.ReceiptModel.PAY;
import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.text.Text;
import javafx.stage.Stage;

public class CardPaymentLayoutController {

    @FXML
    private Text orderpay;

    @FXML
    private Text resultpay;

    @FXML
    private Text discountpay;

    @FXML
    private Button acceptPay;

    @FXML
    private Button canclePay;
    
    private MainApp mainApp;
    private Stage stage;
    private TableData tableData;
    

    @FXML
    void handleAcceptPay(ActionEvent event) {

    	//영수증 기록을 남긴다.
    	mainApp.getReceipts().addReceiptList(PAY.CARD, tableData);
    	
    	tableData.removeAll();
    	mainApp.getRootLayoutController().showOrderMenu();
    	stage.close();
    	
    	
    }

    @FXML
    void handleCanclePay(ActionEvent event) {
    	stage.close();

    }
    
    public void setPaymentObject(MainApp mainApp, TableData tableData, Stage stage) {
    	this.mainApp = mainApp;
    	this.tableData = tableData;
    	this.stage = stage;
    	
    	try {
    		orderpay.setText(String.format("%,20d 원", tableData.getSumPrice()));
        	discountpay.setText(String.format("- %,20d 원", tableData.getDiscount()));
    		resultpay.setText(String.format("%,20d 원", tableData.getSumPrice()-tableData.getDiscount()));
    	}catch (Exception e) {
    		//ignore
    	}
    	
    	
    }

}
