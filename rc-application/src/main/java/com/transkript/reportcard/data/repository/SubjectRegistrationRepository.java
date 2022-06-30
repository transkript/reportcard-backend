package com.transkript.reportcard.data.repository;


import com.transkript.reportcard.data.entity.Subject;
import com.transkript.reportcard.data.entity.SubjectRegistration;
import com.transkript.reportcard.data.entity.relation.StudentApplicationTrial;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface SubjectRegistrationRepository extends JpaRepository<SubjectRegistration, Long> {
    Optional<SubjectRegistration> findBySubject(Subject subject);

    Optional<SubjectRegistration> findByStudentApplicationTrialAndSubject(StudentApplicationTrial studentApplicationTrial, Subject subject);

    List<SubjectRegistration> findAllByStudentApplicationTrial(StudentApplicationTrial studentApplicationTrial);
}
