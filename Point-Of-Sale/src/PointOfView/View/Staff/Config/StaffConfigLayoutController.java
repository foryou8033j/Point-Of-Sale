package PointOfView.View.Staff.Config;

import java.net.URL;
import java.util.ResourceBundle;

import PointOfView.MainApp;
import PointOfView.Models.Staff.StaffModel;
import PointOfView.View.Staff.StaffAddStage;
import javafx.beans.property.BooleanProperty;
import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleBooleanProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.SnapshotParameters;
import javafx.scene.control.Button;
import javafx.scene.control.ContentDisplay;
import javafx.scene.control.ContextMenu;
import javafx.scene.control.MenuItem;
import javafx.scene.control.TableCell;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableColumn.CellDataFeatures;
import javafx.scene.control.TableColumn.CellEditEvent;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.WritableImage;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseButton;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;
import javafx.util.Callback;

/**
 * 직원 정보 수정 레이아웃
 * @author Jeongsam
 *
 */
public class StaffConfigLayoutController implements Initializable {

    // ContextMenu 활성화를 위한 enum
    enum SELECTED_CELL {
	BLANK, DATA, DATA_NO_UP, DATA_NO_DOWN
    };

    @FXML
    TableView<StaffModel> table;

    @FXML
    Button save;

    @FXML
    Button load;

    TableColumn name;

    TableColumn category;

    TableColumn part;

    TableColumn pay;

    private MainApp mainApp;
    private Stage stage;

    final IntegerProperty dataDragFromData = new SimpleIntegerProperty(-1);

    public StaffConfigLayoutController() {
	// TODO Auto-generated constructor stub
    }

    public void setMainApp(Stage stage, MainApp mainApp) {
	this.mainApp = mainApp;

	table.setItems(mainApp.getDataManagement().getStaffs().getStaffDatas());

	if (table.getItems().size() == 0)
	    handleAddMember();

	// setCellDragAndDrop(name);
	// setCellDragAndDrop(id);
	// setCellDragAndDrop(password);
	// setDefaultSet();
    }

    private ContextMenu setDefaultSet(SELECTED_CELL TYPE) {
	ContextMenu contextMenu = new ContextMenu();
	MenuItem up = new MenuItem(" △");
	MenuItem down = new MenuItem(" ▽");
	MenuItem add = new MenuItem("추가");
	MenuItem delete = new MenuItem("삭제");

	table.setContextMenu(contextMenu);

	up.setOnAction(new EventHandler<ActionEvent>() {
	    @Override
	    public void handle(ActionEvent arg0) {
		int index = table.getSelectionModel().getSelectedIndex();
		int desIndex = index - 1;

		if (desIndex < 0)
		    return;
		else {
		    StaffModel tmpData = table.getItems().get(desIndex);
		    table.getItems().set(desIndex, table.getItems().get(index));
		    table.getItems().set(index, tmpData);
		}

	    }
	});

	down.setOnAction(new EventHandler<ActionEvent>() {
	    @Override
	    public void handle(ActionEvent arg0) {
		int index = table.getSelectionModel().getSelectedIndex();
		int desIndex = index + 1;

		if (desIndex > table.getItems().size())
		    return;
		else {
		    StaffModel tmpData = table.getItems().get(desIndex);
		    table.getItems().set(desIndex, table.getItems().get(index));
		    table.getItems().set(index, tmpData);
		}

	    }
	});

	add.setOnAction(new EventHandler<ActionEvent>() {
	    @Override
	    public void handle(ActionEvent arg0) {
		handleAddMember();
	    }

	});

	delete.setOnAction(new EventHandler<ActionEvent>() {
	    @Override
	    public void handle(ActionEvent arg0) {
		mainApp.getDataManagement().getStaffs().getStaffDatas()
			.remove(table.getSelectionModel().getSelectedItem());
	    }

	});

	if (SELECTED_CELL.BLANK == TYPE)
	    contextMenu.getItems().addAll(add);
	else if (SELECTED_CELL.DATA == TYPE)
	    contextMenu.getItems().addAll(up, add, delete, down);
	else if (SELECTED_CELL.DATA_NO_UP == TYPE)
	    contextMenu.getItems().addAll(add, delete, down);
	else if (SELECTED_CELL.DATA_NO_DOWN == TYPE)
	    contextMenu.getItems().addAll(up, add, delete);

	return contextMenu;

    }

