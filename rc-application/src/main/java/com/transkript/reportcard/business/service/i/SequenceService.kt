package com.transkript.reportcard.business.service.i

import com.transkript.reportcard.api.dto.SequenceDto
import com.transkript.reportcard.api.dto.response.EntityResponse
import com.transkript.reportcard.data.entity.Sequence

interface SequenceService {
    fun create(sequenceDto: SequenceDto?): EntityResponse?
    val sequences: List<SequenceDto?>?
    fun getDto(id: Long?): SequenceDto?
    fun update(id: Long?, sequenceDto: SequenceDto?): EntityResponse?
    fun delete(id: Long?): String?
    fun getEntity(sequenceId: Long?): Sequence?
}
