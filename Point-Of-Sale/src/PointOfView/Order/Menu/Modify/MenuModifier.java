package PointOfView.Order.Menu.Modify;



import PointOfView.MainApp;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.BorderPane;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

public class MenuModifier extends Stage{
	
	
	
	
	
	public MenuModifier(MainApp mainApp) {
		super(StageStyle.UNDECORATED);
		
		initOwner(mainApp.getPrimaryStage());
		initModality(Modality.APPLICATION_MODAL);
		
		try {
			FXMLLoader loader = new FXMLLoader(getClass().getResource("MenuModifyLayout.fxml"));
			BorderPane pane = loader.load();
			
			MenuModifyLayoutController controller = loader.getController();
			controller.setMainApp(this, mainApp);
			
			Scene scene = new Scene(pane);
			setScene(scene);
			
		}catch (Exception e) {
			e.printStackTrace();
		}
		
	}
	
	
	
	

}
