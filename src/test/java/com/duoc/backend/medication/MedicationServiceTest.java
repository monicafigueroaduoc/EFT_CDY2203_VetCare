package com.duoc.backend.medication;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

import java.util.List;
import java.util.Optional;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

@ExtendWith(MockitoExtension.class)
class MedicationServiceTest {

    @Mock
    private MedicationRepository medicationRepository;

    @InjectMocks
    private MedicationService medicationService;

    @Test
    void testGetAllMedications() {
        Medication medication = new Medication("Antibiótico", 12000);
        List<Medication> medications = List.of(medication);

        when(medicationRepository.findAll()).thenReturn(medications);

        List<Medication> resultado = medicationService.getAllMedications();

        assertSame(medications, resultado);
        verify(medicationRepository).findAll();
    }

    @Test
    void testGetMedicationById() {
        Medication medication = new Medication("Antibiótico", 12000);

        when(medicationRepository.findById(1L))
                .thenReturn(Optional.of(medication));

        Medication resultado = medicationService.getMedicationById(1L);

        assertSame(medication, resultado);
        verify(medicationRepository).findById(1L);
    }

    @Test
    void testGetMedicationByIdNotFound() {
        when(medicationRepository.findById(99L))
                .thenReturn(Optional.empty());

        Medication resultado = medicationService.getMedicationById(99L);

        assertNull(resultado);
        verify(medicationRepository).findById(99L);
    }

    @Test
    void testSaveMedication() {
        Medication medication = new Medication("Antibiótico", 12000);

        when(medicationRepository.save(medication))
                .thenReturn(medication);

        Medication resultado = medicationService.saveMedication(medication);

        assertSame(medication, resultado);
        verify(medicationRepository).save(medication);
    }

    @Test
    void testDeleteMedication() {
        medicationService.deleteMedication(1L);

        verify(medicationRepository).deleteById(1L);
    }
}