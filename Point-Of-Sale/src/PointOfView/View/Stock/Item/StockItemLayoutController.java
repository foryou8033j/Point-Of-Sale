package PointOfView.View.Stock.Item;

import PointOfView.Models.Menu.MenuItem;
import PointOfView.Models.Menu.Menues;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.layout.GridPane;
import javafx.scene.text.Text;

public class StockItemLayoutController {

    @FXML
    private GridPane pane;

    @FXML
    private Text name;

    @FXML
    private Text count;
    
    private MenuItem item;
    private Menues menu;

    @FXML
    void handleLess(ActionEvent event) {
	
	if(item.getStock() == 0 ) 
	    return;
	
	item.setStock(item.getStock() - 1);
	count.setText(String.valueOf(item.getStock()));
	
	save();
    }

    @FXML
    void handleMore(ActionEvent event) {
	item.setStock(item.getStock() + 1);
	count.setText(String.valueOf(item.getStock()));
	
	save();
    }
    
    private void save() {
	menu.saveDataToFile();
    }
    
    public void setMenuItem(Menues menu, MenuItem item) {

	this.menu = menu;
	this.item = item;
	
	name.setText(item.getName());
	count.setText(String.valueOf(item.getStock()));
    }

}
