package com.transkript.reportcard.data.repository;


import com.transkript.reportcard.data.entity.AcademicYear;
import com.transkript.reportcard.data.entity.ClassLevelSub;
import com.transkript.reportcard.data.entity.Student;
import com.transkript.reportcard.data.entity.StudentApplication;
import com.transkript.reportcard.data.entity.composite.ApplicationKey;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface StudentApplicationRepository extends JpaRepository<StudentApplication, ApplicationKey> {
    List<StudentApplication> findAllByAcademicYear(AcademicYear academicYear);
    List<StudentApplication> findAllByAcademicYearAndClassLevelSub(AcademicYear academicYear, ClassLevelSub classLevelSub);
}
