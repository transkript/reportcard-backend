package com.transkript.reportcard.business.service

import com.transkript.reportcard.api.dto.SchoolDto
import com.transkript.reportcard.api.dto.response.EntityResponse
import com.transkript.reportcard.data.entity.School

interface SchoolService {
    fun getSchoolEntity(id: Long?): School?
    fun addSchool(schoolDto: SchoolDto?): EntityResponse?
    val schools: List<SchoolDto?>?
    fun getSchool(id: Long?): SchoolDto?
    fun updateSchool(id: Long?, schoolDto: SchoolDto?): String?
    fun deleteSchool(id: Long?): String?
}
