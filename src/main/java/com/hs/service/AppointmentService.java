package com.example.hs.service;

import com.example.hs.dto.AppointmentDTO;
import java.util.List;

public interface AppointmentService {
  AppointmentDTO schedule(AppointmentDTO dto);
  AppointmentDTO getById(Long id);
  List<AppointmentDTO> listByPatient(Long patientId);
  AppointmentDTO cancel(Long id);
}
