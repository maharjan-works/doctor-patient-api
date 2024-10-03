package com.maharjanworks.doctor_patient_api.service;

import com.maharjanworks.doctor_patient_api.dto.PatientDTO;

import java.net.URI;

public interface PatientService {
    String create(PatientDTO dto);
}
