package PointOfView.View.Table.Receipt.Cash;

import PointOfView.MainApp;
import PointOfView.Models.OrderList.OrderList;
import PointOfView.Models.Receipt.ReceiptModel;
import PointOfView.Models.Table.TableData;
import PointOfView.Util.StringPadding;
import javafx.fxml.FXML;
import javafx.scene.control.Label;

/**
 * 카드 결제 영수증 레이아웃 컨트롤러
 * @author Jeongsam
 *
 */
public class CashReceiptDetailLayoutController {

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
    
    @FXML
    private Label menuList;
    
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
    	
    	String list = "";
    	
    	int i=0;
    	int less = 0;
    	
    	for(OrderList data:tableData.getOrderList()) {
    		
    		if (i++>6) {
    			less++;
    			continue;
    		}
    		
    		String name = StringPadding.getRPad(data.getName(), 48, " " );
    		String count = StringPadding.getRPad(String.valueOf(data.getCount()), 10, " " );
    		String price = String.valueOf(data.getPrice());
    		
    		list = list.concat(name + count + price + "\n");
    		
    	}
    	
    	if(i>6)
    		list = list.concat("기타 " + less + " 개의 메뉴");
    	
    	menuList.setText(list);
    	
    	
    	
    	int sum = tableData.getSumPrice();
    	int tax = (int)(sum * 0.02);
    	sum = sum - tax;
    	
    	taxExceptPrice.setText(String.format("%,20d 원", sum));
    	taxPrice.setText(String.format("%,20d 원", tax));
    	
    	receiveCash.setText(String.format("%,20d 원", tableData.getSumPrice() + receipt.getReturnedCash()));
    	returnedCash.setText(String.format("%,20d 원", receipt.getReturnedCash()));
    	
    	
    	
    }

}
