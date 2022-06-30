package com.transkript.reportcard.business.service

import com.transkript.reportcard.api.dto.SubjectRegistrationDto
import com.transkript.reportcard.api.dto.response.EntityResponse
import com.transkript.reportcard.data.entity.SubjectRegistration
import org.springframework.transaction.annotation.Transactional

interface SubjectRegistrationService {
    fun addSubjectRegistration(subjectRegistrationDto: SubjectRegistrationDto?): EntityResponse?
    fun addSubjectRegistrations(subjectRegistrationDtoList: List<SubjectRegistrationDto?>?): List<EntityResponse?>?
    fun getSubjectionRegistrations(satId: Long?): List<SubjectRegistrationDto?>?
    fun getSubjectRegistration(registrationId: Long?): SubjectRegistrationDto?

    @Transactional
    fun deleteSubjectRegistration(registrationId: Long?)
    fun getSubjectRegistrationEntity(registrationId: Long?): SubjectRegistration?
    fun getSubjectRegistrationEntity(satId: Long?, subjectId: Long?): SubjectRegistration?
    fun getSubjectRegistrationEntitiesByApplicationTrial(satId: Long?): List<SubjectRegistration?>?
}
