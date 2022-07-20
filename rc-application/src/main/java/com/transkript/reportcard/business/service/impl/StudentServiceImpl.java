package com.transkript.reportcard.business.service.impl;

import com.transkript.reportcard.api.dto.StudentDto;
import com.transkript.reportcard.api.dto.response.EntityResponse;
import com.transkript.reportcard.business.mapper.StudentMapper;
import com.transkript.reportcard.business.service.interf.SchoolSettingsService;
import com.transkript.reportcard.business.service.interf.StudentService;
import com.transkript.reportcard.business.util.SchoolUtil;
import com.transkript.reportcard.business.util.SettingsUtil;
import com.transkript.reportcard.data.entity.Student;
import com.transkript.reportcard.data.repository.StudentRepository;
import com.transkript.reportcard.exception.EntityException;
import com.transkript.reportcard.exception.ReportCardException;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

@Slf4j
@Service
@RequiredArgsConstructor
public class StudentServiceImpl implements StudentService {
    private final SchoolSettingsService schoolSettingsService;
    private final StudentRepository studentRepository;
    private final StudentMapper studentMapper;

    @Override
    public EntityResponse addStudent(StudentDto studentDto) {
        Student student = studentMapper.mapDtoToStudent(studentDto);
        student.setId(null);
        student.setRegNum("");
        if (student.getDob() == null) {
            student.setDob(LocalDateTime.now());
        }

        student = studentRepository.save(student);
        this.updateRegNo(student);

        return EntityResponse.builder().id(student.getId()).message("Student added successfully").build();
    }

    @Override
    public List<StudentDto> getStudents() {
        return studentRepository.findAll().stream()
                .map(studentMapper::mapStudentToDto)
                .collect(Collectors.toList());
    }

    @Override
    public StudentDto getStudent(Long id) {
        return studentMapper.mapStudentToDto(getStudentEntity(id));
    }

    @Override
    public EntityResponse deleteStudent(Long id) {
        studentRepository.findById(id).ifPresentOrElse(
                studentRepository::delete,
                () -> {
                    log.error("Student with id {} not found", id);
                    throw new EntityException.NotFound("student", id);
                }
        );
        return EntityResponse.builder().id(id).message("Student deleted successfully").build();
    }

    @Override
    public Student getStudentEntity(Long id) {
        return studentRepository.findById(id).orElseThrow(() -> new EntityException.NotFound("student", id));
    }

    @Override
    public EntityResponse updateStudent(Long id, StudentDto studentDto) {
        Student student = studentMapper.mapDtoToStudent(studentDto);
        Student existingStudent = getStudentEntity(id);
        {
            if (student.getId() == null) {
                throw new ReportCardException.IllegalArgumentException("Student id cannot be null");
            } else {
                existingStudent.setId(id);
            }
        }
        {
            if (student.getRegNum() != null || !Objects.equals(student.getRegNum(), existingStudent.getRegNum())) {
                existingStudent.setRegNum(student.getRegNum());
            }
            if (student.getDob() != null || student.getDob() != existingStudent.getDob()) {
                existingStudent.setDob(student.getDob());
            }
            if (student.getGender() != null || student.getGender() != existingStudent.getGender()) {
                existingStudent.setGender(student.getGender());
            }
            if (student.getName() != null || !Objects.equals(student.getName(), existingStudent.getName())) {
                existingStudent.setName(student.getName());
            }
            if (student.getPob() != null || !Objects.equals(student.getPob(), existingStudent.getPob())) {
                existingStudent.setPob(student.getPob());
            }
        }

        this.updateRegNo(existingStudent);

        return EntityResponse.builder().entityName("student").id(existingStudent.getId())
                .message("Student updated successfully").build();
    }

    private void updateRegNo(Student student) {
        if (student.getId() == null) {
            return;
        }
        student.setRegNum(SchoolUtil.generateRegNo(student.getId(), SettingsUtil.readSettings().schoolName()));

        student = studentRepository.save(student);
        log.info("Updating regno for student: '{}' to {}", student.getName(), student.getRegNum());
    }
}
