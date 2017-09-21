package PointOfView;

import PointOfView.Model.ManagementData;
import PointOfView.View.RootLayoutController;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

/**
 * Restaurant POS 프로그램의 시작부분이 될 MainApp 클래스이다.
 * @author Jeongsam
 */
public class MainApp extends Application {

	
	//가장 바탕이 될 프레임이다.
	private Stage primaryStage = null;
	private RootLayoutController rootLayoutController = null;
	
	private ManagementData managementData = new ManagementData();
	
	
	@Override
	public void start(Stage primaryStage) throws Exception {
		this.primaryStage = primaryStage;
		
		initStage();
		showTitleMenu();
	}
	
	
	private void initStage(){
		primaryStage.setAlwaysOnTop(true);
		primaryStage.setFocused(true);
		primaryStage.setWidth(800);
		primaryStage.setHeight(670);
		primaryStage.setResizable(false);
		primaryStage.initStyle(StageStyle.UNDECORATED);
		
		primaryStage.show();
	}
	
	/**
	 * 타이틀 화면을 보여준다.
	 */
	public void showTitleMenu(){
		
		
		try{
			primaryStage.setFullScreen(false);
			
			FXMLLoader loader = new FXMLLoader(getClass().getResource("View/RootLayout.fxml"));
			BorderPane pane = loader.load();

			rootLayoutController = loader.getController();
			rootLayoutController.setMainApp(this, pane);
			
			Scene scene = new Scene(pane);
			primaryStage.setScene(scene);
			
		}catch (Exception e){
			e.printStackTrace();
		}
		
	}
	
	
	
	
	
	
	/**
	 * PrimaryStage를 반환한다.
	 * @return {@link Stage}
	 */
	public Stage getPrimaryStage(){
		return primaryStage;
	}
	
	/**
	 * RootLayoutController 를 반환한다.
	 * @return {@link RootLayoutController}
	 */
	public RootLayoutController getRootLayoutController(){
		return rootLayoutController;
	}
	
	/**
	 * ManagementData 를 반환한다.
	 * @return {@link ManagementData}
	 */
	public ManagementData getManagementData() {
		return managementData;
	}
	
	public static void main(String[] args) {
		launch(args);
	}
	
}
