package PointOfView.Order.Model;

import PointOfView.Order.Menu.Model.MenuItem;
import javafx.collections.FXCollections;
import javafx.collections.ListChangeListener;
import javafx.collections.ObservableList;

public class TableData extends TableDataModel{

	private int index;
	private ObservableList<MenuItem> menuItems = null;
	private int sumPrice;
	

	public TableData(int index, int column, int row) {
		super(true);
		setTableIndex(index);
		setColumnAndRow(column, row);
		menuItems = FXCollections.observableArrayList();
		sumPrice = 0;
		calSumPrice();
		
		menuItems.addListener(new ListChangeListener<MenuItem>() {
			@Override
			public void onChanged(Change<? extends MenuItem> c) {
				calSumPrice();
			}
		});
	}
	
	public TableData(){
		super(false);
		menuItems = FXCollections.observableArrayList();
	}
	
	public void setTableIndex(int index) {
		this.index = index; 
	}
	
	public int getTableIndex() {
		return index;
	}
	
	public int getSumPrice() {
		calSumPrice();
		return sumPrice;
	}
	
	public void addMenu(MenuItem menuItem) {
		menuItems.add(menuItem);
	}
	
	public void removeMenu(MenuItem menuItem)
	{
		menuItems.remove(menuItem);
	}
	
	public ObservableList<MenuItem> getMenuItems(){
		return menuItems;
	}
	
	private void calSumPrice() {
		
		sumPrice = 0;
		
		for(MenuItem item:menuItems) {
			if(item == null)
				break;
			
			sumPrice += item.getPrice();
			
		}
		
		
	}
	
	
	
	
}
