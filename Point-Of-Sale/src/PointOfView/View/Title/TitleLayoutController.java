package PointOfView.View.Title;

import java.net.URL;
import java.util.ResourceBundle;

import PointOfView.MainApp;
import PointOfView.View.Table.TableLayoutController;
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
	@FXML private Button btnExit;
	
	/* 버튼 핸들러 관리 */
	@FXML
	private void handleShowOrderMenu(){
		showOrderMenu();
	}
	
	@FXML
	private void handleExitButton() {
		System.exit(1);
	}
	
	
	/* 레이아웃 로드 관리 */
	public void showOrderMenu(){
		try{
			mainApp.getPrimaryStage().setFullScreenExitHint("");
			mainApp.getPrimaryStage().setFullScreen(true);
			
			//이전에 적용된 스타일을 제거한다.
			mainApp.getRootLayoutController().getRootPane().setStyle("");
			
			
			FXMLLoader loader = new FXMLLoader(getClass().getResource("../../Order/View/TableLayout.fxml"));
			BorderPane pane = loader.load();

			
			TableLayoutController controller = loader.getController();
			controller.setMainApp(mainApp, pane);
			
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
