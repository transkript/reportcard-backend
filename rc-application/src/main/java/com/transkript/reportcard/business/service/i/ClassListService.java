package com.transkript.reportcard.business.service.i;

import com.transkript.reportcard.api.dto.request.ClassListRequest;
import com.transkript.reportcard.api.dto.response.ClassListResponse;
import kotlin.Metadata;
import org.jetbrains.annotations.Nullable;

public interface ClassListService {
    
    ClassListResponse getClassList( ClassListRequest var1);
}
