package com.medical.repository;

import com.medical.model.Report;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ReportRepository extends JpaRepository<Report, Long> {
    long countByStatusNot(String status);
}