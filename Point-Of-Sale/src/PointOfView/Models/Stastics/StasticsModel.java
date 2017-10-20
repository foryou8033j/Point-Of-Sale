package PointOfView.Models.Stastics;

import PointOfView.Models.Menu.Menues;
import PointOfView.Models.Receipt.Receipt;
import PointOfView.Models.Stastics.Sale.SaleStasticsModel;

public class StasticsModel {

	private Receipt receipt;
	private Menues menues;
	
	private SaleStasticsModel saleStasticsModel;
	
	public StasticsModel(Receipt receipt, Menues menues) {

		this.receipt = receipt;
		this.menues = menues;
		
		saleStasticsModel = new SaleStasticsModel(receipt);
		
	}
	
	/**
	 * 매출 정산 모델을 반환한다.
	 * @return
	 */
	public SaleStasticsModel getSaleStasticsModel() {
		return saleStasticsModel;
	}
	
	
	
	
}
