package com.github.professor_x_web.model;

import java.util.Date;

public class Data {

    private Integer id;

    private Integer reportId;

    private String title;

    private Integer concurrency;

    private Integer sampleSize;

    private Integer messageSize;

    private Double averageRt;

    private Integer minRt;

    private Integer maxRt;

    private Double tps;

    private Double errorRate;

    private Date createTime;

    private String startTime;

    private String endTime;

    private String topic;

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

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title == null ? null : title.trim();
    }

    public Integer getConcurrency() {
        return concurrency;
    }

    public void setConcurrency(Integer concurrency) {
        this.concurrency = concurrency;
    }

    public Integer getSampleSize() {
        return sampleSize;
    }

    public void setSampleSize(Integer sampleSize) {
        this.sampleSize = sampleSize;
    }

    public Integer getMessageSize() {
        return messageSize;
    }

    public void setMessageSize(Integer messageSize) {
        this.messageSize = messageSize;
    }

    public Double getAverageRt() {
        return averageRt;
    }

    public void setAverageRt(Double averageRt) {
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

    public Double getTps() {
        return tps;
    }

    public void setTps(Double tps) {
        this.tps = tps;
    }

    public Double getErrorRate() {
        return errorRate;
    }

    public void setErrorRate(Double errorRate) {
        this.errorRate = errorRate;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
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

    public String getTopic() {
        return topic;
    }

    public void setTopic(String topic) {
        this.topic = topic;
    }

}
