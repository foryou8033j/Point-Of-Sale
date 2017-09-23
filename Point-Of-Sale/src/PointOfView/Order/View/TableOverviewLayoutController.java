package PointOfView.Order.View;

import java.net.URL;
import java.util.Calendar;
import java.util.ResourceBundle;

import PointOfView.MainApp;
import PointOfView.Order.Table.Model.TableData;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;

public class TableOverviewLayoutController implements Initializable{
	
	private MainApp mainApp = null;
	
	@FXML private Label tableNumber;
	@FXML private Label tableMenuList;
	@FXML private Label tableSumPrice;
	
	private TableData tableData;
	
	@Override
	public void initialize(URL location, ResourceBundle resources) {
		// TODO Auto-generated method stub

		Calendar ad = Calendar.getInstance();
		
		
	}
	
	
	
	private void tableNumberChangeListener() {
		
	}
	
	public void setMainApp(MainApp mainApp, TableData tableData){
		this.mainApp = mainApp;
		this.tableData = tableData;
		
		tableNumber.setText("[ " + String.valueOf(tableData.getTableIndex()+1) + " ]");
		
		
		//TODD 테이블 간략하게 보여주는 부분 수정 필요
		String menus = "";
		for(int i=0; i<tableData.getOrderList().size(); i++) {
			if(i==2) break;
			menus = tableData.getOrderList().get(i).getMenuItem().getName() + "\n";
		}
		
		if(tableData.getOrderList().size() > 2)
			menus = menus.concat("등 " + (tableData.getOrderList().size()-3) + "개");
		
		tableMenuList.setText(menus);
		
		
		tableSumPrice.setText(String.valueOf(tableData.getSumPrice()) + " 원");
		
	}
}