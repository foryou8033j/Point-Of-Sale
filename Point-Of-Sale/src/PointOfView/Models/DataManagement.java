package PointOfView.Models;

import PointOfView.Models.Menu.Menues;
import PointOfView.Models.Stastics.StasticsModel;
import PointOfView.Models.Stastics.Sale.SaleStasticsModel;

public class DataManagement {

	private String posTitle = "남천면옥";
	private String adminPassword = "1234";
	
	private Menues menues = new Menues();
	

	/**
	 * 포스 타이틀을 지정한다.
	 * @param title
	 */
	public void setPOSTitle(String title) {
		this.posTitle = title;
	}
	
	/**
	 * 포스 타이틀을 반환한다.
	 * @return {@link String}
	 */
	public String getPOSTitle() {
		return posTitle;
	}
	
	/**
	 * 메뉴 목록을 반환한다.
	 * @return
	 */
	public Menues getMenues() {
		return menues;
	}
	
	/**
	 * 관리자 패스워드 옳바른지 확인한다.
	 * @param tryPassword 입력 패스워드
	 * @return {@link boolean}
	 */
	public boolean getAdminAuthority(String tryPassword){
		
		if(tryPassword.equals(adminPassword))
			return true;
		
		return false;
	}
	
	/**
	 * 패스워드를 반환받는다.
	 * @return {@link String}
	 */
	public String getPassword() {
		return adminPassword;
	}
	
	/**
	 * 관리자 패스워들 설정한다.
	 * @param password 입력 패스워드
	 */
	public void setAdminPassword(String password){
		adminPassword = password;
	}
}
