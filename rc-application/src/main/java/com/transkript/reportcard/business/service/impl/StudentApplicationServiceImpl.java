package com.transkript.reportcard.business.service.impl;

import com.transkript.reportcard.api.dto.StudentApplicationDto;
import com.transkript.reportcard.api.dto.StudentDto;
import com.transkript.reportcard.api.dto.request.StudentApplicationRequest;
import com.transkript.reportcard.api.dto.response.EntityResponse;
import com.transkript.reportcard.api.dto.response.StudentApplicationResponse;
import com.transkript.reportcard.business.mapper.StudentApplicationMapper;
import com.transkript.reportcard.business.mapper.StudentApplicationTrialMapper;
import com.transkript.reportcard.business.service.i.AcademicYearService;
import com.transkript.reportcard.business.service.i.ClassLevelSubService;
import com.transkript.reportcard.business.service.i.StudentApplicationService;
import com.transkript.reportcard.business.service.i.StudentService;
import com.transkript.reportcard.config.constants.EntityName;
import com.transkript.reportcard.config.constants.ResponseMessage;
import com.transkript.reportcard.data.entity.AcademicYear;
import com.transkript.reportcard.data.entity.ClassLevelSub;
import com.transkript.reportcard.data.entity.Student;
import com.transkript.reportcard.data.entity.composite.ApplicationKey;
import com.transkript.reportcard.data.entity.relation.StudentApplication;
import com.transkript.reportcard.data.entity.relation.StudentApplicationTrial;
import com.transkript.reportcard.data.repository.StudentApplicationRepository;
import com.transkript.reportcard.data.repository.StudentApplicationTrialRepository;
import com.transkript.reportcard.exception.EntityException;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.jetbrains.annotations.NotNull;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Slf4j
@Service
@RequiredArgsConstructor
public class StudentApplicationServiceImpl implements StudentApplicationService {
    private final StudentApplicationRepository studentApplicationRepository;
    private final StudentApplicationTrialRepository studentApplicationTrialRepository;
    private final StudentApplicationMapper studentApplicationMapper;
    private final StudentApplicationTrialMapper studentApplicationTrialMapper;
    private final StudentService studentService;
    private final AcademicYearService academicYearService;
    private final ClassLevelSubService classLevelSubService;
    private final String entityName = EntityName.STUDENT_APPLICATION;

    @Override
    public StudentApplication getEntity(@NotNull ApplicationKey applicationKey) {
        return studentApplicationRepository.findById(applicationKey)
                .orElseThrow(() -> new EntityException.NotFound("student application", applicationKey.getStudentId(), applicationKey.getClassSubId()));
    }

    @Override
    public EntityResponse create(StudentApplicationRequest request) {
        StudentApplication studentApplication = new StudentApplication();

        AcademicYear academicYear = academicYearService.getEntity(request.yearId());
        Student student = studentService.getEntity(request.studentId());
        ClassLevelSub classLevelSub = classLevelSubService.getEntity(request.classSubId());

        StudentApplicationTrial sat = new StudentApplicationTrial();
        {
            sat.setCreatedAt(LocalDateTime.now());
            sat.setUpdatedAt(LocalDateTime.now());
            sat.setAcademicYear(academicYear);
            sat.setRepeating(false);
        }

        ApplicationKey applicationKey = new ApplicationKey();
        {
            applicationKey.setStudentId(student.getId());
            applicationKey.setClassSubId(classLevelSub.getId());

            studentApplication.setStudent(student);
            studentApplication.setClassLevelSub(classLevelSub);

        }
        {
            if (studentApplicationRepository.existsById(applicationKey)) {
                studentApplication = studentApplicationRepository.findById(applicationKey).orElse(studentApplication);
                sat.setOrder(studentApplication.getStudentApplicationTrials().size() + 1);
                sat.setRepeating(true);
            } else {
                studentApplication.setKey(applicationKey);
                studentApplication = studentApplicationRepository.save(studentApplication);
                sat.setOrder(1);
            }
            sat.setStudentApplication(studentApplication);
            studentApplicationTrialRepository.save(sat);
        }
        return new EntityResponse(
                Map.of("class level sub", studentApplication.getClassLevelSub().getId(),
                        "student", studentApplication.getStudent().getId()),
                ResponseMessage.SUCCESS.created(entityName), true);
    }

    @Override
    public List<StudentApplicationDto> getAllAsDto() {
        return studentApplicationRepository.findAll()
                .stream()
                .map(studentApplicationMapper::studentApplicationToStudentApplicationDto)
                .collect(Collectors.toList());
    }

    @Override
    public List<StudentApplicationResponse> getAllAsResponses(StudentApplicationRequest request) {
        if (request.classSubId() < 0) {
            return getAllAsResponseByYear(request.yearId());
        } else {
            ClassLevelSub classLevelSub = classLevelSubService.getEntity(request.classSubId());
            return new ArrayList<>();
        }
    }

    @Override
    public StudentApplicationResponse getAsResponse(StudentApplicationRequest request) {
        StudentApplication application = getEntity(new ApplicationKey(request.studentId(), request.classSubId()));
        ClassLevelSub classLevelSub = application.getClassLevelSub();
        AcademicYear academicYear = academicYearService.getEntity(request.yearId());
        var sat = studentApplicationTrialRepository.findByAcademicYearAndStudentApplication(
                academicYear, application
        ).orElseThrow(() -> new EntityException.NotFound(EntityName.STUDENT_APPLICATION_TRIAL));

        return new StudentApplicationResponse(
                classLevelSub.getClassLevel().getName().concat(" ").concat(classLevelSub.getName()),
                studentService.getDto(request.studentId()),
                studentApplicationMapper.studentApplicationToStudentApplicationDto(application),
                application.getStudentApplicationTrials().stream()
                        .filter(s -> sat.getAcademicYear().getId().equals(sat.getId())).toList()
        );
    }

    @Override
    public StudentApplicationDto getDto(StudentApplicationDto.ApplicationKeyDto applicationKeyDto) {
        return studentApplicationMapper.studentApplicationToStudentApplicationDto(getEntity(
                new ApplicationKey(applicationKeyDto.studentId(), applicationKeyDto.classSubId()))
        );
    }

    @Override
    public void delete(@NotNull StudentApplicationDto.ApplicationKeyDto applicationKeyDto) {
        ApplicationKey applicationKey = new ApplicationKey(applicationKeyDto.studentId(), applicationKeyDto.classSubId());
        studentApplicationRepository.deleteById(applicationKey);
    }

    @NotNull
    @Override
    public List<StudentApplicationResponse> getAllAsResponseByYear(Long yearId) {
        AcademicYear year = academicYearService.getEntity(yearId);
        return studentApplicationTrialRepository.findAllByAcademicYear(year).stream()
                .map((sat) -> {
                    StudentApplication application = sat.getStudentApplication();
                    ClassLevelSub classLevelSub = application.getClassLevelSub();
                    StudentDto studentDto = studentService.getDto(application.getStudent().getId());
                    StudentApplicationDto applicationDto = studentApplicationMapper.studentApplicationToStudentApplicationDto(application);

                    return new StudentApplicationResponse(
                            classLevelSub.getClassLevel().getName().concat(" ").concat(classLevelSub.getName()),
                            studentDto, applicationDto, application.getStudentApplicationTrials()
                    );
                }).toList();
    }
}
