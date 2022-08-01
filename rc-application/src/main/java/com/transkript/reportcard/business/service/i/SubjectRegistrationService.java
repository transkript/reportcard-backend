package com.transkript.reportcard.business.service.i;

import com.transkript.reportcard.api.dto.SubjectRegistrationDto;
import com.transkript.reportcard.api.dto.response.EntityResponse;
import com.transkript.reportcard.data.entity.SubjectRegistration;
import java.util.List;
import org.jetbrains.annotations.NotNull;
import org.springframework.transaction.annotation.Transactional;

public interface SubjectRegistrationService {
    @NotNull
    EntityResponse create(@NotNull SubjectRegistrationDto subjectRegistrationDto);

    @NotNull
    List<EntityResponse> createMultiple(@NotNull List<SubjectRegistrationDto> subjectRegistrationDtoList);

    @NotNull
    List<SubjectRegistrationDto> getAllDtoBySAT(Long satId);

    @NotNull
    SubjectRegistrationDto getDto(Long id);

    @Transactional
    void delete(Long id);

    @NotNull
    SubjectRegistration getEntity(Long id);

    @NotNull
    SubjectRegistration getEntityBySubjectAndSat(Long subjectId, Long satId);
}
