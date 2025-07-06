package com.medical.repository;

import com.medical.model.Annotation;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AnnotationRepository extends JpaRepository<Annotation, Long> { }
