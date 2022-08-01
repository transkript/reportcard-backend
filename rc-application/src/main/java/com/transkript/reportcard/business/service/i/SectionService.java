package com.transkript.reportcard.business.service.i;

import com.transkript.reportcard.api.dto.SectionDto;
import com.transkript.reportcard.api.dto.response.EntityResponse;
import com.transkript.reportcard.data.entity.Section;
import java.util.List;
import kotlin.Metadata;
import org.jetbrains.annotations.NotNull;

public interface SectionService {
    @NotNull
    Section getEntity(long var1);
    @NotNull
    EntityResponse create(@NotNull SectionDto var1);
    @NotNull
    List<SectionDto> getAllDto();
    @NotNull
    SectionDto getDto(long var1);
    @NotNull
    EntityResponse update(@NotNull SectionDto var1);
    void delete(long var1);
    @NotNull
    List<SectionDto> getDtoBySchoolId(long var1);
}
