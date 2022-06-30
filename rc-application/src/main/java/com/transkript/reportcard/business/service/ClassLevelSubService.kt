package com.transkript.reportcard.business.service

import com.transkript.reportcard.api.dto.ClassLevelSubDto
import com.transkript.reportcard.api.dto.response.EntityResponse
import com.transkript.reportcard.data.entity.ClassLevelSub

interface ClassLevelSubService {
    fun addClassLevelSub(classLevelSubDto: ClassLevelSubDto?): EntityResponse?
    val classLevelSubs: List<ClassLevelSubDto?>?
    fun getClassLevelSubsByClassLevel(levelId: Long?): List<ClassLevelSubDto?>?
    fun getClassLevelSub(id: Long?): ClassLevelSubDto?
    fun updateClassLevelSub(id: Long?, classLevelSubDto: ClassLevelSubDto?): EntityResponse?
    fun deleteClassLevelSub(id: Long?)
    fun getClassLevelSubEntity(id: Long?): ClassLevelSub?
}
