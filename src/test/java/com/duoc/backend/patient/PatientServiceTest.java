package com.duoc.backend.patient;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

@ExtendWith(MockitoExtension.class)
class PatientServiceTest {

    @Mock
    private PatientRepository patientRepository;

    @InjectMocks
    private PatientService patientService;

    @Test
    void savePatientCreatesEntityFromDto() {
        PatientRequestDTO request = new PatientRequestDTO(
                "Firulais", "Perro", "Labrador", 5, "Juan Perez");

        when(patientRepository.save(any(Patient.class)))
                .thenAnswer(invocation -> invocation.getArgument(0));

        Patient result = patientService.savePatient(request);

        assertNotNull(result);
        assertNull(result.getId());
        assertEquals("Firulais", result.getName());
        assertEquals("Perro", result.getSpecies());
        assertEquals("Labrador", result.getBreed());
        assertEquals(5, result.getAge());
        assertEquals("Juan Perez", result.getOwner());
        verify(patientRepository).save(any(Patient.class));
    }
}
