package com.duoc.backend.care;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

@ExtendWith(MockitoExtension.class)
class CareControllerTest {

    @Mock
    private CareRepository careRepository;

    @InjectMocks
    private CareController careController;

    @Test
    void saveCareCreatesEntityFromDto() {
        CareRequestDTO request = new CareRequestDTO("Consulta", 15000.0);

        when(careRepository.save(any(Care.class)))
                .thenAnswer(invocation -> invocation.getArgument(0));

        Care result = careController.saveCare(request);

        assertNotNull(result);
        assertNull(result.getId());
        assertEquals("Consulta", result.getName());
        assertEquals(Double.valueOf(15000.0), result.getCost());
        verify(careRepository).save(any(Care.class));
    }
}
