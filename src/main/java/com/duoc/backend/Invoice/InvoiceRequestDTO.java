package com.duoc.backend.Invoice;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;

public class InvoiceRequestDTO {

    private String patientName;
    private LocalDate date;
    private LocalTime time;
    private List<Long> careIds;
    private List<Long> medicationIds;

    public String getPatientName() {
        return patientName;
    }

    public void setPatientName(String patientName) {
        this.patientName = patientName;
    }

    public LocalDate getDate() {
        return date;
    }

    public void setDate(LocalDate date) {
        this.date = date;
    }

    public LocalTime getTime() {
        return time;
    }

    public void setTime(LocalTime time) {
        this.time = time;
    }

    public List<Long> getCareIds() {
        return careIds;
    }

    public void setCareIds(List<Long> careIds) {
        this.careIds = careIds;
    }

    public List<Long> getMedicationIds() {
        return medicationIds;
    }

    public void setMedicationIds(List<Long> medicationIds) {
        this.medicationIds = medicationIds;
    }
}
