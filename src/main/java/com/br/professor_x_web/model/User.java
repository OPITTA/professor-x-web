package com.br.professor_x_web.model;

import java.util.Date;

public class User {

    private Integer id;

    private String account;

    private String passwd;

    private String email;

    private Integer userRole;

    private Integer platformId;

    private Integer valied;

    private Date createTime;

    private String againPasswd;

    private String startTime;

    private String endTime;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getAccount() {
        return account;
    }

    public void setAccount(String account) {
        this.account = account == null ? null : account.trim();
    }

    public String getPasswd() {
        return passwd;
    }

    public void setPasswd(String passwd) {
        this.passwd = passwd == null ? null : passwd.trim();
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email == null ? null : email.trim();
    }

    public Integer getUserRole() {
        return userRole;
    }

    public void setUserRole(Integer userRole) {
        this.userRole = userRole;
    }

    public Integer getPlatformId() {
        return platformId;
    }

    public void setPlatformId(Integer platformId) {
        this.platformId = platformId;
    }

    public Integer getValied() {
        return valied;
    }

    public void setValied(Integer valied) {
        this.valied = valied;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public String getAgainPasswd() {
        return againPasswd;
    }

    public void setAgainPasswd(String againPasswd) {
        this.againPasswd = againPasswd;
    }

    public boolean againPasswdOk() {
        boolean isOk = true;
        if (this.passwd == null) {
            isOk = false;
        }
        if (this.againPasswd == null) {
            isOk = false;
        }
        if (!passwd.equalsIgnoreCase(againPasswd)) {
            isOk = false;
        }
        return isOk;
    }

    public String getStartTime() {
        return startTime;
    }

    public void setStartTime(String startTime) {
        this.startTime = startTime;
    }

    public String getEndTime() {
        return endTime;
    }

    public void setEndTime(String endTime) {
        this.endTime = endTime;
    }

}
