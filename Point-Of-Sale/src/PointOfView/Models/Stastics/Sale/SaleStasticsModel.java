package PointOfView.Models.Stastics.Sale;

import java.util.Calendar;

import PointOfView.Models.Receipt.Receipt;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

public class SaleStasticsModel {

	
	private Receipt receipt;
	
	private ObservableList<String> dayNames = FXCollections.observableArrayList();
	private ObservableList<String> monthNames = FXCollections.observableArrayList();
	private ObservableList<String> yearNames = FXCollections.observableArrayList();
	
	
	public SaleStasticsModel(Receipt receipt) {
		this.receipt = receipt;
		
		Calendar cal = Calendar.getInstance();

        int lastDayOfPreMonth = cal.getMaximum(Calendar.DAY_OF_MONTH);
        int today = cal.getTime().getDate();
        
        //최근 30일의 날자를 리스트화 한다.
        for(int i=0; i<30; i++) {
        	if(today > 0)
        		dayNames.add(String.valueOf(today--));
        	else 
        		dayNames.add(String.valueOf(lastDayOfPreMonth--));
        }
        
        //최근 12달의 월을 리스트화 한다.
        int curMonth = cal.getTime().getMonth();
        int lastMonthOfYear = 12;
        
        for (int i=0; i<12; i++) {
        	if(curMonth < 0)
        		monthNames.add(String.valueOf(curMonth--));
        	else
        		monthNames.add(String.valueOf(lastMonthOfYear--));
        }
        
        //최근 10년의 해를 리스트화 한다.
        
        int year = cal.get(Calendar.YEAR);
        
        for(int i=0; i<10; i++) {
        	System.out.println(year);
        	yearNames.add(String.valueOf(year--));
        }
		
	}
	
	public ObservableList<String> getXAxisModel(CHART_CATEGORY category){
		if(category.equals(CHART_CATEGORY.DAY))
			return dayNames;
		
		else if(category.equals(CHART_CATEGORY.MONTH))
			return monthNames;
		
		else if(category.equals(CHART_CATEGORY.YEAR))
			return yearNames;
		
		return FXCollections.observableArrayList(new String("ERROR"));
		
	}
	
	
	
	
	
	
}
