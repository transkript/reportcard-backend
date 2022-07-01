package com.transkript.reportcard.business.service.interf

import com.transkript.reportcard.api.dto.TermDto
import com.transkript.reportcard.api.dto.response.EntityResponse
import com.transkript.reportcard.data.entity.Term

interface TermService {
    fun addTerm(termDto: TermDto?): EntityResponse?
    val terms: List<TermDto?>?
    fun getTerm(id: Long?): TermDto?
    fun getTermEntity(id: Long?): Term?
    fun updateTerm(id: Long?, termDto: TermDto?): EntityResponse?
    fun deleteTerm(id: Long?): String?
}
