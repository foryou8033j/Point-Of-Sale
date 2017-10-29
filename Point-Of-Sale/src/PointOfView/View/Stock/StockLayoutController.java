package PointOfView.View.Stock;

import PointOfView.MainApp;
import PointOfView.Models.Menu.MenuItem;
import PointOfView.View.Stock.Item.StockItemLayoutController;
import javafx.collections.ListChangeListener;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.ListView;
import javafx.scene.layout.GridPane;
import javafx.scene.text.Text;

/**
 * 재고 관리 컨트롤러
 * 
 * @author Jeongsam
 *
 */
public class StockLayoutController {

    @FXML
    private Text lbnNotice;

    @FXML
    private ListView<GridPane> listView;

    private MainApp mainApp;

    @FXML
    void handleBackToTitle(ActionEvent event) {
	mainApp.showTitleMenu();
    }

    private void drawList() {

	listView.getItems().clear();

	for (MenuItem item : mainApp.getDataManagement().getMenues().getMenuItems()) {

	    try {

		FXMLLoader loader = new FXMLLoader(
			getClass().getResource("/PointOfView/View/Stock/Item/StockItemLayout.fxml"));
		GridPane pane = loader.load();

		StockItemLayoutController controller = loader.getController();
		controller.setMenuItem(mainApp.getDataManagement().getMenues(), item);

		listView.getItems().add(pane);

	    } catch (Exception e) {

	    }

	}

    }

    private void menuItemChangeListener() {

	mainApp.getDataManagement().getMenues().getMenuItems().addListener(new ListChangeListener<MenuItem>() {
	    @Override
	    public void onChanged(Change<? extends MenuItem> c) {

		drawList();

	    }
	});

    }

    public void setMainApp(MainApp mainApp) {
	this.mainApp = mainApp;

	menuItemChangeListener();

	drawList();
    }

}
