package PointOfView.Models.OderList;

import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlType;

import PointOfView.Models.Menu.MenuItem;
import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;


@XmlType( name = "orderList")
public class OrderList extends MenuItem{

	private IntegerProperty count = new SimpleIntegerProperty(0);
	private StringProperty price = new SimpleStringProperty("");
	
	public OrderList(MenuItem item) {
		super(item.getName(), item.getCategory(), item.getPrice(), item.getColumn(), item.getRow());
		count.set(1);
	}
	
	public int plusCount() {
		count.set(count.get()+1);
		price.set(String.format("%,d 원", getPrice()));
		return count.get();
	}
	
	public int minusCount() {
		
		if(count.get()-1 < 0)
			return count.get();
		
		count.set(count.get()-1);
		price.set(String.format("%,d 원", getPrice()));
		return count.get();
	}
	
	public void clearCount() {
		count.set(0);
	}
	
	
	public String getName() {
		return super.getName();
	}
	

	
	public int getPrice() {
		return super.getPrice() * count.get();
	}
	


	public int getCount() {
		return count.get();
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
		return price.get();
	}
	
	public StringProperty priceProperty() {
		price.set(String.format("%,d 원", getPrice()));
		return price;
	}
	
}
