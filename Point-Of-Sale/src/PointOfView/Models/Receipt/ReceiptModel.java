package PointOfView.Models.Receipt;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Locale;
import java.util.Random;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlTransient;
import javax.xml.bind.annotation.adapters.XmlJavaTypeAdapter;

import PointOfView.Models.Receipt.Receipt.CardCompany;
import PointOfView.Models.Table.TableData;
import PointOfView.Util.CalendarWrappingAdapter;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

/**
 * 영수증 모델 관리
 * @author Jeongsam
 *
 */
@XmlAccessorType(XmlAccessType.FIELD)
public class ReceiptModel {

	private PAY_WAY payWay; 
	
	//@XmlJavaTypeAdapter(CalendarWrappingAdapter.class)
	private Calendar payTime;
	private TableData tableData;
	
	@XmlTransient
	private StringProperty date = new SimpleStringProperty();
	@XmlTransient
	private StringProperty pay = new SimpleStringProperty();
	@XmlTransient
	private StringProperty money = new SimpleStringProperty();
	
	private int returnedCash = 0;
	
	private int tradeIndex;
	private String cardCompany = "";
	private String cardName = "";
	private String cardNumber = "";
	
	public ReceiptModel() {
	    	
	}
	
	public ReceiptModel(int index, PAY_WAY payWay, TableData tableData, int returnedCash, Calendar payTime,
		int tradeIndex, String cardCompany, String cardName, String cardNumber ) {
	    	this(index, payWay, tableData);
		this.returnedCash = returnedCash;
		this.payTime = payTime;
		this.tradeIndex = tradeIndex;
		this.cardCompany = cardCompany;
		this.cardName = cardName;
		this.cardNumber = cardNumber;
	}
	
	public ReceiptModel(int index, PAY_WAY payWay, TableData tableData, int returnedCash) {
		this(index, payWay, tableData);
		this.returnedCash = returnedCash;
	}
	
	public ReceiptModel(int index, PAY_WAY payWay, TableData tableData) {
		
		//결제 영수증 고유 번호 저장
		tradeIndex = index;
		
		this.returnedCash = returnedCash;
		
		//결제 시간 저장
		this.payTime = Calendar.getInstance();
		
		//결제 방법 저장
		this.payWay = payWay;
		
		this.tableData = new TableData();
		this.tableData.copyData(tableData);
		
		
		SimpleDateFormat format = new SimpleDateFormat("yyyy.MM.dd(E)  HH:mm:ss", Locale.KOREA );
		date.set(format.format(payTime.getTimeInMillis()));
		
		pay.set(payWay.toString());
		
		money.set(String.valueOf(tableData.getSumPrice()));
		
		
		//카드 결제가 아니면 무시
		if(!payWay.equals(PAY_WAY.CARD)) 
			return;
		
		//결제를 구현하기 위해 가상으로 카드사나 기타 정보를 저장
		this.cardCompany = CardCompany.getCompany(new Random().nextInt(CardCompany.getSize()));
		this.cardName = CardCompany.getCardName(cardCompany);
		
		int rnd0 = new Random().nextInt((9999 - 1000) + 1) + 1000;
		int rnd1 = new Random().nextInt((9999 - 1000) + 1) + 1000;
		int rnd2 = new Random().nextInt((9999 - 1000) + 1) + 1000;
		
		cardNumber = String.valueOf(rnd0) + "-" + String.valueOf(rnd1) + "-****-" + String.valueOf(rnd2);
		
	}
	
	public void makeCell() {
	    	SimpleDateFormat format = new SimpleDateFormat("yyyy.MM.dd(E)  HH:mm:ss", Locale.KOREA );
		date.set(format.format(payTime.getTimeInMillis()));
		
		pay.set(payWay.toString());
		
		money.set(String.format("%,d 원", tableData.getSumPrice()));
		
		
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
	
	public int getIndex() {
		return tradeIndex;
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
	
	public String getCardCompany() {
		return cardCompany;
	}
	
	public String getCardName() {
		return cardName;
	}
	
	public String getCardNumber() {
		return cardNumber;
	}
	
	public void setReturnedCash(int cash) {
		returnedCash = cash;
	}
	
	public int getReturnedCash() {
		return returnedCash;
	}
	
}
