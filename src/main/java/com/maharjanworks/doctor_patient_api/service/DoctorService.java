package com.maharjanworks.doctor_patient_api.service;

import com.maharjanworks.doctor_patient_api.dto.DoctorDTO;
import com.maharjanworks.doctor_patient_api.exception.NullFieldsException;
import com.maharjanworks.doctor_patient_api.response.AppResponse;

import java.net.URI;

public interface DoctorService {
    AppResponse create(DoctorDTO dto) throws NullFieldsException;
}
