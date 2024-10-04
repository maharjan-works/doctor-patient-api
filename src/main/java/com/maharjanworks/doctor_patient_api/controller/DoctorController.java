package com.maharjanworks.doctor_patient_api.controller;

import com.maharjanworks.doctor_patient_api.dto.DoctorDTO;
import com.maharjanworks.doctor_patient_api.exception.NullFieldsException;
import com.maharjanworks.doctor_patient_api.response.AppResponse;
import com.maharjanworks.doctor_patient_api.service.DoctorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/doctors")
public class DoctorController {

    @Autowired
    private DoctorService doctorService;

    @PostMapping()
    public ResponseEntity<AppResponse> create(@RequestBody DoctorDTO dto) throws NullFieldsException {
        return  new ResponseEntity<>(this.doctorService.create(dto), HttpStatus.CREATED );
    }
}
