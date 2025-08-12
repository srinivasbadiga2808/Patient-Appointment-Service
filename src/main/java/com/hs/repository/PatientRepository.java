package com.example.hs.repository;

import com.example.hs.entity.Patient;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.Optional;

public interface PatientRepository extends JpaRepository<Patient, Long> {
  Optional<Patient> findByMedicalRecordNumber(String mrn);
  boolean existsByMedicalRecordNumber(String mrn);
}
