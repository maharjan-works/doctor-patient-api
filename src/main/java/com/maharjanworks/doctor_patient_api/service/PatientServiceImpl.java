package com.maharjanworks.doctor_patient_api.service;

import com.maharjanworks.doctor_patient_api.dto.PatchDTO;
import com.maharjanworks.doctor_patient_api.dto.PatientDTO;
import com.maharjanworks.doctor_patient_api.exception.EmailAlreadyExistsException;
import com.maharjanworks.doctor_patient_api.exception.PatientNotFoundException;
import com.maharjanworks.doctor_patient_api.exception.UsernameAlreadyExistsException;
import com.maharjanworks.doctor_patient_api.model.Patient;
import com.maharjanworks.doctor_patient_api.repository.PatientRepository;
import com.maharjanworks.doctor_patient_api.respone.AppResponse;
import jakarta.transaction.Transactional;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PatchMapping;

import java.time.LocalDate;
import java.time.LocalDateTime;
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

    @Override
    public PatientDTO getById(int id) {
        Optional<Patient> optional = this.patientRepository.findById(id);

        if(optional.isPresent()){
            Patient patient = optional.get();
            PatientDTO dto = new PatientDTO();
            BeanUtils.copyProperties(patient,dto);
            return dto;
        }
        throw new PatientNotFoundException("patient id: "+ id+ " not found");
    }

    @Override
    public AppResponse update(PatientDTO dto) {
        Optional<Patient> optional = this.patientRepository.findById(dto.getId());
        if (optional.isPresent()){
            Patient patient = new Patient();
            if(dto.getId() != 0){
                patient.setId(dto.getId());
            }
            if (dto.getFirstName() != null){
                patient.setFirstName(dto.getFirstName());
            }
            if (dto.getLastName() != null){
                patient.setLastName(dto.getLastName());
            }
            if (dto.getDob() != null){
                patient.setDob(dto.getDob());
            }
            if (dto.getEmail() != null ){
                patient.setEmail(dto.getEmail());
            }
            if (dto.getUsername() != null){
                patient.setUsername(dto.getUsername());
            }
            if(dto.getPassword() != null){
                patient.setPassword(dto.getPassword());
            }
            if(dto.getRegisteredDate() != null){
                patient.setRegisteredDate(dto.getRegisteredDate());
            }

            this.patientRepository.save(patient);
            return new AppResponse("patient updated", LocalDateTime.now());
        }
        throw new PatientNotFoundException("patient not found.");
    }

    @Override
    public AppResponse patch(PatchDTO request) {
        Optional<Patient> optional = this.patientRepository.findById(request.getId());
        if (optional.isPresent()){
            Patient patient = optional.get();
            if("firstName".equalsIgnoreCase(request.getAttributeName())){
                patient.setFirstName(request.getAttributeValue());
            }
            if("lastName".equalsIgnoreCase(request.getAttributeName())){
                patient.setLastName(request.getAttributeValue());
            }
            if("dob".equalsIgnoreCase(request.getAttributeName())){
                patient.setDob(request.getAttributeValue());
            }
            if("email".equalsIgnoreCase(request.getAttributeName())){
                patient.setEmail(request.getAttributeValue());
            }
            if("username".equalsIgnoreCase(request.getAttributeName())){
                patient.setUsername(request.getAttributeValue());
            }
            if("password".equalsIgnoreCase(request.getAttributeName())){
                patient.setPassword(request.getAttributeValue());
            }
            this.patientRepository.save(patient);
            return new AppResponse("patient info patched.", LocalDateTime.now());
        }
        throw new PatientNotFoundException("patient not found");
    }

    @Override
    public boolean deleteById(int id) {

        boolean status = false;
//        if(!this.patientRepository.existsById(id)){
//            throw new PatientNotFoundException("patient id: "+ id + " not found");
//        }
        if(this.patientRepository.existsById(id)){
            this.patientRepository.deleteById(id);
            status = true;
        }
        return status;
    }

}
