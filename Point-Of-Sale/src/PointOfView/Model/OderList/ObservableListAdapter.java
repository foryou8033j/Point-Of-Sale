package PointOfView.Model.OderList;

import java.util.LinkedList;

import javax.xml.bind.annotation.adapters.XmlAdapter;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

public class ObservableListAdapter<OrderList> extends XmlAdapter<LinkedList<OrderList>, ObservableList<OrderList>> {
    @Override
    public ObservableList<OrderList> unmarshal(LinkedList<OrderList> v) throws Exception {
        return FXCollections.observableList(v);
    }

    @Override
    public LinkedList<OrderList> marshal(ObservableList<OrderList> v) throws Exception {
        LinkedList<OrderList> list = new LinkedList<OrderList>();
        list.addAll(v);
        return list; // Or whatever the correct method is
    }
}
