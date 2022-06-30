package com.transkript.reportcard.business.service

import com.transkript.reportcard.api.dto.ClassLevelDto
import com.transkript.reportcard.api.dto.response.EntityResponse
import com.transkript.reportcard.data.entity.ClassLevel

interface ClassLevelService {
    fun addClassLevel(classLevelDto: ClassLevelDto?): EntityResponse?
    val classLevels: List<ClassLevelDto?>?
    fun getClassLevelsBySection(sectionId: Long?): List<ClassLevelDto?>?
    fun getClassLevel(id: Long?): ClassLevelDto?
    fun getClassLevelEntity(id: Long?): ClassLevel?
    fun updateClassLevel(id: Long?, classLevelDto: ClassLevelDto?): EntityResponse?
    fun deleteClassLevel(id: Long?)
}
