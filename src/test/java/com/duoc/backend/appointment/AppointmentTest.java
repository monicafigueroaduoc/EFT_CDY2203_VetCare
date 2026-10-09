package com.duoc.backend.appointment;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.time.LocalDate;
import java.time.LocalTime;

import org.junit.jupiter.api.Test;

class AppointmentTest {

    @Test
    void testGettersAndSetters() {
        Appointment appointment = new Appointment();

        LocalDate date = LocalDate.of(2026, 9, 30);
        LocalTime time = LocalTime.of(10, 30);

        appointment.setId(1L);
        appointment.setDate(date);
        appointment.setTime(time);
        appointment.setReason("Control");
        appointment.setVeterinarian("Dr. Pérez");

        assertEquals(1L, appointment.getId());
        assertEquals(date, appointment.getDate());
        assertEquals(time, appointment.getTime());
        assertEquals("Control", appointment.getReason());
        assertEquals("Dr. Pérez", appointment.getVeterinarian());
    }
}
