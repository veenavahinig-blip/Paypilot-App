package com.hcl.paypilot.dto;


import java.util.List;


import com.hcl.paypilot.entity.BillEntity;


public class UserDashboardDTO {


    private String userId;


    private String userName;


    private String userEmail;


    private Integer totalBills;


    private Integer upcomingBills;


    private Integer overdueBills;


    private Integer paidBills;


    private List<BillEntity> recentBills;


    public UserDashboardDTO() {

    }


    public String getUserId() {

        return userId;

    }


    public void setUserId(String userId) {

        this.userId = userId;

    }


    public String getUserName() {

        return userName;

    }


    public void setUserName(String userName) {

        this.userName = userName;

    }


    public String getUserEmail() {

        return userEmail;

    }


    public void setUserEmail(String userEmail) {

        this.userEmail = userEmail;

    }


    public Integer getTotalBills() {

        return totalBills;

    }


    public void setTotalBills(Integer totalBills) {

        this.totalBills = totalBills;

    }


    public Integer getUpcomingBills() {

        return upcomingBills;

    }


    public void setUpcomingBills(Integer upcomingBills) {

        this.upcomingBills = upcomingBills;

    }


    public Integer getOverdueBills() {

        return overdueBills;

    }


    public void setOverdueBills(Integer overdueBills) {

        this.overdueBills = overdueBills;

    }


    public Integer getPaidBills() {

        return paidBills;

    }


    public void setPaidBills(Integer paidBills) {

        this.paidBills = paidBills;

    }


    public List<BillEntity> getRecentBills() {

        return recentBills;

    }


    public void setRecentBills(List<BillEntity> recentBills) {

        this.recentBills = recentBills;

    }

}
 