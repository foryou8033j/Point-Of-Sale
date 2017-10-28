package PointOfView.Models.Table;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlElements;
import javax.xml.bind.annotation.XmlType;

import PointOfView.Models.Menu.MenuItem;
import PointOfView.Models.OrderList.GridPositionModel;
import PointOfView.Models.OrderList.OrderList;
import javafx.collections.FXCollections;
import javafx.collections.ListChangeListener;
import javafx.collections.ObservableList;

@XmlType(name = "TableData")
@XmlAccessorType(XmlAccessType.NONE)
//@XmlRootElement(name = "TableData")
public class TableData extends GridPositionModel {

    private int index;
    private int discount;
    private int payMoney;

    private ObservableList<OrderList> orderList = FXCollections.observableArrayList();
    private int sumPrice;

    public TableData(int index, int column, int row, ObservableList<OrderList> list) {
	this(index, column, row);
	orderList = FXCollections.observableArrayList(list);
    }

    public TableData(int index, int column, int row) {
	super(true);
	setTableIndex(index);
	setColumnAndRow(column, row);
	orderList = FXCollections.observableArrayList();
	sumPrice = 0;
	discount = 0;
	payMoney = 0;
	calSumPrice();

	orderList.addListener(new ListChangeListener<OrderList>() {
	    @Override
	    public void onChanged(Change<? extends OrderList> c) {
		calSumPrice();
	    }
	});
    }

    public void copyData(TableData data) {

	sumPrice = 0;

	index = data.getTableIndex();
	orderList.clear();
	orderList.addAll(data.getOrderList());
	sumPrice = data.getSumPrice();
	payMoney = data.getPayMoney();
	discount = data.getDiscount();

    }

    public TableData() {
	super(false);
	orderList = FXCollections.observableArrayList();
    }

    public void setTableIndex(int index) {
	this.index = index;
    }

    // @XmlAttribute(name ="index", required = true)
    @XmlAttribute(name = "index")
    public int getTableIndex() {
	return index;
    }

    public int getSumPrice() {
	
	return calSumPrice();
    }

    public void addMenu(MenuItem menuItem) {

	for (OrderList item : orderList) {
	    if (item.getName().equals(menuItem.getName())) {
		item.plusCount();
		return;
	    }
	}

	orderList.add(new OrderList(menuItem));

    }

    public void addMenu(ObservableList<OrderList> menuItem) {

	for (OrderList item : menuItem) {
	    addMenu(item.getMenuItem());
	}

    }

    public void removeMenu(MenuItem menuItem) {
	for (OrderList item : orderList) {
	    if (item.getMenuItem().getName().equals(menuItem.getName())) {
		if (item.minusCount() == 0) {
		    orderList.remove(item);
		    break;
		}
	    }
	}
    }

    public void removeAll() {
	sumPrice = 0;
	discount = 0;
	payMoney = 0;
	orderList.clear();
    }

    // @XmlJavaTypeAdapter(OrderListWrappingAdapter.class)
    
    //@XmlElement(name = "orderlist")
    @XmlElements ( { @XmlElement ( name = "orderlist", type = OrderList.class)})
    public ObservableList<OrderList> getOrderList() {
	return orderList;
    }

    public void setOrderList(ObservableList<OrderList> list) {
	orderList.clear();
	orderList = list;
    }

    public void setDiscount(int dis) {
	discount = dis;
    }

    @XmlAttribute(name = "discount")
    public int getDiscount() {
	return discount;
    }

    public void setPayMoney(int pay) {
	payMoney = pay;
    }

    @XmlAttribute(name = "payMoney")
    public int getPayMoney() {
	return payMoney;
    }

    public int getResultPay() {

	return (calSumPrice() - discount - payMoney);
    }

    public void clearDiscount() {
	discount = 0;
    }

    private int calSumPrice() {

	sumPrice = 0;
	
	
	try {
	    for (OrderList item : orderList) {

		    if (item == null)
			break;
		    
		    sumPrice += item.getPrice();

		}
	}catch (Exception e) {
	    System.out.println("여기");
	    e.printStackTrace();
	}
	
	
	

	return sumPrice;

    }

}
