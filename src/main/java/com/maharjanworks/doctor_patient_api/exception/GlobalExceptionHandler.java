package com.maharjanworks.doctor_patient_api.exception;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(UsernameAlreadyExistsException.class)
    public ResponseEntity<ErrorMessage> handleUsernameAlreadyExistsException(UsernameAlreadyExistsException ex){
            ErrorMessage errorMessage = new ErrorMessage(ex.getMessage(), "ERR100");
            return ResponseEntity.ok(errorMessage);
    }

    @ExceptionHandler(EmailAlreadyExistsException.class)
    public ResponseEntity<ErrorMessage> handleEmailAlreadyExistsException(EmailAlreadyExistsException ex){
        return ResponseEntity.ok(new ErrorMessage(ex.getMessage(),"ERR101"));
    }

    @ExceptionHandler(PatientNotFoundException.class)
    public ResponseEntity<ErrorMessage> handlePatientNotFoundException(PatientNotFoundException ex){
        return ResponseEntity.ok(new ErrorMessage(ex.getMessage(),"ERR_NOT_FOUND"));
    }

    @ExceptionHandler(NullFieldsException.class)
    public ResponseEntity<ErrorMessage> handleNullFieldsException(NullFieldsException ex){
        return ResponseEntity.ok(new ErrorMessage(ex.getMessage(),"ERR_NULL_FIELDS" ));
    }
}
