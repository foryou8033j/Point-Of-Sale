package PointOfView.Models.Staff;

import java.util.List;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

import PointOfView.Models.Receipt.ReceiptModel;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;


@XmlRootElement(name = "Staff")
public class StaffDataWrapper {

    private List<StaffModel> staff = FXCollections.<StaffModel>observableArrayList();

    // @XmlElements({ @XmlElement(name = "menuitem", type = MenuItem.class) })
    @XmlElement(name = "staff")
    public List<StaffModel> getDatas() {
	return staff;
    }

    public void setDatas(ObservableList<StaffModel> receipt) {
	this.staff = (List) receipt;
    }
    
}
