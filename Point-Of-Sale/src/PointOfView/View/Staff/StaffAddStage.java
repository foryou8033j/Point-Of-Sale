package PointOfView.View.Staff;

import java.util.regex.Pattern;

import PointOfView.Models.Menu.MenuItem;
import PointOfView.Models.Staff.Staff;
import PointOfView.Models.Staff.StaffModel;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

/**
 * 직원 추가 스테이지
 * 
 * @author Jeongsam
 *
 */
public class StaffAddStage extends Stage {

    private Staff staff;

    public StaffAddStage(Stage stage, Staff staff) {

	super(StageStyle.UNDECORATED);

	this.staff = staff;

	initOwner(stage);
	initModality(Modality.APPLICATION_MODAL);
	setTitle("직원 추가");

	initLayout();

    }

    private void initLayout() {

	VBox vb = new VBox(10);
	vb.setAlignment(Pos.CENTER);
	HBox hb = new HBox(15);
	hb.setAlignment(Pos.CENTER);

	vb.setStyle("-fx-border-width: 3; -fx-border-color:#F68657");
	vb.setPadding(new Insets(15, 15, 15, 15));

	VBox vbSub_1 = new VBox(5);
	vbSub_1.setAlignment(Pos.CENTER);
	VBox vbSub_2 = new VBox(5);
	vbSub_2.setAlignment(Pos.CENTER);
	VBox vbSub_3 = new VBox(5);
	vbSub_3.setAlignment(Pos.CENTER);

	TextField name = new TextField();
	ComboBox<String> category = new ComboBox<String>();
	ComboBox<String> part = new ComboBox<String>();

	category.setItems(staff.getJobCategory());
	part.setItems(staff.getJobPart());

	vbSub_1.getChildren().addAll(new Label("이름"), name);
	vbSub_2.getChildren().addAll(new Label("직별"), category);
	vbSub_3.getChildren().addAll(new Label("파트"), part);

	hb.getChildren().addAll(vbSub_1, vbSub_2, vbSub_3);

	HBox hbSub = new HBox(5);
	hbSub.setAlignment(Pos.CENTER);
	Button save = new Button("추가");
	save.setDefaultButton(true);
	save.setOnAction(e -> {

	    Alert alert = new Alert(AlertType.ERROR, "오류", ButtonType.OK);
	    alert.initOwner(this);
	    alert.setContentText(null);

	    if (name.getText().equals("")) {
		alert.setHeaderText("이름을 입력 해 주세요.");
		alert.showAndWait();
		return;
	    }

	    staff.getStaffDatas().add(new StaffModel(name.getText(), category.getSelectionModel().getSelectedItem(),
		    part.getSelectionModel().getSelectedItem()));
	    staff.saveDataToFile();

	    close();
	});

	Button cancle = new Button("취소");
	cancle.setCancelButton(true);
	cancle.setOnAction(e -> {
	    close();
	});
	hbSub.getChildren().addAll(save, cancle);

	vb.getChildren().addAll(hb, hbSub);

	Scene scene = new Scene(vb);
	String css = this.getClass().getResource("/PointOfView/Resource/CSS/JMetroLightTheme.css").toExternalForm();
	scene.getStylesheets().add(css);

	setScene(scene);

    }

}
