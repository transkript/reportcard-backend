package com.transkript.reportcard.business.service.i

import com.transkript.reportcard.api.dto.AcademicYearDto
import com.transkript.reportcard.api.dto.response.EntityResponse
import com.transkript.reportcard.data.entity.AcademicYear

interface AcademicYearService {
    fun create(academicYearDto: AcademicYearDto?): EntityResponse?
    val academicYears: List<AcademicYearDto?>?
    fun getDto(id: Long?): AcademicYearDto?
    fun update(id: Long?, academicYearDto: AcademicYearDto?): EntityResponse?
    fun delete(id: Long?)
    fun getEntity(academicYearId: Long?): AcademicYear?
    val academicYearEntities: List<AcademicYear?>?
}
