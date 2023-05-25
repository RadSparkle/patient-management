package com.hd.patient.api.visit.repository;

import com.hd.patient.api.visit.entity.VisitEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface VisitRepository extends JpaRepository<VisitEntity, Long> {
}
