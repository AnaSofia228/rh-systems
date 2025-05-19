package com.rh_systems.payroll_service.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.rh_systems.payroll_service.Entity.PayrollAdjustments;

/**
 * Repository interface for PayrollAdjustments entity.
 */
@Repository
public interface PayrollAdjustmentsRepository extends JpaRepository<PayrollAdjustments, Long> {
    /**
     * Finds a PayrollAdjustments by its type.
     * @param type the payroll adjustment type
     * @return an Optional containing the PayrollAdjustments if found, or empty otherwise
     */
    Optional<PayrollAdjustments> findByPayrollAdjustmentsType(String type);
}
