package com.transkript.reportcard.business.service.impl;

import com.transkript.reportcard.api.dto.StudentApplicationTrialDto;
import com.transkript.reportcard.business.mapper.StudentApplicationTrialMapper;
import com.transkript.reportcard.business.service.i.AcademicYearService;
import com.transkript.reportcard.business.service.i.StudentApplicationTrialService;
import com.transkript.reportcard.config.constants.EntityName;
import com.transkript.reportcard.data.entity.AcademicYear;
import com.transkript.reportcard.data.entity.relation.StudentApplicationTrial;
import com.transkript.reportcard.data.repository.StudentApplicationTrialRepository;
import com.transkript.reportcard.exception.EntityException;
import lombok.RequiredArgsConstructor;
import org.jetbrains.annotations.NotNull;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Objects;

@Service
@RequiredArgsConstructor
public class StudentApplicationTrialServiceImpl implements StudentApplicationTrialService {
    private final StudentApplicationTrialRepository studentApplicationTrialRepository;
    private final StudentApplicationTrialMapper studentApplicationTrialMapper;
    private final AcademicYearService academicYearService;

    @Override
    public StudentApplicationTrial getEntity(Long id) {
        return studentApplicationTrialRepository.findById(id).orElseThrow(() -> new EntityException.NotFound(EntityName.STUDENT_APPLICATION_TRIAL, id));
    }

    @Override
    public List<StudentApplicationTrial> getEntitiesByYear(Long yearId) {
        return studentApplicationTrialRepository.findAllByAcademicYear(academicYearService.getEntity(yearId));
    }

    @NotNull
    @Override
    public List<StudentApplicationTrialDto> getAllTrialsByYearAndClass(Long yearId, Long classSubId) {
        AcademicYear academicYear = academicYearService.getEntity(yearId);
        return studentApplicationTrialRepository.findAllByAcademicYear(academicYear)
                .stream().filter(sat -> Objects.equals(sat.getStudentApplication().getClassLevelSub().getId(), classSubId))
                .map(studentApplicationTrialMapper::studentApplicationTrialToStudentApplicationTrialDto)
                .toList();
    }
}
