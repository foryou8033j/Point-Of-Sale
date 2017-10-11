package PointOfView.Model.Table;

import java.util.LinkedList;
import java.util.List;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlElements;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.adapters.XmlAdapter;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

@XmlRootElement(name = "Tables")
public class TableDataWrapper {
	
	
	
	private List<TableData> table = FXCollections.<TableData>observableArrayList();

	@XmlElements ( { @XmlElement ( name = "table", type = TableData.class)})
    public List<TableData> getTableDatas() {
        return table;
    }

    public void setDatas(ObservableList<TableData> table) {
        this.table = (List) table;
    }
}


