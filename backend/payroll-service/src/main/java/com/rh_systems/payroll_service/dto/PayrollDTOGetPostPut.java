package com.rh_systems.payroll_service.dto;

import java.util.Date;

import com.rh_systems.payroll_service.Entity.Payroll;

/**
 * Data Transfer Object for getting, posting, and putting payroll records.
 */
public class PayrollDTOGetPostPut {
    private Long id;
    private String status;
    private float amount;
    private Date paymentDate;
    private Long employeeId;

    /**
     * Gets the ID.
     * @return the ID
     */
    public Long getId() {
        return id;
    }

    /**
     * Sets the ID.
     * @param id the ID to set
     */
    public void setId(Long id) {
        this.id = id;
    }

    /**
     * Gets the status.
     * @return the status
     */
    public String getStatus() {
        return status;
    }

    /**
     * Sets the status.
     * @param status the status to set
     */
    public void setStatus(String status) {
        this.status = status;
    }

    /**
     * Gets the amount.
     * @return the amount
     */
    public float getAmount() {
        return amount;
    }

    /**
     * Sets the amount.
     * @param amount the amount to set
     */
    public void setAmount(float amount) {
        this.amount = amount;
    }

    /**
     * Gets the payment date.
     * @return the payment date
     */
    public Date getPaymentDate() {
        return paymentDate;
    }

    /**
     * Sets the payment date.
     * @param paymentDate the payment date to set
     */
    public void setPaymentDate(Date paymentDate) {
        this.paymentDate = paymentDate;
    }

    /**
     * Gets the employee ID.
     * @return the employee ID
     */
    public Long getEmployeeId() {
        return employeeId;
    }

    /**
     * Sets the employee ID.
     * @param employeeId the employee ID to set
     */
    public void setEmployeeId(Long employeeId) {
        this.employeeId = employeeId;
    }

    /**
     * Converts a Payroll entity to this DTO.
     * @param payrollEntity the entity to convert
     */
    public void convertToPayrollDTO(Payroll payrollEntity) {
        this.setId(payrollEntity.getId());
        this.setStatus(payrollEntity.getStatus());
        this.setAmount(payrollEntity.getAmount());
        this.setPaymentDate(payrollEntity.getPaymentDate());
        // this.setEmployeeId(payrollEntity.getEmployee().getId());
    }
}
