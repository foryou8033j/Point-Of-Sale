package PointOfView.View.Table.Receipt.Cash;

import PointOfView.MainApp;
import PointOfView.Models.Receipt.ReceiptModel;
import PointOfView.Models.Table.TableData;
import javafx.fxml.FXML;
import javafx.scene.control.Label;

/**
 * 카드 결제 영수증 레이아웃 컨트롤러
 * 
 * @author Jeongsam
 *
 */
public class CashReceiptLayoutController {

    @FXML
    private Label dealNumber;

    @FXML
    private Label dealTime;

    @FXML
    private Label PriceSum;

    @FXML
    private Label shopName;

    @FXML
    private Label taxPrice;

    @FXML
    private Label taxExceptPrice;

    @FXML
    private Label cashPrice;

    @FXML
    private Label receiveCash;

    @FXML
    private Label returnedCash;

    private ReceiptModel receipt;
    private TableData tableData;
    private MainApp mainApp;

    public void setReceipt(MainApp mainApp, ReceiptModel receipt) {

	this.mainApp = mainApp;
	this.receipt = receipt;
	this.tableData = receipt.getPayTableData();

	cashPrice.setText(String.format("%,20d 원", tableData.getSumPrice()));
	PriceSum.setText(String.format("%,20d 원", tableData.getSumPrice()));

	shopName.setText(mainApp.getDataManagement().getPOSTitle());

	dealNumber.setText("거래 번호 : " + String.format("%010d", receipt.getIndex()));

	int sum = tableData.getSumPrice();
	int tax = (int) (sum * 0.02);
	sum = sum - tax;

	taxExceptPrice.setText(String.format("%,20d 원", sum));
	taxPrice.setText(String.format("%,20d 원", tax));

	receiveCash.setText(String.format("%,20d 원", tableData.getSumPrice() + receipt.getReturnedCash()));
	returnedCash.setText(String.format("%,20d 원", receipt.getReturnedCash()));

    }

}
