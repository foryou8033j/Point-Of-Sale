package PointOfView.View.Table.Receipt.Card;

import PointOfView.Models.Receipt.ReceiptModel;
import javafx.fxml.FXML;
import javafx.scene.control.Label;

public class CardReceiptLayoutController {

    @FXML
    private Label dealNumber;

    @FXML
    private Label cardNumber;

    @FXML
    private Label cardName;

    @FXML
    private Label dealTime;

    @FXML
    private Label Pay;

    @FXML
    private Label dealAcceptNumber;

    @FXML
    private Label cardCompany;

    @FXML
    private Label shopName;
    
    private ReceiptModel receipt;
    
    public void setReceipt(ReceiptModel receipt) {
    	this.receipt = receipt;
    	
    	Pay.setText(String.valueOf(receipt.getPayTableData().getSumPrice()));
    	
    }

}
