package com.transkript.reportcard;

import com.deepoove.poi.XWPFTemplate;
import com.deepoove.poi.config.Configure;
import com.deepoove.poi.plugin.table.LoopRowTableRenderPolicy;
import com.transkript.reportcard.document.DocReportCard;
import com.transkript.reportcard.model.ReportCard;

import java.io.FileOutputStream;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

public class ReportCardProcess {
    public static void generateReportCard(ReportCard card) throws IOException {
        String schoolName = card.getSchoolInfo().getName();

        DocReportCard docCard = new DocReportCard(card, card.getSchoolInfo().getTermName());

        Configure subjectsConfig = prepareSubjectsTableRenderPolicy();
        Map<String, Object> data = new HashMap<>();

        // school info
        data.put("school_name", docCard.getSchoolName());
        data.put("year_name", docCard.getYear());
        data.put("term_name", docCard.getTerm());

        // student info
        data.put("student_name", docCard.getStudentName());
        data.put("student_dob", docCard.getStudentDob());
        data.put("student_pob", docCard.getStudentPob());
        data.put("student_gender", docCard.getStudentGender());
        data.put("student_repeating", docCard.getStudentRepeating()); // TODO fix this
        data.put("student_regno", docCard.getStudentRegNum());
        data.put("student_class_id", 0); // TODO fix this
        data.put("average", docCard.getAverage());

        // class info
        data.put("class_name", docCard.getClassName());
        data.put("class_average", docCard.getClassAverage());
        data.put("class_rank", docCard.getClassRank());

        // subject info
        data.put("subjects_registered", docCard.getNumOfSubjects());
        data.put("subjects_passed", docCard.getSubjectsPassed());
        data.put("subjects", docCard.getSubjects());


        XWPFTemplate template = XWPFTemplate.compile(
                "/home/kanye/IdeaProjects/Maven/reportcard-backend/rc-process/src/main/resources/docs/reportcard.docx",
                subjectsConfig
                ).render(data);
        template.writeAndClose(new FileOutputStream(prepareReportCardDocName(
                card.getStudentInfo().getRegNum(),
                card.getSchoolInfo().getYearName(),
                card.getSchoolInfo().getTermName())
        ));
    }

    private static String prepareReportCardDocName(String regNo, String yearName, String termName) {
        yearName = yearName.replaceAll("/", "");
        termName = termName.replaceAll(" ", "");
        return regNo + "_" + yearName + "_" + termName + ".docx";
    }

    private static Configure prepareSubjectsTableRenderPolicy() {
        LoopRowTableRenderPolicy rowTableRenderPolicy = new LoopRowTableRenderPolicy();
        return Configure.builder()
                .bind("subjects", rowTableRenderPolicy)
                .build();
    }
}
