package com.example.hs.entity;

import jakarta.persistence.*;
import lombok.*;
import java.time.Instant;

@Entity
@Table(name = "patients")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Patient {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;

  @Column(nullable = false)
  private String firstName;

  @Column(nullable = false)
  private String lastName;

  @Column(nullable = false, unique = true)
  private String medicalRecordNumber; // MRN

  private String gender;

  private String phone;

  private String email;

  @Column(length = 2000)
  private String allergies;

  @Column(length = 5000)
  private String medicalHistory;

  private Instant createdAt;
  private Instant updatedAt;
}
