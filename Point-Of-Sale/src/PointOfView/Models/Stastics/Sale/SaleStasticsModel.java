package PointOfView.Models.Stastics.Sale;

import java.sql.Date;
import java.util.Calendar;
import java.util.Collection;
import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

import PointOfView.Models.Receipt.Receipt;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.chart.XYChart;

/**
 * 매출 통계 전처리 클래스
 * 
 * @author Jeongsam
 *
 */
public class SaleStasticsModel {

    private Receipt receipt;

    private ObservableList<String> dayNames = FXCollections.observableArrayList();
    private ObservableList<String> weekNames = FXCollections.observableArrayList();
    private ObservableList<String> monthNames = FXCollections.observableArrayList();
    private ObservableList<String> yearNames = FXCollections.observableArrayList();

    public SaleStasticsModel(Receipt receipt) {
	this.receipt = receipt;

	Calendar cal = Calendar.getInstance();

	int lastDayOfPreMonth = cal.getMaximum(Calendar.DAY_OF_MONTH);
	int today = cal.getTime().getDate();

	// 최근 30일의 날자를 리스트화 한다.
	for (int i = 0; i < 30; i++) {
	    if (today > 0)
		dayNames.add(String.valueOf(today--));
	    else
		dayNames.add(String.valueOf(lastDayOfPreMonth--));
	}

	Collections.reverse(dayNames);

	// 최근 4주의 주를 리스트화 한다.
	weekNames.addAll("1", "2주", "3", "4", "5");

	// 최근 12달의 월을 리스트화 한다.
	int curMonth = cal.getTime().getMonth();
	int lastMonthOfYear = 12;

	for (int i = 0; i < 12; i++) {
	    if (curMonth < 0)
		monthNames.add(String.valueOf(curMonth--));
	    else
		monthNames.add(String.valueOf(lastMonthOfYear--));
	}

	Collections.reverse(monthNames);

	// 최근 10년의 해를 리스트화 한다.
	int year = cal.get(Calendar.YEAR);

	for (int i = 0; i < 10; i++) {
	    yearNames.add(String.valueOf(year--));
	}

	Collections.reverse(yearNames);
    }

    public ObservableList<String> getXAxisModel(CHART_CATEGORY category) {
	if (category.equals(CHART_CATEGORY.DAY))
	    return dayNames;

	else if (category.equals(CHART_CATEGORY.WEEK))
	    return weekNames;

	else if (category.equals(CHART_CATEGORY.MONTH))
	    return monthNames;

	else if (category.equals(CHART_CATEGORY.YEAR))
	    return yearNames;

	return FXCollections.observableArrayList(new String("ERROR"));

    }

    /**
     * 년별 매출 통계를 반환한다.
     * 
     * @return XYChart.Series<String, Integer>
     */
    public XYChart.Series<String, Integer> getYearModel() {
	Map<String, Integer> yearModel = new HashMap<>();

	// 월별로 합산 매출액 만든다.
	for (int i = 0; i < receipt.getReceiptList().size(); i++) {

	    String date = String.valueOf(receipt.getReceiptList().get(i).getPayTime().get(Calendar.YEAR));
	    int price = receipt.getReceiptList().get(i).getPayTableData().getSumPrice();

	    if (yearModel.get(date) == null)
		yearModel.put(date, price);
	    else
		yearModel.put(date, yearModel.get(date) + price);
	}

	XYChart.Series<String, Integer> series = new XYChart.Series<>();

	// 그래프 Series 에 데이터를 추가한다.
	for (int i = 0; i < yearNames.size(); i++) {

	    String date = yearNames.get(i);

	    if (yearModel.get(date) == null)
		continue;

	    int price = yearModel.get(date);
	    series.getData().add(new XYChart.Data<>(yearNames.get(i), price));

	    yearModel.put(date, yearModel.get(date) + price);
	}

	series.setName("매출액");

	return series;

    }

