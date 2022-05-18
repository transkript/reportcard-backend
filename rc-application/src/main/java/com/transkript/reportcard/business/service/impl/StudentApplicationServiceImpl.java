package com.transkript.reportcard.business.service.impl;

import com.transkript.reportcard.api.dto.StudentApplicationDto;
import com.transkript.reportcard.api.dto.response.EntityResponse;
import com.transkript.reportcard.business.mapper.StudentApplicationMapper;
import com.transkript.reportcard.business.service.AcademicYearService;
import com.transkript.reportcard.business.service.StudentApplicationService;
import com.transkript.reportcard.business.service.StudentService;
import com.transkript.reportcard.data.entity.AcademicYear;
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
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class StudentApplicationServiceImpl implements StudentApplicationService {
    private final StudentApplicationRepository studentApplicationRepository;
    private final StudentApplicationMapper studentApplicationMapper;
    private final StudentService studentService;
    private final AcademicYearService academicYearService;

    @Override
    public StudentApplication getStudentApplicationEntity(Long id) {
        return studentApplicationRepository.findById(id)
                .orElseThrow(() -> new EntityException.EntityNotFoundException("student application", id));
    }

    @Override
    public EntityResponse addStudentApplication(StudentApplicationDto studentApplicationDto) {
        StudentApplication studentApplication = studentApplicationMapper.mapDtoToStudentApplication(studentApplicationDto);
        System.out.println(studentApplication);
        studentApplication.setId(null);
        studentApplication.setCreatedAt(LocalDateTime.now());
        if (studentApplicationDto.getStudentId() == null) {
            throw new ReportCardException.IllegalArgumentException("Student id is required");
        } else {
            Student student = studentService.getStudentEntity(studentApplicationDto.getStudentId());
            studentApplication.setStudent(student);
        }
        if(studentApplicationDto.getAcademicYearId() == null) {
            throw new ReportCardException.IllegalArgumentException("Academic year id is required");
        } else {
            AcademicYear academicYear = academicYearService.getAcademicYearEntity(studentApplicationDto.getAcademicYearId());
            studentApplication.setAcademicYear(academicYear);
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
