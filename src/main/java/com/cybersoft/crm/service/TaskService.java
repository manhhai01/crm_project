package com.cybersoft.crm.service;

import com.cybersoft.crm.entity.TaskEntity;
import com.cybersoft.crm.model.TaskModel;
import com.cybersoft.crm.repository.TaskRepository;
import com.cybersoft.crm.utils.DateHelper;

import java.util.List;

public class TaskService {

    private TaskRepository taskRepository = new TaskRepository();
    private DateHelper dateHelper = new DateHelper();

    public List<TaskModel> getAllTasks() {
        List<TaskModel> tasks = taskRepository.getTasks();
        for (TaskModel task: tasks) {
            task.setStartDate(dateHelper.changeFormatDate(task.getStartDate(), "/"));
            task.setEndDate(dateHelper.changeFormatDate(task.getEndDate(), "/"));
        }
        return tasks;
    }

    public boolean deleteTaskById(int id) {
        int result = taskRepository.deleteTaskById(id);
        return result > 0;
    }

    public boolean insertTask(TaskEntity task) {
        try {
            task.setStartDate(dateHelper.changeFormatDate(task.getStartDate(), "-"));
            task.setEndDate(dateHelper.changeFormatDate(task.getEndDate(), "-"));
            int result = taskRepository.insertTask(task);
            return result > 0;
        } catch (Exception e) {
            return false;
        }
    }

    public TaskEntity findTaskById(int id) {
        TaskEntity task = taskRepository.findTaskById(id);
        task.setStartDate(dateHelper.changeFormatDate(task.getStartDate(), "/"));
        task.setEndDate((dateHelper.changeFormatDate(task.getEndDate(), "/")));
        return task;
    }

    public boolean updateTask(TaskEntity task) {
        try {
            task.setStartDate(dateHelper.changeFormatDate(task.getStartDate(), "-"));
            task.setEndDate(dateHelper.changeFormatDate(task.getEndDate(), "-"));
            int result = taskRepository.updateTask(task);
            return result > 0;
        } catch (Exception e) {
            return false;
        }
    }

    public List<TaskEntity> findTasksByUserId(int userId) {
        List<TaskEntity> tasks = taskRepository.findTaskByUserId(userId);
        for (TaskEntity task: tasks) {
            task.setStartDate(dateHelper.changeFormatDate(task.getEndDate(), "/"));
            task.setEndDate(dateHelper.changeFormatDate(task.getEndDate(), "/"));
        }
        return tasks;
    }

}
