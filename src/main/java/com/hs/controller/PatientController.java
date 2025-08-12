package com.example.hs.controller;

import com.example.hs.dto.PatientDTO;
import com.example.hs.service.PatientService;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import java.net.URI;
import java.util.List;

@RestController
@RequestMapping("/api/v1/patients")
public class PatientController {

  private final PatientService service;

  public PatientController(PatientService service) {
    this.service = service;
  }

  @PreAuthorize("hasAnyRole('ADMIN','RECEPTIONIST')")
  @PostMapping
  public ResponseEntity<PatientDTO> create(@Valid @RequestBody PatientDTO dto) {
    PatientDTO created = service.create(dto);
    return ResponseEntity.created(URI.create("/api/v1/patients/" + created.getId())).body(created);
  }

  @PreAuthorize("hasAnyRole('ADMIN','DOCTOR','RECEPTIONIST')")
  @GetMapping("/{id}")
  public ResponseEntity<PatientDTO> get(@PathVariable Long id) {
    return ResponseEntity.ok(service.getById(id));
  }

  @PreAuthorize("hasAnyRole('ADMIN','DOCTOR','RECEPTIONIST')")
  @GetMapping
  public ResponseEntity<List<PatientDTO>> list() {
    return ResponseEntity.ok(service.listAll());
  }

  @PreAuthorize("hasAnyRole('ADMIN','RECEPTIONIST')")
  @PutMapping("/{id}")
  public ResponseEntity<PatientDTO> update(@PathVariable Long id, @Valid @RequestBody PatientDTO dto) {
    return ResponseEntity.ok(service.update(id, dto));
  }

  @PreAuthorize("hasRole('ADMIN')")
  @DeleteMapping("/{id}")
  public ResponseEntity<Void> delete(@PathVariable Long id) {
    service.delete(id);
    return ResponseEntity.noContent().build();
  }
}
