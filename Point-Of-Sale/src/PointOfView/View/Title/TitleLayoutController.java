package PointOfView.View.Title;

import java.net.URL;
import java.util.ResourceBundle;

import PointOfView.MainApp;
import PointOfView.Util.Dialog.ExceptionDialog;
import PointOfView.View.Config.ConfigLayoutController;
import PointOfView.View.Table.TableLayoutController;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.layout.BorderPane;

public class TitleLayoutController implements Initializable{

	private MainApp mainApp = null;
	
	
	
	@FXML private Button btnOrderMenu;
	@FXML private Button btnStockMenu;
	@FXML private Button btnStaffMenu;
	@FXML private Button btnStatisticsMenu;
	@FXML private Button btnExit;
	
	/* 버튼 핸들러 관리 */
    @FXML
    void handleConfigurationScreen(ActionEvent event) {
    	showConfigurationMenu();
    }

    @FXML
    void handleExit(ActionEvent event) {
    	System.exit(0);
    }

    @FXML
    void handleStaffManagementScreen(ActionEvent event) {

    }

    @FXML
    void handleStockManagementScreen(ActionEvent event) {

    }

    @FXML
    void handleTableManagementScreen(ActionEvent event) {
    	showOrderMenu();
    }
	
	
	/* 레이아웃 로드 관리 */
	public void showOrderMenu(){
		try{
			mainApp.getPrimaryStage().setFullScreenExitHint("");
			mainApp.getPrimaryStage().setFullScreen(true);
			
			//이전에 적용된 스타일을 제거한다.
			mainApp.getRootLayoutController().getRootPane().setStyle("");
			
			
			FXMLLoader loader = new FXMLLoader(TitleLayoutController.class.getResource("/PointOfView/View/Table/TableLayout.fxml"));
			BorderPane pane = loader.load();

			
			TableLayoutController controller = loader.getController();
			controller.setMainApp(mainApp, pane);
			
			mainApp.getRootLayoutController().showThisPane(pane);
			
			
		}catch (Exception e){
			e.printStackTrace();
		}
	}
	
	public void showConfigurationMenu(){
		try{
			
			FXMLLoader loader = new FXMLLoader(TitleLayoutController.class.getResource("/PointOfView/View/Config/ConfigLayout.fxml"));
			BorderPane pane = loader.load();
			
			ConfigLayoutController controller = loader.getController();
			controller.setMainApp(mainApp);
			
			mainApp.getRootLayoutController().showThisPane(pane);
			
			
		}catch (Exception e){
			new ExceptionDialog(AlertType.ERROR, "에러", "", "", e).showAndWait();
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
