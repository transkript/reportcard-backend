package com.transkript.reportcard.business.service.impl;

import com.transkript.reportcard.api.dto.GradeDto;
import com.transkript.reportcard.api.dto.request.ClassListRequest;
import com.transkript.reportcard.api.dto.response.ClassListResponse;
import com.transkript.reportcard.business.mapper.ClassLevelSubMapper;
import com.transkript.reportcard.business.mapper.GradeMapper;
import com.transkript.reportcard.business.mapper.SubjectMapper;
import com.transkript.reportcard.business.service.AcademicYearService;
import com.transkript.reportcard.business.service.ClassLevelSubService;
import com.transkript.reportcard.business.service.ClassListService;
import com.transkript.reportcard.business.service.SequenceService;
import com.transkript.reportcard.business.service.SubjectRegistrationService;
import com.transkript.reportcard.business.service.SubjectService;
import com.transkript.reportcard.data.entity.AcademicYear;
import com.transkript.reportcard.data.entity.ClassLevel;
import com.transkript.reportcard.data.entity.ClassLevelSub;
import com.transkript.reportcard.data.entity.Sequence;
import com.transkript.reportcard.data.entity.StudentApplication;
import com.transkript.reportcard.data.entity.Subject;
import com.transkript.reportcard.data.entity.SubjectRegistration;
import com.transkript.reportcard.data.entity.composite.GradeKey;
import com.transkript.reportcard.data.entity.relation.Grade;
import com.transkript.reportcard.data.enums.GradeDesc;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
@RequiredArgsConstructor
public class ClassListServiceImpl implements ClassListService {
    private final AcademicYearService academicYearService;
    private final ClassLevelSubService classLevelSubService;
    private final SubjectService subjectService;
    private final SequenceService sequenceService;
    private final SubjectRegistrationService subjectRegistrationService;
    private final GradeMapper gradeMapper;
    private final SubjectMapper subjectMapper;
    private final ClassLevelSubMapper classLevelSubMapper;
    @Override
    public ClassListResponse getClassList(ClassListRequest request) {
        AcademicYear academicYear = academicYearService.getAcademicYearEntity(request.academicYearId());
        ClassLevelSub classLevelSub = classLevelSubService.getClassLevelSubEntity(request.classId());
        ClassLevel classLevel = classLevelSub.getClassLevel();
        Subject subject = subjectService.getSubjectEntity(request.subjectId());
        Sequence sequence = sequenceService.getSequenceEntity(request.sequenceId());


        List<StudentApplication> studentApplications = classLevelSub.getStudentApplications().stream()
                .filter(application -> application.getAcademicYear().equals(academicYear)).toList();
        Map<Long, Grade> grades = new HashMap<>();
        studentApplications.forEach(application -> {
            SubjectRegistration subjectRegistration = subjectRegistrationService.getSubjectRegistrationEntity(application.getApplicationKey(), subject.getId());

            if(subjectRegistration.getGrades().stream().noneMatch(grade -> grade.getSequence().equals(sequence))) {
                grades.put(
                        subjectRegistration.getStudentApplication().getStudent().getId(),
                        Grade.builder().gradeKey(GradeKey.builder().sequenceId(sequence.getId()).registrationId(subjectRegistration.getId()).build())
                                .score(null).description(GradeDesc.NOT_GRADED).subjectRegistration(subjectRegistration).sequence(sequence).build()
                );
            } else {
                subjectRegistration.getGrades().stream().filter(grade -> grade.getSequence().equals(sequence))
                        .forEach(grade -> grades.put(subjectRegistration.getStudentApplication().getStudent().getId(), grade));
            }
        });

        Map<Long, GradeDto> studentGrades = new HashMap<>();
        grades.forEach((key, value) -> studentGrades.put(key, gradeMapper.mapGradeToDto(value)));

        return new ClassListResponse(
                String.format("%s %s", classLevel.getName(), classLevelSub.getName()),
                sequence.getName(),
                subjectMapper.mapSubjectToDto(subject),
                classLevelSubMapper.mapClassLevelSubToDto(classLevelSub),
                studentGrades
        );
    }
}
