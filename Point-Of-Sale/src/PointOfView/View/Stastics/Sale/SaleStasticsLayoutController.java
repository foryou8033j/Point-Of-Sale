package PointOfView.View.Stastics.Sale;

import PointOfView.Models.Stastics.StasticsModel;
import PointOfView.Models.Stastics.Sale.CHART_CATEGORY;
import PointOfView.Util.Dialog.LogPopup;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.chart.CategoryAxis;
import javafx.scene.chart.LineChart;
import javafx.scene.chart.XYChart;
import javafx.scene.chart.XYChart.Data;
import javafx.scene.layout.StackPane;
import javafx.scene.text.Font;
import javafx.scene.text.Text;

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
    private LineChart<String, Integer> weekChart;

    @FXML
    private CategoryAxis weekChartXAxis;

    @FXML
    private LineChart<String, Integer> dayChart;

    @FXML
    private CategoryAxis dayChartXAxis;
    
    private LogPopup logPopup;

    public void setStastics(StasticsModel stasticsModel) {

	dayChartXAxis.setCategories(stasticsModel.getSaleStasticsModel().getXAxisModel(CHART_CATEGORY.DAY));
	weekChartXAxis.setCategories(stasticsModel.getSaleStasticsModel().getXAxisModel(CHART_CATEGORY.WEEK));
	monthChartXAxis.setCategories(stasticsModel.getSaleStasticsModel().getXAxisModel(CHART_CATEGORY.MONTH));
	yearChartXAxis.setCategories(stasticsModel.getSaleStasticsModel().getXAxisModel(CHART_CATEGORY.YEAR));

	dayChart.getData().add(stasticsModel.getSaleStasticsModel().getDayModel());
	weekChart.getData().add(stasticsModel.getSaleStasticsModel().getWeekModel());
	monthChart.getData().add(stasticsModel.getSaleStasticsModel().getMonthModel());
	yearChart.getData().add(stasticsModel.getSaleStasticsModel().getYearModel());

	drawPriceLabel(dayChart.getData().get(0).getData());
	drawPriceLabel(weekChart.getData().get(0).getData());
	drawPriceLabel(monthChart.getData().get(0).getData());
	drawPriceLabel(yearChart.getData().get(0).getData());
	
    }
    
    private void drawPriceLabel(ObservableList<Data<String, Integer>> datas) {
	
	for (Data<String, Integer> data : datas) {

	    try {
		StackPane bar = (StackPane) data.getNode();

		final Text dataText = new Text(String.format("%,d 원", data.getYValue()));
		dataText.setFont(Font.font(9));
		bar.getChildren().add(dataText);

	    } catch (Exception e) {
		e.printStackTrace();
	    }

	}
    }

}