    @Override
    public void initialize(URL arg0, ResourceBundle arg1) {

	/*
	 * File file = new File(MainApp.path + fileName); loadDataFromFile(file);
	 */

	table.setEditable(true);

	Callback<TableColumn, TableCell> cellFactory = new Callback<TableColumn, TableCell>() {
	    public TableCell call(TableColumn p) {
		return new EditingCell();
	    }

	};

	name = new TableColumn("이름");
	name.setCellValueFactory(new PropertyValueFactory<StaffModel, String>("fieldValue"));

	category = new TableColumn("직별");
	category.setCellValueFactory(new PropertyValueFactory<StaffModel, String>("fieldValue"));

	part = new TableColumn("파트");
	part.setCellValueFactory(new PropertyValueFactory<StaffModel, String>("fieldValue"));

	pay = new TableColumn("시급");
	pay.setCellValueFactory(new PropertyValueFactory<StaffModel, String>("fieldValue"));

	name.setCellValueFactory(
		cellData -> ((CellDataFeatures<StaffModel, String>) cellData).getValue().getNameProperty());
	category.setCellValueFactory(
		cellData -> ((CellDataFeatures<StaffModel, String>) cellData).getValue().getCategoryProperty());
	part.setCellValueFactory(
		cellData -> ((CellDataFeatures<StaffModel, String>) cellData).getValue().getPartProperty());
	pay.setCellValueFactory(
		cellData -> ((CellDataFeatures<StaffModel, String>) cellData).getValue().getPayProperty());

	name.setCellFactory(cellFactory);
	name.setOnEditCommit(new EventHandler<TableColumn.CellEditEvent<StaffModel, String>>() {

	    public void handle(CellEditEvent<StaffModel, String> arg0) {
		((StaffModel) arg0.getTableView().getItems().get(arg0.getTablePosition().getRow()))
			.setName(arg0.getNewValue());

	    }
	});

	category.setCellFactory(cellFactory);
	category.setOnEditCommit(new EventHandler<TableColumn.CellEditEvent<StaffModel, String>>() {

	    public void handle(CellEditEvent<StaffModel, String> arg0) {
		((StaffModel) arg0.getTableView().getItems().get(arg0.getTablePosition().getRow()))
			.setName(arg0.getNewValue());

	    }
	});

	part.setCellFactory(cellFactory);
	part.setOnEditCommit(new EventHandler<TableColumn.CellEditEvent<StaffModel, String>>() {

	    public void handle(CellEditEvent<StaffModel, String> arg0) {
		((StaffModel) arg0.getTableView().getItems().get(arg0.getTablePosition().getRow()))
			.setPart(arg0.getNewValue());

	    }
	});

	pay.setCellFactory(cellFactory);
	pay.setOnEditCommit(new EventHandler<TableColumn.CellEditEvent<StaffModel, String>>() {

	    public void handle(CellEditEvent<StaffModel, String> arg0) {
		((StaffModel) arg0.getTableView().getItems().get(arg0.getTablePosition().getRow()))
			.setPay(arg0.getNewValue());

	    }
	});

	table.getColumns().addAll(name, category, part, pay);

	// showDataDetails(null);
	// table.getSelectionModel().selectedItemProperty()
	// .addListener((Observable, oldValue, newValue) -> showDataDetails(newValue));
    }

    private void showDataDetails(StaffModel data) {

    }

    private void handleAddMember() {

	new StaffAddStage(stage, mainApp.getDataManagement().getStaffs()).showAndWait();

    }

    public class EditingCell extends TableCell<StaffModel, String> {

	private TextField textField;

	final StringProperty file = new SimpleStringProperty();

	public EditingCell() {

	}

	@Override
	public void startEdit() {
	    super.startEdit();

	    if (textField == null) {
		createTextField();
	    }

	    setGraphic(textField);
	    setContentDisplay(ContentDisplay.GRAPHIC_ONLY);
	    textField.selectAll();

	}

	@Override
	public void cancelEdit() {
	    super.cancelEdit();

	    setText(String.valueOf(getItem()));
	    setContentDisplay(ContentDisplay.TEXT_ONLY);

	}

	@Override
	public void updateItem(String item, boolean empty) {
	    super.updateItem(item, empty);

	    if (empty) {
		setText(null);
		setGraphic(null);
		setContextMenu(setDefaultSet(SELECTED_CELL.BLANK));
		setOnMouseClicked(new EventHandler<MouseEvent>() {
		    @Override
		    public void handle(MouseEvent arg0) {
			table.getSelectionModel().clearSelection();
		    }
		});
	    } else {
		if (isEditing()) {
		    if (textField != null) {
			textField.setText(getString());
		    }
		    setGraphic(textField);

		    setContentDisplay(ContentDisplay.GRAPHIC_ONLY);
		} else {
		    setText(getString());
		    setContentDisplay(ContentDisplay.TEXT_ONLY);

		}

		if (getIndex() == 0)
		    setContextMenu(setDefaultSet(SELECTED_CELL.DATA_NO_UP));
		else if (getIndex() == table.getItems().size() - 1)
		    setContextMenu(setDefaultSet(SELECTED_CELL.DATA_NO_DOWN));
		else
		    setContextMenu(setDefaultSet(SELECTED_CELL.DATA));

		setOnMouseClicked(new EventHandler<MouseEvent>() {
		    @Override
		    public void handle(MouseEvent arg0) {
			if (arg0.getButton() == MouseButton.PRIMARY)
			    table.getSelectionModel().select(getIndex());
		    }
		});
	    }
	}

	private void createTextField() {
	    final BooleanProperty entered = new SimpleBooleanProperty();
	    textField = new TextField(getString());
	    textField.setMinWidth(this.getWidth() - this.getGraphicTextGap() * 2);
	    textField.setOnKeyPressed(new EventHandler<KeyEvent>() {
		public void handle(KeyEvent t) {
		    if (t.getCode() == KeyCode.ENTER)
			commitEdit(textField.getText());
		    else if (t.getCode() == KeyCode.ESCAPE)
			cancelEdit();
		};
	    });
	    textField.setOnMouseExited(new EventHandler<MouseEvent>() {
		@Override
		public void handle(MouseEvent arg0) {
		    entered.set(false);
		}
	    });

	    textField.setOnMouseEntered(new EventHandler<MouseEvent>() {
		@Override
		public void handle(MouseEvent arg0) {
		    entered.set(true);
		}
	    });
	    textField.setOnMouseClicked(new EventHandler<MouseEvent>() {
		@Override
		public void handle(MouseEvent arg0) {
		    if (!entered.get())
			cancelEdit();
		}
	    });
	}

	private String getString() {
	    return getItem() == null ? "" : getItem().toString();
	}

    }

    /**
     * 드래그 온 한 데이터의 스냅샷을 구현한다.
     * 
     * @param node
     * @return
     */
    public WritableImage createSnapShot(Node node) {
	SnapshotParameters snapshotParameters = new SnapshotParameters();
	WritableImage image = node.snapshot(snapshotParameters, null);
	return image;
    }

}
