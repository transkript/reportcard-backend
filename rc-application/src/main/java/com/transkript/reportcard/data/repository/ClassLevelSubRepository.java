package com.transkript.reportcard.data.repository;


import com.transkript.reportcard.data.entity.ClassLevelSub;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ClassLevelSubRepository extends JpaRepository<ClassLevelSub, Long> {
}
