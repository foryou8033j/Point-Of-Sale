package PointOfView.Models.Menu;

import java.io.File;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.Marshaller;
import javax.xml.bind.Unmarshaller;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;

/**
 * MenuItem 통합 클래스
 * 
 * @author Jeongsam
 *
 */
public class Menues {

    private ObservableList<MenuItem> menuItems = null;

    private File file = new File("POS_MenuItem.xml");

    public Menues() {

	// 메뉴 데이터 리스트를 초기화한다.
	menuItems = FXCollections.observableArrayList();

	loadDataFromFile();

    }

    /**
     * 파일을 불러온다.
     */
    public void loadDataFromFile() {

	try {

	    JAXBContext context = JAXBContext.newInstance(MenuItemDataWrapper.class);
	    Unmarshaller um = context.createUnmarshaller();

	    // 파일로부터 XML을 읽은 다음 역 마샬링한다.
	    MenuItemDataWrapper wrapper = new MenuItemDataWrapper();
	    wrapper = (MenuItemDataWrapper) um.unmarshal(file);

	    menuItems.clear();
	    menuItems.addAll(wrapper.getDatas());

	} catch (Exception e) { // 예외를 잡는다
	    // ignore
	}
    }

    /**
     * 파일을 저장한다.
     */
    public void saveDataToFile() {
	try {
	    JAXBContext context = JAXBContext.newInstance(MenuItemDataWrapper.class);
	    Marshaller m = context.createMarshaller();
	    m.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, true);

	    // 연락처 데이터를 감싼다.
	    MenuItemDataWrapper wrapper = new MenuItemDataWrapper();
	    wrapper.setDatas(menuItems);

	    // 마샬링 후 XML을 파일에 저장한다.
	    m.marshal(wrapper, file);

	} catch (Exception e) { // 예외를 잡는다.
	    e.printStackTrace();
	    Alert alert = new Alert(AlertType.ERROR);
	    alert.setTitle("Error");
	    alert.setHeaderText("Could not save data");
	    alert.setContentText("Could not save data to file:\n" + file.getPath());

	    alert.showAndWait();
	}
    }

    /**
     * {@link MenuItem} 반환
     * 
     * @return {@link MenuItem}
     */
    public ObservableList<MenuItem> getMenuItems() {
	return menuItems;
    }

    /**
     * {@link MenuItem} 반환
     * 
     * @param name
     *            String
     * @return {@link MenuItem}
     */
    public MenuItem getMenuItem(String name) {
	for (MenuItem item : menuItems) {
	    if (item.getName().equals(name))
		return item;
	}

	return null;
    }

}
