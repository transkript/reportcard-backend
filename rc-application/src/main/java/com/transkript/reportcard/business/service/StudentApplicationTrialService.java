package com.transkript.reportcard.business.service;

import com.transkript.reportcard.data.entity.relation.StudentApplicationTrial;

import java.util.List;

public interface StudentApplicationTrialService {
    StudentApplicationTrial getEntity(Long satId);

    List<StudentApplicationTrial> getEntitiesByYear(Long yearId);
}
