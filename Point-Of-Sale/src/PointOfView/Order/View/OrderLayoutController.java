package PointOfView.Order.View;

import java.awt.AWTException;
import java.awt.Robot;
import java.net.URL;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Locale;
import java.util.ResourceBundle;

import PointOfView.MainApp;
import PointOfView.Order.Model.TableData;
import PointOfView.Util.View.PasswordInputDialog;
import javafx.application.Platform;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.effect.Glow;
import javafx.scene.effect.SepiaTone;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;

public class OrderLayoutController implements Initializable {
	
	private enum TableMode {NOMAL, EDIT, MOVE, SHARE};
	
	private boolean tableEditMode;
	private boolean tableAddMode;
	private boolean tableMoveMode;
	private boolean tableShareMode;
	
	private MainApp mainApp = null;
	private BorderPane ground = null;
	
	private ObservableList<TableData> removeTableList = FXCollections.observableArrayList(); 
	
	/** 컴포넌트 정의 **/
	@FXML Button btnPrevToTitle;
	@FXML Button btnTableManagement;
	@FXML Button btnMenuManagement;
	@FXML Button btnReceptionManagement;
	@FXML Button btnStasticsManagement;
	
	@FXML Label lbnRestaurant;
	@FXML Label lbnCurrentTime;
	
	@FXML GridPane tableField;
	
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
				
