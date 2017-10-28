package PointOfView.View.Table.Order;

import java.net.URL;
import java.util.Optional;
import java.util.ResourceBundle;

import PointOfView.MainApp;
import PointOfView.Models.Menu.MenuItem;
import PointOfView.Models.OrderList.OrderList;
import PointOfView.Models.Table.TableData;
import PointOfView.Util.Dialog.NumberInputDialog;
import PointOfView.View.Table.MenuModifiy.MenuModifier;
import PointOfView.View.Table.Payment.Card.CardPaymentLayoutController;
import PointOfView.View.Table.Payment.Cash.CashPaymentLayoutController;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.ListChangeListener;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

/**
 * 선택 된 테이블의 메뉴를 주문 하는 화면의 컨트롤러 클래스
 * 
 * @author Jeongsam
 */
public class TableOrderLayoutController implements Initializable {

    private MainApp mainApp = null;
    private TableData tableData = null;
    private TableData thisTableData = null;

    /** 컴포넌트 정의 **/
    @FXML
    private GridPane menuPane;

    @FXML
    private TableView<OrderList> tableView;
    @FXML
    private TableColumn<OrderList, String> nameColumn;
    @FXML
    private TableColumn<OrderList, String> countColumn;
    @FXML
    private TableColumn<OrderList, String> priceColumn;

    @FXML
    private Button btnPrevToTitle;

    @FXML
    private Button btnMinus;
    @FXML
    private Button btnPlus;
    @FXML
    private Button btnDelete;
    @FXML
    private Button btnOrder;
    @FXML
    private Button btnCancle;

    @FXML
    private Button btnDiscount;
    @FXML
    private Button btnCardPayment;
    @FXML
    private Button btnCashPayment;

    @FXML
    private Label lbnTableTitle;
    @FXML
    private Label lbnWholePrice;
    @FXML
    private Label lbnDiscountPrice;
    @FXML
    private Label lbnPayPrice;
    @FXML
    private Label lbnResultPrice;

    /** 핸들 정의 **/

    /**
     * 이전 메뉴로 이동하는 핸들
     */
    @FXML
    private void handlePrevToTitleButton() {
	// 변경 검사를 한다.
	mainApp.getRootLayoutController().showOrderMenu();
    }

    /**
     * 선택 된 메뉴의 개수를 감소 시키는 핸들
     */
    @FXML
    private void handleMinusButton() {

	if (tableView.getSelectionModel().getSelectedItem() == null)
	    return;

	tableView.getSelectionModel().getSelectedItem().minusCount();
	
	if(tableView.getSelectionModel().getSelectedItem().getCount() <= 0)
	    thisTableData.getOrderList().remove(tableView.getSelectionModel().getSelectedItem());

	printCurrentPrice();
    }

    /**
     * 선택 된 메뉴의 개수를 증가 시키는 핸들
     */
    @FXML
    private void handlePlusButton() {

	if (tableView.getSelectionModel().getSelectedItem() == null)
	    return;

	String menuMame = tableView.getSelectionModel().getSelectedItem().getMenuItem().getName();
	MenuItem item = mainApp.getDataManagement().getMenues().getMenuItem(menuMame);

	if (item.getStock() > 0) {
	    tableView.getSelectionModel().getSelectedItem().plusCount();

	    item.setStock(item.getStock() - 1);
	}

	printCurrentPrice();
    }

    /**
     * 선택 된 메뉴를 제거 시키는 핸들
     */
    @FXML
    private void handleDeleteButton() {

	if (tableView.getSelectionModel().getSelectedItem() == null)
	    return;
	thisTableData.getOrderList().remove(tableView.getSelectionModel().getSelectedItem());
	tableView.getSelectionModel().getSelectedItem().clearCount();
	printCurrentPrice();
    }

    /**
     * 현재 주문된 메뉴를 등록 시킨다.
     */
    @FXML
    private void handleOrderButton() {

	// 메뉴 데이터를 저장하여 재고량을 저장한다.
	mainApp.getDataManagement().getMenues().saveDataToFile();
	tableData.copyData(thisTableData);
	mainApp.getRootLayoutController().showOrderMenu();
    }

