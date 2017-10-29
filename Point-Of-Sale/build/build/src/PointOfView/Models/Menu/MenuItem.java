package PointOfView.Models.Menu;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;

import PointOfView.Models.OrderList.GridPositionModel;

/**
 * Menu 를 관리한다.
 * 
 * @author Jeongsam
 *
 */
@XmlType(name = "menuitem")
@XmlAccessorType(XmlAccessType.FIELD)
public class MenuItem extends GridPositionModel {

    @XmlAttribute(name = "name")
    private String name = null;
    @XmlAttribute(name = "category")
    private String category = null;
    @XmlAttribute(name = "price")
    private int price;

    @XmlAttribute(name = "stock")
    private int stock;

    public MenuItem() {
	super(false);
    }

    public MenuItem(String name, String category, int price) {

	super(false);

	this.name = new String(name);
	this.category = new String(category);
	this.price = price;
	this.stock = 0;
    }

    public MenuItem(String name, String category, int price, int column, int row) {

	super(true);

	this.name = new String(name);
	this.category = new String(category);
	this.price = price;
	setColumn(column);
	setRow(row);
	setShow(true);
    }

    public String getName() {
	return name;
    }

    public String getCategory() {
	return category;
    }

    public int getPrice() {
	return price;
    }

    public void setName(String name) {
	this.name = name;
    }

    public void setCategory(String category) {
	this.category = category;
    }

    public void setPrice(int price) {
	this.price = price;
    }

    public void setStock(int i) {
	stock = i;
    }

    public int getStock() {
	return stock;
    }

}
