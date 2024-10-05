package com.maharjanworks.doctor_patient_api.controller;

import com.maharjanworks.doctor_patient_api.dto.DoctorDTO;
import com.maharjanworks.doctor_patient_api.dto.PatchDTO;
import com.maharjanworks.doctor_patient_api.exception.NullFieldsException;
import com.maharjanworks.doctor_patient_api.model.Doctor;
import com.maharjanworks.doctor_patient_api.response.AppResponse;
import com.maharjanworks.doctor_patient_api.service.DoctorService;
import org.apache.coyote.Response;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/doctors")
public class DoctorController {

    @Autowired
    private DoctorService doctorService;

    @PostMapping()
    public ResponseEntity<AppResponse> create(@RequestBody DoctorDTO dto) throws NullFieldsException {
        return  new ResponseEntity<>(this.doctorService.create(dto), HttpStatus.CREATED );
    }

    @GetMapping()
    public ResponseEntity<List<DoctorDTO>> findAll(){
        return ResponseEntity.ok(this.doctorService.findAll());
    }

    @GetMapping("/{id}")
    public ResponseEntity<DoctorDTO> findById(@PathVariable int id){
        return ResponseEntity.ok(this.doctorService.findById(id));
    }

    @PutMapping()
    public ResponseEntity<AppResponse> update(@RequestBody DoctorDTO request) throws NullFieldsException {
        return ResponseEntity.ok(this.doctorService.update(request));
    }

    @PatchMapping()
    public ResponseEntity<AppResponse> patch(@RequestBody PatchDTO request){
        return ResponseEntity.ok(this.doctorService.patch(request));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<AppResponse> delete(@PathVariable int id){
        return ResponseEntity.ok(this.doctorService.delete(id));
    }
}
