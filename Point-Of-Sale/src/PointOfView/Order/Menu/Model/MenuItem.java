package PointOfView.Order.Menu.Model;

import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlType;

import PointOfView.Order.Table.Model.GridPositionModel;

@XmlType( name = "menuitem")
public class MenuItem extends GridPositionModel{

	private String name = null;
	private String category = null;
	private int price;
	
	public MenuItem() {
		super(false);
	}
	
	public MenuItem(String name, String category, int price) {
		
		super(false);
		
		this.name = new String(name);
		this.category = new String(category);
		this.price = price;
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
	
	@XmlAttribute(name ="name", required = true)
	public String getName() {
		return name;
	}
	
	@XmlAttribute(name ="category", required = true)
	public String getCategory() {
		return category;
	}
	
	@XmlAttribute(name ="price", required = true)
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
	
}
