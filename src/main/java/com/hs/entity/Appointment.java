package com.example.hs.entity;

import jakarta.persistence.*;
import lombok.*;
import java.time.Instant;

@Entity
@Table(name = "appointments",
       uniqueConstraints = @UniqueConstraint(columnNames = {"doctor_id", "start_time"}))
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Appointment {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;

  @ManyToOne(optional = false)
  @JoinColumn(name = "patient_id")
  private Patient patient;

  @ManyToOne(optional = false)
  @JoinColumn(name = "doctor_id")
  private Doctor doctor;

  @Column(name = "start_time", nullable = false)
  private Instant startTime;

  @Column(name = "end_time", nullable = false)
  private Instant endTime;

  @Column(nullable = false)
  private String status; // SCHEDULED, COMPLETED, CANCELED

  private Instant createdAt;
  private Instant updatedAt;
}
