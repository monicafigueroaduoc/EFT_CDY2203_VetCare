package com.duoc.backend;

import com.duoc.backend.Invoice.Invoice;
import com.duoc.backend.Invoice.InvoiceRepository;
import com.duoc.backend.Invoice.InvoiceRequestDTO;
import com.duoc.backend.Invoice.InvoiceService;
import com.duoc.backend.care.Care;
import com.duoc.backend.care.CareRepository;
import com.duoc.backend.medication.Medication;
import com.duoc.backend.medication.MedicationRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

class InvoiceServiceTest {

    @Mock
    private InvoiceRepository invoiceRepository;

    @Mock
    private MedicationRepository medicationRepository;

    @Mock
    private CareRepository careRepository;

    @InjectMocks
    private InvoiceService invoiceService;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void saveInvoiceWithValidData() {
        Medication medication = new Medication("Antibiotico", 30.0);
        Care care = new Care("Consulta", 50.0);
        InvoiceRequestDTO request = new InvoiceRequestDTO(
                "Patient1",
                LocalDate.parse("2025-04-28"),
                LocalTime.of(10, 30),
                List.of(2L),
                List.of(1L)
        );

        when(medicationRepository.findAllById(List.of(1L))).thenReturn(List.of(medication));
        when(careRepository.findAllById(List.of(2L))).thenReturn(List.of(care));
        when(invoiceRepository.save(any(Invoice.class))).thenAnswer(invocation -> invocation.getArgument(0));

        Invoice result = invoiceService.saveInvoice(request);

        assertNotNull(result);
        assertEquals("Patient1", result.getPatientName());
        assertEquals(LocalDate.parse("2025-04-28"), result.getDate());
        assertEquals(LocalTime.of(10, 30), result.getTime());
        assertEquals(80.0, result.getTotalCost(), 0.001);
        assertEquals(1, result.getCares().size());
        assertEquals(1, result.getMedications().size());
        verify(invoiceRepository).save(any(Invoice.class));
    }

    @Test
    void saveInvoiceWithNullListsUsesEmptyLists() {
        InvoiceRequestDTO request = new InvoiceRequestDTO(
                "Patient1",
                LocalDate.parse("2025-04-28"),
                null,
                null,
                null
        );

        when(medicationRepository.findAllById(List.of())).thenReturn(List.of());
        when(careRepository.findAllById(List.of())).thenReturn(List.of());
        when(invoiceRepository.save(any(Invoice.class))).thenAnswer(invocation -> invocation.getArgument(0));

        Invoice result = invoiceService.saveInvoice(request);

        assertNotNull(result);
        assertTrue(result.getCares().isEmpty());
        assertTrue(result.getMedications().isEmpty());
        assertEquals(0.0, result.getTotalCost(), 0.001);
    }

    @Test
    void saveInvoiceRejectsUnknownMedication() {
        InvoiceRequestDTO request = new InvoiceRequestDTO(
                "Patient1",
                LocalDate.parse("2025-04-28"),
                null,
                List.of(),
                List.of(99L)
        );

        when(medicationRepository.findAllById(List.of(99L))).thenReturn(List.of());

        IllegalArgumentException exception = assertThrows(
                IllegalArgumentException.class,
                () -> invoiceService.saveInvoice(request)
        );

        assertEquals("Algunos medicamentos no existen en la base de datos.", exception.getMessage());
        verifyNoInteractions(careRepository);
        verify(invoiceRepository, never()).save(any(Invoice.class));
    }

    @Test
    void saveInvoiceRejectsUnknownCare() {
        Medication medication = new Medication("Antibiotico", 30.0);
        InvoiceRequestDTO request = new InvoiceRequestDTO(
                "Patient1",
                LocalDate.parse("2025-04-28"),
                null,
                List.of(99L),
                List.of(1L)
        );

        when(medicationRepository.findAllById(List.of(1L))).thenReturn(List.of(medication));
        when(careRepository.findAllById(List.of(99L))).thenReturn(List.of());

        IllegalArgumentException exception = assertThrows(
                IllegalArgumentException.class,
                () -> invoiceService.saveInvoice(request)
        );

        assertEquals("Algunos servicios no existen en la base de datos.", exception.getMessage());
        verify(invoiceRepository, never()).save(any(Invoice.class));
    }
}
