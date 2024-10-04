package com.maharjanworks.doctor_patient_api.service;

import com.maharjanworks.doctor_patient_api.dto.DoctorDTO;
import com.maharjanworks.doctor_patient_api.exception.EmailAlreadyExistsException;
import com.maharjanworks.doctor_patient_api.exception.NullFieldsException;
import com.maharjanworks.doctor_patient_api.exception.UsernameAlreadyExistsException;
import com.maharjanworks.doctor_patient_api.model.Doctor;
import com.maharjanworks.doctor_patient_api.repository.DoctorRepository;
import com.maharjanworks.doctor_patient_api.response.AppResponse;
import jakarta.transaction.Transactional;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Optional;

@Service
@Transactional
public class DoctorServiceImpl implements DoctorService{

    @Autowired
    private DoctorRepository doctorRepository;

    @Override
    public AppResponse create(DoctorDTO dto) throws NullFieldsException {

        if(!dto.getFirstName().isEmpty()
                && !dto.getLastName().isEmpty()
                && !dto.getEmail().isEmpty()
                && !dto.getUsername().isEmpty()
                && !dto.getPassword().isEmpty()
                && !dto.getSpecialty().isEmpty()
        ){
            Optional<Doctor> optional = this.doctorRepository.findByEmail(dto.getEmail());
            if(optional.isPresent()){
                throw new EmailAlreadyExistsException("email existed already.");
            }
            Optional<Doctor> optionalEmail = this.doctorRepository.findByUsername(dto.getUsername());
            if(optionalEmail.isPresent()){
                throw new UsernameAlreadyExistsException("username existed already.");
            }
            Doctor doctor = new Doctor();
            BeanUtils.copyProperties(dto,doctor);
            doctor.setRegisteredAt(LocalDate.now());
            this.doctorRepository.save(doctor);
            return new AppResponse("doctor registered", LocalDateTime.now());
        }else{
            throw new NullFieldsException("some fields are missing");
        }
    }
}
