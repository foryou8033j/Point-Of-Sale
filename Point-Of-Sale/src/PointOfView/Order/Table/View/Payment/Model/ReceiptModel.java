package PointOfView.Order.Table.View.Payment.Model;

import java.util.Calendar;

import PointOfView.Order.Table.Model.TableData;

public class ReceiptModel {

	public enum PAY{CARD, CASH};
	
	private PAY payWay; 
	private Calendar payTime; 
	private TableData tableData;
	
	public ReceiptModel(PAY payWay, TableData tableData) {
		
		//결제 시간 저장
		this.payTime = Calendar.getInstance();
		
		this.payWay = payWay;
		
		this.tableData = new TableData();
		this.tableData.copyData(tableData);
		
	}
	
	
	public PAY getPayWay() {
		return payWay;
	}
	
	public Calendar getPayTime() {
		return payTime;
	}
	
	public TableData getPayTableData() {
		return tableData;
	}
	
}
