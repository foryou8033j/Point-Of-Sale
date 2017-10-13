package PointOfView.Models.Receipt;

import PointOfView.Models.Table.TableData;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

/**
 * 영수증 데이터를 관리한다.
 * @author Jeongsam
 *
 */
public class Receipt {
	
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
	public void addReceiptList(PAY_WAY payWay, TableData tableData) {
		receiptList.add(new ReceiptModel(payWay, tableData));
	}
}
