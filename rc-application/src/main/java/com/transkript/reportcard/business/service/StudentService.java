package com.transkript.reportcard.business.service;


import com.transkript.reportcard.api.dto.StudentDto;
import com.transkript.reportcard.api.dto.response.EntityResponse;
import com.transkript.reportcard.data.entity.Student;

import java.util.List;

public interface StudentService {
    EntityResponse addStudent(StudentDto studentDto);

    List<StudentDto> getStudents();

    StudentDto getStudent(Long id);

    EntityResponse deleteStudent(Long id);

    Student getStudentEntity(Long id);

    EntityResponse updateStudent(Long id, StudentDto studentDto);
}
