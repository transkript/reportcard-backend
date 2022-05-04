package com.transkript.reportcard.data.repository;


import com.transkript.reportcard.data.entity.Sequence;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface SequenceRepository extends JpaRepository<Sequence, Long> {
}
