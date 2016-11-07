package com.github.professor_x_web.model;

import java.util.Date;

public class Data {

    private Integer id;

    private Integer reportId;

    private Integer averageRt;

    private Integer minRt;

    private Integer maxRt;

    private Integer tps;

    private Integer concurrency;

    private Integer errorRate;

    private Date createTime;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getReportId() {
        return reportId;
    }

    public void setReportId(Integer reportId) {
        this.reportId = reportId;
    }

    public Integer getAverageRt() {
        return averageRt;
    }

    public void setAverageRt(Integer averageRt) {
        this.averageRt = averageRt;
    }

    public Integer getMinRt() {
        return minRt;
    }

    public void setMinRt(Integer minRt) {
        this.minRt = minRt;
    }

    public Integer getMaxRt() {
        return maxRt;
    }

    public void setMaxRt(Integer maxRt) {
        this.maxRt = maxRt;
    }

    public Integer getTps() {
        return tps;
    }

    public void setTps(Integer tps) {
        this.tps = tps;
    }

    public Integer getConcurrency() {
        return concurrency;
    }

    public void setConcurrency(Integer concurrency) {
        this.concurrency = concurrency;
    }

    public Integer getErrorRate() {
        return errorRate;
    }

    public void setErrorRate(Integer errorRate) {
        this.errorRate = errorRate;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }
}
