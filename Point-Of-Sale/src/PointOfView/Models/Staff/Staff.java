package PointOfView.Models.Staff;

import java.util.ArrayList;
import java.util.List;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

public class Staff {

    private ObservableList<StaffModel> staffModels = FXCollections.observableArrayList();

    private List<String> jobCategory = new ArrayList<String>();
    private List<String> jobPart = new ArrayList<String>();
    
    private int payPerHour = 6550;

    public Staff() {
	initJobCategory();
	initJobPart();
	
	staffModels.add(new StaffModel("서정삼", "사장", "사무실"));
	staffModels.add(new StaffModel("김두상", "부사장", "관리"));
	staffModels.add(new StaffModel("김영우", "직원", "주방"));
	staffModels.add(new StaffModel("조수정", "아르바이트", "홀"));
	
    }

    private void initJobCategory() {
	jobCategory.add("사장");
	jobCategory.add("부사장");
	jobCategory.add("직원");
	jobCategory.add("아르바이트");
	jobCategory.add("용역");
	jobCategory.add("일당");
	jobCategory.add("대타");
    }

    private void initJobPart() {
	jobPart.add("홀");
	jobPart.add("주방");
	jobPart.add("사무실");
	jobPart.add("배달");
	jobPart.add("홍보");
	jobPart.add("관리");
	jobPart.add("매니저");
    }
    
    public List<String> getJobCategory() {
	return jobCategory;
    }
    
    public List<String> getJobPart(){
	return jobPart;
    }
    
    public ObservableList<StaffModel> getStaffDatas(){
	return staffModels;
    }
    
    public int getPayPerHour() {
	return payPerHour;
    }
    
    public void setPayPerHour(int pay) {
	payPerHour = pay;
    }

}
