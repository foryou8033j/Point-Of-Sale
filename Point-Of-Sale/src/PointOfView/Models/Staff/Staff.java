package PointOfView.Models.Staff;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.Marshaller;
import javax.xml.bind.Unmarshaller;

import PointOfView.Models.Receipt.ReceiptDataWrapper;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;

/**
 * Staff 정보 관리
 * 
 * @author Jeongsam
 *
 */
public class Staff {

    private ObservableList<StaffModel> staffModels = FXCollections.observableArrayList();

    private ObservableList<String> jobCategory;
    private ObservableList<String> jobPart;

    private int payPerHour = 6550;

    private File file = new File("POS_Staff.xml");

    public Staff() {
	initJobCategory();
	initJobPart();

	loadDataFromFile();

    }

    private void initJobCategory() {

	jobCategory = FXCollections.observableArrayList("사장", "부사장", "직원", "아르바이트", "용역", "일당", "대타");
    }

    private void initJobPart() {

	jobPart = FXCollections.observableArrayList("홀", "주방", "사무실", "배달", "홍보", "관리", "매니저");
    }

    public void saveAndQuit() {

	for (StaffModel model : staffModels) {

	    model.quitWork();

	}

	saveDataToFile();

	// saveAndQuit();

    }

    public void loadDataFromFile() {

	try {

	    JAXBContext context = JAXBContext.newInstance(StaffDataWrapper.class);
	    Unmarshaller um = context.createUnmarshaller();

	    // 파일로부터 XML을 읽은 다음 역 마샬링한다.
	    StaffDataWrapper wrapper = new StaffDataWrapper();
	    wrapper = (StaffDataWrapper) um.unmarshal(file);

	    staffModels.clear();
	    staffModels.addAll(wrapper.getDatas());

	} catch (Exception e) { // 예외를 잡는다
	    // ignore
	}
    }

    public void saveDataToFile() {
	try {
	    JAXBContext context = JAXBContext.newInstance(StaffDataWrapper.class);
	    Marshaller m = context.createMarshaller();
	    m.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, true);

	    // 연락처 데이터를 감싼다.
	    StaffDataWrapper wrapper = new StaffDataWrapper();
	    wrapper.setDatas(staffModels);

	    // 마샬링 후 XML을 파일에 저장한다.
	    m.marshal(wrapper, file);

	} catch (Exception e) { // 예외를 잡는다.
	    e.printStackTrace();
	    Alert alert = new Alert(AlertType.ERROR);
	    alert.setTitle("Error");
	    alert.setHeaderText("Could not save data");
	    alert.setContentText("Could not save data to file:\n" + file.getPath());

	    alert.showAndWait();
	}
    }

    public ObservableList<String> getJobCategory() {
	return jobCategory;
    }

    public ObservableList<String> getJobPart() {
	return jobPart;
    }

    public ObservableList<StaffModel> getStaffDatas() {
	return staffModels;
    }

    public int getPayPerHour() {
	return payPerHour;
    }

    public void setPayPerHour(int pay) {
	payPerHour = pay;
    }

}
