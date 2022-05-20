package com.transkript.reportcard.business.service.impl;

import com.transkript.reportcard.api.dto.StudentApplicationDto;
import com.transkript.reportcard.api.dto.response.EntityResponse;
import com.transkript.reportcard.business.mapper.StudentApplicationMapper;
import com.transkript.reportcard.business.service.AcademicYearService;
import com.transkript.reportcard.business.service.ClassLevelService;
import com.transkript.reportcard.business.service.StudentApplicationService;
import com.transkript.reportcard.business.service.StudentService;
import com.transkript.reportcard.data.entity.AcademicYear;
import com.transkript.reportcard.data.entity.ClassLevel;
import com.transkript.reportcard.data.entity.ClassLevelSub;
import com.transkript.reportcard.data.entity.Student;
import com.transkript.reportcard.data.entity.StudentApplication;
import com.transkript.reportcard.data.repository.StudentApplicationRepository;
import com.transkript.reportcard.exception.EntityException;
import com.transkript.reportcard.exception.ReportCardException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.Date;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class StudentApplicationServiceImpl implements StudentApplicationService {
    private final StudentApplicationRepository studentApplicationRepository;
    private final StudentApplicationMapper studentApplicationMapper;
    private final StudentService studentService;
    private final AcademicYearService academicYearService;
    private final ClassLevelService classLevelService;

    @Override
    public StudentApplication getStudentApplicationEntity(Long id) {
        return studentApplicationRepository.findById(id)
                .orElseThrow(() -> new EntityException.EntityNotFoundException("student application", id));
    }

    @Override
    public EntityResponse addStudentApplication(StudentApplicationDto applicationDto) {
        StudentApplication studentApplication = studentApplicationMapper.mapDtoToStudentApplication(applicationDto);

        studentApplication.setId(null);
        studentApplication.setCreatedAt(LocalDateTime.now());
        {
            if (applicationDto.getStudentId() == null) {
                throw new ReportCardException.IllegalArgumentException("Student id is required");
            } else {
                Student student = studentService.getStudentEntity(applicationDto.getStudentId());
                studentApplication.setStudent(student);
            }
        }
        {
            if (applicationDto.getAcademicYearId() == null) {
                throw new ReportCardException.IllegalArgumentException("Academic year id is required");
            } else {
                AcademicYear academicYear = academicYearService.getAcademicYearEntity(applicationDto.getAcademicYearId());
                studentApplication.setAcademicYear(academicYear);
            }
        }
        {
            if(applicationDto.getClassLevelId() == null || applicationDto.getClassLevelSubId() == null) {
                throw new ReportCardException.IllegalArgumentException("Class level and class level sub ids are required");
            } else {
                ClassLevel classLevel = classLevelService.getClassLevelEntity(applicationDto.getClassLevelId());
                ClassLevelSub classLevelSub = classLevel.getClassLevelSubs().stream()
                        .filter((classSub) -> Objects.equals(classSub.getId(), applicationDto.getClassLevelSubId()))
                        .findFirst().orElseThrow(() -> new ReportCardException.IllegalStateException(
                                String.format(
                                        "class level sub %d does not exist for class level %d in student application",
                                                applicationDto.getClassLevelSubId(), applicationDto.getClassLevelId())));
                studentApplication.setClassLevel(classLevel);
                studentApplication.setClassLevelSub(classLevelSub);
            }
        }
        return EntityResponse.builder().id(studentApplicationRepository.save(studentApplication).getId())
                .message("Student application created successfully").date(new Date()).build();
    }

    @Override
    public List<StudentApplicationDto> getStudentApplications() {
        return studentApplicationRepository.findAll()
                .stream()
                .map(studentApplicationMapper::mapStudentApplicationToDto)
                .collect(Collectors.toList());
    }

    @Override
    public StudentApplicationDto getStudentApplicationById(Long id) {
        return studentApplicationMapper.mapStudentApplicationToDto(getStudentApplicationEntity(id));
    }
}
