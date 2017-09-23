package PointOfView.Model;

import PointOfView.Order.Menu.Model.MenuItem;
import javafx.scene.layout.HBox;

/**
 * 특정 인덱스 정보를 가진 Hbox_sub Inner 클래스
 * Hbox 에 MenuItem 클래스를 포함하기 위해 상속 받아 정의
 * @author Jeongsam
 *
 */
public class Hbox_MenuItem extends HBox{
	private MenuItem item;
	
	public Hbox_MenuItem(double s, MenuItem item) {
		super(s);
		this.item = item;
	}
	
	public MenuItem getMenuItem() {
		return item;
	}
	
	public void setMenuItem(MenuItem menuItem) {
		this.item = menuItem;
	}
}
