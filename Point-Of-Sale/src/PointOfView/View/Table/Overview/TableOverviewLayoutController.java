package PointOfView.View.Table.Overview;

import java.net.URL;
import java.util.Calendar;
import java.util.ResourceBundle;

import PointOfView.MainApp;
import PointOfView.Models.Table.TableData;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;

/**
 * 테이블에 테이블 정보를 간략하게 보여주는 TableOvervieLayout 컨트롤러
 * 
 * @author Jeongsam
 *
 */
public class TableOverviewLayoutController implements Initializable {

    private MainApp mainApp = null;

    @FXML
    private Label tableNumber;
    @FXML
    private Label tableMenuList;
    @FXML
    private Label tableSumPrice;

    private TableData tableData;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
	// TODO Auto-generated method stub

	Calendar ad = Calendar.getInstance();

    }

    private void tableNumberChangeListener() {

    }

    public void setMainApp(MainApp mainApp, TableData tableData) {
	this.mainApp = mainApp;
	this.tableData = tableData;

	tableNumber.setText("[ " + String.valueOf(tableData.getTableIndex() + 1) + " ]");

	// TODD 테이블 간략하게 보여주는 부분 수정 필요
	String menus = "";
	int count = tableData.getOrderList().size();

	if (count == 0)
	    menus = "";
	else if (count == 1)
	    menus = tableData.getOrderList().get(0).getName();
	else
	    menus = tableData.getOrderList().get(0).getName() + " 등 " + count + "개";

	tableMenuList.setText(menus);

	tableSumPrice.setText(String.format("" + "%,20d 원\n" + "%,20d 원\n" + "%,20d 원", tableData.getSumPrice(),
		(tableData.getDiscount() + tableData.getPayMoney()) * -1,
		tableData.getSumPrice() - tableData.getDiscount() + tableData.getPayMoney()));

    }
}