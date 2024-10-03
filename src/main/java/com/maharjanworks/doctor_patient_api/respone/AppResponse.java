package com.maharjanworks.doctor_patient_api.respone;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;

import java.time.LocalDateTime;

@AllArgsConstructor
@Data
public final class AppResponse {
    private String message;
    private  LocalDateTime dateTime;
}
