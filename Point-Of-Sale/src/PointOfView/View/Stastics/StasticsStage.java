package PointOfView.View.Stastics;

import PointOfView.MainApp;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.BorderPane;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

/**
 * 통계 스테이지
 * 
 * @author Jeongsam
 *
 */
public class StasticsStage extends Stage {

    public StasticsStage(MainApp mainApp) {
	super(StageStyle.UNDECORATED);

	initOwner(mainApp.getPrimaryStage());
	initModality(Modality.APPLICATION_MODAL);

	try {
	    FXMLLoader loader = new FXMLLoader(getClass().getResource("StasticsLayout.fxml"));
	    BorderPane pane = loader.load();

	    StasticsLayoutController controller = loader.getController();
	    controller.setMainApp(this, mainApp);

	    Scene scene = new Scene(pane);
	    setScene(scene);

	} catch (Exception e) {
	    e.printStackTrace();
	}

    }

}
