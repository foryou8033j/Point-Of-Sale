package PointOfView.Util.View;

import javafx.scene.control.Alert;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

public class SimpleAlert extends Alert{

	public SimpleAlert(Stage owner, AlertType type, String title, String message) {
		super(type);
		initOwner(owner);
		initStyle(StageStyle.UTILITY);
		setTitle(title);
		setHeaderText(message);
		setContentText("");
	}
	
}
