package PointOfView.Model.Receipt;

import PointOfView.Model.Receipt.ReceiptModel.PAY;
import PointOfView.Order.Table.Model.TableData;
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
