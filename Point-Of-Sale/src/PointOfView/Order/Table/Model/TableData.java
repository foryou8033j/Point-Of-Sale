package PointOfView.Order.Table.Model;

import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlType;

import PointOfView.Order.Menu.Model.MenuItem;
import javafx.collections.FXCollections;
import javafx.collections.ListChangeListener;
import javafx.collections.ObservableList;

@XmlType( name = "table")
public class TableData extends GridPositionModel{

	private int index;
	private int discount;
	private ObservableList<OrderList> orderList = null;
	private int sumPrice;

	public TableData(int index, int column, int row) {
		super(true);
		setTableIndex(index);
		setColumnAndRow(column, row);
		orderList = FXCollections.observableArrayList();
		sumPrice = 0;
		discount = 0;
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
		discount =data.getDiscount();
		
	}
	
	public TableData(){
		super(false);
		orderList = FXCollections.observableArrayList();
	}
	
	public void setTableIndex(int index) {
		this.index = index; 
	}
	
	@XmlAttribute(name ="index", required = true)
	public int getTableIndex() {
		return index;
	}
	
	public int getSumPrice() {
		calSumPrice();
		return sumPrice;
	}
	
	public void addMenu(MenuItem menuItem) {
		
		for(OrderList item:orderList) {
			if(item.getName() == menuItem.getName()) {
				item.plusCount();
				return;
			}
		}
		
		orderList.add(new OrderList(menuItem));
		
	}
	
	public void addMenu(ObservableList<OrderList> menuItem) {
		
		for(OrderList item:menuItem) {
			addMenu(item.getMenuItem());
		}
		
	}
	
	public void removeMenu(MenuItem menuItem)
	{
		for(OrderList item:orderList) {
			if(item.getMenuItem().getName().equals(menuItem.getName())) {
				if(item.minusCount() == 0) {
					orderList.remove(item);
					break;
				}
			}
		}
	}
	
	public void removeAll() {
		orderList.clear();
	}

	public ObservableList<OrderList> getOrderList(){
		return orderList;
	}
	
	public void setDiscount(int dis) {
		discount = dis;
	}
	
	public int getDiscount() {
		return discount;
	}
	
	public void clearDiscount() {
		discount = 0;
	}
	
	private void calSumPrice() {
		
		sumPrice = 0;
		
		for(OrderList item:orderList) {{
			if(item.getCount() == 0)
				orderList.remove(item);
		}
			
			
			sumPrice += item.getPrice();
			
		}
		
		
	}
	
	
	
	
}
