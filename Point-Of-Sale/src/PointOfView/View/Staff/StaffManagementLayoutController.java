package PointOfView.View.Staff;

import java.awt.AWTException;
import java.awt.Robot;
import java.net.URL;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Locale;
import java.util.ResourceBundle;

import PointOfView.MainApp;
import PointOfView.Models.Staff.Staff;
import PointOfView.Models.Staff.StaffModel;
import PointOfView.Util.Dialog.PasswordInputDialog;
import PointOfView.View.Staff.Config.StaffConfigStage;
import PointOfView.View.Table.Receipt.ReceiptStage;
import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.layout.AnchorPane;
import javafx.scene.text.Text;
import javafx.stage.Stage;

public class StaffManagementLayoutController implements Initializable {

    private StaffModel currentSelectedStaffModel = null;

    @FXML
    private TableView<StaffModel> staffTableView;

    @FXML
    private TableColumn<StaffModel, String> nameColumn;

    @FXML
    private TableColumn<StaffModel, String> jobCategoryColumn;

    @FXML
    private TableColumn<StaffModel, String> jobPartColumn;

    @FXML
    private TableColumn<StaffModel, String> jobStatusColumn;

    @FXML
    private AnchorPane pane;

    @FXML
    private Button btnStatus;

    @FXML
    private Text name;

    @FXML
    private Text category;

    @FXML
    private Text part;

    @FXML
    private Text todayWorkTime;

    @FXML
    private Text currentTime;

    @FXML
    private Text wholeWorkDay;

    @FXML
    private Text montylyWorkTime;

    @FXML
    private Text monthlyPay;

    @FXML
    void handleAddStaff(ActionEvent event) {
	if (!new PasswordInputDialog(mainApp).isPass())
	    return;
	
	new StaffAddStage(stage, staff).showAndWait();

    }

    @FXML
    void handleClose(ActionEvent event) {
	stage.close();
    }

    @FXML
    void handleDefaultSetting(ActionEvent event) {

	if (!new PasswordInputDialog(mainApp).isPass())
	    return;

	new StaffConfigStage(mainApp, stage).showAndWait();
    }

    @FXML
    void handleRemoveStaff(ActionEvent event) {

	if (!new PasswordInputDialog(mainApp).isPass())
	    return;

	StaffModel item = staffTableView.getSelectionModel().getSelectedItem();

	if (item == null)
	    return;
	else {
	    item.quitWork();
	    staff.getStaffDatas().remove(item);
	}

    }

    @FXML
    void handleWorkStatus(ActionEvent event) {

	if (currentSelectedStaffModel == null)
	    return;

	if (currentSelectedStaffModel.isSupended()) {
	    btnStatus.setText("퇴근");
	    currentSelectedStaffModel.startWork();
	} else if (!currentSelectedStaffModel.isWork()) {
	    btnStatus.setText("퇴근");
	    currentSelectedStaffModel.startWork();
	} else {
	    btnStatus.setText("출근");
	    currentSelectedStaffModel.stopWork();
	}

    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {

	nameColumn.setCellValueFactory(cellData -> cellData.getValue().getNameProperty());
	jobCategoryColumn.setCellValueFactory(cellData -> cellData.getValue().getCategoryProperty());
	jobPartColumn.setCellValueFactory(cellData -> cellData.getValue().getPartProperty());
	jobStatusColumn.setCellValueFactory(cellData -> cellData.getValue().getStatusProperty());

	staffTableView.getSelectionModel().selectedItemProperty().addListener((observable, oldValue, newValue) -> {
	    drawStaffInfomation(newValue);
	});

    }

    private void drawStaffInfomation(StaffModel staffModel) {
	if (staffModel == null) {
	    pane.setVisible(false);
	    return;
	}

	currentSelectedStaffModel = staffModel;

	pane.setVisible(true);
	name.setText(staffModel.getName());
	category.setText(staffModel.getCategory());
	part.setText(staffModel.getPart());

	wholeWorkDay.setText(currentSelectedStaffModel.getWholeWorkHour() / 24 + " 일");
	montylyWorkTime.setText(currentSelectedStaffModel.getMonthlyWorkHour() + " 시간");

	monthlyPay.setText(
		String.format("%,20d 원", currentSelectedStaffModel.getMonthlyWorkHour() * staff.getPayPerHour()));

	if (staffModel.isWork() && !staffModel.isSupended()) {
	    btnStatus.setText("퇴근");
	} else {
	    btnStatus.setText("출근");
	}

    }

    /**
     * 현재 시간을 그려준다.
     */
    private void printCurrentTime() {
	new Thread(() -> {

	    SimpleDateFormat formatter = new SimpleDateFormat("yyyy.MM.dd(E)  HH:mm:ss:SSS", Locale.KOREA);
	    SimpleDateFormat workFormatter = new SimpleDateFormat("mm:ss");

	    while (true) {
		Calendar curTime = Calendar.getInstance();
		Platform.runLater(() -> {

		    if (currentSelectedStaffModel != null) {

			wholeWorkDay.setText(currentSelectedStaffModel.getWholeWorkHour() / 24 + " 일");
			montylyWorkTime.setText(currentSelectedStaffModel.getMonthlyWorkHour() + " 시간");

			monthlyPay.setText(String.format("%,20d 원", currentSelectedStaffModel.getMonthlyWorkHour()
				* Integer.parseInt(currentSelectedStaffModel.getPay())));

			currentTime.setText(formatter.format(curTime.getTime()));
			if (currentSelectedStaffModel.isWork())
			    todayWorkTime.setText(currentSelectedStaffModel.getTodayWorkHours() + ":"
				    + workFormatter.format(currentSelectedStaffModel.getTodayWorkTime()));
			else if (!currentSelectedStaffModel.isWork())
			    todayWorkTime.setText(currentSelectedStaffModel.getTodayWorkHours() + ":"
				    + workFormatter.format(currentSelectedStaffModel.getTodayWorkTime()));

		    }

		});
		try {
		    new Robot().delay(1);
		} catch (AWTException e) {
		    // ignore
		}

	    }

	}).start();
    }

    private Staff staff;
    private MainApp mainApp;
    private Stage stage;

    public void setStaffData(MainApp mainApp, Stage stage, Staff staff) {
	this.mainApp = mainApp;
	this.staff = staff;
	this.stage = stage;

	pane.setVisible(false);
	staffTableView.setItems(staff.getStaffDatas());

	try {
	    staffTableView.getSelectionModel().select(0);
	} catch (Exception e) {

	}

	printCurrentTime();
    }

}
