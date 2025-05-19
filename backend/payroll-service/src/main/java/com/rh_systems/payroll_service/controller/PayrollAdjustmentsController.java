package com.rh_systems.payroll_service.controller;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.rh_systems.payroll_service.dto.PayrollAdjustmentsDTO;
import com.rh_systems.payroll_service.dto.PayrollAdjustmentsDTOGetPostPut;
import com.rh_systems.payroll_service.service.PayrollAdjustmentsService;

import jakarta.validation.Valid;

/**
 * REST controller for managing payroll adjustments.
 */
@RestController
@RequestMapping("/api/payroll-adjustments")
public class PayrollAdjustmentsController {
    @Autowired
    private PayrollAdjustmentsService payrollAdjustmentsService;

    /**
     * Gets all payroll adjustments.
     * @return a list of PayrollAdjustmentsDTOGetPostPut
     */
    @GetMapping
    public List<PayrollAdjustmentsDTOGetPostPut> getAllPayrollAdjustments() {
        return payrollAdjustmentsService.getAllPayrollAdjustments();
    }

    /**
     * Gets a payroll adjustment by its ID.
     * @param id the payroll adjustment ID
     * @return a ResponseEntity with the PayrollAdjustmentsDTOGetPostPut if found, or 404 otherwise
     */
    @GetMapping("/{id}")
    public ResponseEntity<PayrollAdjustmentsDTOGetPostPut> getPayrollAdjustmentById(@PathVariable Long id) {
        Optional<PayrollAdjustmentsDTOGetPostPut> payrollAdjustmentDTO = payrollAdjustmentsService.getPayrollAdjustmentsById(id);
        return payrollAdjustmentDTO.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.notFound().build());
    }

    /**
     * Gets a payroll adjustment by its type.
     * @param type the payroll adjustment type
     * @return a ResponseEntity with the PayrollAdjustmentsDTOGetPostPut if found, or 404 otherwise
     */
    @GetMapping("/type/{type}")
    public ResponseEntity<PayrollAdjustmentsDTOGetPostPut> getPayrollAdjustmentByType(@PathVariable String type) {
        Optional<PayrollAdjustmentsDTOGetPostPut> payrollAdjustmentDTO = payrollAdjustmentsService.getPayrollAdjustmentsByType(type);
        return payrollAdjustmentDTO.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.notFound().build());
    }

    /**
     * Creates a new payroll adjustment.
     * @param payrollAdjustmentDTO the payroll adjustment data
     * @return a ResponseEntity with the created PayrollAdjustmentsDTOGetPostPut, or 400 if a payroll adjustment with the same type exists
     */
    @PostMapping
    public ResponseEntity<PayrollAdjustmentsDTOGetPostPut> createPayrollAdjustment(@Valid @RequestBody PayrollAdjustmentsDTO payrollAdjustmentDTO) {
        Optional<PayrollAdjustmentsDTOGetPostPut> savedPayrollAdjustment = payrollAdjustmentsService.createPayrollAdjustments(payrollAdjustmentDTO);
        return savedPayrollAdjustment.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.badRequest().build());
    }

    /**
     * Updates an existing payroll adjustment.
     * @param id the payroll adjustment ID
     * @param payrollAdjustmentsDTO the payroll adjustment data to update
     * @return a ResponseEntity with the updated PayrollAdjustmentsDTOGetPostPut, or 404 if not found
     */
    @PutMapping("/{id}")
    public ResponseEntity<PayrollAdjustmentsDTOGetPostPut> updatePayrollAdjustment(@PathVariable long id, @Valid @RequestBody PayrollAdjustmentsDTO payrollAdjustmentsDTO) {
        Optional<PayrollAdjustmentsDTOGetPostPut> updatedPayrollAdjustment = payrollAdjustmentsService.updatePayrollAdjustment(id, payrollAdjustmentsDTO);
        return updatedPayrollAdjustment.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.notFound().build());
    }

    /**
     * Deletes a payroll adjustment by its ID.
     * @param id the payroll adjustment ID
     * @return a ResponseEntity with status 200 if deleted, or 404 if not found
     */
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletePayrollAdjustment(@PathVariable Long id) {
        if (payrollAdjustmentsService.deletePayrollAdjustment(id)) {
            return ResponseEntity.ok().build();
        } else {
            return ResponseEntity.notFound().build();
        }
    }
}
