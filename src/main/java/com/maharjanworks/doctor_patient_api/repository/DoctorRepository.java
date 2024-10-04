package com.maharjanworks.doctor_patient_api.repository;

import com.maharjanworks.doctor_patient_api.model.Doctor;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface DoctorRepository extends JpaRepository<Doctor, Integer> {

    Optional<Doctor> findByUsername(String username);
    Optional<Doctor> findByEmail(String email);

}
