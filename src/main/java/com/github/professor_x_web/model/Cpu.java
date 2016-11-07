package com.github.professor_x_web.model;

import java.util.Date;

public class Cpu {

    private Integer id;

    private String model;

    private Float frequency;

    private Integer coreNum;

    private String describe;

    private Date createTime;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getModel() {
        return model;
    }

    public void setModel(String model) {
        this.model = model == null ? null : model.trim();
    }

    public Float getFrequency() {
        return frequency;
    }

    public void setFrequency(Float frequency) {
        this.frequency = frequency;
    }

    public Integer getCoreNum() {
        return coreNum;
    }

    public void setCoreNum(Integer coreNum) {
        this.coreNum = coreNum;
    }

    public String getDescribe() {
        return describe;
    }

    public void setDescribe(String describe) {
        this.describe = describe == null ? null : describe.trim();
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }
}
