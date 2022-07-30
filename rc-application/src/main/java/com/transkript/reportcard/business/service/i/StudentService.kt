package com.transkript.reportcard.business.service.i

import com.transkript.reportcard.api.dto.StudentDto
import com.transkript.reportcard.api.dto.response.EntityResponse
import com.transkript.reportcard.data.entity.Student

interface StudentService {
    fun addStudent(studentDto: StudentDto?): EntityResponse?
    val students: List<StudentDto?>?
    fun getStudent(id: Long?): StudentDto?
    fun deleteStudent(id: Long?)
    fun getEntity(id: Long?): Student?
    fun updateStudent(id: Long?, studentDto: StudentDto?): EntityResponse?
}
