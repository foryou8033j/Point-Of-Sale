package PointOfView.View.Staff.Config;

import PointOfView.MainApp;
import PointOfView.Models.Staff.Staff;
import PointOfView.Util.Dialog.ExceptionDialog;
import PointOfView.View.Config.ConfigLayoutController;
import PointOfView.View.Title.TitleLayoutController;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.BorderPane;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

public class StaffConfigStage extends Stage {

    private MainApp mainApp;
    private Stage stage;
    private Staff staff;

    public StaffConfigStage(MainApp mainApp, Stage stage) {

	super(StageStyle.UTILITY);

	this.staff = mainApp.getDataManagement().getStaffs();
	this.stage = stage;
	this.mainApp = mainApp;

	initOwner(stage);
	initModality(Modality.APPLICATION_MODAL);
	setTitle("직원 정보 관리");

	initLayout();

    }

    private void initLayout() {

	try {

	    FXMLLoader loader = new FXMLLoader(
		    TitleLayoutController.class.getResource("/PointOfView/View/Staff/Config/StaffConfigLayout.fxml"));
	    AnchorPane pane = loader.load();

	    StaffConfigLayoutController controller = loader.getController();
	    controller.setMainApp(this, mainApp);

	    Scene scene = new Scene(pane);

	    setScene(scene);

	} catch (Exception e) {
	    new ExceptionDialog(AlertType.ERROR, "에러", "", "", e).showAndWait();
	    e.printStackTrace();
	}

    }
}
