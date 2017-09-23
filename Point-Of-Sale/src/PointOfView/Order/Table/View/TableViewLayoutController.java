package PointOfView.Order.Table.View;

import java.net.URL;
import java.util.ResourceBundle;

import PointOfView.MainApp;
import PointOfView.Model.Hbox_MenuItem;
import PointOfView.Order.Menu.Model.MenuItem;
import PointOfView.Order.Menu.Modify.MenuModifier;
import PointOfView.Order.Table.Model.OrderList;
import PointOfView.Order.Table.Model.TableData;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.geometry.Pos;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;

public class TableViewLayoutController implements Initializable{
	
	private MainApp mainApp = null;
	private TableData tableData = null;
	private TableData thisTableData = null;
	
	private ObservableList<Hbox_MenuItem> orderMenuList = FXCollections.observableArrayList();
	
	/** 컴포넌트 정의 **/
	@FXML private GridPane menuPane;
	@FXML private ListView<Hbox_MenuItem> orderList;
	
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
		printCurrentPrice();
	}
	
	@FXML
	private void handlePlusButton() {
		thisTableData.addMenu(mainApp.getDataManagement().getMenues().getMenuItems().get(1));
		printCurrentPrice();
	}
	
	@FXML
	private void handleDeleteButton() {
		printCurrentPrice();
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
		
		for(OrderList item:thisTableData.getOrderList()) {

			Hbox_MenuItem pane = new Hbox_MenuItem(15, item.getMenuItem());
			pane.setPrefHeight(50);
			pane.setAlignment(Pos.CENTER_RIGHT);
			
			Label name = new Label(String.format("%30s%30d%,30d 원", item.getName(),item.getCount(),item.getPrice() ));
			name.setFont(Font.font( "Malgun Gothic", FontWeight.BOLD, 24));
			
			pane.getChildren().addAll(name);
			
			orderMenuList.add(pane);
			
		}
		
	}
	
	private void drawMenuPane() {
		
		int showMenus = 0;
		
		for(MenuItem item:mainApp.getDataManagement().getMenues().getMenuItems()) {
			
			if(!item.isShow())
				continue;
			
			VBox pane = new VBox(10);
			pane.setAlignment(Pos.CENTER);
			
			Label name = new Label(item.getName());
			name.setFont(Font.font("맑은 고딕", FontWeight.BOLD, 24));
			
			Label price = new Label(String.format("%,10d 원", item.getPrice()));
			name.setFont(Font.font("맑은 고딕", FontWeight.BOLD, 18));
			
			pane.getChildren().add(name);
			pane.getChildren().add(price);
			
			
			pane.setStyle("-fx-border-color: #000000;"
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
			
			pane.setOnMouseClicked(e -> {
				
				thisTableData.addMenu(item);
				drawOrderList();
				printCurrentPrice();
				
			});
			
			
			menuPane.add(pane, item.getColumn(), item.getRow());
			showMenus++;
				
		}
		
		if(showMenus==0) {
			
			Alert alert = new Alert(AlertType.ERROR, "오류", ButtonType.OK);
			alert.initOwner(mainApp.getPrimaryStage());
			alert.setHeaderText("등록 된 메뉴가 없습니다.");
			alert.setContentText("메뉴를 등록 해 주세요.");
			alert.showAndWait();
			
			new MenuModifier(mainApp).showAndWait();
			
			drawMenuPane();
		}
			
		
	}
	
	private void printCurrentPrice() {
		lbnWholePrice.setText(String.format("%,20d 원", thisTableData.getSumPrice()));
	}
	
	public void setMainApp(MainApp mainApp, TableData tableData){
		this.mainApp = mainApp;
		this.tableData = tableData;
		
		thisTableData = new TableData();
		thisTableData.copyData(tableData);
		
		orderList.setItems(orderMenuList);
		
		
		
		lbnTableTitle.setText(String.valueOf(tableData.getTableIndex()+1 + " 번 테이블"));
		printCurrentPrice();
		
		drawOrderList();

		drawMenuPane();
	}
}