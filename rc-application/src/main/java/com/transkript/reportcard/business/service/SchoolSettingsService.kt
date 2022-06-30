package com.transkript.reportcard.business.service

import com.transkript.reportcard.api.dto.SchoolSettingsDto
import com.transkript.reportcard.api.dto.response.EntityResponse
import com.transkript.reportcard.data.entity.SchoolSettings

interface SchoolSettingsService {
    fun save(schoolSettingsDto: SchoolSettingsDto?): EntityResponse?
    fun update(schoolSettingsDto: SchoolSettingsDto?): EntityResponse?
    fun retrieve(id: Long?): SchoolSettingsDto?
    fun retrieveBySchoolId(schoolId: Long?): SchoolSettingsDto?
    fun retrieveAll(): List<SchoolSettingsDto?>?
    fun retrieveEntity(id: Long?): SchoolSettings?
}
