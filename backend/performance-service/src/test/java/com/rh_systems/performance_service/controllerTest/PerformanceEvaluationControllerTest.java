package com.rh_systems.performance_service.controllerTest;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.setup.MockMvcBuilders.standaloneSetup;

import java.util.Optional;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.rh_systems.performance_service.controller.PerformanceEvaluationController;
import com.rh_systems.performance_service.dto.PerformanceEvaluationDTO;
import com.rh_systems.performance_service.dto.PerformanceEvaluationDTOGetPostPut;
import com.rh_systems.performance_service.service.PerformanceEvaluationService;

class PerformanceEvaluationControllerTest {

    private MockMvc mockMvc;
    private PerformanceEvaluationService service;
    private PerformanceEvaluationController controller;

    @BeforeEach
    void setUp() {
        service = mock(PerformanceEvaluationService.class);
        controller = new PerformanceEvaluationController();
        // Use reflection to set the service
        try {
            java.lang.reflect.Field field = PerformanceEvaluationController.class.getDeclaredField("performanceEvaluationService");
            field.setAccessible(true);
            field.set(controller, service);
        } catch (Exception e) {
            throw new RuntimeException("Failed to set service", e);
        }
        mockMvc = standaloneSetup(controller).build();
    }

    @Test
    void shouldCreateEvaluationViaApi() throws Exception {
        // Arrange
        PerformanceEvaluationDTO request = new PerformanceEvaluationDTO();
        request.setScore(90);

        PerformanceEvaluationDTOGetPostPut response = new PerformanceEvaluationDTOGetPostPut();
        when(service.createPerformanceEvaluation(any(PerformanceEvaluationDTO.class)))
            .thenReturn(Optional.of(response));

        // Act & Assert
        mockMvc.perform(post("/api/performance-evaluation")
                .contentType(MediaType.APPLICATION_JSON)
                .content(new ObjectMapper().writeValueAsString(request)))
                .andExpect(status().isOk());
    }
}
