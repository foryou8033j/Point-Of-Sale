package PointOfView.Order.Table.Model;

import PointOfView.Order.Menu.Model.MenuItem;

public class OrderList {

	private MenuItem menuItem;
	private int count;
	
	public OrderList(MenuItem menuItem) {
		this.menuItem = menuItem;
		count = 1;
	}
	
	public int plusCount() {
		return ++count;
	}
	
	public int minusCount() {
		return --count;
	}
	
	public String getName() {
		return menuItem.getName();
	}
	
	public int getPrice() {
		return menuItem.getPrice() * count;
	}
	
	public int getCount() {
		return count;
	}
	
	public MenuItem getMenuItem() {
		return menuItem;
	}
	
}
