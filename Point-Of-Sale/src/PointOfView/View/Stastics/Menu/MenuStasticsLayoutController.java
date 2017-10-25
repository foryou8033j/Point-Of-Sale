package PointOfView.View.Stastics.Menu;

import PointOfView.Models.Stastics.StasticsModel;
import javafx.fxml.FXML;
import javafx.scene.chart.BarChart;
import javafx.scene.chart.CategoryAxis;

public class MenuStasticsLayoutController {

    @FXML
    private BarChart<String, Integer> menuChart;

    @FXML
    private CategoryAxis menuXAxis;

    public void setStastics(StasticsModel stasticsModel) {


	menuXAxis.setCategories(stasticsModel.getMenuStasticsModel().getXAxisModel());
	menuChart.getData().add(stasticsModel.getMenuStasticsModel().getYearModel());

    }
}
