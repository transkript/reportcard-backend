package com.transkript.reportcard.data.repository;

import com.transkript.reportcard.data.entity.School;
import com.transkript.reportcard.data.entity.SchoolSettings;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface SchoolSettingsRepository extends JpaRepository<SchoolSettings, Long> {
    Optional<SchoolSettings> findBySchool(School school);
}
