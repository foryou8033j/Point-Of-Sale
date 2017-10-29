package PointOfView.Models.Receipt;

import java.io.File;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.Marshaller;
import javax.xml.bind.Unmarshaller;

import PointOfView.Models.Menu.MenuItemDataWrapper;
import PointOfView.Models.Table.TableData;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;

/**
 * 영수증 데이터를 관리한다.
 * 
 * @author Jeongsam
 *
 */
public class Receipt {

    /**
     * 카드사 정보를 구현 할 InnerClass를 Static 으로 선언한다.
     * 
     * @author Jeongsam
     *
     */
    public static class CardCompany {

	private static Map<String, String> cardCompany = new HashMap<String, String>();

	static {
	    cardCompany.put("KB국민카드", "KB국민카드체크");
	    cardCompany.put("KB국민카드", "KB국민카드신용");
	    cardCompany.put("신한카드", "KB신한카드체크");
	    cardCompany.put("신한카드", "KB신한카드신용");
	    cardCompany.put("NH농협", "NH농협체크");
	    cardCompany.put("NH농협", "NH농협신용");
	    cardCompany.put("카카오뱅크", "카카오뱅크체크");
	    cardCompany.put("K뱅크", "K뱅크체크");
	    cardCompany.put("대구은행", "대구은행체크");
	    cardCompany.put("대구은행", "대구은행신용");
	    cardCompany.put("우체국", "우체국여신체크");
	    cardCompany.put("IBK금융", "IBK체크");
	    cardCompany.put("IBK금융", "IBK신용");
	}

	/**
	 * 카드사를 반환한다
	 * 
	 * @param i
	 *            인덱스
	 * @return 카드사명
	 */
	public static String getCompany(int i) {
	    List keys = new ArrayList(cardCompany.keySet());
	    return (String) keys.get(i);
	}

	/**
	 * 카드 이름을 반환한다.
	 * 
	 * @param name
	 *            카드사
	 * @return 카드 이름
	 */
	public static String getCardName(String name) {
	    return cardCompany.get(name);
	}

	/**
	 * Map 사이즈를 반환한다.
	 * 
	 * @return int
	 */
	public static int getSize() {
	    return cardCompany.size();
	}

    }

    private File file = new File("POS_Receipt.xml");
    private ObservableList<ReceiptModel> receiptList;

    public Receipt() {
	receiptList = FXCollections.observableArrayList();

	loadDataFromFile();

	// JAXB는 기본 생성자에서만 동작하기 때문에 객체 생성 후 Property 작업을 해줘야한다.
	for (ReceiptModel item : receiptList)
	    item.makeCell();
    }

    /**
     * 결제 영수증 목록을 반환한다.
     * 
     * @return
     */
    public ObservableList<ReceiptModel> getReceiptList() {
	return receiptList;
    }

    /**
     * 영수증을 추가한다.
     * 
     * @param payWay
     * @param tableData
     */
    public void addReceiptList(PAY_WAY payWay, TableData tableData, int returnedCash) {

	int index = receiptList.size();
	receiptList.add(new ReceiptModel(index, payWay, tableData, returnedCash));
	saveDataToFile();
    }

    /**
     * 영수증을 추가한다.
     * 
     * @param payWay
     * @param tableData
     */
    public void addReceiptList(PAY_WAY payWay, TableData tableData) {

	int index = receiptList.size();
	receiptList.add(new ReceiptModel(index, payWay, tableData));
	saveDataToFile();
    }

    public void loadDataFromFile() {

	try {

	    JAXBContext context = JAXBContext.newInstance(ReceiptDataWrapper.class);
	    Unmarshaller um = context.createUnmarshaller();

	    // 파일로부터 XML을 읽은 다음 역 마샬링한다.
	    ReceiptDataWrapper wrapper = new ReceiptDataWrapper();
	    wrapper = (ReceiptDataWrapper) um.unmarshal(file);

	    receiptList.clear();
	    receiptList.addAll(wrapper.getDatas());

	} catch (Exception e) { // 예외를 잡는다
	    // ignore
	}
    }

    public void saveDataToFile() {
	try {
	    JAXBContext context = JAXBContext.newInstance(ReceiptDataWrapper.class);
	    Marshaller m = context.createMarshaller();
	    m.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, true);

	    // 연락처 데이터를 감싼다.
	    ReceiptDataWrapper wrapper = new ReceiptDataWrapper();
	    wrapper.setDatas(receiptList);

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
