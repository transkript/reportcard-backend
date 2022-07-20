package com.transkript.reportcard.business.service.interf

import com.transkript.reportcard.api.dto.SubjectDto
import com.transkript.reportcard.api.dto.response.EntityResponse
import com.transkript.reportcard.data.entity.Subject

interface SubjectService {
    val subjects: List<SubjectDto?>?
    fun getSubject(id: Long?): SubjectDto?
    fun addSubject(subjectDto: SubjectDto?): EntityResponse?
    fun updateSubject(id: Long?, subjectDto: SubjectDto?): EntityResponse?
    fun deleteSubject(id: Long?): String?
    fun getSubjectEntity(subjectId: Long?): Subject?
}
