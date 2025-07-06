package com.medical.repository;

import com.medical.model.DicomImage;
import org.springframework.data.jpa.repository.JpaRepository;

public interface DicomImageRepository extends JpaRepository<DicomImage, Long> { }
