package com.transkript.reportcard.business.service.i

import com.transkript.reportcard.api.dto.SchoolDto
import com.transkript.reportcard.api.dto.response.EntityResponse
import com.transkript.reportcard.data.entity.School

interface SchoolService {
    fun getEntity(id: Long?): School?
    fun create(schoolDto: SchoolDto?): EntityResponse?
    val schools: List<SchoolDto?>?
    fun getDto(id: Long?): SchoolDto?
    fun update(id: Long?, schoolDto: SchoolDto?): EntityResponse?
    fun delete(id: Long?): String?
}
