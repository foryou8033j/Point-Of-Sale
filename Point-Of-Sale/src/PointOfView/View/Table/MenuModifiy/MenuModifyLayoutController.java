package PointOfView.View.Table.MenuModifiy;

import java.util.regex.Pattern;

import PointOfView.MainApp;
import PointOfView.Models.Hbox_MenuItem;
import PointOfView.Models.Menu.MenuItem;
import javafx.collections.FXCollections;
import javafx.collections.ListChangeListener;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

public class MenuModifyLayoutController {

    private Stage stage;
    private MainApp mainApp;

    boolean moving = false;

    @FXML
    ListView<Hbox_MenuItem> menuList;
    @FXML
    ObservableList<Hbox_MenuItem> observableMenuList = FXCollections.observableArrayList();

    @FXML
    GridPane gridPane;

    @FXML
    private void handleSave() {
	mainApp.getDataManagement().getMenues().saveDataToFile();
	stage.close();
    }

    @FXML
    private void handleMenuAdd() {

	Stage addDialog = new Stage(StageStyle.UNDECORATED);
	addDialog.initOwner(stage);
	addDialog.initModality(Modality.APPLICATION_MODAL);

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
	TextField price = new TextField();
	TextField category = new TextField();

	vbSub_1.getChildren().addAll(new Label("음식이름"), name);
	vbSub_2.getChildren().addAll(new Label("가격"), price);
	vbSub_3.getChildren().addAll(new Label("종류"), category);

	hb.getChildren().addAll(vbSub_1, vbSub_2, vbSub_3);

	HBox hbSub = new HBox(5);
	hbSub.setAlignment(Pos.CENTER);
	Button save = new Button("추가");
	save.setDefaultButton(true);
	save.setOnAction(e -> {

	    Alert alert = new Alert(AlertType.ERROR, "오류", ButtonType.OK);
	    alert.initOwner(addDialog);
	    alert.setContentText(null);

	    if (!Pattern.matches("^[0-9]+$", price.getText())) {
		alert.setHeaderText("가격에는 숫자만 입력 할 수 있습니다.");
		alert.setContentText(null);
		alert.showAndWait();
		return;
	    } else if (name.getText().equals("")) {
		alert.setHeaderText("음식 이름을 입력 해 주세요.");
		alert.showAndWait();
		return;
	    } else if (price.getText().equals("")) {
		alert.setHeaderText("가격을 입력 해 주세요.");
		alert.showAndWait();
		return;
	    } else if (category.getText().equals("")) {
		alert.setHeaderText("카테고리를 입력 해 주세요.");
		alert.showAndWait();
		return;
	    }

	    mainApp.getDataManagement().getMenues().getMenuItems()
		    .add(new MenuItem(name.getText(), category.getText(), Integer.valueOf(price.getText())));

	    mainApp.getDataManagement().getMenues().saveDataToFile();

	    addDialog.close();
	});

	Button cancle = new Button("취소");
	cancle.setCancelButton(true);
	cancle.setOnAction(e -> {
	    addDialog.close();
	});
	hbSub.getChildren().addAll(save, cancle);

	vb.getChildren().addAll(hb, hbSub);

	Scene scene = new Scene(vb);
	String css = this.getClass().getResource("/PointOfView/Resource/CSS/JMetroLightTheme.css").toExternalForm();
	scene.getStylesheets().add(css);

	addDialog.setScene(scene);
	addDialog.showAndWait();
    }

    private void drawMenuList() {

	observableMenuList.clear();

	for (MenuItem item : mainApp.getDataManagement().getMenues().getMenuItems()) {

	    if (item.isShow())
		continue;

	    Hbox_MenuItem hb = new Hbox_MenuItem(10, item);
	    hb.setPrefHeight(40);

	    hb.setAlignment(Pos.CENTER_RIGHT);
	    hb.setStyle("-fx-border-width: 1.0; -fx-border-color: #000000");

	    Label name = new Label(String.format("%10s", item.getName()));

	    String strPrice = String.format("%,10d 원", item.getPrice());

	    Label price = new Label(strPrice);

	    Button btnRemove = new Button("X");
	    btnRemove.setOnAction(e -> {
		mainApp.getDataManagement().getMenues().getMenuItems().remove(item);
	    });

	    hb.getChildren().addAll(name, price, btnRemove);

	    observableMenuList.add(hb);

	}

    }

    private void drawMenuToGridPane() {
	for (MenuItem item : mainApp.getDataManagement().getMenues().getMenuItems()) {
	    VBox pane = new VBox(10);
	    pane.setAlignment(Pos.CENTER);

	    Label name = new Label(item.getName());
	    name.setFont(Font.font("Malgun Gothic", FontWeight.BOLD, 18));
	    if (name.getText().length() > 3)
		name.setFont(Font.font("Malgun Gothic", FontWeight.BOLD, 18 - name.getText().length()));

	    Label price = new Label(String.format("%,10d 원", item.getPrice()));
	    price.setFont(Font.font("Malgun Gothic", FontWeight.BOLD, 13));

	    pane.getChildren().add(name);
	    pane.getChildren().add(price);

	    pane.setStyle("-fx-border-color: #000000; " + "-fx-border-width: 1.5;" + "-fx-border-radius: 15;"
		    + "-fx-background-radius: 16.4, 15;" + "-fx-background-color: #EFFFE9");

	    pane.setOnMouseClicked(e -> {
		item.clearShow();
		gridPane.getChildren().clear();
		drawMenuToGridPane();
		drawMenuList();
	    });

	    pane.setOnMousePressed(e -> {
		pane.setStyle("-fx-border-color: #0000FF;" + "-fx-border-width: 1.5;" + "-fx-border-radius: 15;"
			+ "-fx-background-radius: 16.4, 15;" + "-fx-background-color: #FFFFFF");
	    });

	    pane.setOnMouseReleased(e -> {
		pane.setStyle("-fx-border-color: #000000; " + "-fx-border-width: 1.5;" + "-fx-border-radius: 15;"
			+ "-fx-background-radius: 16.4, 15;" + "-fx-background-color: #EFFFE9");
	    });

	    if (item.isShow())
		gridPane.add(pane, item.getColumn(), item.getRow());
	}
    }

    /**
     * GridPane으로부터 해당 col, row의 노드를 반환한다.
     * 
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

    public void setMainApp(Stage stage, MainApp mainApp) {
	this.stage = stage;
	this.mainApp = mainApp;

	menuList.setItems(observableMenuList);

	drawMenuList();

	mainApp.getDataManagement().getMenues().getMenuItems().addListener(new ListChangeListener<MenuItem>() {

	    @Override
	    public void onChanged(Change<? extends MenuItem> c) {
		drawMenuList();
	    }
	});

	drawMenuToGridPane();

	menuList.setOnMouseClicked(e -> {

	    gridPane.getChildren().clear();
	    drawMenuToGridPane();

	    int index = menuList.getSelectionModel().getSelectedIndex();
	    if (index == -1)
		return;

	    for (int i = 0; i < 6; i++) {
		for (int j = 0; j < 8; j++) {
		    if (getNodeFromGridPane(gridPane, i, j) == null) {

			final int _i = i;
			final int _j = j;

			final Button btn = new Button("+");

			btn.setOnAction(ae -> {

			    menuList.getSelectionModel().clearSelection();
			    gridPane.getChildren().clear();

			    observableMenuList.get(index).getMenuItem().setColumnAndRow(_i, _j);

			    drawMenuToGridPane();
			    drawMenuList();
			});

			gridPane.add(btn, i, j);
		    }
		}
	    }
	});

    }

}
