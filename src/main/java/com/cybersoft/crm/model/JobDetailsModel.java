package com.cybersoft.crm.model;

import com.cybersoft.crm.entity.TaskEntity;

import java.util.List;

public class JobDetailsModel {
   private int idUser;
   private String userName;
   List<TaskEntity> taskEntities;

    public JobDetailsModel() {
    }

    public JobDetailsModel(int idUser, String userName, List<TaskEntity> taskEntities) {
        this.idUser = idUser;
        this.userName = userName;
        this.taskEntities = taskEntities;
    }

    public int getIdUser() {
        return idUser;
    }

    public void setIdUser(int idUser) {
        this.idUser = idUser;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public List<TaskEntity> getTaskEntities() {
        return taskEntities;
    }

    public void setTaskEntities(List<TaskEntity> taskEntities) {
        this.taskEntities = taskEntities;
    }
}
