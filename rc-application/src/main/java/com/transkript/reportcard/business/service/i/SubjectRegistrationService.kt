package com.transkript.reportcard.business.service.i

import com.transkript.reportcard.api.dto.SubjectRegistrationDto
import com.transkript.reportcard.api.dto.response.EntityResponse
import com.transkript.reportcard.data.entity.SubjectRegistration
import org.springframework.transaction.annotation.Transactional

interface SubjectRegistrationService {
    fun create(subjectRegistrationDto: SubjectRegistrationDto): EntityResponse
    fun createMultiple(subjectRegistrationDtoList: List<SubjectRegistrationDto>): List<EntityResponse>
    fun getDtoList(satId: Long): List<SubjectRegistrationDto>
    fun getDto(registrationId: Long): SubjectRegistrationDto

    @Transactional
    fun delete(registrationId: Long)
    fun getEntity(registrationId: Long): SubjectRegistration
    fun getEntity(satId: Long, subjectId: Long): SubjectRegistration
    fun getEntitiesByApplicationTrial(satId: Long): List<SubjectRegistration>
}
