package PointOfView.Models.Stastics.Menu;

import java.util.HashMap;
import java.util.Map;

import PointOfView.Models.Menu.MenuItem;
import PointOfView.Models.Menu.Menues;
import PointOfView.Models.OrderList.OrderList;
import PointOfView.Models.Receipt.Receipt;
import PointOfView.Models.Receipt.ReceiptModel;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.Node;
import javafx.scene.chart.XYChart;
import javafx.scene.layout.StackPane;

/**
 * 메뉴 통계 전처리 클래스
 * 
 * @author Jeongsam
 *
 */
public class MenuStasticsModel {

    private Menues menues;
    private Receipt receipt;

    private ObservableList<String> menuNames = FXCollections.observableArrayList();

    public MenuStasticsModel(Menues menues, Receipt receipt) {
	this.menues = menues;
	this.receipt = receipt;

	createMenuList();

    }

    private void createMenuList() {

	menuNames.clear();

	// 등록 된 메뉴를 바탕으로 메뉴 리스트를 만든다.
	for (MenuItem data : menues.getMenuItems())
	    menuNames.add(data.getName());

	// 등록 되었다가 지워졌지만 영수증에 남아 있는 메뉴를 리스트에 등록한다.
	for (ReceiptModel item : receipt.getReceiptList()) {

	    for (OrderList list : item.getPayTableData().getOrderList()) {

		if (!menuNames.contains(list.getName()))
		    menuNames.add(list.getName());
	    }

	}
    }

    public ObservableList<String> getXAxisModel() {
	return menuNames;
    }

    /**
     * 년별 매출 통계를 반환한다.
     * 
     * @return XYChart.Series<String, Integer>
     */
    public XYChart.Series<String, Integer> getMenuModel() {

	createMenuList();

	Map<String, Integer> model = new HashMap<>();

	// 메뉴 별로 합산 매출액 만든다.
	for (int i = 0; i < receipt.getReceiptList().size(); i++) {

	    for (OrderList order : receipt.getReceiptList().get(i).getPayTableData().getOrderList()) {
		String menu = order.getName();
		int count = order.getCount();

		if (model.get(menu) == null)
		    model.put(menu, count);
		else
		    model.put(menu, model.get(menu) + count);
	    }

	}

	XYChart.Series<String, Integer> series = new XYChart.Series<>();

	// 그래프 Series 에 데이터를 추가한다.
	for (int i = 0; i < model.size(); i++) {

	    String menu = menuNames.get(i);

	    if (model.get(menu) == null)
		continue;

	    int count = model.get(menu);

	    // XYChart.Data<> data = new XYChart.Data<>(menuNames.get(i), count);

	    XYChart.Data<String, Integer> data = new XYChart.Data<>(menuNames.get(i), count);

	    series.getData().add(data);
	}

	series.setName("판매량");

	return series;

    }

}
