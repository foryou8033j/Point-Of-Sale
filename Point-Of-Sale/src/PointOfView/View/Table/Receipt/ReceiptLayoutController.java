package PointOfView.View.Table.Receipt;

import java.net.URL;
import java.util.ResourceBundle;

import PointOfView.MainApp;
import PointOfView.Models.OrderList.OrderList;
import PointOfView.Models.Receipt.PAY_WAY;
import PointOfView.Models.Receipt.ReceiptModel;
import PointOfView.View.Table.Receipt.Card.CardReceiptDetailLayoutController;
import PointOfView.View.Table.Receipt.Card.CardReceiptLayoutController;
import PointOfView.View.Table.Receipt.Cash.CashReceiptDetailLayoutController;
import PointOfView.View.Table.Receipt.Cash.CashReceiptLayoutController;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.control.RadioButton;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.ToggleGroup;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.BorderPane;
import javafx.scene.text.Text;
import javafx.stage.Stage;

/**
 * 영수증 레이아웃을 관리하는 컨트롤러
 * 
 * @author Jeongsam
 *
 */
public class ReceiptLayoutController implements Initializable {

    @FXML
    private ToggleGroup receipeGroup;

    @FXML
    private RadioButton commonReceipe;

    @FXML
    private RadioButton specReceipe;

    @FXML
    private TableView<ReceiptModel> table;

    @FXML
    private TableColumn<ReceiptModel, String> dateColumn;

    @FXML
    private TableColumn<ReceiptModel, String> wayColumn;

    @FXML
    private TableColumn<ReceiptModel, String> payColumn;

    @FXML
    private TableView<OrderList> receipeTable;

    @FXML
    private TableColumn<OrderList, String> orderColumn;

    @FXML
    private TableColumn<OrderList, String> countColumn;

    @FXML
    private TableColumn<OrderList, String> priceColumn;

    @FXML
    private BorderPane receipePane;

    @Override
    public void initialize(URL location, ResourceBundle resources) {

	dateColumn.setCellValueFactory(cellData -> cellData.getValue().dateProperty());
	wayColumn.setCellValueFactory(cellData -> cellData.getValue().payProperty());
	payColumn.setCellValueFactory(cellData -> cellData.getValue().moneyProperty());

	orderColumn.setCellValueFactory(cellData -> cellData.getValue().nameProperty());
	countColumn.setCellValueFactory(cellData -> cellData.getValue().countProperty().asString());
	priceColumn.setCellValueFactory(cellData -> cellData.getValue().priceProperty());

	table.getSelectionModel().selectedItemProperty()
		.addListener((observable, oldValue, newValue) -> drawReceipt(newValue));

	commonReceipe.selectedProperty().addListener(
		(observable, oldValue, newValue) -> drawReceipt(table.getSelectionModel().getSelectedItem()));

	specReceipe.selectedProperty().addListener(
		(observable, oldValue, newValue) -> drawReceipt(table.getSelectionModel().getSelectedItem()));

    }

    private void drawReceipt(ReceiptModel receipt) {
	if (receipt == null)
	    receipePane.setCenter(null);

	receipeTable.setItems(receipt.getPayTableData().getOrderList());

	if (receipt.getPayWay().equals(PAY_WAY.CARD)) {
	    if (commonReceipe.isSelected())
		receipePane.setCenter(drawCardReceipt(receipt));
	    else
		receipePane.setCenter(drawCardDetailReceipt(receipt));
	} else {
	    if (commonReceipe.isSelected())
		receipePane.setCenter(drawCashReceipt(receipt));
	    else
		receipePane.setCenter(drawCashDetailReceipt(receipt));
	}

    }

    private AnchorPane drawCardReceipt(ReceiptModel receipt) {
	try {
	    FXMLLoader loader = new FXMLLoader(getClass().getResource("Card/CardReceiptLayout.fxml"));
	    AnchorPane pane = loader.load();

	    CardReceiptLayoutController controller = loader.getController();
	    controller.setReceipt(mainApp, receipt);

	    return pane;

	} catch (Exception e) {
	    e.printStackTrace();
	}
	return null;
    }

    private AnchorPane drawCardDetailReceipt(ReceiptModel receipt) {
	try {
	    FXMLLoader loader = new FXMLLoader(getClass().getResource("Card/CardReceiptDetailLayout.fxml"));
	    AnchorPane pane = loader.load();

	    CardReceiptDetailLayoutController controller = loader.getController();
	    controller.setReceipt(mainApp, receipt);

	    return pane;

	} catch (Exception e) {
	    e.printStackTrace();
	}
	return null;
    }

    private AnchorPane drawCashReceipt(ReceiptModel receipt) {
	try {
	    FXMLLoader loader = new FXMLLoader(getClass().getResource("Cash/CashReceiptLayout.fxml"));
	    AnchorPane pane = loader.load();

	    CashReceiptLayoutController controller = loader.getController();
	    controller.setReceipt(mainApp, receipt);

	    return pane;

	} catch (Exception e) {
	    e.printStackTrace();
	}
	return null;

    }

    private AnchorPane drawCashDetailReceipt(ReceiptModel receipt) {
	try {
	    FXMLLoader loader = new FXMLLoader(getClass().getResource("Cash/CashReceiptDetailLayout.fxml"));
	    AnchorPane pane = loader.load();

	    CashReceiptDetailLayoutController controller = loader.getController();
	    controller.setReceipt(mainApp, receipt);

	    return pane;

	} catch (Exception e) {
	    e.printStackTrace();
	}
	return null;

    }

    /*
     * private AnchorPane drawCashReceipt(ReceiptModel receipt) {
     * 
     * }
     */

    @FXML
    void handleClose(ActionEvent event) {
	stage.close();
    }

    private Stage stage;
    private MainApp mainApp;

    public void setMainApp(Stage stage, MainApp mainApp) {
	this.mainApp = mainApp;
	this.stage = stage;

	table.setItems(mainApp.getReceipts().getReceiptList());

	receipePane.setCenter(new Text("선택 된 영수증 없음"));

    }

}
