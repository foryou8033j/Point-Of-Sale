package PointOfView.Models.Receipt;

import PointOfView.Models.Receipt.ReceiptModel.PAY;
import PointOfView.Models.Table.TableData;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

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
	
	public void addReceiptList(PAY payWay, TableData tableData) {
		receiptList.add(new ReceiptModel(payWay, tableData));
	}
}
