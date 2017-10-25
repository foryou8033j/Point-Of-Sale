package PointOfView.Models.Table;

import java.io.File;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.Marshaller;
import javax.xml.bind.Unmarshaller;

import PointOfView.Models.OrderList.OrderList;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;

public class Tables {
	
	private File file = new File("POS_Tables.xml");
	private ObservableList<TableData> tableDatas;

	public Tables() {
		tableDatas = FXCollections.observableArrayList();
		
		loadDataFromFile();
		
	}
	
	public ObservableList<TableData> getTableDatas(){
		return tableDatas;
	}
	
	public int getSize(){
		return tableDatas.size();
	}
	
	public void addTable(int column, int row){
		tableDatas.add(new TableData(getSize(), column, row));
	}
	
	public void removeTable(TableData tableData) {
		tableDatas.remove(tableData);
		reArangeTableIndex();
	}
	
	public void removeTable(int index) {
		tableDatas.remove(index);
		reArangeTableIndex();
	}
	
	public void removeTable(int column, int row) {
		
		for(TableData data:tableDatas) {
			if(data.getColumn()==column && data.getRow()==row) {
				tableDatas.remove(data);
				break;
			}
		}
		reArangeTableIndex();
	}
	
	private void reArangeTableIndex() {
		
		for(int i=0; i<getSize(); i++) {
			tableDatas.get(i).setTableIndex(i);
		}
	}
	
	
public void loadDataFromFile() {
	    
		
		try {
			
	        JAXBContext context = JAXBContext.newInstance(TableDataWrapper.class);
	        Unmarshaller um = context.createUnmarshaller();

	        // 파일로부터 XML을 읽은 다음 역 마샬링한다.
	        TableDataWrapper wrapper = new TableDataWrapper();
	        wrapper = (TableDataWrapper) um.unmarshal(file);

	        tableDatas.clear();
	        tableDatas.addAll(wrapper.getTableDatas());


	    } catch (Exception e) { // 예외를 잡는다
	    	e.printStackTrace();
	    }
	}
	
	
	public void saveDataToFile() {
	    try {

	        JAXBContext context = JAXBContext.newInstance(TableDataWrapper.class);
	        Marshaller m = context.createMarshaller();
	        m.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, true);

	        // 연락처 데이터를 감싼다.
	        TableDataWrapper wrapper = new TableDataWrapper();
	        wrapper.setDatas(tableDatas);

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
	
}
