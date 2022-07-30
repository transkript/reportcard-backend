package com.transkript.reportcard.business.service.impl;

import com.transkript.reportcard.api.dto.StudentDto;
import com.transkript.reportcard.api.dto.response.EntityResponse;
import com.transkript.reportcard.business.mapper.StudentMapper;
import com.transkript.reportcard.business.service.i.SchoolService;
import com.transkript.reportcard.business.service.i.StudentService;
import com.transkript.reportcard.config.constants.EntityName;
import com.transkript.reportcard.config.constants.ResponseMessage;
import com.transkript.reportcard.data.entity.School;
import com.transkript.reportcard.data.entity.Student;
import com.transkript.reportcard.data.repository.StudentRepository;
import com.transkript.reportcard.exception.EntityException;
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
    private final SchoolService schoolService;
    private final StudentRepository studentRepository;
    private final StudentMapper studentMapper;
    private final String entityName = EntityName.STUDENT;

    @Override
    public EntityResponse addStudent(StudentDto studentDto) {
        Student student = studentMapper.mapDtoToStudent(studentDto);
        School school = schoolService.getEntity(studentDto.schoolId());
        student.setId(null);
        student.setRegNum("");
        student.setSchool(school);
        if (student.getDob() == null) {
            student.setDob(LocalDateTime.now());
        }
        student = studentRepository.save(student);


        return new EntityResponse(student.getId(), ResponseMessage.SUCCESS.updated(entityName), true);
    }

    @Override
    public List<StudentDto> getStudents() {
        return studentRepository.findAll().stream()
                .map(studentMapper::mapStudentToDto)
                .collect(Collectors.toList());
    }

    @Override
    public StudentDto getStudent(Long id) {
        return studentMapper.mapStudentToDto(getEntity(id));
    }

    @Override
    public void deleteStudent(Long id) {
        studentRepository.deleteById(id);
    }

    @Override
    public Student getEntity(Long id) {
        return studentRepository.findById(id).orElseThrow(() -> new EntityException.NotFound("student", id));
    }

    @Override
    public EntityResponse updateStudent(Long id, StudentDto studentDto) {
        Student student = studentMapper.mapDtoToStudent(studentDto);
        Student existingStudent = this.getEntity(id);

        {
            assert existingStudent != null;
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

        return new EntityResponse(student.getId(), ResponseMessage.SUCCESS.updated(entityName), true);
    }
}
