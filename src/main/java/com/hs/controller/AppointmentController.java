package com.example.hs.controller;

import com.example.hs.dto.AppointmentDTO;
import com.example.hs.service.AppointmentService;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import java.net.URI;
import java.util.List;

@RestController
@RequestMapping("/api/v1/appointments")
public class AppointmentController {

  private final AppointmentService service;

  public AppointmentController(AppointmentService service) {
    this.service = service;
  }

  @PreAuthorize("hasAnyRole('ADMIN','RECEPTIONIST')")
  @PostMapping
  public ResponseEntity<AppointmentDTO> schedule(@Valid @RequestBody AppointmentDTO dto) {
    AppointmentDTO created = service.schedule(dto);
    return ResponseEntity.created(URI.create("/api/v1/appointments/" + created.getId())).body(created);
  }

  @PreAuthorize("hasAnyRole('ADMIN','DOCTOR','RECEPTIONIST')")
  @GetMapping("/{id}")
  public ResponseEntity<AppointmentDTO> get(@PathVariable Long id) {
    return ResponseEntity.ok(service.getById(id));
  }

  @PreAuthorize("hasAnyRole('ADMIN','DOCTOR','RECEPTIONIST')")
  @GetMapping("/patient/{patientId}")
  public ResponseEntity<List<AppointmentDTO>> listByPatient(@PathVariable Long patientId) {
    return ResponseEntity.ok(service.listByPatient(patientId));
  }

  @PreAuthorize("hasAnyRole('ADMIN','RECEPTIONIST')")
  @PostMapping("/{id}/cancel")
  public ResponseEntity<AppointmentDTO> cancel(@PathVariable Long id) {
    return ResponseEntity.ok(service.cancel(id));
  }
}
