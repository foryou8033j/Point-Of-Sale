package PointOfView.Order.Menu.Model;

public class MenuItem {

	private String name = null;
	private String category = null;
	private int price;
	
	public MenuItem(String name, String category, int price) {
		this.name = new String(name);
		this.category = new String(category);
		this.price = price;
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
	
}
