package PointOfView.Title.View;

import java.net.URL;
import java.util.ResourceBundle;

import PointOfView.MainApp;
import PointOfView.Order.View.OrderLayoutController;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.layout.BorderPane;

public class TitleLayoutController implements Initializable{

	private MainApp mainApp = null;
	
	@FXML private Button btnOrderMenu;
	@FXML private Button btnStockMenu;
	@FXML private Button btnStaffMenu;
	@FXML private Button btnStatisticsMenu;
	
	/* 버튼 핸들러 관리 */
	@FXML
	private void handleShowOrderMenu(){
		showOrderMenu();
	}
	
	
	
	
	/* 레이아웃 로드 관리 */
	private void showOrderMenu(){
		try{
			FXMLLoader loader = new FXMLLoader(getClass().getResource("../../Order/View/OrderLayout.fxml"));
			BorderPane pane = loader.load();

			
			OrderLayoutController controller = loader.getController();
			controller.setMainApp(mainApp);
			
			mainApp.getRootLayoutController().showThisPane(pane);
			
			
		}catch (Exception e){
			e.printStackTrace();
		}
	}
	
	
	
	@Override
	public void initialize(URL location, ResourceBundle resources) {
		// TODO Auto-generated method stub
		
	}
	
	

	public void setMainApp(MainApp mainApp){
		this.mainApp = mainApp;
	}
	
}
