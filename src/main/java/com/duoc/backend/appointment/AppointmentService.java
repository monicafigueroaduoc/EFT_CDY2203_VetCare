package com.duoc.backend.appointment;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class AppointmentService {

    @Autowired
    private AppointmentRepository appointmentRepository;

    public Iterable<Appointment> getAllAppointments() {
        return appointmentRepository.findAll();
    }

    public Appointment getAppointmentById(Long id) {
        return appointmentRepository.findById(id).orElse(null);
    }

    public Appointment saveAppointment(AppointmentRequestDTO appointmentRequest) {
        Appointment appointment = new Appointment();
        appointment.setDate(appointmentRequest.date());
        appointment.setTime(appointmentRequest.time());
        appointment.setReason(appointmentRequest.reason());
        appointment.setVeterinarian(appointmentRequest.veterinarian());
        return appointmentRepository.save(appointment);
    }

    public void deleteAppointment(Long id) {
        appointmentRepository.deleteById(id);
    }
}