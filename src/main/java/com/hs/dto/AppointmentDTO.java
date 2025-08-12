package com.example.hs.dto;

import lombok.*;
import jakarta.validation.constraints.*;
import java.time.Instant;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class AppointmentDTO {
  private Long id;

  @NotNull
  private Long patientId;

  @NotNull
  private Long doctorId;

  @NotNull
  private Instant startTime;

  @NotNull
  private Instant endTime;

  private String status;
}
