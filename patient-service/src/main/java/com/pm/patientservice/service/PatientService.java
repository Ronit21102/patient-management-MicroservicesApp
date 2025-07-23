package com.pm.patientservice.service;

import com.pm.patientservice.dto.PatientRequestDto;
import com.pm.patientservice.dto.PatientResponseDto;
import com.pm.patientservice.exception.PatientNotFoundException;
import com.pm.patientservice.exception.ResourceAlreadyExistsException;
import com.pm.patientservice.grpc.BillingsServiceGrpcClient;
import com.pm.patientservice.kafka.kafkaProducer;
import com.pm.patientservice.mapper.PatientMapper;
import com.pm.patientservice.model.Patient;
import com.pm.patientservice.repository.PatientRepository;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;
import java.util.UUID;

@Service
public class PatientService {
    private final PatientRepository patientRepository;
    private final BillingsServiceGrpcClient billingsServiceGrpcClient;
    private final kafkaProducer kafkaProducer;
    public PatientService(PatientRepository patientRepository , BillingsServiceGrpcClient billingsServiceGrpcClient, kafkaProducer kafkaProducer) {
        this.billingsServiceGrpcClient = billingsServiceGrpcClient;
        this.patientRepository = patientRepository;
        this.kafkaProducer = kafkaProducer;
    }

    public List<PatientResponseDto> getPatients() {
        List<Patient> patients = patientRepository.findAll();

        return patients.stream()
                .map(PatientMapper::toDto)
                .toList();
    }

    public PatientResponseDto createPatient(PatientRequestDto patientRequestDto) {
        if (patientRepository.existsByEmail(patientRequestDto.getEmail())) {
            throw new ResourceAlreadyExistsException("Patient with this email already exists: " + patientRequestDto.getEmail());
        }
        Patient savedPatient = patientRepository.save(PatientMapper.toModel(patientRequestDto));
        billingsServiceGrpcClient.createBillingRequest(savedPatient.getId().toString(), savedPatient.getName(), savedPatient.getEmail());

        kafkaProducer.sendEvent(savedPatient);
        return PatientMapper.toDto(savedPatient);
    }

    public PatientResponseDto updatePatient(UUID id, PatientRequestDto patientRequestDto) {
        Patient patient = patientRepository.findById(id).orElseThrow(() -> new PatientNotFoundException("Patient not found: " + id));
        if (patientRepository.existsByEmail(patientRequestDto.getEmail())) {
            throw new ResourceAlreadyExistsException("Patient with this email already exists: " + patientRequestDto.getEmail());
        }
        patient.setName(patientRequestDto.getName());
        patient.setAddress(patientRequestDto.getAddress());
        patient.setDateOfBirth(LocalDate.parse(patientRequestDto.getDateOfBirth()));
        patient.setEmail(patientRequestDto.getEmail());
        patient.setRegistrationDate(LocalDate.parse(patientRequestDto.getRegistrationDate()));
        Patient updatedPatient = patientRepository.save(patient);
        return PatientMapper.toDto(updatedPatient);
    }

    public void DeletePatient(UUID id) {
        Patient patient = patientRepository.findById(id).orElseThrow(() -> new PatientNotFoundException("Patient not found: " + id));
        patientRepository.delete(patient);
    }
}
