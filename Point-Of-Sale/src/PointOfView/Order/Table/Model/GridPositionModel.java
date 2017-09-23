package PointOfView.Order.Table.Model;

public class GridPositionModel {

	private boolean showTable; //등록한 테이블인지 확인
	
	private int column;	//테이블 간략정보가 보여질 셀 가로축
	private int row;	//테이블 간략정보가 보여질 셀 세로축
	
	public GridPositionModel(boolean showStat) {
		this.showTable = showStat;
	}
	
	public void setColumnAndRow(int column, int row){
		showTable = true;
		this.column = column;
		this.row = row;
	}
	
	public boolean isShow(){
		return showTable;
	}
	
	public void setShow(boolean set)
	{
		showTable = set;
		if(!showTable){
			column = -1;
			row = -1;
		}
	}
	
	public int getColumn(){
		return column;
	}
	
	public int getRow(){
		return row;
	}
	
}
