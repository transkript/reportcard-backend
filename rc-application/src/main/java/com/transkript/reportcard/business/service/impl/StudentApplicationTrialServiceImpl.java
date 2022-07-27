package com.transkript.reportcard.business.service.impl;

import com.transkript.reportcard.business.service.i.AcademicYearService;
import com.transkript.reportcard.business.service.i.StudentApplicationTrialService;
import com.transkript.reportcard.config.constants.EntityName;
import com.transkript.reportcard.data.entity.relation.StudentApplicationTrial;
import com.transkript.reportcard.data.repository.StudentApplicationTrialRepository;
import com.transkript.reportcard.exception.EntityException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class StudentApplicationTrialServiceImpl implements StudentApplicationTrialService {
    private final StudentApplicationTrialRepository studentApplicationTrialRepository;
    private final AcademicYearService academicYearService;

    @Override
    public StudentApplicationTrial getEntity(Long satId) {
        return studentApplicationTrialRepository.findById(satId).orElseThrow(() -> new EntityException.NotFound(EntityName.STUDENT_APPLICATION_TRIAL, satId));
    }

    @Override
    public List<StudentApplicationTrial> getEntitiesByYear(Long yearId) {
        return studentApplicationTrialRepository.findAllByAcademicYear(academicYearService.getAcademicYearEntity(yearId));
    }
}
