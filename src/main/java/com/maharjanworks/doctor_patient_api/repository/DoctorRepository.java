package com.maharjanworks.doctor_patient_api.repository;

import com.maharjanworks.doctor_patient_api.model.Doctor;
import org.springframework.data.jpa.repository.JpaRepository;

public interface DoctorRepository extends JpaRepository<Doctor, Integer> {
}
