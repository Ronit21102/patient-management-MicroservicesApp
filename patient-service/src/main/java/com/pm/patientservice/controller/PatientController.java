package com.pm.patientservice.controller;

import com.pm.patientservice.model.Patient;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
public class PatientController {


    @GetMapping("/health")
    public String healthCheck() {
        return "Patient Service is up and running!";
    }


}
