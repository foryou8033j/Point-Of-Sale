package PointOfView.Models.Staff;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Locale;

import org.apache.commons.lang3.time.StopWatch;

import com.sun.javafx.binding.StringFormatter;

import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

public class StaffModel {

    private StringProperty name = new SimpleStringProperty();
    private StringProperty category = new SimpleStringProperty();
    private StringProperty part = new SimpleStringProperty();
    private StringProperty status = new SimpleStringProperty();

    private StopWatch stopwatch;

    private int todayWorkHour = 0;
    private int wholeWorkHour = 0;
    private int monthlyWorkHour = 0;
    
    private int lastMonth;

    public StaffModel(String name, String category, String part) {

	this.name.set(name);
	this.category.set(category);
	this.part.set(part);
	this.status.set(WORK_STATUS.REST.toString());

	lastMonth = Calendar.getInstance().get(Calendar.MONTH);
	
	stopwatch = new StopWatch();

	new Thread(() -> {

	    if (isWork()) {

		try {

		    SimpleDateFormat formatterMM = new SimpleDateFormat("mm", Locale.KOREA);
		    SimpleDateFormat formatterSS = new SimpleDateFormat("ss", Locale.KOREA);

		    int min = Integer.parseInt(formatterMM.format(stopwatch.getTime()));
		    int sec = Integer.parseInt(formatterSS.format(stopwatch.getTime()));
		    
		    if(Calendar.getInstance().get(Calendar.MONTH) != lastMonth)
			monthlyWorkHour = 0;
		    
		    if (min >= 59 && sec >= 59) {
			todayWorkHour++;
			monthlyWorkHour++;
			wholeWorkHour++;
		    }else if(todayWorkHour > 12) {
			todayWorkHour = 0;
			stopWork();
			stopwatch.stop();
			stopwatch.reset();
		    }
		    

		    Thread.sleep(500);
		} catch (InterruptedException e) {
		    // TODO Auto-generated catch block
		    e.printStackTrace();
		}

	    }

	}).start();

    }

    public String getName() {
	return name.get();
    }

    public void setName(String name) {
	this.name.set(name);
    }

    public StringProperty getNameProperty() {
	return name;
    }

    public String getCategory() {
	return category.get();
    }

    public void setCategory(String category) {
	this.category.set(category);
    }

    public StringProperty getCategoryProperty() {
	return category;
    }

    public String getPart() {
	return part.get();
    }

    public void setPart(String part) {
	this.part.set(part);
    }

    public StringProperty getPartProperty() {
	return part;
    }

    public String getStatus() {
	return status.get();
    }

    public void setStatus(String status) {
	this.status.set(status);
    }

    public StringProperty getStatusProperty() {
	return status;
    }

    public void startWork() {
	
	if(stopwatch.isSuspended())
	    stopwatch.resume();
	else
	    stopwatch.start();
	
	this.status.set(WORK_STATUS.WORK.toString());
    }

    public void stopWork() {
	stopwatch.suspend();
	this.status.set(WORK_STATUS.REST.toString());
    }

    public long getTodayWorkTime() {
	return stopwatch.getTime();
    }
    
    public int getTodayWorkHours() {
	return todayWorkHour;
    }

    public boolean isWork() {
	return stopwatch.isStarted();
    }
    
    public boolean isSupended() {
	return stopwatch.isSuspended();
    }
    
    public int getWholeWorkHour() {
	return wholeWorkHour;
    }
    
    public int getMonthlyWorkHour() {
	return monthlyWorkHour;
    }
    

}
