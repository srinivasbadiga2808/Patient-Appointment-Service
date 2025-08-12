package com.example.hs.service.impl;

import com.example.hs.dto.AppointmentDTO;
import com.example.hs.entity.Appointment;
import com.example.hs.entity.Doctor;
import com.example.hs.entity.Patient;
import com.example.hs.repository.AppointmentRepository;
import com.example.hs.repository.DoctorRepository;
import com.example.hs.repository.PatientRepository;
import com.example.hs.service.AppointmentService;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.Instant;
import java.util.List;
import java.util.stream.Collectors;

@Service
@Transactional
public class AppointmentServiceImpl implements AppointmentService {

  private final AppointmentRepository appointmentRepo;
  private final PatientRepository patientRepo;
  private final DoctorRepository doctorRepo;
  private final ModelMapper mapper;

  public AppointmentServiceImpl(AppointmentRepository appointmentRepo,
                                PatientRepository patientRepo,
                                DoctorRepository doctorRepo,
                                ModelMapper mapper) {
    this.appointmentRepo = appointmentRepo;
    this.patientRepo = patientRepo;
    this.doctorRepo = doctorRepo;
    this.mapper = mapper;
  }

  @Override
  public AppointmentDTO schedule(AppointmentDTO dto) {
    Patient patient = patientRepo.findById(dto.getPatientId())
            .orElseThrow(() -> new IllegalArgumentException("Patient not found"));
    Doctor doctor = doctorRepo.findById(dto.getDoctorId())
            .orElseThrow(() -> new IllegalArgumentException("Doctor not found"));

    // Basic conflict check: doctor availability
    List<Appointment> conflicts = appointmentRepo.findByDoctorAndStartTimeBetween(
            doctor, dto.getStartTime(), dto.getEndTime());
    if (!conflicts.isEmpty()) {
      throw new IllegalArgumentException("Doctor not available for the requested time");
    }

    Appointment appt = Appointment.builder()
            .patient(patient)
            .doctor(doctor)
            .startTime(dto.getStartTime())
            .endTime(dto.getEndTime())
            .status(dto.getStatus() == null ? "SCHEDULED" : dto.getStatus())
            .createdAt(Instant.now())
            .updatedAt(Instant.now())
            .build();

    Appointment saved = appointmentRepo.save(appt);
    return mapper.map(saved, AppointmentDTO.class);
  }

  @Override
  public AppointmentDTO getById(Long id) {
    Appointment a = appointmentRepo.findById(id).orElseThrow(() -> new IllegalArgumentException("Appointment not found"));
    return mapper.map(a, AppointmentDTO.class);
  }

  @Override
  public List<AppointmentDTO> listByPatient(Long patientId) {
    return appointmentRepo.findByPatientId(patientId).stream()
            .map(a -> mapper.map(a, AppointmentDTO.class)).collect(Collectors.toList());
  }

  @Override
  public AppointmentDTO cancel(Long id) {
    Appointment a = appointmentRepo.findById(id).orElseThrow(() -> new IllegalArgumentException("Appointment not found"));
    a.setStatus("CANCELED");
    a.setUpdatedAt(Instant.now());
    return mapper.map(appointmentRepo.save(a), AppointmentDTO.class);
  }
}
