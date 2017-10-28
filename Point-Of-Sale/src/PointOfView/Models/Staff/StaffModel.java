package PointOfView.Models.Staff;

import java.awt.AWTException;
import java.awt.Robot;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Locale;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlAttribute;

import org.apache.commons.lang3.time.StopWatch;

import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

@XmlAccessorType(XmlAccessType.NONE)
public class StaffModel {

    private StringProperty name = new SimpleStringProperty();
    private StringProperty category = new SimpleStringProperty();
    private StringProperty part = new SimpleStringProperty();
    private StringProperty status = new SimpleStringProperty();
    
    private StringProperty pay = new SimpleStringProperty();

    private StopWatch stopwatch;

    @XmlAttribute(name = "todayWorkHour")
    private int todayWorkHour = 0;
    @XmlAttribute(name = "wholeWorkHour")
    private int wholeWorkHour = 0;
    @XmlAttribute(name = "monthlyWorkHour")
    private int monthlyWorkHour = 0;

    @XmlAttribute(name = "lastMonth")
    private int lastMonth;

    private Thread thread;

    public StaffModel() {
	stopwatch = new StopWatch();

	staffWorkListener();
    }

    public StaffModel(String name, String category, String part) {

	this.name.set(name);
	this.category.set(category);
	this.part.set(part);
	this.status.set(WORK_STATUS.REST.toString());

	lastMonth = Calendar.getInstance().get(Calendar.MONTH);

	stopwatch = new StopWatch();

	staffWorkListener();
    }

    private void staffWorkListener() {

	thread = new Thread(() -> {

	    while (!thread.isInterrupted()) {

		SimpleDateFormat formatterMM = new SimpleDateFormat("mm", Locale.KOREA);
		SimpleDateFormat formatterSS = new SimpleDateFormat("ss", Locale.KOREA);

		int min = Integer.parseInt(formatterMM.format(stopwatch.getTime()));
		int sec = Integer.parseInt(formatterSS.format(stopwatch.getTime()));

		System.out.println(this.name.get() + " : " + min + ":" + sec);

		if (Calendar.getInstance().get(Calendar.MONTH) != lastMonth)
		    monthlyWorkHour = 0;

		// 매 시간이 지나게 하면, 근무 시간을 추가 한다.
		if (min >= 59 && sec >= 59) {
		    todayWorkHour++;
		    monthlyWorkHour++;
		    wholeWorkHour++;

		    // 최대 근무시간은 12시간으로 한다.
		} else if (todayWorkHour > 12) {
		    todayWorkHour = 0;
		    stopWork();
		    stopwatch.stop();
		    stopwatch.reset();
		}

		// 1000ms 마다 스레드를 반복
		try {
		    new Robot().delay(1000);
		} catch (AWTException e) {
		    //Sleep Interrupted가 반복하면 쓰레드 외부에서 Interrupted가 발생한것으로 판단하고 반복종료
		    break;
		}

	    }

	});
	
	thread.setDaemon(true);
	thread.start();

    }

    @XmlAttribute(name = "name")
    public String getName() {
	return name.get();
    }

    public void setName(String name) {
	this.name.set(name);
    }

    public StringProperty getNameProperty() {
	return name;
    }

    @XmlAttribute(name = "category")
    public String getCategory() {
	return category.get();
    }

    public void setCategory(String category) {
	this.category.set(category);
    }

    public StringProperty getCategoryProperty() {
	return category;
    }

    @XmlAttribute(name = "part")
    public String getPart() {
	return part.get();
    }

    public void setPart(String part) {
	this.part.set(part);
    }

    public StringProperty getPartProperty() {
	return part;
    }

    @XmlAttribute(name = "status")
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

	if (stopwatch.isSuspended())
	    stopwatch.resume();
	else
	    stopwatch.start();

	this.status.set(WORK_STATUS.WORK.toString());
    }

    public void stopWork() {
	stopwatch.suspend();
	this.status.set(WORK_STATUS.REST.toString());
    }

    public void quitWork() {

	try {
	    
	    thread.interrupt();
	    
	    if (isWork())
		stopwatch.stop();

	    if (isSupended()) {
		stopwatch.resume();
		stopwatch.stop();
	    }

	    
	} catch (Exception e) {
	    // ignore
	}

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
    
    @XmlAttribute(name = "pay")
    public String getPay() {
	return pay.get();
    }

    public void setPay(String pay) {
	this.pay.set(pay);
    }

    public StringProperty getPayProperty() {
	return pay;
    }

}
