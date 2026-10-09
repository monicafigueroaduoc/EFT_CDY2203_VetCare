package com.duoc.backend.medication;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

class MedicationTest {

    @Test
    void testGettersAndSetters() {
        Medication medication = new Medication("Antibiótico", 12000);

        medication.setId(1L);
        medication.setName("Analgésico");
        medication.setCost(15000.0);

        assertEquals(1L, medication.getId());
        assertEquals("Analgésico", medication.getName());
        assertEquals(15000.0, medication.getCost());
    }
}
