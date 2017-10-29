package PointOfView.Models.OrderList;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlTransient;
import javax.xml.bind.annotation.XmlType;

import PointOfView.Models.Menu.MenuItem;
import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

/**
 * 주문 리스트 관리 클래스
 * 
 * @author Jeongsam
 *
 */
@XmlType(name = "orderList")
@XmlAccessorType(XmlAccessType.FIELD)
public class OrderList extends MenuItem {

    @XmlTransient
    private IntegerProperty count = new SimpleIntegerProperty(0);
    @XmlTransient
    private StringProperty priceProperty = new SimpleStringProperty("");

    public OrderList() {
	super();
    }

    public OrderList(int count, String name, String category, int price, int column, int row) {
	super(name, category, price, column, row);
	this.count.set(1);
    }

    public OrderList(MenuItem item) {
	super(item.getName(), item.getCategory(), item.getPrice(), item.getColumn(), item.getRow());
	count.set(1);
    }

    public int plusCount() {
	count.set(count.get() + 1);
	priceProperty.set(String.format("%,d 원", getPrice()));
	return count.get();
    }

    public int minusCount() {

	if (count.get() - 1 < 0)
	    return count.get();

	count.set(count.get() - 1);
	priceProperty.set(String.format("%,d 원", getPrice()));
	return count.get();
    }

    public void clearCount() {
	count.set(0);
    }

    public String getName() {
	return super.getName();
    }

    @XmlTransient
    public int getPrice() {
	return super.getPrice() * count.get();
    }

    @XmlAttribute(name = "count")
    public int getCount() {
	return count.get();
    }

    public void setCount(int count) {
	this.count.set(count);
    }

    public MenuItem getMenuItem() {
	return (MenuItem) this;
    }

    public StringProperty nameProperty() {
	return new SimpleStringProperty(getName());
    }

    public IntegerProperty countProperty() {
	return count;
    }

    public String getPriceNaive() {
	return priceProperty.get();
    }

    public StringProperty priceProperty() {
	priceProperty.set(String.format("%,d 원", getPrice()));
	return priceProperty;
    }

}
