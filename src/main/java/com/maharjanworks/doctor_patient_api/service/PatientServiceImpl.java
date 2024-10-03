package com.maharjanworks.doctor_patient_api.service;

import com.maharjanworks.doctor_patient_api.dto.PatientDTO;
import com.maharjanworks.doctor_patient_api.exception.EmailAlreadyExistsException;
import com.maharjanworks.doctor_patient_api.exception.UsernameAlreadyExistsException;
import com.maharjanworks.doctor_patient_api.model.Patient;
import com.maharjanworks.doctor_patient_api.repository.PatientRepository;
import jakarta.transaction.Transactional;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
@Transactional
public class PatientServiceImpl implements PatientService{

    @Autowired
    private PatientRepository patientRepository;

    @Override
    public String create(PatientDTO dto) {
        Optional<Patient> optionalByUsername = this.patientRepository.findByUsername(dto.getUsername());
        if (optionalByUsername.isPresent()){
            throw new UsernameAlreadyExistsException("username is already existed");
        }
        Optional<Patient> optionalByEmail = this.patientRepository.findByEmail(dto.getEmail());
        if (optionalByEmail.isPresent()){
            throw new EmailAlreadyExistsException("email is already existed.");
        }
        Patient patient = new Patient();
        BeanUtils.copyProperties(dto, patient);
        patient.setRegisteredDate(LocalDate.now());
        this.patientRepository.save(patient);
        return "patient registered successfully.";
    }

    @Override
    public List<PatientDTO> retrieveAll() {
        List<Patient> patients= this.patientRepository.findAll();
        List<PatientDTO> dtoList = new ArrayList<>();

        for(Patient patient:patients){
            PatientDTO dto = new PatientDTO();
            BeanUtils.copyProperties(patient,dto);
            dtoList.add(dto);
        }
        return dtoList;
    }
}
