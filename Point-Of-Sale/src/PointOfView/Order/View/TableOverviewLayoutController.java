package PointOfView.Order.View;

import java.net.URL;
import java.util.ResourceBundle;

import PointOfView.MainApp;
import javafx.fxml.Initializable;

public class TableOverviewLayoutController implements Initializable{
	
	private MainApp mainApp = null;
	
	@Override
	public void initialize(URL location, ResourceBundle resources) {
		// TODO Auto-generated method stub
		
	}
	

	
	public void setMainApp(MainApp mainApp){
		this.mainApp = mainApp;
	}
}