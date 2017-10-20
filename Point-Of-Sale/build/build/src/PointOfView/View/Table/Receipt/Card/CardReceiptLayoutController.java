package PointOfView.View.Table.Receipt.Card;

import PointOfView.MainApp;
import PointOfView.Models.Receipt.ReceiptModel;
import PointOfView.Models.Table.TableData;
import javafx.fxml.FXML;
import javafx.scene.control.Label;

/**
 * 카드 결제 영수증 레이아웃 컨트롤러
 * @author Jeongsam
 *
 */
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
    private Label Pay0;
    
    @FXML
    private Label Pay1;

    @FXML
    private Label dealAcceptNumber;

    @FXML
    private Label cardCompany;

    @FXML
    private Label shopName;
    
    private ReceiptModel receipt;
    private TableData tableData;
    private MainApp mainApp;
    public void setReceipt(MainApp mainApp, ReceiptModel receipt) {
    	
    	this.mainApp = mainApp;
    	this.receipt = receipt;
    	this.tableData = receipt.getPayTableData();
    	
    	Pay0.setText(String.format("%,20d 원", tableData.getSumPrice()));
    	Pay1.setText(String.format("%,20d 원", tableData.getSumPrice()));
    	
    	shopName.setText(mainApp.getDataManagement().getPOSTitle());
    	
    	dealNumber.setText("거래 번호 : " + String.format("%010d", receipt.getIndex()));
    	dealAcceptNumber.setText("승인 번호 : " + String.format("%010d", receipt.getIndex()));
    	
    	
    	cardCompany.setText(receipt.getCardCompany());
    	cardName.setText(receipt.getCardName());
    	cardNumber.setText(receipt.getCardNumber());
    	
    }

}
