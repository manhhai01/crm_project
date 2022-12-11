package com.cybersoft.crm.service;

import com.cybersoft.crm.entity.JobEntity;
import com.cybersoft.crm.entity.TaskEntity;
import com.cybersoft.crm.entity.UserEntity;
import com.cybersoft.crm.model.JobDetailsModel;
import com.cybersoft.crm.repository.JobRepository;
import com.cybersoft.crm.utils.DateHelper;

import java.util.ArrayList;
import java.util.List;

public class JobService {
    private JobRepository jobRepository = new JobRepository();
    private DateHelper dateHelper = new DateHelper();

    public List<JobEntity> getAllJobs() {
        List<JobEntity> jobs = jobRepository.getJobs();
        for (JobEntity job: jobs) {
            job.setStartDate(dateHelper.changeFormatDate(job.getStartDate(), "/"));
            job.setEndDate(dateHelper.changeFormatDate(job.getEndDate(), "/"));
        }
        return jobs;
    }

    public boolean deleteJobById(int id) {
        int result = jobRepository.deleteJobById(id);
        return result > 0;
    }

    public boolean insertJob(JobEntity job) {
        try {
            job.setStartDate(dateHelper.changeFormatDate(job.getStartDate(), "-"));
            job.setEndDate(dateHelper.changeFormatDate(job.getEndDate(), "-"));
            int result = jobRepository.insertJob(job);
            return result > 0;
        } catch (Exception e) {
            return false;
        }
    }

    public JobEntity findJobById(int id) {
        JobEntity job = jobRepository.findJobById(id);
        job.setStartDate(dateHelper.changeFormatDate(job.getStartDate(), "/"));
        job.setEndDate(dateHelper.changeFormatDate(job.getEndDate(), "/"));
        return job;
    }

    public boolean updateJob(JobEntity job) {
        try {
            job.setStartDate(dateHelper.changeFormatDate(job.getStartDate(), "-"));
            job.setEndDate(dateHelper.changeFormatDate(job.getEndDate(), "-"));
            int result = jobRepository.updateJob(job);
            return result > 0;
        } catch (Exception e) {
            return false;
        }
    }

    public List<JobDetailsModel> getDetailsByJobId(int jobId) {
        List<JobDetailsModel> jobDetails = new ArrayList<>();

        List<UserEntity> users = jobRepository.getUserByJobId(jobId);
        for (UserEntity user: users) {
            JobDetailsModel jobDetail = new JobDetailsModel();
            jobDetail.setIdUser(user.getId());
            jobDetail.setUserName(user.getFirstName() + " " + user.getLastName());
            List<TaskEntity> tasks = jobRepository.getTaskByJobIdAndUserId(jobId, user.getId());
            jobDetail.setTaskEntities(tasks);

            jobDetails.add(jobDetail);
        }
        return jobDetails;
    }
}
