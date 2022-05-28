package com.trankscript.reportcard;

import com.deepoove.poi.XWPFTemplate;
import com.deepoove.poi.util.ResourceLoader;
import com.transkript.reportcard.model.ReportCard;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

public class ReportCardProcess {
    public static void generateReportCard(ReportCard card) throws IOException {
        String schoolName = card.getRcSchool().getName();

        Map<String, Object> data = new HashMap<>();
        data.put("school_name", schoolName);

        XWPFTemplate template = XWPFTemplate.compile("/home/kanye/IdeaProjects/Maven/reportcard-backend/rc-process/src/main/resources/docs/reportcard.docx")
                .render(data);
        template.writeAndClose(new FileOutputStream(card.getRcStudent().getRegNum() + ".docx"));
    }
}
