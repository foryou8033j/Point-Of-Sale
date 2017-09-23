package PointOfView.Order.Table.Model;

import javax.xml.bind.annotation.XmlAttribute;

public class GridPositionModel {

	private boolean showTable; //등록한 테이블인지 확인
	
	private int column;	//테이블 간략정보가 보여질 셀 가로축
	private int row;	//테이블 간략정보가 보여질 셀 세로축
	
	public GridPositionModel() {
		
	}
	
	public GridPositionModel(boolean showStat) {
		this.showTable = showStat;
	}
	
	public void setColumnAndRow(int column, int row){
		showTable = true;
		this.column = column;
		this.row = row;
	}
	
	public void setColumnAndRow(boolean showtable, int column, int row){
		this.showTable = showTable;
		this.column = column;
		this.row = row;
	}

	@XmlAttribute(name ="show", required = true)
	public boolean isShow(){
		return showTable;
	}
	
	public void clearShow() {
		showTable = false;
		column = -1;
		row = -1;
	}
	
	public void setShow(boolean set)
	{
		showTable = set;
		if(!showTable){
			clearShow();
		}
	}
	
	@XmlAttribute(name ="column", required = true)
	public int getColumn(){
		return column;
	}
	
	@XmlAttribute(name ="row", required = true)
	public int getRow(){
		return row;
	}
	
	public void setColumn(int column) {
		this.column = column;
	}
	
	public void setRow(int row) {
		this.row = row;
	}
	
}
