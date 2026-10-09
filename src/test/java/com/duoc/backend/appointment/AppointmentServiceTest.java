package com.duoc.backend.appointment;

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
        Appointment appointment = new Appointment();

        when(appointmentRepository.save(appointment))
                .thenReturn(appointment);

        Appointment resultado = appointmentService.saveAppointment(appointment);

        assertSame(appointment, resultado);
        verify(appointmentRepository).save(appointment);
    }

    @Test
    void testDeleteAppointment() {
        appointmentService.deleteAppointment(1L);

        verify(appointmentRepository).deleteById(1L);
    }
}
