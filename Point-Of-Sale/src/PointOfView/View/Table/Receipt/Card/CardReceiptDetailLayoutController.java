package PointOfView.View.Table.Receipt.Card;

import PointOfView.MainApp;
import PointOfView.Models.OderList.OrderList;
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
public class CardReceiptDetailLayoutController {

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
    
    @FXML
    private Label menuList;
    
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
    	
    	String list = "";
    	
    	int i=0;
    	int less = 0;
    	
    	for(OrderList data:tableData.getOrderList()) {
    		
    		if (i++>5) {
    			less++;
    			continue;
    		}
    		
    		String name = StringPadding.getRPad(data.getName(), 48, " " );
    		String count = StringPadding.getRPad(String.valueOf(data.getCount()), 10, " " );
    		String price = String.valueOf(data.getPrice());
    		
    		list = list.concat(name + count + price + "\n");
    		
    	}
    	
    	if(i>5)
    		list = list.concat("기타 " + less + " 개의 메뉴");
    	
    	menuList.setText(list);
    	
    	cardCompany.setText(receipt.getCardCompany());
    	cardName.setText(receipt.getCardName());
    	cardNumber.setText(receipt.getCardNumber());
    	
    }

}
