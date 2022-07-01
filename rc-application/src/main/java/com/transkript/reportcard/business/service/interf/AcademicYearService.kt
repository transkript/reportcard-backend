package com.transkript.reportcard.business.service.interf

import com.transkript.reportcard.api.dto.AcademicYearDto
import com.transkript.reportcard.api.dto.response.EntityResponse
import com.transkript.reportcard.data.entity.AcademicYear

interface AcademicYearService {
    fun addAcademicYear(academicYearDto: AcademicYearDto?): EntityResponse?
    val academicYears: List<AcademicYearDto?>?
    fun getAcademicYear(id: Long?): AcademicYearDto?
    fun updateAcademicYear(id: Long?, academicYearDto: AcademicYearDto?): EntityResponse?
    fun deleteAcademicYear(id: Long?)
    fun getAcademicYearEntity(academicYearId: Long?): AcademicYear?
    val academicYearEntities: List<AcademicYear?>?
}
