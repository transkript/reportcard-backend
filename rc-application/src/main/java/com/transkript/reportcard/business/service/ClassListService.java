package com.transkript.reportcard.business.service;

import com.transkript.reportcard.api.dto.request.ClassListRequest;
import com.transkript.reportcard.api.dto.response.ClassListResponse;

public interface ClassListService {

    ClassListResponse getClassList(ClassListRequest request);
}
