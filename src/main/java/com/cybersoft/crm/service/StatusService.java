package com.cybersoft.crm.service;

import com.cybersoft.crm.entity.StatusEntity;
import com.cybersoft.crm.repository.StatusRepository;

import java.util.List;

public class StatusService {

    private StatusRepository statusRepository = new StatusRepository();

    public List<StatusEntity> getAllStatuses() {
        return statusRepository.getStatuses();
    }
}
