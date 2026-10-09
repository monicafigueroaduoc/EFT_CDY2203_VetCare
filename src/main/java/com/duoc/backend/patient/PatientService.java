package com.duoc.backend.patient;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class PatientService {

    @Autowired
    private PatientRepository patientRepository;

    public Iterable<Patient> getAllPatients() {
        return patientRepository.findAll();
    }

    public Patient getPatientById(Long id) {
        return patientRepository.findById(id).orElse(null);
    }

    public Patient savePatient(PatientRequestDTO patientRequest) {
        Patient patient = new Patient();
        patient.setName(patientRequest.name());
        patient.setSpecies(patientRequest.species());
        patient.setBreed(patientRequest.breed());
        patient.setAge(patientRequest.age());
        patient.setOwner(patientRequest.owner());
        return patientRepository.save(patient);
    }

    public void deletePatient(Long id) {
        patientRepository.deleteById(id);
    }
}