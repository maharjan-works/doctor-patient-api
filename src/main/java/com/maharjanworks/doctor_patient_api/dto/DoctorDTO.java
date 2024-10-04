package com.maharjanworks.doctor_patient_api.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class DoctorDTO {

    private int id;
    private String firstName;
    private String lastName;
    private String email;
    private String username;
    private String specialty;
    private LocalDate registeredAt;
}
