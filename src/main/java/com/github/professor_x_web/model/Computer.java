package com.github.professor_x_web.model;

import java.util.Date;

public class Computer {

    private Integer id;

    private Integer userId;

    private Integer cpuId;

    private Integer memId;

    private Integer distId;

    private Integer solidDistId;

    private Integer networkCardId;

    private String describe;

    private Date createTime;

    private Boolean used = false;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }

    public Integer getCpuId() {
        return cpuId;
    }

    public void setCpuId(Integer cpuId) {
        this.cpuId = cpuId;
    }

    public Integer getMemId() {
        return memId;
    }

    public void setMemId(Integer memId) {
        this.memId = memId;
    }

    public Integer getDistId() {
        return distId;
    }

    public void setDistId(Integer distId) {
        this.distId = distId;
    }

    public Integer getSolidDistId() {
        return solidDistId;
    }

    public void setSolidDistId(Integer solidDistId) {
        this.solidDistId = solidDistId;
    }

    public Integer getNetworkCardId() {
        return networkCardId;
    }

    public void setNetworkCardId(Integer networkCardId) {
        this.networkCardId = networkCardId;
    }

    public String getDescribe() {
        return describe;
    }

    public void setDescribe(String describe) {
        this.describe = describe;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public Boolean getUsed() {
        return used;
    }

    public void setUsed(Boolean used) {
        this.used = used;
    }

}
