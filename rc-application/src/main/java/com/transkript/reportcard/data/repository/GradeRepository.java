package com.transkript.reportcard.data.repository;


import com.transkript.reportcard.data.entity.composite.GradeKey;
import com.transkript.reportcard.data.entity.relation.Grade;
import com.transkript.reportcard.data.entity.relation.SubjectRegistration;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface GradeRepository extends JpaRepository<Grade, GradeKey> {
    List<Grade> findAllBySubjectRegistration(SubjectRegistration subjectRegistration);
}
