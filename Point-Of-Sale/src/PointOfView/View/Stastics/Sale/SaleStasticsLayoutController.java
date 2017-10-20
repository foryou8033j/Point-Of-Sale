package PointOfView.View.Stastics.Sale;

import java.awt.image.DataBufferUShort;

import PointOfView.Models.Stastics.StasticsModel;
import PointOfView.Models.Stastics.Sale.CHART_CATEGORY;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.fxml.FXML;
import javafx.geometry.Bounds;
import javafx.scene.Group;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.chart.BarChart;
import javafx.scene.chart.CategoryAxis;
import javafx.scene.chart.XYChart;
import javafx.scene.text.Text;

public class SaleStasticsLayoutController {

	@FXML
	private BarChart<String, Integer> yearChart;

	@FXML
	private CategoryAxis yearChartXAxis;

	@FXML
	private BarChart<String, Integer> monthChart;

	@FXML
	private CategoryAxis monthChartXAxis;

	@FXML
	private BarChart<String, Integer> dayChart;

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
