package com.transkript.reportcard.api.controller;

import com.transkript.reportcard.data.entity.AcademicYear;
import com.transkript.reportcard.data.entity.School;
import com.transkript.reportcard.data.entity.Section;
import com.transkript.reportcard.data.entity.Sequence;
import com.transkript.reportcard.data.entity.Student;
import com.transkript.reportcard.data.entity.StudentApplication;
import com.transkript.reportcard.data.entity.Subject;
import com.transkript.reportcard.data.entity.Term;
import com.transkript.reportcard.data.enums.Gender;
import com.transkript.reportcard.data.repository.AcademicYearRepository;
import com.transkript.reportcard.data.repository.SchoolRepository;
import com.transkript.reportcard.data.repository.SectionRepository;
import com.transkript.reportcard.data.repository.SequenceRepository;
import com.transkript.reportcard.data.repository.StudentApplicationRepository;
import com.transkript.reportcard.data.repository.StudentRepository;
import com.transkript.reportcard.data.repository.SubjectRepository;
import com.transkript.reportcard.data.repository.TermRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.time.LocalDateTime;
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
    private final StudentApplicationRepository studentApplicationRepository;
    private final StudentRepository studentRepository;
    private final TermRepository termRepository;
    private final SequenceRepository sequenceRepository;

    private School defaultSchool = School.builder().id(null).name("Default School").build();
    private final Section[] sections = new Section[] {
            Section.builder().id(null).name("Default Section 1").category("Category").classLevels(List.of()).subjects(List.of()).build(),
            Section.builder().id(null).name("Default Section 2").category("Category").classLevels(List.of()).subjects(List.of()).build(),
    };
    private final Subject[] subjects = new Subject[] {
            Subject.builder().id(null).name("Maths").coefficient(2).code("MAT").section(null).build(),
            Subject.builder().id(null).name("English").coefficient(2).code("ENG").section(null).build(),
            Subject.builder().id(null).name("French").coefficient(2).code("FRE").section(null).build(),
    };
    private final Term[] terms = new Term[] {
            Term.builder().id(null).name("First Term").build(),
            Term.builder().id(null).name("Second Term").build(),
            Term.builder().id(null).name("Third Term").build(),
    };
    private final Sequence[] sequences = new Sequence[] {
            Sequence.builder().id(null).name("First Sequence").build(),
            Sequence.builder().id(null).name("Second Sequence").build(),
            Sequence.builder().id(null).name("Third Sequence").build(),
            Sequence.builder().id(null).name("Fourth Sequence").build(),
            Sequence.builder().id(null).name("Fifth Sequence").build(),
            Sequence.builder().id(null).name("Sixth Sequence").build(),
    };
    private AcademicYear academicYear = AcademicYear.builder().id(null).name("2020/2021").build();
    private Student student = Student.builder()
            .id(null).name("John Bae").dob(LocalDateTime.now())
            .pob("Cameroon").regNum("123456789").gender(Gender.MALE).build();
    private final StudentApplication studentApplication = StudentApplication.builder().id(null).student(null)
            .createdAt(LocalDateTime.now()).build();

    @PostMapping(value = "/create")
    public String createDefaults() {
        defaultSchool = schoolRepository.save(defaultSchool);
        student = studentRepository.save(student);
        academicYear = academicYearRepository.save(academicYear);
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
        {
            studentApplication.setStudent(student);
            studentApplication.setAcademicYear(academicYear);
            studentApplicationRepository.save(studentApplication);
        }
        for(int i = 0, j = 0; i < terms.length; i++, j = j+2) {
            terms[i].setAcademicYear(academicYear);
            terms[i] = termRepository.save(terms[i]);

            {
                sequences[j].setTerm(terms[i]);
                sequences[j] = sequenceRepository.save(sequences[j]);
                sequences[j+1].setTerm(terms[i]);
                sequences[j+1] = sequenceRepository.save(sequences[j+1]);
            }
        }
        return "Success";
    }

    @DeleteMapping(value = "/delete")
    public String deleteDefaults() {
        return "Success";
    }
}
