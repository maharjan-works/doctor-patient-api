package com.maharjanworks.doctor_patient_api;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.util.Date;

@SpringBootApplication
public class DoctorPatientApiApplication {

	public static void main(String[] args) {

		System.out.println(new Date());

		SpringApplication.run(DoctorPatientApiApplication.class, args);
	}

}
