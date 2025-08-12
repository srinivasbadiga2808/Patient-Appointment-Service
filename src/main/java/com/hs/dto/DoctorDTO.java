package com.example.hs.dto;

import lombok.*;
import jakarta.validation.constraints.*;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class DoctorDTO {
  private Long id;

  @NotBlank
  private String firstName;

  @NotBlank
  private String lastName;

  @NotBlank
  private String licenseNumber;

  private String specialty;
  private String phone;
}
