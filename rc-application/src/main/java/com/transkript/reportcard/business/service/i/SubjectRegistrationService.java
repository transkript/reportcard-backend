package com.transkript.reportcard.business.service.i;

import com.transkript.reportcard.api.dto.SubjectRegistrationDto;
import com.transkript.reportcard.api.dto.response.EntityResponse;
import com.transkript.reportcard.data.entity.composite.SubjectRegistrationKey;
import com.transkript.reportcard.data.entity.relation.SubjectRegistration;
import java.util.List;
import kotlin.Metadata;
import org.jetbrains.annotations.NotNull;
import org.springframework.transaction.annotation.Transactional;

@Metadata(
        mv = {1, 6, 0},
        k = 1,
        d1 = {"\u00004\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010 \n\u0002\b\u0002\n\u0002\u0010\u0002\n\u0000\n\u0002\u0010\t\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\b\u0003\bf\u0018\u00002\u00020\u0001J\u0010\u0010\u0002\u001a\u00020\u00032\u0006\u0010\u0004\u001a\u00020\u0005H&J\u001c\u0010\u0006\u001a\b\u0012\u0004\u0012\u00020\u00030\u00072\f\u0010\b\u001a\b\u0012\u0004\u0012\u00020\u00050\u0007H&J\u0010\u0010\t\u001a\u00020\n2\u0006\u0010\u000b\u001a\u00020\fH'J\u0010\u0010\r\u001a\u00020\u00052\u0006\u0010\u000b\u001a\u00020\fH&J\u0016\u0010\u000e\u001a\b\u0012\u0004\u0012\u00020\u00050\u00072\u0006\u0010\u000f\u001a\u00020\fH&J\u0016\u0010\u0010\u001a\b\u0012\u0004\u0012\u00020\u00110\u00072\u0006\u0010\u000f\u001a\u00020\fH&J\u0010\u0010\u0012\u001a\u00020\u00112\u0006\u0010\u000b\u001a\u00020\fH&J\u0018\u0010\u0012\u001a\u00020\u00112\u0006\u0010\u000f\u001a\u00020\f2\u0006\u0010\u0013\u001a\u00020\fH&¨\u0006\u0014"},
        d2 = {"Lcom/transkript/reportcard/business/service/i/SubjectRegistrationService;", "", "create", "Lcom/transkript/reportcard/api/dto/response/EntityResponse;", "subjectRegistrationDto", "Lcom/transkript/reportcard/api/dto/SubjectRegistrationDto;", "createMultiple", "", "subjectRegistrationDtoList", "delete", "", "registrationId", "", "getDto", "getDtoList", "satId", "getEntitiesByApplicationTrial", "Lcom/transkript/reportcard/data/entity/SubjectRegistration;", "getEntity", "subjectId", "rc-application"}
)
public interface SubjectRegistrationService {
    @NotNull
    EntityResponse create(@NotNull SubjectRegistrationDto subjectRegistrationDto);

    @NotNull
    List<EntityResponse> createMultiple(@NotNull List<SubjectRegistrationDto> subjectRegistrationDtoList);

    @NotNull
    List<SubjectRegistrationDto> getAllDtoBySAT(Long satId);

    @NotNull
    SubjectRegistrationDto getDto(SubjectRegistrationDto.SubjectRegistrationKeyDto keyDto);

    @Transactional
    void delete(SubjectRegistrationDto.SubjectRegistrationKeyDto keyDto);

    @NotNull
    SubjectRegistration getEntity(SubjectRegistrationKey key);
}
