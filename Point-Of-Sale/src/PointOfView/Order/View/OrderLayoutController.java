package PointOfView.Order.View;

import java.net.URL;
import java.util.ResourceBundle;

import PointOfView.MainApp;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;

public class OrderLayoutController implements Initializable {
	
	private MainApp mainApp = null;
	
	@FXML Button btnPrevToTitle;
	
	@FXML
	private void handlePrevToTitleButton(){
		mainApp.showTitleMenu();
	}
	
	@Override
	public void initialize(URL location, ResourceBundle resources) {
		// TODO Auto-generated method stub
		
	}

	public OrderLayoutController() {
		// TODO Auto-generated constructor stub
	}
	
	public void setMainApp(MainApp mainApp){
		this.mainApp = mainApp;
		
	}
	
}
