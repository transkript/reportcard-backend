package com.transkript.reportcard.api.controller;

import com.transkript.reportcard.data.entity.AcademicYear;
import com.transkript.reportcard.data.entity.ClassLevel;
import com.transkript.reportcard.data.entity.ClassLevelSub;
import com.transkript.reportcard.data.entity.School;
import com.transkript.reportcard.data.entity.Section;
import com.transkript.reportcard.data.entity.Sequence;
import com.transkript.reportcard.data.entity.Student;
import com.transkript.reportcard.data.entity.Subject;
import com.transkript.reportcard.data.entity.Term;
import com.transkript.reportcard.data.entity.composite.ApplicationKey;
import com.transkript.reportcard.data.entity.relation.StudentApplication;
import com.transkript.reportcard.data.enums.Gender;
import com.transkript.reportcard.data.repository.AcademicYearRepository;
import com.transkript.reportcard.data.repository.ClassLevelRepository;
import com.transkript.reportcard.data.repository.ClassLevelSubRepository;
import com.transkript.reportcard.data.repository.SchoolRepository;
import com.transkript.reportcard.data.repository.SectionRepository;
import com.transkript.reportcard.data.repository.SequenceRepository;
import com.transkript.reportcard.data.repository.StudentApplicationRepository;
import com.transkript.reportcard.data.repository.StudentRepository;
import com.transkript.reportcard.data.repository.SubjectRepository;
import com.transkript.reportcard.data.repository.TermRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.time.LocalDateTime;
import java.util.Map;

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
    private final ClassLevelRepository classLevelRepository;
    private final ClassLevelSubRepository classLevelSubRepository;
    private final Subject[] subjects = new Subject[]{
            Subject.builder().id(null).name("Maths").coefficient(2).code("MAT").section(null).build(),
            Subject.builder().id(null).name("English").coefficient(2).code("ENG").section(null).build(),
            Subject.builder().id(null).name("French").coefficient(2).code("FRE").section(null).build(),
    };
    private final Term[] terms = new Term[]{
            Term.builder().id(null).name("First Term").build(),
            Term.builder().id(null).name("Second Term").build(),
            Term.builder().id(null).name("Third Term").build(),
    };
    private final Sequence[] sequences = new Sequence[]{
            Sequence.builder().id(null).name("First Sequence").build(),
            Sequence.builder().id(null).name("Second Sequence").build(),
            Sequence.builder().id(null).name("Third Sequence").build(),
            Sequence.builder().id(null).name("Fourth Sequence").build(),
            Sequence.builder().id(null).name("Fifth Sequence").build(),
            Sequence.builder().id(null).name("Sixth Sequence").build(),
    };
    private final StudentApplication studentApplication = StudentApplication.builder().key(null).student(null)
            .build();
    private final Section[] sections = {
            Section.builder().id(null).name("Secondary School").category("TECHNICAL").build(),
            Section.builder().id(null).name("High School").category("TECHNICAL").build()
    };
    private final ClassLevel[] classLevels = {
            ClassLevel.builder().id(null).name("Form 1").build(),
            ClassLevel.builder().id(null).name("Form 2").build(),
            ClassLevel.builder().id(null).name("Form 3").build(),
            ClassLevel.builder().id(null).name("Form 4").build(),
            ClassLevel.builder().id(null).name("Form 5").build(),
    };
    private School defaultSchool = School.builder().id(null).name("Default School").build();
    private AcademicYear academicYear = AcademicYear.builder().id(null).name("2020/2021").build();
    private Student student = Student.builder()
            .id(null).name("John Bae").dob(LocalDateTime.now())
            .pob("Cameroon").regNum("123456789").gender(Gender.MALE).build();

    @PostMapping(value = "/create")
    public String createDefaults() {
        defaultSchool = schoolRepository.save(defaultSchool);


        student = studentRepository.save(student);
        academicYear = academicYearRepository.save(academicYear);


        for (int i = 0; i < sections.length; i++) {
            sections[i].setSchool(defaultSchool);
            sections[i] = sectionRepository.save(sections[i]);
        }
        for (int i = 0; i < classLevels.length; i++) {
            classLevels[i].setSection(sections[0]);
            classLevels[i] = classLevelRepository.save(classLevels[i]);

            classLevels[i].getClassLevelSubs().add(classLevelSubRepository.save(
                    ClassLevelSub.builder().id(null).name("A").classLevel(classLevels[i]).build())
            );
            classLevels[i].getClassLevelSubs().add(classLevelSubRepository.save(
                    ClassLevelSub.builder().id(null).name("B").classLevel(classLevels[i]).build())
            );
        }
        for (int i = 0; i < subjects.length - 1; i++) {
            subjects[i].setSection(sections[0]);
            subjectRepository.save(subjects[i]);
        }
        {
            subjects[subjects.length - 1].setSection(sections[1]);
            subjectRepository.save(subjects[subjects.length - 1]);
        }
        {
            studentApplication.setKey(new ApplicationKey(
                    student.getId(),
                    classLevels[0].getClassLevelSubs().stream().findFirst().orElseThrow().getId()
            ));
            studentApplication.setStudent(student);
            studentApplication.setClassLevelSub(classLevels[0].getClassLevelSubs().stream().findFirst().orElseThrow());
            studentApplicationRepository.save(studentApplication);
        }
        for (int i = 0, j = 0; i < terms.length; i++, j = j + 2) {
            terms[i] = termRepository.save(terms[i]);

            {
                sequences[j].setTerm(terms[i]);
                sequences[j] = sequenceRepository.save(sequences[j]);
                sequences[j + 1].setTerm(terms[i]);
                sequences[j + 1] = sequenceRepository.save(sequences[j + 1]);
            }
        }
        return "Success";
    }

    @GetMapping(value = "/test")
    public ResponseEntity<Map<String, String>> test() {
        log.info("TESTING BACKEND");
        return ResponseEntity.ok(Map.of("message", "Success"));
    }

    @DeleteMapping(value = "/delete")
    public String deleteDefaults() {
        return "Success";
    }
}
