package PointOfView.Models.OrderList;

import java.util.List;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlElements;
import javax.xml.bind.annotation.adapters.XmlAdapter;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

public class OrderListWrappingAdapter extends XmlAdapter<List<OrderList>, ObservableList<OrderList>> {

    @Override
    public ObservableList<OrderList> unmarshal(List<OrderList> v) throws Exception {
	return FXCollections.observableArrayList(v);
    }

    @Override
    public List<OrderList> marshal(ObservableList<OrderList> v) throws Exception {
	return v;
    }
}