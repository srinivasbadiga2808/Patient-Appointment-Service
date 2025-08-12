package com.example.hs.dto;

import lombok.*;
import jakarta.validation.constraints.*;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class PatientDTO {
  private Long id;

  @NotBlank
  private String firstName;

  @NotBlank
  private String lastName;

  @NotBlank
  private String medicalRecordNumber;

  private String gender;
  private String phone;

  @Email
  private String email;

  private String allergies;
  private String medicalHistory;
}
