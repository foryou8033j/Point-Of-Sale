package PointOfView.Util.Dialog;

import javafx.scene.Scene;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.scene.text.Text;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

/**
 * MouseEvent 를 받아 LogDialog를 OverView 한다.
 * 
 * @author Jeongsam
 *
 */
public class LogPopup extends Stage {

    public LogPopup(MouseEvent event, String title, String object) {

	initModality(Modality.WINDOW_MODAL);
	initOwner(null);
	initStyle(StageStyle.UNDECORATED);
	setAlwaysOnTop(true);

	movePos(event);

	Text titleText = new Text(title);
	Text objectText = new Text(object);

	titleText.setFont(Font.font("", FontWeight.EXTRA_BOLD, 13));

	VBox vb = new VBox(titleText, objectText);

	Scene scene = new Scene(vb);
	setScene(scene);

    }

    public void movePos(MouseEvent event) {
	setX(event.getScreenX() - (double) 5.0);
	setY(event.getScreenY() - (double) 30.0);
    }

}
