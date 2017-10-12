package PointOfView.View.Config;

import javax.management.timer.Timer;

import PointOfView.MainApp;
import PointOfView.Util.InnerService;
import PointOfView.Util.ProcessService;
import PointOfView.Util.Dialog.PasswordInputDialog;
import PointOfView.Util.Dialog.PasswordInputDialog.Mode;
import PointOfView.Util.Dialog.SimpleAlert;
import javafx.application.Platform;
import javafx.concurrent.Task;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.input.MouseEvent;
import javafx.scene.text.Text;

public class ConfigLayoutController {

    @FXML
    private PasswordField password;

    @FXML
    private PasswordField repassword;

    @FXML
    private Text lbnPasswordAlert;

    @FXML
    private TextField name;

    @FXML
    private Text lbnNotice;
    
    private boolean oldPassPass = false;
    private boolean passwordChanged = false;

    @FXML
    void handleBackToTitle(ActionEvent event) {
    	mainApp.showTitleMenu();
    }

    @FXML
    void handleResetAllData(ActionEvent event) {
    	new SimpleAlert(mainApp.getPrimaryStage(), AlertType.ERROR, "NOT YET!", "응 아직 아니야").showAndWait();
    }
    

    @FXML
    void handleSave(ActionEvent event) {
    	
    	ProcessService service = new ProcessService(new InnerService() {
    			
    		@Override
    		public void doSomeThing(){
    			InnerService.super.doSomeThing();
    			try {
        			Thread.sleep(2000);
    			}catch (Exception e) {
    				
    			}
    		}
    		
		});
    	
    	service.setOnSucceeded(e -> {
    		lbnNotice.setVisible(false);
    		password.setDisable(false);
    		repassword.setDisable(false);
    		service.reset();
    	});
    	
    	if(!name.getText().equals("")) {

    		mainApp.getDataManagement().setPOSTitle(name.getText());
    	}
    	
    	if(!password.getText().equals(repassword.getText())) {
    		password.setText("");
    		repassword.setText("");
    		lbnPasswordAlert.setVisible(false);
    	}
    	
    	if(passwordChanged) {
    		mainApp.getDataManagement().setAdminPassword(repassword.getText());
    	}
    	else {
    		password.setDisable(true);
    		repassword.setDisable(true);
    	}
    		
    		
    	
    	lbnNotice.setVisible(true);
    	service.start();
    	
    	
    }
    
    @FXML
    void handlePasswordFieldInputDialog(MouseEvent event) {
    	
    	if(!oldPassPass) {
    		if(new PasswordInputDialog(mainApp).isPass())
    			oldPassPass = true;
    	}
    	
    	if(!oldPassPass)
    		return;
    	
    	PasswordInputDialog dialog = new PasswordInputDialog(mainApp, Mode.MODIFY);
    	dialog.setMode(Mode.MODIFY);
    	dialog.showAndWait();
    	password.setText(dialog.getValue());
    }

    @FXML
    void handleRePasswordFieldInputDialog(MouseEvent event) {
    	PasswordInputDialog dialog = new PasswordInputDialog(mainApp, Mode.MODIFY);
    	dialog.setMode(Mode.MODIFY);
    	dialog.showAndWait();
    	repassword.setText(dialog.getValue());
    	
    	if(password.getText().equals(repassword.getText())) {
    		passwordChanged = true;
			lbnPasswordAlert.setVisible(false);
    	}

    	else {
		lbnPasswordAlert.setText("패스워드가 일치하지 않습니다.");
		lbnPasswordAlert.setVisible(true);
		passwordChanged = false;
    	}
    }

    
    private MainApp mainApp;
    
    public void setMainApp(MainApp mainApp) {
    	this.mainApp = mainApp;
    	lbnNotice.setVisible(false);
    	lbnPasswordAlert.setVisible(false);
    	
    	password.setPromptText("패스워드 입력");
    	repassword.setPromptText("패스워드 재입력");
    	
    	name.setText(mainApp.getDataManagement().getPOSTitle());
    	

    }

}
