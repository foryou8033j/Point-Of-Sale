package PointOfView.Models;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.util.Properties;

import PointOfView.Models.Menu.Menues;
import PointOfView.Models.Staff.Staff;

/**
 * 고정 데이터 관리
 * 
 * @author Jeongsam
 *
 */
public class DataManagement {

    private String propertiesFileName = "config.properties";

    private String posTitle = "남천면옥";
    private String adminPassword = "1234";

    private Menues menues = new Menues();
    private Staff staffs = new Staff();

    public DataManagement() {
	loadProperties();
    }

    /**
     * 포스 타이틀을 지정한다.
     * 
     * @param title
     */
    public void setPOSTitle(String title) {
	this.posTitle = title;
    }

    /**
     * 포스 타이틀을 반환한다.
     * 
     * @return {@link String}
     */
    public String getPOSTitle() {
	return posTitle;
    }

    /**
     * 메뉴 목록을 반환한다.
     * 
     * @return
     */
    public Menues getMenues() {
	return menues;
    }

    /**
     * 직원 목록을 반환한다.
     * 
     * @return
     */
    public Staff getStaffs() {
	return staffs;
    }

    /**
     * 관리자 패스워드 옳바른지 확인한다.
     * 
     * @param tryPassword
     *            입력 패스워드
     * @return {@link boolean}
     */
    public boolean getAdminAuthority(String tryPassword) {

	if (tryPassword.equals(adminPassword))
	    return true;

	return false;
    }

    /**
     * 패스워드를 반환받는다.
     * 
     * @return {@link String}
     */
    public String getPassword() {
	return adminPassword;
    }

    /**
     * 관리자 패스워들 설정한다.
     * 
     * @param password
     *            입력 패스워드
     */
    public void setAdminPassword(String password) {
	adminPassword = password;
    }

    public void loadProperties() {

	try {

	    // 프로퍼티 파일 위치
	    String propFile = propertiesFileName;

	    // 프로퍼티 객체 생성
	    Properties props = new Properties();

	    // 프로퍼티 파일 스트림에 담기
	    FileInputStream fis = new FileInputStream(propFile);

	    // 프로퍼티 파일 로딩
	    props.load(new java.io.BufferedInputStream(fis));

	    // 항목 읽기
	    setPOSTitle(props.getProperty("TITLE"));
	    setAdminPassword(props.getProperty("PASSWORD"));

	    // 콘솔 출력
	    System.out.println(getPOSTitle());
	    System.out.println(getPassword());

	} catch (IOException e) {

	}

    }

    public void saveProperties() {
	Properties prop = new Properties();
	prop.setProperty("TITLE", getPOSTitle());
	prop.setProperty("PASSWORD", getPassword());
	try {
	    OutputStream stream = new FileOutputStream(propertiesFileName);
	    prop.store(stream, "POS INFO");
	    stream.close();
	} catch (IOException ex) {
	    ex.printStackTrace();
	}

    }

}
