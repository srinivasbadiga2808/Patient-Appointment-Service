
package com.example.hs.controller;

import com.example.hs.dto.DoctorDTO;
import com.example.hs.service.DoctorService;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import java.net.URI;
import java.util.List;

@RestController
@RequestMapping("/api/v1/doctors")
public class DoctorController {
  private final DoctorService service;

  public DoctorController(DoctorService service) {
    this.service = service;
  }

  @PreAuthorize("hasRole('ADMIN')")
  @PostMapping
  public ResponseEntity<DoctorDTO> create(@Valid @RequestBody DoctorDTO dto) {
    DoctorDTO created = service.create(dto);
    return ResponseEntity.created(URI.create("/api/v1/doctors/" + created.getId())).body(created);
  }

  @PreAuthorize("hasAnyRole('ADMIN','DOCTOR','RECEPTIONIST')")
  @GetMapping("/{id}")
  public ResponseEntity<DoctorDTO> get(@PathVariable Long id) {
    return ResponseEntity.ok(service.getById(id));
  }

  @PreAuthorize("hasAnyRole('ADMIN','DOCTOR','RECEPTIONIST')")
  @GetMapping
  public ResponseEntity<List<DoctorDTO>> list() {
    return ResponseEntity.ok(service.listAll());
  }
}
