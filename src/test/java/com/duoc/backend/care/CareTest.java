package com.duoc.backend.care;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

class CareTest {

    @Test
    void testGettersAndSetters() {
        Care care = new Care("Consulta", 15000);

        care.setId(1L);
        care.setName("Vacunación");
        care.setCost(20000.0);

        assertEquals(1L, care.getId());
        assertEquals("Vacunación", care.getName());
        assertEquals(20000.0, care.getCost());
    }
}