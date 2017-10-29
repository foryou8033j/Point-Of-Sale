package PointOfView.View.Stastics.Menu;

import PointOfView.Models.Menu.Menues;
import PointOfView.Models.Stastics.StasticsModel;
import PointOfView.Util.Dialog.LogPopup;
import javafx.fxml.FXML;
import javafx.scene.chart.BarChart;
import javafx.scene.chart.CategoryAxis;
import javafx.scene.chart.XYChart;
import javafx.scene.chart.XYChart.Data;
import javafx.scene.layout.StackPane;
import javafx.scene.text.Text;
import javafx.stage.Stage;

/**
 * 메뉴 통계 컨트롤러
 * @author Jeongsam
 *
 */
public class MenuStasticsLayoutController {

    @FXML
    private BarChart<String, Integer> menuChart;

    @FXML
    private CategoryAxis menuXAxis;

    private LogPopup logPopup;

    public void setStastics(Menues menues, Stage parents, StasticsModel stasticsModel) {

	menuXAxis.setCategories(stasticsModel.getMenuStasticsModel().getXAxisModel());

	XYChart.Series<String, Integer> series = stasticsModel.getMenuStasticsModel().getMenuModel();
	menuChart.getData().add(series);

	for (Data<String, Integer> data : series.getData()) {

	    try {
		StackPane bar = (StackPane) data.getNode();

		final Text dataText = new Text(data.getYValue() + "");
		bar.getChildren().add(dataText);

		bar.setOnMouseEntered(e -> {

		    if (logPopup == null) {

			int price = menues.getMenuItem(data.getXValue()).getPrice();

			logPopup = new LogPopup(e, String.format("단품 %,d 원", price),
				String.format("총 %,d 원", price * data.getYValue()));
			logPopup.show();
		    }

		});

		bar.setOnMouseExited(e -> {

		    if (logPopup != null) {
			logPopup.close();
			logPopup = null;
		    }

		});

	    } catch (Exception e) {
		e.printStackTrace();
	    }

	}

    }

}
