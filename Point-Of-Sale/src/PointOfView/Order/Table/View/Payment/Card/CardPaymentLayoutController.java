package PointOfView.Order.Table.View.Payment.Card;

import javax.swing.text.PlainDocument;

import PointOfView.MainApp;
import PointOfView.Order.Table.Model.TableData;
import PointOfView.Order.Table.View.Payment.Model.ReceiptModel.PAY;
import PointOfView.Util.View.SimpleAlert;
import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Alert.AlertType;
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
    private Text requestPay;

    @FXML
    private Button acceptPay;

    @FXML
    private Button canclePay;
    
    private MainApp mainApp;
    private Stage stage;
    private TableData tableData;
    
    private int payMoney;
    
    @FXML
    void handleAcceptPay(ActionEvent event) {

    	if(payMoney == 0) {
    		new SimpleAlert(stage, AlertType.WARNING, "승인 불가", "승인 불가 : 잔액 부족").showAndWait();
    		stage.close();
    		return;
    	}else {
    		tableData.setPayMoney(tableData.getPayMoney() + payMoney);
    		
    		if(tableData.getResultPay() <= 0) {
    			//영수증 기록을 남긴다.
    	    	mainApp.getReceipts().addReceiptList(PAY.CARD, tableData);
    	    	
    	    	tableData.removeAll();
    	    	mainApp.getRootLayoutController().showOrderMenu();
    	    	stage.close();
    		}else {
    			stage.close();
    		}
    		
    	}
    	
    	
    	
    	
    }

    @FXML
    void handleCanclePay(ActionEvent event) {
    	stage.close();
    }
    
    public void setPaymentObject(MainApp mainApp, TableData tableData, Stage stage, int payMoney) {
    	this.mainApp = mainApp;
    	this.tableData = tableData;
    	this.stage = stage;
    	this.payMoney = payMoney;
    	
    	
    	if(payMoney >= tableData.getResultPay())
    		payMoney = tableData.getResultPay();

    	try {
    		orderpay.setText(String.format("%,20d 원", tableData.getSumPrice() - tableData.getPayMoney()));
        	discountpay.setText(String.format("- %,20d 원", tableData.getDiscount()));
        	requestPay.setText(String.format("- %,20d 원", payMoney));
    		resultpay.setText(String.format("%,20d 원", tableData.getSumPrice()-tableData.getDiscount()-tableData.getPayMoney()-payMoney));
    	}catch (Exception e) {
    		//ignore
    	}
    	
    	
    }
    
    public TableData getTableData() {
    	return tableData;
    }

}
