package com.maharjanworks.doctor_patient_api.service;

import com.maharjanworks.doctor_patient_api.dto.PatientDTO;

import java.net.URI;
import java.util.List;

public interface PatientService {

    String create(PatientDTO dto);

    List<PatientDTO> retrieveAll();

    PatientDTO getById(int id);
}
