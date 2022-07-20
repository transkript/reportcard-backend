package com.transkript.reportcard.data.repository;


import com.transkript.reportcard.data.entity.ClassLevel;
import com.transkript.reportcard.data.entity.Section;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface ClassLevelRepository extends JpaRepository<ClassLevel, Long> {
    List<ClassLevel> findAllBySection(Section section);

    Optional<ClassLevel> findByName(String name);
}
