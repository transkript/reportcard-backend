package com.transkript.reportcard.business.service.impl;

import com.transkript.reportcard.api.dto.request.ClassListRequest;
import com.transkript.reportcard.api.dto.response.ClassListResponse;
import com.transkript.reportcard.api.dto.response.StudentGrade;
import com.transkript.reportcard.business.mapper.ClassLevelSubMapper;
import com.transkript.reportcard.business.mapper.GradeMapper;
import com.transkript.reportcard.business.mapper.StudentMapper;
import com.transkript.reportcard.business.mapper.SubjectMapper;
import com.transkript.reportcard.business.service.i.AcademicYearService;
import com.transkript.reportcard.business.service.i.ClassLevelSubService;
import com.transkript.reportcard.business.service.i.ClassListService;
import com.transkript.reportcard.business.service.i.SequenceService;
import com.transkript.reportcard.business.service.i.StudentApplicationTrialService;
import com.transkript.reportcard.business.service.i.SubjectRegistrationService;
import com.transkript.reportcard.business.service.i.SubjectService;
import com.transkript.reportcard.data.entity.AcademicYear;
import com.transkript.reportcard.data.entity.ClassLevel;
import com.transkript.reportcard.data.entity.ClassLevelSub;
import com.transkript.reportcard.data.entity.Sequence;
import com.transkript.reportcard.data.entity.Subject;
import com.transkript.reportcard.data.entity.SubjectRegistration;
import com.transkript.reportcard.data.entity.composite.GradeKey;
import com.transkript.reportcard.data.entity.relation.Grade;
import com.transkript.reportcard.data.entity.relation.StudentApplicationTrial;
import com.transkript.reportcard.data.enums.GradeDesc;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class ClassListServiceImpl implements ClassListService {
    private final AcademicYearService academicYearService;
    private final ClassLevelSubService classLevelSubService;
    private final SubjectService subjectService;
    private final SequenceService sequenceService;
    private final SubjectRegistrationService subjectRegistrationService;
    private final StudentApplicationTrialService studentApplicationTrialService;
    private final GradeMapper gradeMapper;
    private final SubjectMapper subjectMapper;
    private final ClassLevelSubMapper classLevelSubMapper;
    private final StudentMapper studentMapper;

    @Override
    public ClassListResponse getClassList(ClassListRequest request) {
        AcademicYear academicYear = academicYearService.getEntity(request.academicYearId());
        ClassLevelSub classLevelSub = classLevelSubService.getEntity(request.classId());
        ClassLevel classLevel = classLevelSub.getClassLevel();
        Subject subject = subjectService.getEntity(request.subjectId());
        Sequence sequence = sequenceService.getEntity(request.sequenceId());


        List<StudentApplicationTrial> sats = studentApplicationTrialService.getEntitiesByYear(academicYear.getId()).stream()
                .filter((sat) -> sat.getStudentApplication().getClassLevelSub().getId().equals(classLevelSub.getId()))
                .toList();


        List<StudentGrade> studentGrades = new ArrayList<>();
        sats.forEach(sat -> {
            SubjectRegistration subjectRegistration = subjectRegistrationService.getEntityBySubjectAndSat(subject.getId(), sat.getId());

            if (subjectRegistration.getGrades().stream().noneMatch(grade -> grade.getSequence().equals(sequence))) {
                studentGrades.add(new StudentGrade(
                        studentMapper.mapStudentToDto(subjectRegistration.getStudentApplicationTrial().getStudentApplication().getStudent()),
                        gradeMapper.gradeToGradeDto(
                                Grade.builder().key(GradeKey.builder().sequenceId(sequence.getId()).registrationId(subjectRegistration.getId()).build())
                                        .score(null).description(GradeDesc.NOT_GRADED).registration(subjectRegistration).sequence(sequence).build()
                        )
                ));
            } else {
                subjectRegistration.getGrades().stream().filter(grade -> grade.getSequence().equals(sequence)).forEach(
                        grade -> studentGrades.add(new StudentGrade(
                                studentMapper.mapStudentToDto(subjectRegistration.getStudentApplicationTrial().getStudentApplication().getStudent()),
                                gradeMapper.gradeToGradeDto(grade)
                        ))
                );
            }
        });

        return new ClassListResponse(
                String.format("%s %s", classLevel.getName(), classLevelSub.getName()),
                sequence.getName(),
                subjectMapper.subjectToSubjectDto(subject),
                classLevelSubMapper.mapClassLevelSubToDto(classLevelSub),
                studentGrades
        );
    }
}
