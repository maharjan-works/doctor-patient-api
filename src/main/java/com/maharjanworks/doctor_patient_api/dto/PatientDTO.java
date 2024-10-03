package com.maharjanworks.doctor_patient_api.dto;

import jakarta.persistence.Column;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.cglib.core.Local;

import java.time.LocalDate;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class PatientDTO {

    private String firstName;
    private String lastName;
    private String dob;
    private String email;
    private String username;
    private String password;
    private LocalDate registeredDate;


}
