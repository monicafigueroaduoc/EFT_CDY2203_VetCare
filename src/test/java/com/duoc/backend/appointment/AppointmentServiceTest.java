package com.duoc.backend.appointment;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;
import java.util.Optional;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

@ExtendWith(MockitoExtension.class)
class AppointmentServiceTest {

    @Mock
    private AppointmentRepository appointmentRepository;

    @InjectMocks
    private AppointmentService appointmentService;

    @Test
    void testGetAllAppointments() {
        Appointment appointment = new Appointment();
        List<Appointment> appointments = List.of(appointment);

        when(appointmentRepository.findAll()).thenReturn(appointments);

        Iterable<Appointment> resultado = appointmentService.getAllAppointments();

        assertSame(appointments, resultado);
        verify(appointmentRepository).findAll();
    }

    @Test
    void testGetAppointmentById() {
        Appointment appointment = new Appointment();

        when(appointmentRepository.findById(1L))
                .thenReturn(Optional.of(appointment));

        Appointment resultado = appointmentService.getAppointmentById(1L);

        assertSame(appointment, resultado);
        verify(appointmentRepository).findById(1L);
    }

    @Test
    void testGetAppointmentByIdNotFound() {
        when(appointmentRepository.findById(99L))
                .thenReturn(Optional.empty());

        Appointment resultado = appointmentService.getAppointmentById(99L);

        assertNull(resultado);
        verify(appointmentRepository).findById(99L);
    }

    @Test
    void testSaveAppointment() {
        LocalDate date = LocalDate.of(2026, 10, 9);
        LocalTime time = LocalTime.of(10, 30);
        AppointmentRequestDTO request = new AppointmentRequestDTO(
                date, time, "Control", "Dr. Perez");

        when(appointmentRepository.save(any(Appointment.class)))
                .thenAnswer(invocation -> invocation.getArgument(0));

        Appointment resultado = appointmentService.saveAppointment(request);

        assertNotNull(resultado);
        assertNull(resultado.getId());
        assertEquals(date, resultado.getDate());
        assertEquals(time, resultado.getTime());
        assertEquals("Control", resultado.getReason());
        assertEquals("Dr. Perez", resultado.getVeterinarian());
        verify(appointmentRepository).save(any(Appointment.class));
    }

    @Test
    void testDeleteAppointment() {
        appointmentService.deleteAppointment(1L);

        verify(appointmentRepository).deleteById(1L);
    }
}
