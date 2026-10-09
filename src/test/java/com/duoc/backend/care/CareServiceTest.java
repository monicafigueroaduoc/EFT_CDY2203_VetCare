package com.duoc.backend.care;

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
class CareServiceTest {

    @Mock
    private CareRepository careRepository;

    @InjectMocks
    private CareService careService;

    @Test
    void testGetAllCares() {
        Care care = new Care("Consulta", 15000);
        List<Care> cares = List.of(care);

        when(careRepository.findAll()).thenReturn(cares);

        List<Care> resultado = careService.getAllCares();

        assertSame(cares, resultado);
        verify(careRepository).findAll();
    }

    @Test
    void testGetCareById() {
        Care care = new Care("Consulta", 15000);

        when(careRepository.findById(1L))
                .thenReturn(Optional.of(care));

        Care resultado = careService.getCareById(1L);

        assertSame(care, resultado);
        verify(careRepository).findById(1L);
    }

    @Test
    void testGetCareByIdNotFound() {
        when(careRepository.findById(99L))
                .thenReturn(Optional.empty());

        Care resultado = careService.getCareById(99L);

        assertNull(resultado);
        verify(careRepository).findById(99L);
    }

    @Test
    void testSaveCare() {
        Care care = new Care("Consulta", 15000);

        when(careRepository.save(care))
                .thenReturn(care);

        Care resultado = careService.saveCare(care);

        assertSame(care, resultado);
        verify(careRepository).save(care);
    }

    @Test
    void testDeleteCare() {
        careService.deleteCare(1L);

        verify(careRepository).deleteById(1L);
    }
}