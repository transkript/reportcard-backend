package com.transkript.reportcard.data.repository;


import com.transkript.reportcard.data.entity.School;
import com.transkript.reportcard.data.entity.Section;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface SectionRepository extends JpaRepository<Section, Long> {
    List<Section> findAllBySchool(School school);
}
