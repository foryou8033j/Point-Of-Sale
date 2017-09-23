package PointOfView.Order.Model;

import PointOfView.Order.Menu.Model.MenuItem;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

public class Tables {

	
	private ObservableList<TableData> tableDatas;

	public Tables() {
		tableDatas = FXCollections.observableArrayList();
		
		//기본으로 데이터를 가져올 경우 여기 추가한다.
		addTable(2, 2);
		addTable(2, 3);
		addTable(3, 4);
		addTable(3, 5);
		
	}
	
	public ObservableList<TableData> getTableDatas(){
		return tableDatas;
	}
	
	public int getSize(){
		return tableDatas.size();
	}
	
	public void addTable(int column, int row){
		tableDatas.add(new TableData(getSize(), column, row));
	}
	
	public void removeTable(TableData tableData) {
		tableDatas.remove(tableData);
		reArangeTableIndex();
	}
	
	public void removeTable(int index) {
		tableDatas.remove(index);
		reArangeTableIndex();
	}
	
	public void removeTable(int column, int row) {
		
		for(TableData data:tableDatas) {
			if(data.getColumn()==column && data.getRow()==row) {
				tableDatas.remove(data);
				break;
			}
		}
		reArangeTableIndex();
	}
	
	private void reArangeTableIndex() {
		
		for(int i=0; i<getSize(); i++) {
			tableDatas.get(i).setTableIndex(i);
		}
	}
	
}