    /**
     * 현재 주문 된 메뉴를 등록하지 않고 이전 화면으로 이동한다.
     */
    @FXML
    private void handleCancleButton() {
	// 메뉴 데이터를 저장하지 않고 기존 데이터를 다시 불러온다. (재고량 변동 복구)
	mainApp.getDataManagement().getMenues().loadDataFromFile();
	mainApp.getRootLayoutController().showOrderMenu();
    }

    /**
     * 할인 금액을 지정 할 수 있다.
     */
    @FXML
    private void handleDiscountButton() {

	try {

	    int var = new NumberInputDialog(mainApp, "할인 금액을 입력하세요.", false).getInputValue();

	    if (var >= 0)
		thisTableData.setDiscount(var);

	} catch (Exception e) {
	    // ignore
	}

	printCurrentPrice();
    }

    @FXML
    private void handleCardPaymentButton() {

	if (!tableData.getOrderList().equals(thisTableData.getOrderList())
		|| tableData.getSumPrice() != thisTableData.getSumPrice()) {

	    Alert alert = new Alert(AlertType.INFORMATION);
	    alert.initOwner(mainApp.getPrimaryStage());
	    alert.setTitle("카드 결제");
	    alert.setHeaderText("주문 정보가 저장 되지 않았습니니다.");
	    alert.setContentText("저장 후 계속 진행 하시겠습니까?");
	    alert.getButtonTypes().clear();
	    alert.getButtonTypes().addAll(ButtonType.YES, ButtonType.NO);

	    Optional<ButtonType> result = alert.showAndWait();

	    if (result.get().equals(ButtonType.NO))
		return;

	}

	// 주문된 정보를 저장한다.
	tableData.copyData(thisTableData);

	int payMoney = new NumberInputDialog(mainApp, "지불 금액을 입력하세요.", false).getInputValue();

	if (payMoney == -1)
	    return;

	try {

	    Stage stage = new Stage();
	    stage.setTitle("카드 결제");
	    stage.initModality(Modality.APPLICATION_MODAL);
	    stage.initOwner(mainApp.getPrimaryStage());
	    stage.initStyle(StageStyle.UNDECORATED);

	    FXMLLoader loader = new FXMLLoader(TableOrderLayoutController.class
		    .getResource("/PointOfView/View/Table/Payment/Card/CardPaymentLayout.fxml"));
	    BorderPane pane = loader.load();

	    CardPaymentLayoutController controller = loader.getController();
	    controller.setPaymentObject(mainApp, tableData, stage, payMoney);

	    Scene scene = new Scene(pane);
	    stage.setScene(scene);

	    stage.showAndWait();

	    // 주문된 정보를 저장한다.
	    thisTableData.copyData(controller.getTableData());
	    tableData.copyData(thisTableData);

	} catch (Exception e) {
	    e.printStackTrace();
	}

	printCurrentPrice();
    }

