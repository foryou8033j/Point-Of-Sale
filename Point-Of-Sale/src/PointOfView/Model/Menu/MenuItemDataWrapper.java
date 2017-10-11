package PointOfView.Model.Menu;

import java.util.List;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlElements;
import javax.xml.bind.annotation.XmlRootElement;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

@XmlRootElement(name = "Menuitems")	
public class MenuItemDataWrapper {
	
	private List<MenuItem> menuitem = FXCollections.<MenuItem>observableArrayList();;

	@XmlElements ( { @XmlElement ( name = "menuitem", type = MenuItem.class)})
    public List<MenuItem> getDatas() {
        return menuitem;
    }

    public void setDatas(ObservableList<MenuItem> menuitem) {
        this.menuitem = (List) menuitem;
    }
}
