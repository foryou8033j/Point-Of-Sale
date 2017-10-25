package PointOfView.Models.Stastics.Menu;

import java.util.Calendar;
import java.util.HashMap;
import java.util.Map;

import PointOfView.Models.Menu.MenuItem;
import PointOfView.Models.Menu.Menues;
import PointOfView.Models.OrderList.OrderList;
import PointOfView.Models.Receipt.Receipt;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.chart.XYChart;

public class MenuStasticsModel {

	
	private Receipt receipt;
	
	private ObservableList<String> menuNames = FXCollections.observableArrayList();
	
	
	public MenuStasticsModel(Menues menues, Receipt receipt) {
		this.receipt = receipt;
		
		
        for(MenuItem data:menues.getMenuItems()) 
        	menuNames.add(data.getName());
	}
	
	public ObservableList<String> getXAxisModel(){
		return menuNames;
	}
	
	/**
	 * 년별 매출 통계를 반환한다.
	 * @return	XYChart.Series<String, Integer>
	 */
	public XYChart.Series<String, Integer> getYearModel(){
		
		Map<String, Integer> model = new HashMap<>();
		
        // 월별로 합산 매출액 만든다.
        for (int i = 0; i < receipt.getReceiptList().size(); i++) {
        	
        	for(OrderList order:receipt.getReceiptList().get(i).getPayTableData().getOrderList()) {
        		String menu = order.getName();
        		int count = order.getCount();
        		
        		if(model.get(menu) == null)
            		model.put(menu, count);
            	else
            		model.put(menu, model.get(menu) + count);
        	}
        	
        }
        	
        XYChart.Series<String, Integer> series = new XYChart.Series<>();
        
        // 그래프 Series 에 데이터를 추가한다.
        for (int i=0; i < model.size(); i++) {
        	
        	String menu = menuNames.get(i);
        	
        	if(model.get(menu) == null)
        		continue;
        	
        	int count = model.get(menu);
        	System.out.println(count);
        	series.getData().add(new XYChart.Data<>(menuNames.get(i), count));
        	
        	model.put(menu, model.get(menu) + count);
        }
        
        return series;
		
	}

	
}
