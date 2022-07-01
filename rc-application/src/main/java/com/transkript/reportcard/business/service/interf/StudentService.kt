package com.transkript.reportcard.business.service.interf

import com.transkript.reportcard.api.dto.StudentDto
import com.transkript.reportcard.api.dto.response.EntityResponse
import com.transkript.reportcard.data.entity.Student

interface StudentService {
    fun addStudent(studentDto: StudentDto?): EntityResponse?
    val students: List<StudentDto?>?
    fun getStudent(id: Long?): StudentDto?
    fun deleteStudent(id: Long?): EntityResponse?
    fun getStudentEntity(id: Long?): Student?
    fun updateStudent(id: Long?, studentDto: StudentDto?): EntityResponse?
}
