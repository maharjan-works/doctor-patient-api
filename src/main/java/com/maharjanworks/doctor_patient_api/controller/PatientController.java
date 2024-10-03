package com.maharjanworks.doctor_patient_api.controller;

import com.maharjanworks.doctor_patient_api.dto.PatchDTO;
import com.maharjanworks.doctor_patient_api.dto.PatientDTO;
import com.maharjanworks.doctor_patient_api.respone.AppResponse;
import com.maharjanworks.doctor_patient_api.service.PatientService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;
import java.util.Objects;

@RestController
@RequestMapping("/api/v1/patients")
public class PatientController {

    @Autowired
    private PatientService patientService;

    @PostMapping()
    public ResponseEntity<String> create(@RequestBody PatientDTO dto){
        return new ResponseEntity<>(this.patientService.create(dto), HttpStatus.CREATED);
//       return ResponseEntity.created(this.patientService.create(dto));
    }

    @GetMapping()
    public ResponseEntity<List<PatientDTO>> retrieveAll(){
        return ResponseEntity.ok(this.patientService.retrieveAll());
    }

    @GetMapping("/{id}")
    public ResponseEntity<PatientDTO> getById(@PathVariable("id") int id){
        return ResponseEntity.ok(this.patientService.getById(id));
    }

    @PutMapping()
    public ResponseEntity<AppResponse> update(@RequestBody PatientDTO dto){
        return ResponseEntity.ok(this.patientService.update(dto));
    }

    @PatchMapping()
    public ResponseEntity<AppResponse> patch(@RequestBody PatchDTO request){
        return ResponseEntity.ok(this.patientService.patch(request));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable int id){
        boolean status = this.patientService.deleteById(id);
        return new ResponseEntity<>(status? HttpStatus.NO_CONTENT:HttpStatus.NOT_FOUND);
    }




}
