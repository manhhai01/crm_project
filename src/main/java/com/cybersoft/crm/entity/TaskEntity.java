package com.cybersoft.crm.entity;

public class TaskEntity {
    private int id;
    private String name;
    private String startDate;
    private String endDate;
    private int userId;
    private int jobId;
    private int statusId;

    public TaskEntity() {
    }

    public TaskEntity(int id, String name, String startDate, String endDate, int userId, int jobId, int statusId) {
        this.id = id;
        this.name = name;
        this.startDate = startDate;
        this.endDate = endDate;
        this.userId = userId;
        this.jobId = jobId;
        this.statusId = statusId;
    }

    public TaskEntity(String name, String startDate, String endDate, int userId, int jobId) {
        this.name = name;
        this.startDate = startDate;
        this.endDate = endDate;
        this.userId = userId;
        this.jobId = jobId;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getStartDate() {
        return startDate;
    }

    public void setStartDate(String startDate) {
        this.startDate = startDate;
    }

    public String getEndDate() {
        return endDate;
    }

    public void setEndDate(String endDate) {
        this.endDate = endDate;
    }

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public int getJobId() {
        return jobId;
    }

    public void setJobId(int jobId) {
        this.jobId = jobId;
    }

    public int getStatusId() {
        return statusId;
    }

    public void setStatusId(int statusId) {
        this.statusId = statusId;
    }
}
