package com.transkript.reportcard.business.service.impl;

import com.transkript.reportcard.api.dto.StudentApplicationDto;
import com.transkript.reportcard.api.dto.response.EntityResponse;
import com.transkript.reportcard.business.mapper.StudentApplicationMapper;
import com.transkript.reportcard.business.service.AcademicYearService;
import com.transkript.reportcard.business.service.ClassLevelService;
import com.transkript.reportcard.business.service.ClassLevelSubService;
import com.transkript.reportcard.business.service.StudentApplicationService;
import com.transkript.reportcard.business.service.StudentService;
import com.transkript.reportcard.data.entity.AcademicYear;
import com.transkript.reportcard.data.entity.ClassLevelSub;
import com.transkript.reportcard.data.entity.Student;
import com.transkript.reportcard.data.entity.StudentApplication;
import com.transkript.reportcard.data.entity.composite.ApplicationKey;
import com.transkript.reportcard.data.repository.StudentApplicationRepository;
import com.transkript.reportcard.exception.EntityException;
import com.transkript.reportcard.exception.ReportCardException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class StudentApplicationServiceImpl implements StudentApplicationService {
    private final StudentApplicationRepository studentApplicationRepository;
    private final StudentApplicationMapper studentApplicationMapper;
    private final StudentService studentService;
    private final AcademicYearService academicYearService;
    private final ClassLevelService classLevelService;
    private final ClassLevelSubService classLevelSubService;

    @Override
    public StudentApplication getStudentApplicationEntity(ApplicationKey applicationKey) {
        return studentApplicationRepository.findById(applicationKey)
                .orElseThrow(() -> new EntityException.EntityNotFoundException("student application", applicationKey.getStudentId(), applicationKey.getYearId()));
    }

    @Override
    public EntityResponse addStudentApplication(StudentApplicationDto applicationDto) {
        StudentApplication studentApplication = studentApplicationMapper.mapDtoToStudentApplication(applicationDto);

        ApplicationKey applicationKey = new ApplicationKey();
        studentApplication.setCreatedAt(LocalDateTime.now());
        {
            if (applicationDto.getStudentId() == null) {
                throw new ReportCardException.IllegalArgumentException("Student id is required");
            } else {
                Student student = studentService.getStudentEntity(applicationDto.getStudentId());
                applicationKey.setStudentId(student.getId());
                studentApplication.setStudent(student);
            }
        }
        {
            if (applicationDto.getAcademicYearId() == null) {
                throw new ReportCardException.IllegalArgumentException("Academic year id is required");
            } else {
                AcademicYear academicYear = academicYearService.getAcademicYearEntity(applicationDto.getAcademicYearId());
                applicationKey.setYearId(academicYear.getId());
                studentApplication.setAcademicYear(academicYear);
            }
        }
        {
            if (applicationDto.getClassLevelSubId() == null) {
                throw new ReportCardException.IllegalArgumentException("Class level and class level sub ids are required");
            } else {
                ClassLevelSub classLevelSub = classLevelSubService.getClassLevelSubEntity(applicationDto.getClassLevelSubId());
                studentApplication.setClassLevelSub(classLevelSub);
            }
        }
        {
            if (studentApplicationRepository.existsById(applicationKey)) {
                throw new EntityException.EntityAlreadyExistsException("student application", applicationKey.getStudentId(), applicationKey.getYearId());
            }
        }
        studentApplication.setApplicationKey(applicationKey);
        studentApplication = studentApplicationRepository.save(studentApplication);
        return EntityResponse.builder().ids(
                Map.of("academic year", studentApplication.getAcademicYear().getId(),
                        "student", studentApplication.getStudent().getId()
                )).message("Student application created successfully").build();
    }

    @Override
    public List<StudentApplicationDto> getStudentApplications() {
        return studentApplicationRepository.findAll()
                .stream()
                .map(studentApplicationMapper::mapStudentApplicationToDto)
                .collect(Collectors.toList());
    }

    @Override
    public StudentApplicationDto getStudentApplication(Long studentId, Long yearId) {
        return studentApplicationMapper.mapStudentApplicationToDto(getStudentApplicationEntity(
                new ApplicationKey(studentId, yearId))
        );
    }
}
