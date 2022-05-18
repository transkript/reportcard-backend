package com.transkript.reportcard.data.repository;


import com.transkript.reportcard.data.entity.StudentApplication;
import com.transkript.reportcard.data.entity.Subject;
import com.transkript.reportcard.data.entity.composite.SubjectRegistrationKey;
import com.transkript.reportcard.data.entity.relation.SubjectRegistration;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface SubjectRegistrationRepository extends JpaRepository<SubjectRegistration, SubjectRegistrationKey> {
    Optional<SubjectRegistration> findBySubject(Subject subject);

    List<SubjectRegistration> findAllByStudentApplication(StudentApplication studentApplication);
}