				btnMenuManagement.setText("편집");
				btnReceptionManagement.setText("이동");
				btnStasticsManagement.setText("합석");
			
		}else{
			tableEditMode = false;
			btnTableManagement.setEffect(null);
			
			resetButtonText();
		}
			
	}
	
	@FXML
	private void handleMenuManagementButton(){
		if(tableEditMode){
			
			//mainApp.getTables().addTable(2, 2);
			//loadTablesOnTheGround();
			
			if(!tableAddMode){
				
				if(!new PasswordInputDialog(mainApp).isPass()) return;
					
				tableField.getChildren().clear();
					
				tableAddMode = true;
				
				btnMenuManagement.setText("완료");
				btnReceptionManagement.setDisable(true);
				btnStasticsManagement.setDisable(true);
				
				for(int i=0; i<8; i++){
					for(int j=0; j<10; j++){
						if(getNodeFromGridPane(tableField, i, j) == null){
							
							final int _i = i;
							final int _j = j;
							
							final Button btn = new Button("ADD");
							
							btn.setOnAction(e -> {
								mainApp.getTables().addTable(_i, _j);
								loadTablesOnTheGround(TableMode.EDIT);
							});
							
							tableField.add(btn, i, j);
							
						}
							
					}
				}
				
			}else{
				
				tableField.getChildren().clear();
				
				if(removeTableList.size() > 0) {
					
					for(TableData data:removeTableList)
						mainApp.getTables().removeTable(data);
					
					removeTableList.clear();
				}
				
				loadTablesOnTheGround(TableMode.NOMAL);
				
				
				tableAddMode = false;
				btnMenuManagement.setText("편집");
				btnReceptionManagement.setDisable(false);
				btnStasticsManagement.setDisable(false);
			}
			
			
		}else{
			if(new PasswordInputDialog(mainApp).isPass()){
				
				//여기에 메뉴 관리 화면을 보여준다.
				
			}
		}
	}
	
	@FXML
	private void handleReceptionManagementButton(){
		if(tableEditMode){
			
			if(!tableMoveMode){
				tableMoveMode = true;
				btnReceptionManagement.setText("완료");
				btnMenuManagement.setDisable(true);
				btnStasticsManagement.setDisable(true);
			}else{
				tableMoveMode = false;
				
				tableField.getChildren().clear();
				loadTablesOnTheGround(TableMode.MOVE);
				
				
				btnReceptionManagement.setText("이동");
				btnMenuManagement.setDisable(false);
				btnStasticsManagement.setDisable(false);
			}
			
			
			
			
			
		}else{
			if(!new PasswordInputDialog(mainApp).isPass()) return;
				
				//여기에 영수증 관리 화면을 보여준다.
				
			
		}
	}
	
	@FXML
	private void handleStasticsManagementhButton(){
		if(tableEditMode){
			if(!tableShareMode){
				tableShareMode = true;
				btnStasticsManagement.setText("완료");
				btnReceptionManagement.setDisable(true);
				btnMenuManagement.setDisable(true);
			}else{
				tableShareMode = false;
				
				tableField.getChildren().clear();
				loadTablesOnTheGround(TableMode.SHARE);
				
				
				btnStasticsManagement.setText("합석");
				btnReceptionManagement.setDisable(false);
				btnMenuManagement.setDisable(false);
			}
		}else{
			if(!new PasswordInputDialog(mainApp).isPass()) return;
				
				//여기에 정산, 통계 화면을 보여준다.
				
			
		}
	}
	
	/**
	 * 테이블을 Ground 에 출력한다.
	 */
	private void loadTablesOnTheGround(TableMode tableMode){
		
		int size = mainApp.getTables().getSize();
		
		ObservableList<TableData> tables = mainApp.getTables().getTableDatas();
		TableOverviewLayoutController[] tableOverviewLayoutControllers= new TableOverviewLayoutController[size];
		
		for(int i=0; i<size; i++){
			if(tables.get(i).isShow()){
				
				final int index = i;
				
				//여기다가 간이 정보를 연결한다.
				BorderPane pane = drawTablesOnTheGround(i, tableOverviewLayoutControllers[i], tables.get(i));
				final int column = tables.get(i).getColumn();
				final int row = tables.get(i).getRow();
				
				
				pane.setOnMouseClicked(e -> {
					
					if(tableMoveMode || tableShareMode)
						pane.setStyle("-fx-border-color: #000000; "
								+ "-fx-border-width: 3;"
								+ "-fx-border-radius: 15;"
								+ "-fx-background-radius: 16.4, 15;");
					
					switch(tableMode) {
					
					case NOMAL:
						//테이블 관리 메뉴를 보여준다.
						break;
						
					case EDIT:
						//수정 모드에서 선택 될 경우는 삭제라고 가정한다.
						if(removeTableList.contains(tables.get(index))) {
							removeTableList.remove(tables.get(index));
							pane.setStyle("-fx-border-color: #000000; "
									+ "-fx-border-width: 3;"
									+ "-fx-border-radius: 15;"
									+ "-fx-background-radius: 16.4, 15;");
						}else {
							removeTableList.add(tables.get(index));
							pane.setStyle("-fx-border-color: #FF0000; "
									+ "-fx-border-width: 3;"
									+ "-fx-border-radius: 15;"
									+ "-fx-background-radius: 16.4, 15;");
						}
							
						
						break;
						
					case MOVE:
						break;
						
					case SHARE:
						break;
						
					}
					
				});
				
				tableField.add(pane, tables.get(i).getColumn(), tables.get(i).getRow());
			}
				
		}
		
	}
	
	private BorderPane drawTablesOnTheGround(int tableNumber, TableOverviewLayoutController controller, TableData tableData){
		try{
			FXMLLoader loader = new FXMLLoader(getClass().getResource("TableOverviewLayout.fxml"));
			BorderPane pane = loader.load();

			controller = loader.getController();
			
			//디버그를 위한 메뉴 추가
			/*tableData.addMenu(mainApp.getDataManagement().getMenues().getMenuItems().get(0));
			tableData.addMenu(mainApp.getDataManagement().getMenues().getMenuItems().get(1));
			tableData.addMenu(mainApp.getDataManagement().getMenues().getMenuItems().get(2));*/
			
			
			controller.setMainApp(mainApp, tableData);
			
			return pane;
			
		}catch (Exception e){
			e.printStackTrace();
		}
		
		return null;
	}
	

	
	/**
	 * GridPane으로부터 해당 col, row의 노드를 반환한다.
	 * @param gridPane
	 * @param col
	 * @param row
	 * @return
	 */
	private Node getNodeFromGridPane(GridPane gridPane, int col, int row) {
	    for (Node node : gridPane.getChildren()) {
	        if (GridPane.getColumnIndex(node) == col && GridPane.getRowIndex(node) == row) {
	            return node;
	        }
	    }
	    return null;
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
	 * 버튼 기본 속성 관련
	 */
	
	private void resetButtonText(){
		btnMenuManagement.setText("메뉴\n관리");
		btnReceptionManagement.setText("영수증\n관리");
		btnStasticsManagement.setText("정산\n통계");
		
		btnMenuManagement.setDisable(false);
		btnReceptionManagement.setDisable(false);
		btnStasticsManagement.setDisable(false);
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
		loadTablesOnTheGround(TableMode.NOMAL);
		printCurrentTime();
	}
	
}
