package com.example.hs.repository;

import com.example.hs.entity.Appointment;
import com.example.hs.entity.Doctor;
import org.springframework.data.jpa.repository.JpaRepository;
import java.time.Instant;
import java.util.List;

public interface AppointmentRepository extends JpaRepository<Appointment, Long> {
  List<Appointment> findByDoctorAndStartTimeBetween(Doctor doctor, Instant start, Instant end);
  List<Appointment> findByPatientId(Long patientId);
}
