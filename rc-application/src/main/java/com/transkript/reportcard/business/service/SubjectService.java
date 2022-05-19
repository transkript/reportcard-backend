package com.transkript.reportcard.business.service;


import com.transkript.reportcard.api.dto.SubjectDto;
import com.transkript.reportcard.data.entity.Subject;

import java.util.List;

public interface SubjectService {

    List<SubjectDto> getSubjects();

    SubjectDto getSubject(Long id);

    String addSubject(SubjectDto subjectDto);

    String updateSubject(Long id, SubjectDto subjectDto);

    String deleteSubject(Long id);

    Subject getSubjectEntity(Long subjectId);
}
