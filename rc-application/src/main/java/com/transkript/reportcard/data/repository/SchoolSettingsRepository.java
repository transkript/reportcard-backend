package com.transkript.reportcard.data.repository;

import com.transkript.reportcard.data.entity.extra.SchoolSettings;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface SchoolSettingsRepository extends JpaRepository<SchoolSettings, Long> {

}
