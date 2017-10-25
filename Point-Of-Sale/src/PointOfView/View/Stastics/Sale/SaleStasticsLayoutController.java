package PointOfView.View.Stastics.Sale;

import PointOfView.Models.Stastics.StasticsModel;
import PointOfView.Models.Stastics.Sale.CHART_CATEGORY;
import javafx.fxml.FXML;
import javafx.scene.chart.BarChart;
import javafx.scene.chart.CategoryAxis;
import javafx.scene.chart.LineChart;

public class SaleStasticsLayoutController {

	@FXML
	private LineChart<String, Integer> yearChart;

	@FXML
	private CategoryAxis yearChartXAxis;

	@FXML
	private LineChart<String, Integer> monthChart;

	@FXML
	private CategoryAxis monthChartXAxis;

	@FXML
	private LineChart<String, Integer> dayChart;

	@FXML
	private CategoryAxis dayChartXAxis;

	public void setStastics(StasticsModel stasticsModel) {

		dayChartXAxis.setCategories(stasticsModel.getSaleStasticsModel().getXAxisModel(CHART_CATEGORY.DAY));
		monthChartXAxis.setCategories(stasticsModel.getSaleStasticsModel().getXAxisModel(CHART_CATEGORY.MONTH));
		yearChartXAxis.setCategories(stasticsModel.getSaleStasticsModel().getXAxisModel(CHART_CATEGORY.YEAR));

		dayChart.getData().add(stasticsModel.getSaleStasticsModel().getDayModel());
		monthChart.getData().add(stasticsModel.getSaleStasticsModel().getMonthModel());
		yearChart.getData().add(stasticsModel.getSaleStasticsModel().getYearModel());
		

	}

}
