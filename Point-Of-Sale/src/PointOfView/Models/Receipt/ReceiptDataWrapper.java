package PointOfView.Models.Receipt;

import java.util.List;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

@XmlRootElement(name = "Receipt")
public class ReceiptDataWrapper {

    private List<ReceiptModel> receipt = FXCollections.<ReceiptModel>observableArrayList();

    // @XmlElements({ @XmlElement(name = "menuitem", type = MenuItem.class) })
    @XmlElement(name = "receipt")
    public List<ReceiptModel> getDatas() {
	return receipt;
    }

    public void setDatas(ObservableList<ReceiptModel> receipt) {
	this.receipt = (List) receipt;
    }
}
