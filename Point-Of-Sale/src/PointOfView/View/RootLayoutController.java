package PointOfView.View;

import java.net.URL;
import java.util.ResourceBundle;

import PointOfView.MainApp;
import PointOfView.Title.View.TitleLayoutController;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.layout.BorderPane;

public class RootLayoutController implements Initializable{

	//메인앱과 연동 될 객체이다.
	private MainApp mainApp = null;
	private BorderPane rootPane = null;
	
	@Override
	public void initialize(URL location, ResourceBundle resources) {
		// TODO Auto-generated method stub
		
	}

	
	public RootLayoutController() {
		// TODO Auto-generated constructor stub
	}
	
	private void loadTitleMenu(){
		try{
			FXMLLoader loader = new FXMLLoader(getClass().getResource("../Title/View/TitleLayout.fxml"));
			BorderPane pane = loader.load();

			
			TitleLayoutController controller = loader.getController();
			controller.setMainApp(mainApp);
			
			rootPane.setCenter(pane);
			
			
		}catch (Exception e){
			e.printStackTrace();
		}
	}
	
	/**
	 * RootPane 을 반환한다.
	 * @return
	 */
	public BorderPane getRootPane(){
		return rootPane;
	}
	
	public void showThisPane(Node pane){
		rootPane.getChildren().clear();
		
		rootPane.setCenter(pane);
	}
	
	//MainApp과 연동한다.
	public void setMainApp(MainApp mainApp, BorderPane rootPane){
		this.mainApp = mainApp;
		this.rootPane = rootPane;
		
		loadTitleMenu();
		
	}
	
}
