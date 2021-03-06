package PointOfView.Util.Dialog;

import java.net.URL;

import PointOfView.MainApp;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.PasswordField;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.Text;
import javafx.scene.text.TextAlignment;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

/**
 * 패스워드 입력 인증 여부 반환
 * 
 * @author Jeongsam
 *
 */
public class PasswordInputDialog extends Stage {

    public enum Mode {
	INPUT, MODIFY
    };

    Mode mode;

    VBox vb = new VBox(10);

    private MainApp mainApp;

    private PasswordField password = new PasswordField();
    private Text title = new Text("관리자 패스워드를 입력하세요.");
    private Text text = new Text("");

    private Button btnCancle = new Button("취소");

    private GridPane numberPane = new GridPane();

    private int maxLength = 13;
    private boolean pass = false;

    public PasswordInputDialog(MainApp mainApp, Mode mode, String str) {
	this.mainApp = mainApp;
	setMode(mode);
	title.setText(str);
	initLayout();
    }

    public PasswordInputDialog(MainApp mainApp, Mode mode) {
	this.mainApp = mainApp;
	setMode(mode);
	initLayout();
    }

    public PasswordInputDialog(MainApp mainApp) {
	this.mainApp = mainApp;

	setMode(Mode.INPUT);
	initLayout();

	showAndWait();
    }

    private void initLayout() {

	/*URL url = this.getClass().getResource("https://ci.kumoh.ac.kr/api/JMetroLightTheme.css");
	if (url == null) {
	    System.out.println("Resource not found. Aborting.");
	    System.exit(-1);
	}
	String css = url.toExternalForm();*/

	
	vb.setStyle("-fx-border-width: 3; -fx-border-color: #F68657;");

	vb.setPadding(new Insets(10, 10, 10, 10));

	Button button[] = new Button[12];
	for (int i = 0; i < 10; i++) {
	    final int j = i;
	    button[i] = new Button(String.valueOf(i));
	    button[i].setPrefSize(200, 200);
	    button[i].setOnAction(e -> {
		password.setText(password.getText() + String.valueOf(j));
	    });
	}

	button[10] = new Button("<");
	button[10].setPrefSize(200, 200);
	button[10].setOnAction(e -> {
	    if (password.getText().equals(""))
		return;
	    password.setText(password.getText(0, password.getText().length() - 1));
	});

	button[11] = new Button("OK");
	button[11].setDefaultButton(true);
	button[11].setPrefSize(200, 200);
	button[11].setOnAction(e -> {

	    if (mode.equals(Mode.INPUT))
		checkPassword();
	    else
		close();
	});

	numberPane.setHgap(5);
	numberPane.setVgap(5);

	numberPane.add(button[9], 2, 0);
	numberPane.add(button[8], 1, 0);
	numberPane.add(button[7], 0, 0);
	numberPane.add(button[6], 2, 1);
	numberPane.add(button[5], 1, 1);
	numberPane.add(button[4], 0, 1);
	numberPane.add(button[3], 2, 2);
	numberPane.add(button[2], 1, 2);
	numberPane.add(button[1], 0, 2);
	numberPane.add(button[0], 0, 3);
	numberPane.add(button[10], 1, 3);
	numberPane.add(button[11], 2, 3);

	vb.getChildren().add(title);
	vb.getChildren().add(password);
	vb.getChildren().add(text);

	vb.getChildren().add(btnCancle);
	vb.getChildren().add(numberPane);

	vb.setAlignment(Pos.CENTER);

	title.setFont(Font.font(14));
	title.setTextAlignment(TextAlignment.CENTER);

	text.setTextAlignment(TextAlignment.CENTER);

	password.setFont(Font.font("Malgun Gothic"));

	btnCancle.setCancelButton(true);
	btnCancle.setPrefSize(200, 400);
	btnCancle.setOnAction(e -> {
	    close();
	});

	setTitle(mainApp.getDataManagement().getPOSTitle());
	setAlwaysOnTop(true);
	initOwner(mainApp.getPrimaryStage());
	initModality(Modality.APPLICATION_MODAL);
	initStyle(StageStyle.UNDECORATED);

	setWidth(340);
	setHeight(400);
	setResizable(false);

	Scene scene = new Scene(vb);
	scene.getStylesheets().add("https://ci.kumoh.ac.kr/api/JMetroLightTheme.css");
	
	setScene(scene);

	setOnCloseRequest(Event -> {
	    pass = false;
	});

	password.textProperty().addListener(new ChangeListener<String>() {
	    @Override
	    public void changed(ObservableValue<? extends String> obersvable, String oldValue, String newValue) {

		if (newValue.length() > maxLength) {
		    String s = newValue.substring(0, maxLength);
		    password.setText(s);
		    return;
		}

		if (newValue.matches(".*[ㄱ-ㅎㅏ-ㅣ가-힣]+.*")) {
		    text.setFill(Color.RED);
		    text.setText("한글이 입력되고 있습니다.");
		} else {
		    text.setText("");
		}

	    }
	});

	password.setOnKeyPressed(new EventHandler<KeyEvent>() {
	    public void handle(KeyEvent arg0) {

		if (arg0.getCode() == KeyCode.ENTER) {
		    checkPassword();
		}

	    };
	});
    }

    public void setMode(Mode mode) {

	this.mode = mode;

	switch (mode) {
	case INPUT:
	    title.setText("관리자 패스워드를 입력하세요.");
	    break;
	case MODIFY:
	    title.setText("변경 할 패스워드를 입력하세요.");
	    break;
	}
    }

    private void checkPassword() {
	if (mainApp.getDataManagement().getAdminAuthority(password.getText())) {
	    close();
	    pass = true;
	} else {
	    password.setText("");
	    text.setFill(Color.RED);
	    text.setText("패스워드가 올바르지 않습니다.");
	    pass = false;
	}
    }

    public boolean isPass() {
	return pass;
    }

    public String getValue() {
	return password.getText();
    }

}
