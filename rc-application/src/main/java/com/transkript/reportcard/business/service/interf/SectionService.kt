package com.transkript.reportcard.business.service.interf

import com.transkript.reportcard.api.dto.SectionDto
import com.transkript.reportcard.data.entity.Section

interface SectionService {
    fun getSectionEntity(id: Long?): Section?
    fun addSection(sectionDto: SectionDto?): String?
    val sections: List<SectionDto?>?
    fun getSection(id: Long?): SectionDto?
    fun updateSection(id: Long?, sectionDto: SectionDto?): String?
    fun deleteSection(id: Long?): String?
}
