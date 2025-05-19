package com.rh_systems.payroll_service.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.rh_systems.payroll_service.Entity.Payroll;
import com.rh_systems.payroll_service.Entity.PayrollAdjustments;
import com.rh_systems.payroll_service.dto.PayrollAdjustmentsDTO;
import com.rh_systems.payroll_service.dto.PayrollAdjustmentsDTOGetPostPut;
import com.rh_systems.payroll_service.repository.PayrollAdjustmentsRepository;

@Service
public class PayrollAdjustmentsService {
    @Autowired
    private PayrollAdjustmentsRepository payrollAdjustmentsRepository;

    /**
     * Gets all payroll adjustments.
     * @return a list of PayrollAdjustmentsDTOGetPostPut
     */
    public List<PayrollAdjustmentsDTOGetPostPut> getAllPayrollAdjustments() {
        List<PayrollAdjustmentsDTOGetPostPut> payrollAdjustmentToReturn = new ArrayList<>();
        List<PayrollAdjustments> payrollAdjustment = payrollAdjustmentsRepository.findAll();
        for (PayrollAdjustments pa : payrollAdjustment) {
            PayrollAdjustmentsDTOGetPostPut payrollAdjustmentDTO = new PayrollAdjustmentsDTOGetPostPut();
            payrollAdjustmentDTO.convertToPayrollAdjustmentDTO(pa);
            payrollAdjustmentToReturn.add(payrollAdjustmentDTO);
        }
        return payrollAdjustmentToReturn;
    }

    /**
     * Gets a payroll adjustment by its ID.
     * @param id the payroll adjustment ID
     * @return an Optional containing the PayrollAdjustmentsDTOGetPostPut if found, or empty otherwise
     */
    public Optional<PayrollAdjustmentsDTOGetPostPut> getPayrollAdjustmentsById(Long id) {
        Optional<PayrollAdjustments> payrollAdjustment = payrollAdjustmentsRepository.findById(id);
        if (payrollAdjustment.isPresent()) {
            PayrollAdjustmentsDTOGetPostPut payrollAdjustmentDTO = new PayrollAdjustmentsDTOGetPostPut();
            payrollAdjustmentDTO.convertToPayrollAdjustmentDTO(payrollAdjustment.get());
            return Optional.of(payrollAdjustmentDTO);
        }
        return Optional.empty();
    }

    /**
     * Gets a payroll adjustment by its type.
     * @param type the payroll adjustment type
     * @return an Optional containing the PayrollAdjustmentsDTOGetPostPut if found, or empty otherwise
     */
    public Optional<PayrollAdjustmentsDTOGetPostPut> getPayrollAdjustmentsByType(String type) {
        Optional<PayrollAdjustments> payrollAdjustment = payrollAdjustmentsRepository.findByPayrollAdjustmentsType(type);
        if (payrollAdjustment.isPresent()) {
            PayrollAdjustmentsDTOGetPostPut payrollAdjustmentDTO = new PayrollAdjustmentsDTOGetPostPut();
            payrollAdjustmentDTO.convertToPayrollAdjustmentDTO(payrollAdjustment.get());
            return Optional.of(payrollAdjustmentDTO);
        }
        return Optional.empty();
    }

    /**
     * Creates a new payroll adjustment.
     * @param payrollAdjustmentDTO the payroll adjustment data
     * @return an Optional containing the created PayrollAdjustmentsDTOGetPostPut, or empty if a payroll adjustment with the same type exists
     */
    public Optional<PayrollAdjustmentsDTOGetPostPut> createPayrollAdjustments(PayrollAdjustmentsDTO payrollAdjustmentDTO) {
        if (payrollAdjustmentsRepository.findByPayrollAdjustmentsType(payrollAdjustmentDTO.getType()).isPresent()) {
            return Optional.empty();
        }
        PayrollAdjustments payrollAdjustment = new PayrollAdjustments();
        payrollAdjustment.setType(payrollAdjustmentDTO.getType());
        payrollAdjustment.setDescription(payrollAdjustmentDTO.getDescription());
        payrollAdjustment.setAmount(payrollAdjustmentDTO.getAmount());
        Payroll payroll = new Payroll();
        payroll.setId(payrollAdjustmentDTO.getPayrollId());
        payrollAdjustment.setPayroll(payroll);
        PayrollAdjustmentsDTOGetPostPut savedPayrollAdjustment = new PayrollAdjustmentsDTOGetPostPut();
        savedPayrollAdjustment.convertToPayrollAdjustmentDTO(payrollAdjustmentsRepository.save(payrollAdjustment));
        return Optional.of(savedPayrollAdjustment);
    }

    /**
     * Updates an existing payroll adjustment.
     * @param id the payroll adjustment ID
     * @param payrollAdjustmentDTO the payroll adjustment data to update
     * @return an Optional containing the updated PayrollAdjustmentsDTOGetPostPut, or empty if not found or type conflict
     */
    public Optional<PayrollAdjustmentsDTOGetPostPut> updatePayrollAdjustment(Long id, PayrollAdjustmentsDTO payrollAdjustmentDTO) {
        Optional<PayrollAdjustments> payrollAdjustment = payrollAdjustmentsRepository.findById(id);
        if (payrollAdjustment.isPresent()) {
            if (!payrollAdjustment.get().getType().equalsIgnoreCase(payrollAdjustmentDTO.getType())) {
                if (payrollAdjustmentsRepository.findByPayrollAdjustmentsType(payrollAdjustmentDTO.getType()).isPresent()) {
                    return Optional.empty();
                }
            }
            PayrollAdjustments payrollAdjustmentToUpdate = payrollAdjustment.get();
            payrollAdjustmentToUpdate.setType(payrollAdjustmentDTO.getType());
            payrollAdjustmentToUpdate.setDescription(payrollAdjustmentDTO.getDescription());
            payrollAdjustmentToUpdate.setAmount(payrollAdjustmentDTO.getAmount());
            Payroll payroll = new Payroll();
            payroll.setId(payrollAdjustmentDTO.getPayrollId());
            payrollAdjustmentToUpdate.setPayroll(payroll);
            PayrollAdjustmentsDTOGetPostPut updatedPayrollAdjustmentDTO = new PayrollAdjustmentsDTOGetPostPut();
            updatedPayrollAdjustmentDTO.convertToPayrollAdjustmentDTO(payrollAdjustmentsRepository.save(payrollAdjustmentToUpdate));
            return Optional.of(updatedPayrollAdjustmentDTO);
        }
        return Optional.empty();
    }

    /**
     * Deletes a payroll adjustment by its ID.
     * @param id the payroll adjustment ID
     * @return true if the payroll adjustment was deleted, false otherwise
     */
    public boolean deletePayrollAdjustment(Long id) {
        if (payrollAdjustmentsRepository.findById(id).isPresent()) {
            payrollAdjustmentsRepository.deleteById(id);
            return true;
        }
        return false;
    }
}