    /**
     * 월별 매출 통계를 반환한다.
     * 
     * @return XYChart.Series<String, Integer>
     */
    public XYChart.Series<String, Integer> getMonthModel() {
	Map<String, Integer> monthModel = new HashMap<>();

	// 월별로 합산 매출액 만든다.
	for (int i = 0; i < receipt.getReceiptList().size(); i++) {

	    String date = String.valueOf(receipt.getReceiptList().get(i).getPayTime().get(Calendar.MONTH) + 1);
	    int price = receipt.getReceiptList().get(i).getPayTableData().getSumPrice();

	    if (monthModel.get(date) == null)
		monthModel.put(date, price);
	    else
		monthModel.put(date, monthModel.get(date) + price);
	}

	XYChart.Series<String, Integer> series = new XYChart.Series<>();

	// 그래프 Series 에 데이터를 추가한다.
	for (int i = 0; i < monthNames.size(); i++) {

	    String date = monthNames.get(i);

	    if (monthModel.get(date) == null)
		continue;

	    int price = monthModel.get(date);
	    series.getData().add(new XYChart.Data<>(monthNames.get(i), price));

	    monthModel.put(date, monthModel.get(date) + price);
	}

	series.setName("매출액");

	return series;

    }

    /**
     * 주별 매출 통계를 반환한다.
     * 
     * @return XYChart.Series<String, Integer>
     */
    public XYChart.Series<String, Integer> getWeekModel() {

	Map<String, Integer> weekModel = new HashMap<>();

	// 주별로 합산 통계를 만든다
	for (int i = 0; i < receipt.getReceiptList().size(); i++) {

	    String week = String.valueOf(receipt.getReceiptList().get(i).getPayTime().get(Calendar.WEEK_OF_MONTH));

	    int year = receipt.getReceiptList().get(i).getPayTime().get(Calendar.YEAR);
	    int month = receipt.getReceiptList().get(i).getPayTime().get(Calendar.MONTH);

	    Calendar cal = Calendar.getInstance();

	    int cur_year = cal.get(Calendar.YEAR);
	    int cur_month = cal.get(Calendar.MONTH);

	    // 올해나 이번달이 아닌경우 무시한다.
	    if (year != cur_year || month != cur_month)
		continue;

	    int price = receipt.getReceiptList().get(i).getPayTableData().getSumPrice();

	    if (weekModel.get(week) == null)
		weekModel.put(week, price);
	    else
		weekModel.put(week, weekModel.get(week) + price);
	}

	XYChart.Series<String, Integer> series = new XYChart.Series<>();

	// 그래프 Series 에 데이터를 추가한다.
	for (int i = 0; i < dayNames.size(); i++) {

	    String date = dayNames.get(i);

	    if (weekModel.get(date) == null)
		continue;

	    int price = weekModel.get(date);
	    series.getData().add(new XYChart.Data<>(dayNames.get(i), price));

	    weekModel.put(date, weekModel.get(date) + price);
	}

	series.setName("매출액");

	return series;
    }

    /**
     * 일별 매출 통계를 반환한다.
     * 
     * @return XYChart.Series<String, Integer>
     */
    public XYChart.Series<String, Integer> getDayModel() {
	Map<String, Integer> dayModel = new HashMap<>();

	// 일별로 합산 매출액 만든다.
	for (int i = 0; i < receipt.getReceiptList().size(); i++) {

	    String date = String.valueOf(receipt.getReceiptList().get(i).getPayTime().get(Calendar.DATE));
	    int price = receipt.getReceiptList().get(i).getPayTableData().getSumPrice();

	    if (dayModel.get(date) == null)
		dayModel.put(date, price);
	    else
		dayModel.put(date, dayModel.get(date) + price);
	}

	XYChart.Series<String, Integer> series = new XYChart.Series<>();

	// 그래프 Series 에 데이터를 추가한다.
	for (int i = 0; i < dayNames.size(); i++) {

	    String date = dayNames.get(i);

	    if (dayModel.get(date) == null)
		continue;

	    int price = dayModel.get(date);
	    series.getData().add(new XYChart.Data<>(dayNames.get(i), price));

	    dayModel.put(date, dayModel.get(date) + price);
	}

	series.setName("매출액");

	return series;

    }

}
