package com.transkript.reportcard.api.controller;

import com.transkript.reportcard.business.service.SchoolService;
import com.transkript.reportcard.business.service.SectionService;
import com.transkript.reportcard.data.entity.AcademicYear;
import com.transkript.reportcard.data.entity.School;
import com.transkript.reportcard.data.entity.Section;
import com.transkript.reportcard.data.entity.Subject;
import com.transkript.reportcard.data.repository.AcademicYearRepository;
import com.transkript.reportcard.data.repository.SchoolRepository;
import com.transkript.reportcard.data.repository.SectionRepository;
import com.transkript.reportcard.data.repository.SubjectRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@Slf4j
@RestController
@RequiredArgsConstructor
@RequestMapping(value = "/api/default")
public class DefaultController {
    private final SchoolRepository schoolRepository;
    private final SectionRepository sectionRepository;
    private final SubjectRepository subjectRepository;

    private final AcademicYearRepository academicYearRepository;

    private School defaultSchool = School.builder().id(null).name("Default School").build();
    private final Section[] sections = new Section[] {
            Section.builder().id(null).name("Default Section 1").category("Category").classLevels(List.of()).subjects(List.of()).build(),
            Section.builder().id(null).name("Default Section 2").category("Category").classLevels(List.of()).subjects(List.of()).build(),
    };
    private final Subject[] subjects = new Subject[] {
            Subject.builder().id(null).name("Maths").code("MAT").section(null).build(),
            Subject.builder().id(null).name("English").code("ENG").section(null).build(),
            Subject.builder().id(null).name("French").code("FRE").section(null).build(),
    };

    private final AcademicYear academicYear = AcademicYear.builder().id(null).name("2020/2021").build();
    @PostMapping(value = "create")
    public String createDefaults() {
        defaultSchool = schoolRepository.save(defaultSchool);
        for(int i = 0; i < sections.length; i++) {
            sections[i].setSchool(defaultSchool);
            sections[i] = sectionRepository.save(sections[i]);
        }
        for(int i = 0; i < subjects.length - 1; i++) {
            subjects[i].setSection(sections[0]);
            subjectRepository.save(subjects[i]);
        }
        {
            subjects[subjects.length - 1].setSection(sections[1]);
            subjectRepository.save(subjects[subjects.length - 1]);
        }
        academicYearRepository.save(academicYear);
        return "Success";
    }
}
