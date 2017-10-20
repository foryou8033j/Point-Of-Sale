package PointOfView.View.Stastics.Sale;

import PointOfView.Models.Stastics.StasticsModel;
import PointOfView.Models.Stastics.Sale.CHART_CATEGORY;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.chart.BarChart;
import javafx.scene.chart.CategoryAxis;

public class SaleStasticsLayoutController {


    @FXML
    private BarChart<?, ?> yearChart;

    @FXML
    private CategoryAxis yearChartXAxis;

    @FXML
    private BarChart<?, ?> monthChart;

    @FXML
    private CategoryAxis monthChartXAxis;

    @FXML
    private BarChart<?, ?> dayChart;

    @FXML
    private CategoryAxis dayChartXAxis;

    
    void dayChartOpen(ActionEvent event) {

    }

    
    void monthChartOpen(ActionEvent event) {

    }

    
    void weekChartOpen(ActionEvent event) {

    }

    
    void yearChartOpen(ActionEvent event) {

    }
    
    private StasticsModel stasticsModel;
    
    public void setStastics(StasticsModel stasticsModel) {
    	this.stasticsModel = stasticsModel;
    	
    	dayChartXAxis.setCategories(stasticsModel.getSaleStasticsModel().getXAxisModel(CHART_CATEGORY.DAY));
    	monthChartXAxis.setCategories(stasticsModel.getSaleStasticsModel().getXAxisModel(CHART_CATEGORY.MONTH));
    	yearChartXAxis.setCategories(stasticsModel.getSaleStasticsModel().getXAxisModel(CHART_CATEGORY.YEAR));
    }

}
