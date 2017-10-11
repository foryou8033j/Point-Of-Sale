package PointOfView.Util.Dialog;
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

public class PasswordInputDialog extends Stage{

	VBox vb = new VBox(10);
	
	private MainApp mainApp;
	
	private PasswordField password = new PasswordField();
	private Text title = new Text("관리자 패스워드를 입력하세요.");
	private Text text = new Text("");
	
	private Button btnCancle = new Button("취소");
	
	private GridPane numberPane = new GridPane();
	
	private int maxLength = 13;
	private boolean pass = false;
	
	public PasswordInputDialog(MainApp mainApp) {
		this.mainApp = mainApp;
		
		vb.getStylesheets().add("JMetroLightTheme.css");
		vb.setStyle("-fx-border-width: 3; -fx-border-color: #F68657;");
		

		
		vb.setPadding(new Insets(10, 10, 10, 10));
		
		Button button[] = new Button[12];
		for(int i=0; i<10; i++){
			final int j = i;
			button[i] = new Button(String.valueOf(i));
			button[i].setPrefSize(100, 100);
			button[i].setOnAction(e -> {
				password.setText(password.getText() + String.valueOf(j));
			});
		}
		
		button[10] = new Button("<");
		button[10].setPrefSize(100, 100);
		button[10].setOnAction(e -> {
			if(password.getText().equals(""))
				return;
			password.setText(password.getText(0, password.getText().length()-1));
		});
		
		
		button[11] = new Button("OK");
		button[11].setDefaultButton(true);
		button[11].setPrefSize(100, 100);
		button[11].setOnAction(e -> {
			checkPassword();
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
		btnCancle.setPrefSize(150, 300);
		btnCancle.setOnAction(e -> {
			close();
		});
		
		setTitle(mainApp.getDataManagement().getPOSTitle());
		initOwner(mainApp.getPrimaryStage());
		initModality(Modality.APPLICATION_MODAL);
		initStyle(StageStyle.UNDECORATED);
		
		setWidth(240);
		setHeight(300);
		setResizable(false);
		
		Scene scene = new Scene(vb);
		setScene(scene);
		
		setOnCloseRequest(Event -> {
			pass = false;
		});
		
		
		
		password.textProperty().addListener(new ChangeListener<String>() {
			@Override
			public void changed(ObservableValue<? extends String> obersvable, String oldValue, String newValue) {
				
				if(newValue.length() > maxLength){
					String s = newValue.substring(0, maxLength);
					password.setText(s);
					return;
				}
				
				if(newValue.matches(".*[ㄱ-ㅎㅏ-ㅣ가-힣]+.*")){
					text.setFill(Color.RED);
					text.setText("한글이 입력되고 있습니다.");
				}else{
					text.setText("");
				}
				
			}
		});
		
		password.setOnKeyPressed(new EventHandler<KeyEvent>() {
			public void handle(KeyEvent arg0) {
				
				if(arg0.getCode() == KeyCode.ENTER){
					checkPassword();
				}
				
			};
		});
		
		showAndWait();
		
	}
	
	private void checkPassword(){
		if(mainApp.getDataManagement().getAdminAuthority(password.getText()))
		{
			close();
			pass = true;
		}
		else{
			password.setText("");
			text.setFill(Color.RED);
			text.setText("패스워드가 올바르지 않습니다.");
			pass = false;
		}
	}
	
	public boolean isPass(){
		return pass;
	}
	
	
}
