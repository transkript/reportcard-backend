package com.transkript.reportcard.business.service.interf

import com.transkript.reportcard.api.dto.SequenceDto
import com.transkript.reportcard.api.dto.response.EntityResponse
import com.transkript.reportcard.data.entity.Sequence

interface SequenceService {
    fun addSequence(sequenceDto: SequenceDto?): EntityResponse?
    val sequences: List<SequenceDto?>?
    fun getSequence(id: Long?): SequenceDto?
    fun updateSequence(id: Long?, sequenceDto: SequenceDto?): EntityResponse?
    fun deleteSequence(id: Long?): String?
    fun getSequenceEntity(sequenceId: Long?): Sequence?
}