    @FXML
    private void handleCashPaymentButton() {
	if (!tableData.getOrderList().equals(thisTableData.getOrderList())
		|| tableData.getSumPrice() != thisTableData.getSumPrice()) {

	    Alert alert = new Alert(AlertType.INFORMATION);
	    alert.initOwner(mainApp.getPrimaryStage());
	    alert.setTitle("카드 결제");
	    alert.setHeaderText("주문 정보가 저장 되지 않았습니니다.");
	    alert.setContentText("저장 후 계속 진행 하시겠습니까?");
	    alert.getButtonTypes().clear();
	    alert.getButtonTypes().addAll(ButtonType.YES, ButtonType.NO);

	    Optional<ButtonType> result = alert.showAndWait();

	    if (result.get().equals(ButtonType.NO))
		return;

	}

	// 주문된 정보를 저장한다.
	tableData.copyData(thisTableData);

	int payMoney = new NumberInputDialog(mainApp, "지불 금액을 입력하세요.", false).getInputValue();

	if (payMoney == -1)
	    return;

	try {

	    Stage stage = new Stage();
	    stage.setTitle("현금 결제");
	    stage.initModality(Modality.APPLICATION_MODAL);
	    stage.initOwner(mainApp.getPrimaryStage());
	    stage.initStyle(StageStyle.UNDECORATED);

	    FXMLLoader loader = new FXMLLoader(TableOrderLayoutController.class
		    .getResource("/PointOfView/View/Table/Payment/Cash/CashPaymentLayout.fxml"));
	    BorderPane pane = loader.load();

	    CashPaymentLayoutController controller = loader.getController();
	    controller.setPaymentObject(mainApp, tableData, stage, payMoney);

	    Scene scene = new Scene(pane);
	    stage.setScene(scene);

	    stage.showAndWait();

	    // 주문된 정보를 저장한다.
	    thisTableData.copyData(controller.getTableData());
	    tableData.copyData(thisTableData);

	} catch (Exception e) {
	    e.printStackTrace();
	}
	printCurrentPrice();
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {

	nameColumn.setCellValueFactory(cellData -> cellData.getValue().nameProperty());
	countColumn.setCellValueFactory(cellData -> cellData.getValue().countProperty().asString());
	priceColumn.setCellValueFactory(cellData -> cellData.getValue().priceProperty());

    }

    private void drawMenuPane() {

	int showMenus = 0;

	for (MenuItem item : mainApp.getDataManagement().getMenues().getMenuItems()) {

	    if (!item.isShow())
		continue;

	    VBox pane = new VBox(10);
	    pane.setAlignment(Pos.CENTER);

	    Label name = new Label(item.getName());
	    name.setFont(Font.font("Malgun Gothic", FontWeight.BOLD, 20));
	    if (name.getText().length() > 5)
		name.setFont(Font.font("Malgun Gothic", FontWeight.BOLD, 20 - name.getText().length()));

	    Label price = new Label(String.format("%,10d 원", item.getPrice()));
	    price.setFont(Font.font("Malgun Gothic", FontWeight.BOLD, 14));

	    pane.getChildren().add(name);
	    pane.getChildren().add(price);

	    pane.setStyle("-fx-border-color: #000000;" + "-fx-border-width: 1.5;" + "-fx-border-radius: 15;"
		    + "-fx-background-radius: 16.4, 15;" + "-fx-background-color: #EFFFE9");

	    pane.setOnMousePressed(e -> {
		pane.setStyle("-fx-border-color: #0000FF;" + "-fx-border-width: 1.5;" + "-fx-border-radius: 15;"
			+ "-fx-background-radius: 16.4, 15;" + "-fx-background-color: #FFFFFF");
	    });

	    pane.setOnMouseReleased(e -> {
		pane.setStyle("-fx-border-color: #000000; " + "-fx-border-width: 1.5;" + "-fx-border-radius: 15;"
			+ "-fx-background-radius: 16.4, 15;" + "-fx-background-color: #EFFFE9");
	    });

	    pane.setOnMouseClicked(e -> {

		if (item.getStock() > 0) {
		    thisTableData.addMenu(item);
		    item.setStock(item.getStock() - 1);
		    printCurrentPrice();

		    if (item.getStock() <= 0)
			pane.setDisable(true);
		}

	    });

	    if (item.getStock() <= 0)
		pane.setDisable(true);

	    menuPane.add(pane, item.getColumn(), item.getRow());
	    showMenus++;

	}

	if (showMenus == 0) {

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
	lbnDiscountPrice.setText(String.format("- %,20d 원", thisTableData.getDiscount()));
	lbnPayPrice.setText(String.format("- %,20d 원", thisTableData.getPayMoney()));
	lbnResultPrice.setText(String.format("%,20d 원",
		thisTableData.getSumPrice() - thisTableData.getDiscount() - thisTableData.getPayMoney()));
    }

    private void checkItemListToButton() {
	if (thisTableData.getOrderList().size() == 0) {
	    btnCardPayment.setDisable(true);
	    btnCashPayment.setDisable(true);
	} else {
	    btnCardPayment.setDisable(false);
	    btnCashPayment.setDisable(false);
	}
    }

    public void setMainApp(MainApp mainApp, TableData tableData) {
	this.mainApp = mainApp;
	this.tableData = tableData;

	// 테이블 주문 전 기존 메뉴 데이터를 저장한다.
	mainApp.getDataManagement().getMenues().saveDataToFile();

	thisTableData = new TableData();
	thisTableData.copyData(tableData);

	tableView.setItems(thisTableData.getOrderList());

	checkItemListToButton();
	thisTableData.getOrderList().addListener(new ListChangeListener<OrderList>() {
	    @Override
	    public void onChanged(Change<? extends OrderList> c) {

		checkItemListToButton();

	    }
	});

	lbnTableTitle.setText(String.valueOf(tableData.getTableIndex() + 1 + " 번 테이블"));
	printCurrentPrice();

	drawMenuPane();
    }
}