package PointOfView.Models.Stastics;

import PointOfView.Models.Menu.Menues;
import PointOfView.Models.Receipt.Receipt;
import PointOfView.Models.Stastics.Menu.MenuStasticsModel;
import PointOfView.Models.Stastics.Sale.SaleStasticsModel;

public class StasticsModel {

    private Receipt receipt;
    private Menues menues;

    private SaleStasticsModel saleStasticsModel;
    private MenuStasticsModel menuStasticsModel;

    public StasticsModel(Receipt receipt, Menues menues) {

	this.receipt = receipt;
	this.menues = menues;

	
	saleStasticsModel = new SaleStasticsModel(receipt);
	menuStasticsModel = new MenuStasticsModel(menues, receipt);

    }

    /**
     * 매출 정산 모델을 반환한다.
     * 
     * @return SaleStasticsModel
     */
    public SaleStasticsModel getSaleStasticsModel() {
	return saleStasticsModel;
    }

    /**
     * 메뉴 정산 모델을 반환한다.
     * 
     * @return MenuStasticsModel
     */
    public MenuStasticsModel getMenuStasticsModel() {
	return menuStasticsModel;
    }

}
