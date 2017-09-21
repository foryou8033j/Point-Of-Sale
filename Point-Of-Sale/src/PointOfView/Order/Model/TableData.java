package PointOfView.Order.Model;

public class TableData extends TableDataModel{

	
	public TableData(int column, int row) {
		super(true);
		setColumnAndRow(column, row);
	}
	
	public TableData(){
		super(false);
	}
	
}
