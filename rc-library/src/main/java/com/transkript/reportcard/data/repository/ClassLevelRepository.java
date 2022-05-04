package com.transkript.reportcard.data.repository;


import com.transkript.reportcard.data.entity.ClassLevel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ClassLevelRepository extends JpaRepository<ClassLevel, Long> {
}
