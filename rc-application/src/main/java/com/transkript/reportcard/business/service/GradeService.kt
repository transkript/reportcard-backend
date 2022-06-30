package com.transkript.reportcard.business.service

import com.transkript.reportcard.api.dto.GradeDto
import com.transkript.reportcard.api.dto.response.EntityResponse
import com.transkript.reportcard.data.entity.composite.GradeKey
import com.transkript.reportcard.data.entity.relation.Grade

interface GradeService {
    fun addGrade(gradeDto: GradeDto?): EntityResponse?
    fun getGradesBySequence(sequenceId: Long?): List<GradeDto?>?
    fun getGradesByRegistration(registrationId: Long?): List<GradeDto?>?
    fun getGrade(registrationId: Long?, sequenceId: Long?): GradeDto?
    fun getGradeEntity(gradeKey: GradeKey?): Grade?
    fun getGradeEntitiesBySubjectRegistration(registrationId: Long?): List<Grade?>?
    fun updateGrade(gradeDto: GradeDto?): EntityResponse?
}
