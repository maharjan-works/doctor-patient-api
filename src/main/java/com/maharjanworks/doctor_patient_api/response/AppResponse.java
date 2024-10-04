package com.maharjanworks.doctor_patient_api.response;


import lombok.AllArgsConstructor;
import lombok.Data;

import java.time.LocalDateTime;

@AllArgsConstructor
@Data
public final class AppResponse {
    private String message;
    private  LocalDateTime dateTime;
}
