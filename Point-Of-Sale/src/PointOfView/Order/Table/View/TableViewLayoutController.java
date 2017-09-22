package PointOfView.Order.Table.View;

import java.net.URL;
import java.util.ResourceBundle;

import PointOfView.MainApp;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;

public class TableViewLayoutController implements Initializable{
	
	private MainApp mainApp = null;
	
	/** 컴포넌트 정의 **/
	@FXML private Button btnPrevToTitle;
	
	@FXML private Button btnMinus;
	@FXML private Button btnPlus;
	@FXML private Button btnDelete;
	@FXML private Button btnOrder;
	@FXML private Button btnCancle;
	
	@FXML private Button btnDiscount;
	@FXML private Button btnCardPayment;
	@FXML private Button btnCashPayment;
	
	/** 핸들 정의 **/
	@FXML
	private void handlePrevToTitleButton(){
		mainApp.getRootLayoutController().showOrderMenu();
	}
	
	@Override
	public void initialize(URL location, ResourceBundle resources) {
		// TODO Auto-generated method stub
		
	}
	
	
	
	public void setMainApp(MainApp mainApp){
		this.mainApp = mainApp;
	}
}