package PointOfView.Models.Receipt;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Locale;

import PointOfView.Models.Table.TableData;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

/**
 * 영수증 모델 관리
 * @author Jeongsam
 *
 */
public class ReceiptModel {

	private PAY_WAY payWay; 
	private Calendar payTime; 
	private TableData tableData;
	
	private StringProperty date = new SimpleStringProperty();
	private StringProperty pay = new SimpleStringProperty();
	private StringProperty money = new SimpleStringProperty();
	
	public ReceiptModel(PAY_WAY payWay, TableData tableData) {
		
		//결제 시간 저장
		this.payTime = Calendar.getInstance();
		
		//결제 방법 저장
		this.payWay = payWay;
		
		this.tableData = new TableData();
		this.tableData.copyData(tableData);
		
		
		
		SimpleDateFormat format = new SimpleDateFormat("yyyy.MM.dd(E)  HH:mm:ss:SSS", Locale.KOREA );
		date.set(format.format(payTime.getTimeInMillis()));
		
		pay.set(payWay.toString());
		
		money.set(String.valueOf(tableData.getSumPrice()));
		
	}
	
	public StringProperty dateProperty() {
		return date;
	}
	
	public StringProperty payProperty() {
		return pay;
	}
	
	public StringProperty moneyProperty() {
		return money;
	}
	
	
	public PAY_WAY getPayWay() {
		return payWay;
	}
	
	public Calendar getPayTime() {
		return payTime;
	}
	
	public TableData getPayTableData() {
		return tableData;
	}
	
}
