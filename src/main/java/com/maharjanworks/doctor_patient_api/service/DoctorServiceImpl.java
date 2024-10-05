package com.maharjanworks.doctor_patient_api.service;

import com.maharjanworks.doctor_patient_api.dto.DoctorDTO;
import com.maharjanworks.doctor_patient_api.dto.PatchDTO;
import com.maharjanworks.doctor_patient_api.exception.DoctorNotFoundException;
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
import java.util.ArrayList;
import java.util.List;
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

    @Override
    public List<DoctorDTO> findAll() {
        List<Doctor> doctors = this.doctorRepository.findAll();
        if (!doctors.isEmpty()){
            List<DoctorDTO> dtos = new ArrayList<>();
            for(Doctor doctor:doctors){
                DoctorDTO dto = new DoctorDTO();
                BeanUtils.copyProperties(doctor, dto);
                dtos.add(dto);
            }
            return dtos;
        }else{
            throw new DoctorNotFoundException("no doctor found.");
        }
    }

    @Override
    public DoctorDTO findById(int id) {
       Optional<Doctor>  optional = this.doctorRepository.findById(id);

       if(optional.isPresent()){
           DoctorDTO dto = new DoctorDTO();
           BeanUtils.copyProperties(optional.get(), dto);
           return dto;
       }else{
           throw new DoctorNotFoundException("no doctor found.");
       }
    }

    @Override
    public AppResponse update(DoctorDTO request) throws NullFieldsException {
        Optional<Doctor> optional=this.doctorRepository.findById(request.getId());
        if (optional.isEmpty()){
            throw new DoctorNotFoundException("doctor id "+request.getId()+ "  not found.");
        }else{
            Doctor doctor = optional.get();
            if(!request.getFirstName().isEmpty()){
                doctor.setFirstName(request.getFirstName());
            }else{
                throw new NullFieldsException("firstname is missing");
            }
            if (!request.getLastName().isEmpty()){
                doctor.setLastName(request.getLastName());
            }else{
                throw new NullFieldsException("lastname is missing");
            }
            if (!request.getEmail().isEmpty()){
                doctor.setEmail(request.getEmail());
            }else{
                throw new NullFieldsException("email is missing");
            }
            if(!request.getUsername().isEmpty()){
                doctor.setUsername(request.getUsername());
            }else{
                throw new NullFieldsException("username is missing");
            }
            if(!request.getPassword().isEmpty()){
                doctor.setPassword(request.getPassword());
            }else{
                throw new NullFieldsException("password is missing");
            }
            if(!request.getSpecialty().isEmpty()){
                doctor.setSpecialty(request.getSpecialty());
            }else{
                throw new NullFieldsException("specialty is missing");
            }
            this.doctorRepository.save(doctor);
            return new AppResponse("doctor info updated", LocalDateTime.now());
        }

    }

    @Override
    public AppResponse patch(PatchDTO request) {
        Optional<Doctor> optional = this.doctorRepository.findById(request.getId());

        if (optional.isPresent()){
            Doctor doctor = optional.get();
            if("firstName".equalsIgnoreCase(request.getAttributeName())){
                doctor.setFirstName(request.getAttributeValue());
            }
            if("lastName".equalsIgnoreCase(request.getAttributeName())){
                doctor.setLastName(request.getAttributeValue());
            }
            if("email".equalsIgnoreCase(request.getAttributeName()) ){
                doctor.setEmail(request.getAttributeValue());
            }
            if("username".equalsIgnoreCase(request.getAttributeName())){
                doctor.setUsername(request.getAttributeValue());
            }
            if("password".equalsIgnoreCase(request.getAttributeName())){
                doctor.setPassword(request.getAttributeValue());
            }
            if("specialty".equalsIgnoreCase(request.getAttributeName())){
                doctor.setSpecialty(request.getAttributeValue());
            }
            this.doctorRepository.save(doctor);

            return new AppResponse("doctor - "+ request.getAttributeName() + " updated", LocalDateTime.now());
        }else{
            throw new DoctorNotFoundException("doctor id: "+ request.getId()+ " not found.");
        }
      
    }

    @Override
    public AppResponse delete(int id) {
        Optional<Doctor> optional = this.doctorRepository.findById(id);
        if(optional.isPresent()){
            this.doctorRepository.deleteById(id);
            return new AppResponse("doctor id: "+ id + " deleted.", LocalDateTime.now());
        }else{
            throw new DoctorNotFoundException("doctor id: "+id+ " not found.");
        }

    }
}
