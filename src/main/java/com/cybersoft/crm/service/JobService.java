package com.cybersoft.crm.service;

import com.cybersoft.crm.entity.JobEntity;
import com.cybersoft.crm.repository.JobRepository;

import java.util.List;

public class JobService {
    private JobRepository jobRepository = new JobRepository();

    public List<JobEntity> getAllJobs() {
        List<JobEntity> jobs = jobRepository.getJobs();
        for (JobEntity job: jobs) {
            job.setStartDate(changeFormatDate(job.getStartDate(), "/"));
            job.setEndDate(changeFormatDate(job.getEndDate(), "/"));
        }
        return jobs;
    }

    public boolean deleteJobById(int id) {
        int result = jobRepository.deleteJobById(id);
        return result > 0;
    }

    public boolean insertJob(JobEntity job) {
        try {
            job.setStartDate(changeFormatDate(job.getStartDate(), "-"));
            job.setEndDate(changeFormatDate(job.getEndDate(), "-"));
            int result = jobRepository.insertJob(job);
            return result > 0;
        } catch (Exception e) {
            return false;
        }
    }

    public JobEntity findJobById(int id) {
        JobEntity job = jobRepository.findJobById(id);
        job.setStartDate(changeFormatDate(job.getStartDate(), "/"));
        job.setEndDate(changeFormatDate(job.getEndDate(), "/"));
        return job;
    }

    public boolean updateJob(JobEntity job) {
        try {
            job.setStartDate(changeFormatDate(job.getStartDate(), "-"));
            job.setEndDate(changeFormatDate(job.getEndDate(), "-"));
            int result = jobRepository.updateJob(job);
            return result > 0;
        } catch (Exception e) {
            return false;
        }
    }

    private String changeFormatDate(String date, String style) {
        switch (style) {
            case "/":
                String[] strings1 = date.split("-");
                return strings1[2] + "/" + strings1[1] + "/" + strings1[0];
            case "-":
                String[] strings2 = date.split("/");
                return strings2[2] + "-" + strings2[1] + "-" + strings2[0];
        }
        return "";
    }
}
