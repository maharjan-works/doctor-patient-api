package com.maharjanworks.doctor_patient_api.repository;

import com.maharjanworks.doctor_patient_api.model.Patient;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface PatientRepository extends JpaRepository<Patient, Integer> {

    Optional<Patient> findByUsername(String username);
    Optional<Patient> findByEmail(String email);


}
