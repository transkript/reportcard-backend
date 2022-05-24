package com.transkript.reportcard.data.repository;


import com.transkript.reportcard.data.entity.ClassLevel;
import com.transkript.reportcard.data.entity.ClassLevelSub;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ClassLevelSubRepository extends JpaRepository<ClassLevelSub, Long> {
    List<ClassLevelSub> findAllByClassLevel(ClassLevel classLevel);
}
