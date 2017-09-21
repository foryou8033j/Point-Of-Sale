package PointOfView.Order.Model;

public class Tables {

	
	private TableData[] tableDatas = new TableData[80];
	private int size;
	
	public Tables() {
		
		int index=0;
		
		for(int i=0; i<8; i++){
			for(int j=0; j<10; j++){
				tableDatas[index++] = new TableData();
			}
		}
		
		size = index;
		
	}
	
	public TableData[] getTableDatas(){
		return tableDatas;
	}
	
	public int getSize(){
		return size;
	}
	
}
