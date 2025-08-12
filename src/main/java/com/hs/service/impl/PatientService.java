package com.example.hs.service;

import com.example.hs.dto.PatientDTO;
import java.util.List;

public interface PatientService {
  PatientDTO create(PatientDTO dto);
  PatientDTO getById(Long id);
  PatientDTO update(Long id, PatientDTO dto);
  List<PatientDTO> listAll();
  void delete(Long id);
}
