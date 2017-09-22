package PointOfView.Order.Menu.Model;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

public class Menues {

	private ObservableList<MenuItem> menuItems = null;
	
	public Menues() {
		
		//메뉴 데이터 리스트를 초기화한다.
		menuItems = FXCollections.observableArrayList();
		
		
		//임시로 메뉴를 추가한다.
		menuItems.addAll(new MenuItem("육개장", "주메뉴", 8000),
				new MenuItem("순대국",  "주메뉴", 5000),
				new MenuItem("공기밥", "주메뉴", 1000),
				new MenuItem("탕수육", "주메뉴", 14000),
				new MenuItem("자장면", "주메뉴", 5000),
				new MenuItem("짬뽕", "주메뉴", 6000));
	}
	
	public ObservableList<MenuItem> getMenuItems(){
		return menuItems;
	}
	
}
