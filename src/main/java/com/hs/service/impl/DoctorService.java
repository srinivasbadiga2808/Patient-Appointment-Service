package com.example.hs.service;

import com.example.hs.dto.DoctorDTO;
import java.util.List;

public interface DoctorService {
  DoctorDTO create(DoctorDTO dto);
  DoctorDTO getById(Long id);
  List<DoctorDTO> listAll();
}
