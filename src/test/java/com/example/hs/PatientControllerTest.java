package com.example.hs;

import com.example.hs.dto.PatientDTO;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.http.*;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class PatientControllerTest {

  @Autowired
  private TestRestTemplate rest;

  @Test
  void createAndGetPatient() {
    rest.getRestTemplate().getInterceptors().add((request, body, execution) -> {
      request.getHeaders().setBasicAuth("admin", "adminpass");
      return execution.execute(request, body);
    });

    PatientDTO dto = PatientDTO.builder()
            .firstName("John")
            .lastName("Doe")
            .medicalRecordNumber("MRN-001")
            .email("john@example.com")
            .build();

    ResponseEntity<PatientDTO> resp = rest.postForEntity("/api/v1/patients", dto, PatientDTO.class);
    assertThat(resp.getStatusCode()).isEqualTo(HttpStatus.CREATED);
    PatientDTO created = resp.getBody();
    assertThat(created).isNotNull();
    assertThat(created.getMedicalRecordNumber()).isEqualTo("MRN-001");
  }
}
