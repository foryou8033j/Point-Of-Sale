package PointOfView.Models;

import PointOfView.Models.Menu.Menues;

public class DataManagement {

	private String posTitle = "뀨뀨까까 레스토랑";
	private String adminPassword = "1234";
	
	private Menues menues = new Menues();

	
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
	 * 관리자 패스워들 설정한다.
	 * @param password 입력 패스워드
	 */
	public void setAdminPassword(String password){
		adminPassword = password;
	}
}
