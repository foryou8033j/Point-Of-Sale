package PointOfView.Models.Menu;

import java.io.File;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.Marshaller;
import javax.xml.bind.Unmarshaller;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;

public class Menues {
	
	private ObservableList<MenuItem> menuItems = null;
	
	private File file = new File("POS_MenuItem.xml");
	
	public Menues() {
		
		//메뉴 데이터 리스트를 초기화한다.
		menuItems = FXCollections.observableArrayList();
		
		loadDataFromFile();
		
		/*//임시로 메뉴를 추가한다.
		menuItems.addAll(new MenuItem("육개장", "주메뉴", 8000),
				new MenuItem("순대국",  "주메뉴", 5000),
				new MenuItem("공기밥", "주메뉴", 1000),
				new MenuItem("탕수육", "주메뉴", 14000),
				new MenuItem("자장면", "주메뉴", 5000),
				new MenuItem("폭탄피자", "주메뉴", 12000));*/
	}
	
	
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
	    	//ignore
	    }
	}
	
	
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
	
	
	public ObservableList<MenuItem> getMenuItems(){
		return menuItems;
	}
	
}
