package PointOfView;

import java.awt.Dimension;
import java.awt.Toolkit;
import java.beans.Transient;

import PointOfView.Models.DataManagement;
import PointOfView.Models.Receipt.Receipt;
import PointOfView.Models.Stastics.StasticsModel;
import PointOfView.Models.Table.Tables;
import PointOfView.View.RootLayoutController;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

/**
 * Restaurant POS 프로그램의 시작부분이 될 MainApp 클래스이다. MainApp 주석 변경
 * 
 * @author Jeongsam
 */
public class MainApp extends Application {

	// 가장 바탕이 될 프레임이다.
	private Stage primaryStage = null;
	private RootLayoutController rootLayoutController = null;

	private DataManagement managementData = new DataManagement();

	private Tables tables;
	private Receipt receipts;
	private StasticsModel stasticsModel;

	@Override
	public void start(Stage primaryStage) throws Exception {
		this.primaryStage = primaryStage;
		this.tables = new Tables();
		this.receipts = new Receipt();

		stasticsModel = new StasticsModel(receipts, managementData.getMenues());

		initStage();
		showTitleMenu();
	}

	private void initStage() {

		Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
		double width = screenSize.getWidth();
		double height = screenSize.getHeight();

		// primaryStage.setAlwaysOnTop(true);
		primaryStage.setFocused(true);
		primaryStage.setWidth(800);
		primaryStage.setHeight(670);
		primaryStage.setResizable(false);
		primaryStage.initStyle(StageStyle.UNDECORATED);

		/*
		 * String css =
		 * this.getClass().getResource("/PointOfView/Resource/CSS/JMetroLightTheme.css")
		 * .toExternalForm(); primaryStage.getScene().getStylesheets().add(css);
		 */

		// 최초 화면을 무조건 화면 중앙으로 이동
		primaryStage.setX(width / 2 - primaryStage.getWidth() / 2);
		primaryStage.setY(height / 2 - primaryStage.getHeight() / 2);

		primaryStage.show();
	}

	/**
	 * 타이틀 화면을 보여준다.
	 */
	public void showTitleMenu() {

		try {
			primaryStage.setFullScreen(false);

			FXMLLoader loader = new FXMLLoader(getClass().getResource("View/RootLayout.fxml"));
			BorderPane pane = loader.load();

			rootLayoutController = loader.getController();
			rootLayoutController.setMainApp(this, pane);

			Scene scene = new Scene(pane);
			primaryStage.setScene(scene);

		} catch (Exception e) {
			e.printStackTrace();
		}

	}

	/**
	 * PrimaryStage를 반환한다.
	 * 
	 * @return {@link Stage}
	 */
	public Stage getPrimaryStage() {
		return primaryStage;
	}

	/**
	 * RootLayoutController 를 반환한다.
	 * 
	 * @return {@link RootLayoutController}
	 */
	public RootLayoutController getRootLayoutController() {
		return rootLayoutController;
	}

	/**
	 * ManagementData 를 반환한다.
	 * 
	 * @return {@link DataManagement}
	 */
	public DataManagement getDataManagement() {
		return managementData;
	}

	/**
	 * 테이블 정보를 반환한다.
	 * 
	 * @return
	 */
	public Tables getTables() {
		return tables;
	}

	/**
	 * 영수증 정보를 반환한다.
	 * 
	 * @return
	 */
	public Receipt getReceipts() {
		return receipts;
	}

	public StasticsModel getStasticsModel() {
		return stasticsModel;
	}

	public static void main(String[] args) {
		launch(args);
	}

}
