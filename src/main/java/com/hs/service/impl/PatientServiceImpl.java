package com.example.hs.service.impl;

import com.example.hs.dto.PatientDTO;
import com.example.hs.entity.Patient;
import com.example.hs.repository.PatientRepository;
import com.example.hs.service.PatientService;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.Instant;
import java.util.List;
import java.util.stream.Collectors;

@Service
@Transactional
public class PatientServiceImpl implements PatientService {

  private final PatientRepository repo;
  private final ModelMapper mapper;

  public PatientServiceImpl(PatientRepository repo, ModelMapper mapper) {
    this.repo = repo;
    this.mapper = mapper;
  }

  @Override
  public PatientDTO create(PatientDTO dto) {
    if (repo.existsByMedicalRecordNumber(dto.getMedicalRecordNumber())) {
      throw new IllegalArgumentException("MRN already exists");
    }
    Patient p = mapper.map(dto, Patient.class);
    Instant now = Instant.now();
    p.setCreatedAt(now);
    p.setUpdatedAt(now);
    Patient saved = repo.save(p);
    return mapper.map(saved, PatientDTO.class);
  }

  @Override
  public PatientDTO getById(Long id) {
    Patient p = repo.findById(id).orElseThrow(() -> new IllegalArgumentException("Patient not found"));
    return mapper.map(p, PatientDTO.class);
  }

  @Override
  public PatientDTO update(Long id, PatientDTO dto) {
    Patient p = repo.findById(id).orElseThrow(() -> new IllegalArgumentException("Patient not found"));
    p.setFirstName(dto.getFirstName());
    p.setLastName(dto.getLastName());
    p.setPhone(dto.getPhone());
    p.setEmail(dto.getEmail());
    p.setAllergies(dto.getAllergies());
    p.setMedicalHistory(dto.getMedicalHistory());
    p.setUpdatedAt(Instant.now());
    Patient saved = repo.save(p);
    return mapper.map(saved, PatientDTO.class);
  }

  @Override
  public List<PatientDTO> listAll() {
    return repo.findAll().stream().map(p -> mapper.map(p, PatientDTO.class)).collect(Collectors.toList());
  }

  @Override
  public void delete(Long id) {
    repo.deleteById(id);
  }
}
