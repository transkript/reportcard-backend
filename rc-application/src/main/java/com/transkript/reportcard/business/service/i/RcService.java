package com.transkript.reportcard.business.service.i;

import com.transkript.reportcard.api.dto.request.ReportCardRequest;
import java.io.File;
import kotlin.Metadata;
import org.jetbrains.annotations.Nullable;

public interface RcService {
    
    File getReportCard( Long var1,  ReportCardRequest var2);
}
