package com.maharjanworks.doctor_patient_api.dto;

import lombok.AllArgsConstructor;
import lombok.Data;

@AllArgsConstructor
@Data
public class PatchDTO {

    private int id;
    private String AttributeName;
    private String AttributeValue;



}
