package sample.Model;

import java.util.Calendar;

public class User {

    private int userid;
    private String userName;
    private String password;
    private boolean isActive;
    private Calendar createDate;
    private String createdBy;
    private Calendar lastUpdated;
    private String lastUpdatedBy;


    public User(int userid, String userName, String password, boolean isActive,
                    Calendar createDate, String createdBy, Calendar lastUpdated, String lastUpdatedBy){
        this.userid = userid;
        this.userName = userName;
        this.password = password;
        this.isActive = isActive;
        this.createDate = createDate;
        this.createdBy = createdBy;
        this.lastUpdated = lastUpdated;
        this.lastUpdatedBy = lastUpdatedBy;

    }

    public int getUserid(){
        return userid;
    }

    public void setUserid(int userid) {
        this.userid = userid;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getPassword(){
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public boolean getIsActive(){
        return isActive;
    }

    public void setActive(boolean active) {
        isActive = active;
    }

    public Calendar getCreateDate() {
        return createDate;
    }

    public void setCreateDate(Calendar createDate) {
        this.createDate = createDate;
    }

    public void setCreatedBy(String createdBy) {
        this.createdBy = createdBy;
    }

    public String getCreatedBy(){
        return createdBy;
    }

    public Calendar getLastUpdated() {
        return lastUpdated;
    }

    public void setLastUpdated(Calendar lastUpdated) {
        this.lastUpdated = lastUpdated;
    }

    public String getLastUpdatedBy() {
        return lastUpdatedBy;
    }

    public void setLastUpdatedBy(String lastUpdatedBy) {
        this.lastUpdatedBy = lastUpdatedBy;
    }




}

