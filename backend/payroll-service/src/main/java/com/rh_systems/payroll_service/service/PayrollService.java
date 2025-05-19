package com.rh_systems.payroll_service.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.rh_systems.payroll_service.Entity.Payroll;
import com.rh_systems.payroll_service.dto.PayrollDTO;
import com.rh_systems.payroll_service.dto.PayrollDTOGetPostPut;
import com.rh_systems.payroll_service.repository.PayrollRepository;

@Service
public class PayrollService {
    @Autowired
    private PayrollRepository payrollRepository;

    /**
     * Gets all payroll records.
     * @return a list of PayrollDTOGetPostPut
     */
    public List<PayrollDTOGetPostPut> getAllPayroll() {
        List<PayrollDTOGetPostPut> payrollToReturn = new ArrayList<>();
        List<Payroll> payroll = payrollRepository.findAll();
        for (Payroll p : payroll) {
            PayrollDTOGetPostPut payrollDTO = new PayrollDTOGetPostPut();
            payrollDTO.convertToPayrollDTO(p);
            payrollToReturn.add(payrollDTO);
        }
        return payrollToReturn;
    }

    /**
     * Gets a payroll record by its ID.
     * @param id the payroll ID
     * @return an Optional containing the PayrollDTOGetPostPut if found, or empty otherwise
     */
    public Optional<PayrollDTOGetPostPut> getPayrollById(Long id) {
        Optional<Payroll> payroll = payrollRepository.findById(id);
        if (payroll.isPresent()) {
            PayrollDTOGetPostPut payrollDTO = new PayrollDTOGetPostPut();
            payrollDTO.convertToPayrollDTO(payroll.get());
            return Optional.of(payrollDTO);
        }
        return Optional.empty();
    }

    /**
     * Gets a payroll record by its status.
     * @param status the payroll status
     * @return an Optional containing the PayrollDTOGetPostPut if found, or empty otherwise
     */
    public Optional<PayrollDTOGetPostPut> getPayrollByStatus(String status) {
        Optional<Payroll> payroll = payrollRepository.findByPayrollStatus(status);
        if (payroll.isPresent()) {
            PayrollDTOGetPostPut payrollDTO = new PayrollDTOGetPostPut();
            payrollDTO.convertToPayrollDTO(payroll.get());
            return Optional.of(payrollDTO);
        }
        return Optional.empty();
    }

    /**
     * Creates a new payroll record.
     * @param payrollDTO the payroll data
     * @return an Optional containing the created PayrollDTOGetPostPut, or empty if a payroll with the same status exists
     */
    public Optional<PayrollDTOGetPostPut> createPayroll(PayrollDTO payrollDTO) {
        if (payrollRepository.findByPayrollStatus(payrollDTO.getStatus()).isPresent()) {
            return Optional.empty();
        }
        Payroll payroll = new Payroll();
        payroll.setStatus(payrollDTO.getStatus());
        payroll.setPaymentDate(payrollDTO.getPaymentDate());
        payroll.setAmount(payrollDTO.getAmount());
        // Employee employee = new Employee();
        // employee.setId(payrollDTO.getEmployeeId());
        // payroll.setEmployee(employee);
        PayrollDTOGetPostPut savedPayroll = new PayrollDTOGetPostPut();
        savedPayroll.convertToPayrollDTO(payrollRepository.save(payroll));
        return Optional.of(savedPayroll);
    }

    /**
     * Updates an existing payroll record.
     * @param id the payroll ID
     * @param payrollDTO the payroll data to update
     * @return an Optional containing the updated PayrollDTOGetPostPut, or empty if not found or status conflict
     */
    public Optional<PayrollDTOGetPostPut> updatePayroll(Long id, PayrollDTO payrollDTO) {
        Optional<Payroll> payroll = payrollRepository.findById(id);
        if (payroll.isPresent()) {
            if (!payroll.get().getStatus().equalsIgnoreCase(payrollDTO.getStatus())) {
                if (payrollRepository.findByPayrollStatus(payrollDTO.getStatus()).isPresent()) {
                    return Optional.empty();
                }
            }
            Payroll payrollToUpdate = payroll.get();
            payrollToUpdate.setStatus(payrollDTO.getStatus());
            payrollToUpdate.setPaymentDate(payrollDTO.getPaymentDate());
            payrollToUpdate.setAmount(payrollDTO.getAmount());
            // Employee employee = new Employee();
            // employee.setId(payrollDTO.getEmployeeId());
            // payrollToUpdate.setEmployee(employee);
            PayrollDTOGetPostPut updatedPayrollDTO = new PayrollDTOGetPostPut();
            updatedPayrollDTO.convertToPayrollDTO(payrollRepository.save(payrollToUpdate));
            return Optional.of(updatedPayrollDTO);
        }
        return Optional.empty();
    }

    /**
     * Deletes a payroll record by its ID.
     * @param id the payroll ID
     * @return true if the payroll was deleted, false otherwise
     */
    public boolean deletePayroll(Long id) {
        if (payrollRepository.findById(id).isPresent()) {
            payrollRepository.deleteById(id);
            return true;
        }
        return false;
    }
}
