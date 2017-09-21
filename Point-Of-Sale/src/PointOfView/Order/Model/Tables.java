package PointOfView.Order.Model;

public class Tables {

	
	private TableData[] tableDatas = new TableData[80];
	private int size;
	private int index;
	
	public Tables() {
		
		index = 0;
		
		//기본으로 데이터를 가져올 경우 여기 추가한다.
		
		size = index;
		
	}
	
	public TableData[] getTableDatas(){
		return tableDatas;
	}
	
	public int getSize(){
		return size;
	}
	
	public void addTable(int column, int row){
		tableDatas[index++] = new TableData(column, row);
		size = index;
	}
	
}
