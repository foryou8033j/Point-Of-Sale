package PointOfView.Order.Table.View;

import java.net.URL;
import java.util.ResourceBundle;

import PointOfView.MainApp;
import PointOfView.Order.Table.Model.TableData;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;

public class TableViewLayoutController implements Initializable{
	
	private MainApp mainApp = null;
	
	/** 컴포넌트 정의 **/
	
	@FXML private GridPane menuPane;
	@FXML private ListView<HBox> orderList;
	
	@FXML private Button btnPrevToTitle;
	
	@FXML private Button btnMinus;
	@FXML private Button btnPlus;
	@FXML private Button btnDelete;
	@FXML private Button btnOrder;
	@FXML private Button btnCancle;
	
	@FXML private Button btnDiscount;
	@FXML private Button btnCardPayment;
	@FXML private Button btnCashPayment;
	
	@FXML private Label lbnTableTitle;
	@FXML private Label lbnWholePrice;
	@FXML private Label lbnDiscountPrice;
	@FXML private Label lbnResultPrice;
	
	/** 핸들 정의 **/
	@FXML
	private void handlePrevToTitleButton(){
		mainApp.getRootLayoutController().showOrderMenu();
	}
	
	@FXML
	private void handleMinusButton() {
		
	}
	
	@FXML
	private void handlePlusButton() {
		
	}
	
	@FXML
	private void handleDeleteButton() {
		
	}
	
	
	@FXML
	private void handleOrderButton() {
		
	}
	
	@FXML
	private void handleCancleButton() {
		
	}
	
	@FXML
	private void handleDiscountButton() {
		
	}
	
	@FXML
	private void handleCardPaymentButton() {
		
	}
	
	@FXML
	private void handleCashPaymentButton() {
		
	}
	
	
	@Override
	public void initialize(URL location, ResourceBundle resources) {
		// TODO Auto-generated method stub
		
	}
	
	
	
	public void setMainApp(MainApp mainApp, TableData tableData){
		this.mainApp = mainApp;
		
		lbnTableTitle.setText(String.valueOf(tableData.getTableIndex()+1 + " 번 테이블"));
		lbnWholePrice.setText(String.valueOf(tableData.getSumPrice()) + " 원");
	}
}