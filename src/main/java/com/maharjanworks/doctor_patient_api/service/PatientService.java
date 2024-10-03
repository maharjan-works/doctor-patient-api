package com.maharjanworks.doctor_patient_api.service;

import com.maharjanworks.doctor_patient_api.dto.PatchDTO;
import com.maharjanworks.doctor_patient_api.dto.PatientDTO;
import com.maharjanworks.doctor_patient_api.respone.AppResponse;

import java.net.URI;
import java.util.List;

public interface PatientService {

    String create(PatientDTO dto);

    List<PatientDTO> retrieveAll();

    PatientDTO getById(int id);

    AppResponse update(PatientDTO dto);

    AppResponse patch(PatchDTO request);
}
