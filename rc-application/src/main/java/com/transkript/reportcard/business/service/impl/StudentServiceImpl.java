package com.transkript.reportcard.business.service.impl;

import com.transkript.reportcard.api.dto.StudentDto;
import com.transkript.reportcard.api.dto.response.EntityResponse;
import com.transkript.reportcard.business.mapper.StudentMapper;
import com.transkript.reportcard.business.service.StudentService;
import com.transkript.reportcard.data.entity.Student;
import com.transkript.reportcard.data.repository.StudentRepository;
import com.transkript.reportcard.exception.EntityException;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

@Slf4j
@Service
@RequiredArgsConstructor
public class StudentServiceImpl implements StudentService {
    private final StudentRepository studentRepository;
    private final StudentMapper studentMapper;

    @Override
    public EntityResponse addStudent(StudentDto studentDto) {
        Long[] id = new Long[1];
        Student student = studentMapper.mapDtoToStudent(studentDto);
        student.setId(null);
        if (student.getDob() == null) {
            student.setDob(LocalDateTime.now());
        }
        studentRepository.findByRegNum(student.getRegNum()).ifPresentOrElse(
                (s) -> {
                    log.error("Student with regNum {} already exists", student.getRegNum());
                    throw new EntityException.EntityAlreadyExistsException("student", s.getRegNum());
                },
                () -> {
                    log.info("Adding student with regNum {}", student.getRegNum());
                    id[0] = studentRepository.save(student).getId();
                }
        );
        return EntityResponse.builder().id(id[0]).message("Student added successfully").build();
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
                    throw new EntityException.EntityNotFoundException("student", id);
                }
        );
        return EntityResponse.builder().id(id).message("Student deleted successfully").build();
    }

    @Override
    public Student getStudentEntity(Long id) {
        return studentRepository.findById(id).orElseThrow(() -> new EntityException.EntityNotFoundException("student", id));
    }
}
