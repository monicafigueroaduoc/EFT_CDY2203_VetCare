package com.duoc.backend.patient;

public record PatientRequestDTO(
        String name,
        String species,
        String breed,
        int age,
        String owner
) {
}
