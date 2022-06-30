package com.transkript.reportcard.data.repository;

import com.transkript.reportcard.data.entity.AcademicYear;
import com.transkript.reportcard.data.entity.relation.StudentApplication;
import com.transkript.reportcard.data.entity.relation.StudentApplicationTrial;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface StudentApplicationTrialRepository extends JpaRepository<StudentApplicationTrial, Long> {
    List<StudentApplicationTrial> findAllByAcademicYear(AcademicYear year);

    List<StudentApplicationTrial> findAllByStudentApplication(StudentApplication application);

    Optional<StudentApplicationTrial> findByAcademicYearAndStudentApplication(AcademicYear academicYear, StudentApplication studentApplication);
}
