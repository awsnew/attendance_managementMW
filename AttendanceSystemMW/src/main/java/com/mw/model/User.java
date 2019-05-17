package com.mw.model;

import java.util.ArrayList;
import java.util.List;


public class User {
    
    private String userId;
    private String firstName;
    private String lastName;
    
    private List<UserAttendance> userAttendance=new ArrayList<UserAttendance>();

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public List<UserAttendance> getUserAttendance() {
        return userAttendance;
    }

    public void setUserAttendance(List<UserAttendance> userAttendance) {
        this.userAttendance = userAttendance;
    }
    
}
