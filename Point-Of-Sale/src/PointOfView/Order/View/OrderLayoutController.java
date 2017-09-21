package PointOfView.Order.View;

import java.awt.AWTException;
import java.awt.Robot;
import java.net.URL;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Locale;
import java.util.ResourceBundle;

import PointOfView.MainApp;
import PointOfView.Util.View.PasswordInputDialog;
import javafx.application.Platform;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.effect.Glow;
import javafx.scene.effect.SepiaTone;
import javafx.scene.layout.BorderPane;
import javafx.scene.paint.Paint;

public class OrderLayoutController implements Initializable {
	
	private boolean tableEditMode;
	
	private MainApp mainApp = null;
	private BorderPane ground = null;
	
	
	/** 컴포넌트 정의 **/
	@FXML Button btnPrevToTitle;
	@FXML Button btnTableManagement;
	@FXML Button btnMenuManagement;
	@FXML Button btnReceptionManagement;
	@FXML Button btnStasticsManagement;
	
	@FXML Label lbnRestaurant;
	@FXML Label lbnCurrentTime;
	
	/** 핸들 정의 **/
	@FXML
	private void handlePrevToTitleButton(){
		mainApp.showTitleMenu();
	}
	
	@FXML
	private void handleTableManagementButton(){
		
		if(!tableEditMode){
			
				Glow glow = new Glow(10);
				SepiaTone sepiaTone = new SepiaTone(0.5);
				btnTableManagement.setEffect(sepiaTone);
				tableEditMode = true;
				
				btnMenuManagement.setText("추가");
				btnReceptionManagement.setText("이동");
				btnStasticsManagement.setText("합석");
			
		}else{
			tableEditMode = false;
			btnTableManagement.setEffect(null);
			
			btnMenuManagement.setText("메뉴\n관리");
			btnReceptionManagement.setText("영수증\n관리");
			btnStasticsManagement.setText("정산\n통계");
		}
			
	}
	
	@FXML
	private void handleMenuManagementButton(){
		if(tableEditMode){
			
		}else{
			if(new PasswordInputDialog(mainApp).isPass()){
				
				//여기에 메뉴 관리 화면을 보여준다.
				
			}
		}
	}
	
	@FXML
	private void handleReceptionManagementButton(){
		if(tableEditMode){
			
		}else{
			if(new PasswordInputDialog(mainApp).isPass()){
				
				//여기에 영수증 관리 화면을 보여준다.
				
			}
		}
	}
	
	@FXML
	private void handleStasticsManagementhButton(){
		if(tableEditMode){
			
		}else{
			if(new PasswordInputDialog(mainApp).isPass()){
				
				//여기에 정산, 통계 화면을 보여준다.
				
			}
		}
	}
	
	/**
	 * 테이블을 Ground 에 출력한다.
	 */
	private void showTablesOnTheGround(){
		
	}
	
	/** 기본 메소드 **/
	@Override
	public void initialize(URL location, ResourceBundle resources) {
		// TODO Auto-generated method stub
		
	}

	public OrderLayoutController() {
		// TODO Auto-generated constructor stub
	}
	
	/**
	 * 현재 시간을 그려준다.
	 */
	private void printCurrentTime(){
		new Thread(() -> {
			
			SimpleDateFormat formatter = new SimpleDateFormat ( "yyyy.MM.dd(E)  HH:mm:ss:SSS", Locale.KOREA );
			
			
			
			while(true){
				
				Calendar curTime = Calendar.getInstance();
				
				
				Platform.runLater(() -> {
					lbnCurrentTime.setText(formatter.format ( curTime.getTime() ));
				});
				
				try {
					new Robot().delay(1);
				} catch (AWTException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				
			}
			
			
		}).start();
	}
	
	/**
	 * MainApp 과 연동한다.
	 * @param mainApp {@link MainApp}
	 * @param ground {@link BorderPane}
	 */
	public void setMainApp(MainApp mainApp, BorderPane ground){
		this.mainApp = mainApp;
		this.ground = ground;
		
		tableEditMode = false;
		
		lbnRestaurant.setText(mainApp.getDataManagement().getPOSTitle());
		
		//테이블을 Ground 에 출력한다.
		showTablesOnTheGround();
		printCurrentTime();
	}
	
}
