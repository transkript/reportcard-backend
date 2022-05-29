package com.transkript.reportcard.data.repository;


import com.transkript.reportcard.data.entity.StudentApplication;
import com.transkript.reportcard.data.entity.Subject;
import com.transkript.reportcard.data.entity.SubjectRegistration;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface SubjectRegistrationRepository extends JpaRepository<SubjectRegistration, Long> {
    Optional<SubjectRegistration> findBySubject(Subject subject);

    Optional<SubjectRegistration> findByStudentApplicationAndSubject(StudentApplication studentApplication, Subject subject);

    List<SubjectRegistration> findAllByStudentApplication(StudentApplication studentApplication);
}
