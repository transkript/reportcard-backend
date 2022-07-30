package com.transkript.reportcard.business.service.i

import com.transkript.reportcard.api.dto.StudentApplicationDto
import com.transkript.reportcard.api.dto.StudentApplicationDto.ApplicationKeyDto
import com.transkript.reportcard.api.dto.request.StudentApplicationRequest
import com.transkript.reportcard.api.dto.response.EntityResponse
import com.transkript.reportcard.api.dto.response.StudentApplicationResponse
import com.transkript.reportcard.data.entity.composite.ApplicationKey
import com.transkript.reportcard.data.entity.relation.StudentApplication

interface StudentApplicationService {
    fun getAsEntity(applicationKey: ApplicationKey): StudentApplication?
    fun create(request: StudentApplicationRequest): EntityResponse?
    val allAsDto: List<StudentApplicationDto?>?
    fun getAllAsResponses(request: StudentApplicationRequest): List<StudentApplicationResponse?>?
    fun getAsResponse(request: StudentApplicationRequest): StudentApplicationResponse?
    fun getDto(applicationKeyDto: ApplicationKeyDto): StudentApplicationDto?
    fun delete(applicationKeyDto: ApplicationKeyDto)
    fun getAllAsResponseByYear(yearId: Long): List<StudentApplicationResponse>
}
