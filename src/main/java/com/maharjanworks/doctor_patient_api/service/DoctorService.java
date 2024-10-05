package com.maharjanworks.doctor_patient_api.service;

import com.maharjanworks.doctor_patient_api.dto.DoctorDTO;
import com.maharjanworks.doctor_patient_api.dto.PatchDTO;
import com.maharjanworks.doctor_patient_api.exception.NullFieldsException;
import com.maharjanworks.doctor_patient_api.model.Doctor;
import com.maharjanworks.doctor_patient_api.response.AppResponse;

import java.net.URI;
import java.util.List;

public interface DoctorService {
    AppResponse create(DoctorDTO dto) throws NullFieldsException;

    List<DoctorDTO> findAll();

    DoctorDTO findById(int id);

    AppResponse update(DoctorDTO request) throws NullFieldsException;

    AppResponse patch(PatchDTO request);

    AppResponse delete(int id);
}
