package com.transkript.reportcard.data.repository;


import com.transkript.reportcard.data.entity.composite.ApplicationKey;
import com.transkript.reportcard.data.entity.relation.StudentApplication;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface StudentApplicationRepository extends JpaRepository<StudentApplication, ApplicationKey> {
}
