package com.transkript.reportcard.business.service.i;

import com.transkript.reportcard.api.dto.StudentApplicationDto;
import com.transkript.reportcard.api.dto.request.StudentApplicationRequest;
import com.transkript.reportcard.api.dto.response.EntityResponse;
import com.transkript.reportcard.api.dto.response.StudentApplicationResponse;
import com.transkript.reportcard.data.entity.composite.ApplicationKey;
import com.transkript.reportcard.data.entity.relation.StudentApplication;
import java.util.List;

import org.jetbrains.annotations.NotNull;

public interface StudentApplicationService {
    @NotNull
    StudentApplication getEntity(@NotNull ApplicationKey var1);

    @NotNull
    EntityResponse create(@NotNull StudentApplicationRequest var1);

    @NotNull
    List<StudentApplicationDto> getAllAsDto();

    @NotNull
    List<StudentApplicationResponse> getAllAsResponses(@NotNull StudentApplicationRequest var1);

    @NotNull
    StudentApplicationResponse getAsResponse(@NotNull StudentApplicationRequest var1);

    @NotNull
    StudentApplicationDto getDto(@NotNull StudentApplicationDto.ApplicationKeyDto var1);

    void delete(@NotNull StudentApplicationDto.ApplicationKeyDto var1);

    @NotNull
    List<StudentApplicationResponse> getAllAsResponseByYear(Long yearId);
}
