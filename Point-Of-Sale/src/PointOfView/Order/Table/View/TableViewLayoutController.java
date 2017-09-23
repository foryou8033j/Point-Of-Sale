package PointOfView.Order.Table.View;

import java.net.URL;
import java.util.ResourceBundle;

import PointOfView.MainApp;
import PointOfView.Order.Menu.Model.MenuItem;
import PointOfView.Order.Table.Model.TableData;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;

public class TableViewLayoutController implements Initializable{
	
	private MainApp mainApp = null;
	private TableData tableData = null;
	private TableData thisTableData = null;
	
	private ObservableList<HBox> orderMenuList = FXCollections.observableArrayList();
	private ObservableList<TableOrderListLayoutController> orderMenuControllerList = FXCollections.observableArrayList();
	
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
		//변경 검사를 한다.
		mainApp.getRootLayoutController().showOrderMenu();
	}
	
	@FXML
	private void handleMinusButton() {
		thisTableData.removeMenu(mainApp.getDataManagement().getMenues().getMenuItems().get(1));
		lbnWholePrice.setText(String.valueOf(thisTableData.getSumPrice()) + " 원");
	}
	
	@FXML
	private void handlePlusButton() {
		thisTableData.addMenu(mainApp.getDataManagement().getMenues().getMenuItems().get(1));
		lbnWholePrice.setText(String.valueOf(thisTableData.getSumPrice()) + " 원");
	}
	
	@FXML
	private void handleDeleteButton() {
		
	}
	
	
	@FXML
	private void handleOrderButton() {
		
		tableData.copyData(thisTableData);
		mainApp.getRootLayoutController().showOrderMenu();
	}
	
	@FXML
	private void handleCancleButton() {
		mainApp.getRootLayoutController().showOrderMenu();
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

	private void drawOrderList() {
		
		
		orderMenuList.clear();
		
		try{
			
			FXMLLoader loader = new FXMLLoader(getClass().getResource("TableOrderListLayout.fxml"));
			HBox pane = loader.load();

			
			TableOrderListLayoutController controller = loader.getController();
			//controller.setMainApp(mainApp, tableData);
			
			orderMenuList.add(pane);
			
			
		}catch (Exception e){
			e.printStackTrace();
		}
		
	}
	
	private void drawMenuPane() {
		
		int x = 0;
		int y = 0;
		
		for(MenuItem item:mainApp.getDataManagement().getMenues().getMenuItems()) {
			
			
			VBox pane = new VBox(10);
			pane.setAlignment(Pos.CENTER);
			
			Label name = new Label(item.getName());
			name.setFont(Font.font("맑은 고딕", FontWeight.BOLD, 24));
			
			Label price = new Label(String.valueOf(item.getPrice()));
			name.setFont(Font.font("맑은 고딕", FontWeight.BOLD, 18));
			
			pane.getChildren().add(name);
			pane.getChildren().add(price);
			
			
			pane.setStyle("-fx-border-color: #000000; "
					+ "-fx-border-width: 1.5;"
					+ "-fx-border-radius: 15;"
					+ "-fx-background-radius: 16.4, 15;"
					+ "-fx-background-color: #EFFFE9");
			
			pane.setOnMousePressed(e -> {
				pane.setStyle("-fx-border-color: #0000FF;"
						+ "-fx-border-width: 1.5;"
						+ "-fx-border-radius: 15;"
						+ "-fx-background-radius: 16.4, 15;"
						+ "-fx-background-color: #FFFFFF");
			});
			
			pane.setOnMouseReleased(e -> {
				pane.setStyle("-fx-border-color: #000000; "
						+ "-fx-border-width: 1.5;"
						+ "-fx-border-radius: 15;"
						+ "-fx-background-radius: 16.4, 15;"
						+ "-fx-background-color: #EFFFE9");
			});
			
			menuPane.add(pane, x++, y);
			
			if(x % 6 == 0) {
				x = 0;
				y++;
			}
				
			
		}
		
	}
	
	public void setMainApp(MainApp mainApp, TableData tableData){
		this.mainApp = mainApp;
		this.tableData = tableData;
		
		thisTableData = new TableData();
		thisTableData.copyData(tableData);
		
		orderList.setItems(orderMenuList);
		
		drawMenuPane();
		
		lbnTableTitle.setText(String.valueOf(tableData.getTableIndex()+1 + " 번 테이블"));
		lbnWholePrice.setText(String.valueOf(tableData.getSumPrice()) + " 원");
		
		
		drawOrderList();
		
	}
}