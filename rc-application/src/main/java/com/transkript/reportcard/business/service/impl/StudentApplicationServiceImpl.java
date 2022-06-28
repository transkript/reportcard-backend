package com.transkript.reportcard.business.service.impl;

import com.transkript.reportcard.api.dto.StudentApplicationDto;
import com.transkript.reportcard.api.dto.StudentDto;
import com.transkript.reportcard.api.dto.request.StudentApplicationRequest;
import com.transkript.reportcard.api.dto.response.EntityResponse;
import com.transkript.reportcard.api.dto.response.StudentApplicationResponse;
import com.transkript.reportcard.business.mapper.StudentApplicationMapper;
import com.transkript.reportcard.business.mapper.SubjectRegistrationMapper;
import com.transkript.reportcard.business.service.AcademicYearService;
import com.transkript.reportcard.business.service.ClassLevelSubService;
import com.transkript.reportcard.business.service.StudentApplicationService;
import com.transkript.reportcard.business.service.StudentService;
import com.transkript.reportcard.config.constants.EntityName;
import com.transkript.reportcard.data.entity.AcademicYear;
import com.transkript.reportcard.data.entity.ClassLevelSub;
import com.transkript.reportcard.data.entity.Student;
import com.transkript.reportcard.data.entity.relation.StudentApplication;
import com.transkript.reportcard.data.entity.composite.ApplicationKey;
import com.transkript.reportcard.data.entity.relation.StudentApplicationTrial;
import com.transkript.reportcard.data.repository.StudentApplicationRepository;
import com.transkript.reportcard.data.repository.StudentApplicationTrialRepository;
import com.transkript.reportcard.exception.EntityException;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.stream.Collectors;

@Slf4j
@Service
@RequiredArgsConstructor
public class StudentApplicationServiceImpl implements StudentApplicationService {
    private final StudentApplicationRepository studentApplicationRepository;
    private final StudentApplicationTrialRepository studentApplicationTrialRepository;
    private final StudentApplicationMapper studentApplicationMapper;
    private final StudentService studentService;
    private final AcademicYearService academicYearService;
    private final ClassLevelSubService classLevelSubService;
    private final SubjectRegistrationMapper subjectRegistrationMapper;

    @Override
    public StudentApplication getAsEntity(ApplicationKey applicationKey) {
        return studentApplicationRepository.findById(applicationKey)
                .orElseThrow(() -> new EntityException.EntityNotFoundException("student application", applicationKey.getStudentId(), applicationKey.getClassSubId()));
    }

    @Override
    public EntityResponse create(StudentApplicationRequest request) {
        StudentApplication studentApplication = new StudentApplication();

        AcademicYear academicYear = academicYearService.getAcademicYearEntity(request.yearId());
        Student student = studentService.getStudentEntity(request.studentId());
        ClassLevelSub classLevelSub = classLevelSubService.getClassLevelSubEntity(request.classSubId());

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
                sat.setRepeating(true);
                studentApplication = studentApplicationRepository.findById(applicationKey).orElse(studentApplication);
            } else {
                studentApplication.setKey(applicationKey);
                studentApplication = studentApplicationRepository.save(studentApplication);
            }
            sat.setStudentApplication(studentApplication);
            studentApplicationTrialRepository.save(sat);
        }
        return EntityResponse.builder().ids(
                Map.of("class level sub", studentApplication.getClassLevelSub().getId(),
                        "student", studentApplication.getStudent().getId()
                )).message("Student application created successfully").build();
    }

    @Override
    public List<StudentApplicationDto> getAllAsDto() {
        return studentApplicationRepository.findAll()
                .stream()
                .map(studentApplicationMapper::mapStudentApplicationToDto)
                .collect(Collectors.toList());
    }

    @Override
    public List<StudentApplicationResponse> getAllAsResponseByYear(Long yearId) {
        AcademicYear year = academicYearService.getAcademicYearEntity(yearId);
        return studentApplicationTrialRepository.findAllByAcademicYear(year).stream()
                .map((sat) -> {
                    StudentApplication application = sat.getStudentApplication();
                    ClassLevelSub classLevelSub = application.getClassLevelSub();
                    StudentDto studentDto = studentService.getStudent(application.getStudent().getId());
                    StudentApplicationDto applicationDto = studentApplicationMapper.mapStudentApplicationToDto(application);

                    return new StudentApplicationResponse(
                        classLevelSub.getClassLevel().getName().concat(" ").concat(classLevelSub.getName()),
                        studentDto, applicationDto, application.getStudentApplicationTrials()
                    );
        }).toList();
    }


    @Override
    public List<StudentApplicationResponse> getAllAsResponses(StudentApplicationRequest request) {
        if(request.classSubId() < 0) {
            return getAllAsResponseByYear(request.yearId());
        } else {
            ClassLevelSub classLevelSub = classLevelSubService.getClassLevelSubEntity(request.classSubId());
            return getAllAsResponseByYear(request.yearId()).stream()
                    .filter((sar) -> Objects.equals(sar.application().applicationKeyDto().classSubId(), classLevelSub.getId())).toList();
        }
    }

    @Override
    public StudentApplicationResponse getAsResponse(StudentApplicationRequest request) {
        StudentApplication application = getAsEntity(new ApplicationKey(request.studentId(), request.classSubId()));
        ClassLevelSub classLevelSub = application.getClassLevelSub();
        AcademicYear academicYear = academicYearService.getAcademicYearEntity(request.yearId());
        var sat = studentApplicationTrialRepository.findByAcademicYearAndStudentApplication(
                academicYear, application
        ).orElseThrow(() -> new EntityException.EntityNotFoundException(EntityName.STUDENT_APPLICATION_TRIAL));

        return new StudentApplicationResponse(
                classLevelSub.getClassLevel().getName().concat(" ").concat(classLevelSub.getName()),
                studentService.getStudent(request.studentId()),
                studentApplicationMapper.mapStudentApplicationToDto(application),
                application.getStudentApplicationTrials().stream()
                        .filter(s -> sat.getAcademicYear().getId().equals(sat.getId())).toList()
        );
    }

    @Override
    public StudentApplicationDto getAsDto(StudentApplicationDto.ApplicationKeyDto applicationKeyDto) {
        return studentApplicationMapper.mapStudentApplicationToDto(getAsEntity(
                new ApplicationKey(applicationKeyDto.studentId(), applicationKeyDto.classSubId()))
        );
    }

    @Override
    public EntityResponse delete(StudentApplicationDto.ApplicationKeyDto applicationKeyDto) {
        ApplicationKey applicationKey = new ApplicationKey(applicationKeyDto.studentId(), applicationKeyDto.classSubId());
        if(studentApplicationRepository.existsById(applicationKey)) {
            studentApplicationRepository.deleteById(applicationKey);
            return new EntityResponse(0L, Map.of("student_id", applicationKeyDto.studentId(), "class_id", applicationKeyDto.classSubId()),
                    "Student application has been deleted successfully",
                    EntityName.STUDENT_APPLICATION, HttpStatus.OK.value(), true, "success"
            );
        } else {
            return new EntityResponse(0L, Map.of("student_id", applicationKeyDto.studentId(), "class_id", applicationKeyDto.classSubId()),
                    "Student application not found, so has not been deleted",
                    EntityName.STUDENT_APPLICATION, HttpStatus.NOT_FOUND.value(), true, "warn"
            );
        }
    }
}
