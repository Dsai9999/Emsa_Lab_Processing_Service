package com.EMSA.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import com.EMSA.entity.LabResult;

public interface LabResultRepository extends JpaRepository<LabResult, Long> {
}