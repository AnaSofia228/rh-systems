package com.rh_systems.performance_service.controller;

import java.util.Date;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.rh_systems.performance_service.dto.PerformanceEvaluationDTO;
import com.rh_systems.performance_service.dto.PerformanceEvaluationDTOGetPostPut;
import com.rh_systems.performance_service.service.PerformanceEvaluationService;

import jakarta.validation.Valid;

/**
 * REST controller for managing performance evaluations.
 */
@RestController
@RequestMapping("/api/performance-evaluation")
public class PerformanceEvaluationController {
    @Autowired
    private PerformanceEvaluationService performanceEvaluationService;

    /**
     * Gets all performance evaluations.
     * @return a list of PerformanceEvaluationDTOGetPostPut
     */
    @GetMapping
    public List<PerformanceEvaluationDTOGetPostPut> getAllPerformanceEvaluation() {
        return performanceEvaluationService.getAllPerformanceEvaluation();
    }

    /**
     * Gets a performance evaluation by its ID.
     * @param id the performance evaluation ID
     * @return a ResponseEntity with the PerformanceEvaluationDTOGetPostPut if found, or 404 otherwise
     */
    @GetMapping("/{id}")
    public ResponseEntity<PerformanceEvaluationDTOGetPostPut> getPerformanceEvaluationById(@PathVariable Long id) {
        Optional<PerformanceEvaluationDTOGetPostPut> performanceEvaluationDTO = performanceEvaluationService.getPerformanceEvaluationById(id);
        return performanceEvaluationDTO.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.notFound().build());
    }

    /**
     * Gets a performance evaluation by employee ID and date.
     * @param employeeId the employee ID
     * @param date the evaluation date
     * @return a ResponseEntity with the PerformanceEvaluationDTOGetPostPut if found, or 404 otherwise
     */
    @GetMapping("/employee/{employeeId}/date/{date}")
    public ResponseEntity<PerformanceEvaluationDTOGetPostPut> getPerformanceEvaluationByEmployeeIdAndDate(@PathVariable Long employeeId, @PathVariable Date date) {
        Optional<PerformanceEvaluationDTOGetPostPut> performanceEvaluationDTO = performanceEvaluationService.getPerformanceEvaluationByEmployeeIdAndDate(employeeId, date);
        return performanceEvaluationDTO.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.notFound().build());
    }

    /**
     * Creates a new performance evaluation.
     * @param performanceEvaluationDTO the performance evaluation data
     * @return a ResponseEntity with the created PerformanceEvaluationDTOGetPostPut, or 400 if a performance evaluation with the same employee and date exists
     */
    @PostMapping
    public ResponseEntity<PerformanceEvaluationDTOGetPostPut> createPerformanceEvaluation(@Valid @RequestBody PerformanceEvaluationDTO performanceEvaluationDTO) {
        Optional<PerformanceEvaluationDTOGetPostPut> savedPerformanceEvaluation = performanceEvaluationService.createPerformanceEvaluation(performanceEvaluationDTO);
        return savedPerformanceEvaluation.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.badRequest().build());
    }

    /**
     * Updates an existing performance evaluation.
     * @param id the performance evaluation ID
     * @param performanceEvaluationDTO the performance evaluation data to update
     * @return a ResponseEntity with the updated PerformanceEvaluationDTOGetPostPut, or 404 if not found
     */
    @PutMapping("/{id}")
    public ResponseEntity<PerformanceEvaluationDTOGetPostPut> updatePerformanceEvaluation(@PathVariable long id, @Valid @RequestBody PerformanceEvaluationDTO performanceEvaluationDTO) {
        Optional<PerformanceEvaluationDTOGetPostPut> updatedPerformanceEvaluation = performanceEvaluationService.updatePerformanceEvaluation(id, performanceEvaluationDTO);
        return updatedPerformanceEvaluation.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.notFound().build());
    }

    /**
     * Deletes a performance evaluation by its ID.
     * @param id the performance evaluation ID
     * @return a ResponseEntity with status 200 if deleted, or 404 if not found
     */
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletePerformanceEvaluation(@PathVariable Long id) {
        if (performanceEvaluationService.deletePerformanceEvaluation(id)) {
            return ResponseEntity.ok().build();
        } else {
            return ResponseEntity.notFound().build();
        }
    }
}
