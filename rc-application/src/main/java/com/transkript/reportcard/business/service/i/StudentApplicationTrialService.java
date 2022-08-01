package com.transkript.reportcard.business.service.i;

import com.transkript.reportcard.api.dto.StudentApplicationTrialDto;
import com.transkript.reportcard.data.entity.relation.StudentApplicationTrial;
import java.util.List;
import kotlin.Metadata;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

@Metadata(
    mv = {1, 6, 0},
    k = 1,
    d1 = {"\u0000\u001c\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010 \n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\t\n\u0002\b\u0005\bf\u0018\u00002\u00020\u0001J!\u0010\u0002\u001a\f\u0012\u0006\u0012\u0004\u0018\u00010\u0004\u0018\u00010\u00032\b\u0010\u0005\u001a\u0004\u0018\u00010\u0006H&¢\u0006\u0002\u0010\u0007J\u0019\u0010\b\u001a\u0004\u0018\u00010\u00042\b\u0010\t\u001a\u0004\u0018\u00010\u0006H&¢\u0006\u0002\u0010\n¨\u0006\u000b"},
    d2 = {"Lcom/transkript/reportcard/business/service/i/StudentApplicationTrialService;", "", "getEntitiesByYear", "", "Lcom/transkript/reportcard/data/entity/relation/StudentApplicationTrial;", "yearId", "", "(Ljava/lang/Long;)Ljava/util/List;", "getEntity", "satId", "(Ljava/lang/Long;)Lcom/transkript/reportcard/data/entity/relation/StudentApplicationTrial;", "rc-application"}
)
public interface StudentApplicationTrialService {
    
    StudentApplicationTrial getEntity( Long id);

    
    List<StudentApplicationTrial> getEntitiesByYear( Long yearId);

    @NotNull
    List<StudentApplicationTrialDto> getAllTrialsByYearAndClass(Long yearId, Long classSubId);
}
