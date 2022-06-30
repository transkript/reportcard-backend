package com.transkript.reportcard.business.service

import com.transkript.reportcard.api.dto.request.ClassListRequest
import com.transkript.reportcard.api.dto.response.ClassListResponse

interface ClassListService {
    fun getClassList(request: ClassListRequest?): ClassListResponse?
}
