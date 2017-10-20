package PointOfView.Models.Receipt;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import PointOfView.Models.Table.TableData;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

/**
 * 영수증 데이터를 관리한다.
 * @author Jeongsam
 *
 */
public class Receipt {
	
	//카드사 정보를 구현할 InnerClass, Static으로 선언한다
	public static class CardCompany {
		
		private static Map<String, String> cardCompany = new HashMap<String, String>(); 
		
		static
	    {
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
		 * @param	i	인덱스
		 * @return 		카드사명
		 */
		public static String getCompany(int i) {
			List keys = new ArrayList(cardCompany.keySet());
			return (String)keys.get(i);
		}
		
		/**
		 * 카드 이름을 반환한다.
		 * @param	name	카드사
		 * @return			카드 이름
		 */
		public static String getCardName(String name) {
			return cardCompany.get(name);
		}
		
		/**
		 * Map 사이즈를 반환한다.
		 * @return int
		 */
		public static int getSize() {
			return cardCompany.size();
		}
		
		
		
	}
	
	private ObservableList<ReceiptModel> receiptList;
	
	public Receipt() {
		receiptList = FXCollections.observableArrayList();
	}
	
	/**
	 * 결제 영수증 목록을 반환한다.
	 * @return
	 */
	public ObservableList<ReceiptModel> getReceiptList(){
		return receiptList;
	}
	
	/**
	 * 영수증을 추가한다.
	 * @param payWay
	 * @param tableData
	 */
	public void addReceiptList(PAY_WAY payWay, TableData tableData, int returnedCash) {
		
		int index = receiptList.size();
		receiptList.add(new ReceiptModel(index, payWay, tableData, returnedCash));
	}
	
	/**
	 * 영수증을 추가한다.
	 * @param payWay
	 * @param tableData
	 */
	public void addReceiptList(PAY_WAY payWay, TableData tableData) {
		
		int index = receiptList.size();
		receiptList.add(new ReceiptModel(index, payWay, tableData));
	}
}
