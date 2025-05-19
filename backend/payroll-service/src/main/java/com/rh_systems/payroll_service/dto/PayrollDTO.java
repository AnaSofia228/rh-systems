package com.rh_systems.payroll_service.dto;

import java.util.Date;

import jakarta.validation.constraints.*;

/**
 * Data Transfer Object for payroll.
 */
public class PayrollDTO {
    @NotBlank(message = "Payroll cannot be null")
    @Size(max = 50, message = "Status cannot exceed 50 characters")
    private String status;
    
    @NotNull(message = "Payment date cannot be null")
    private Date paymentDate;

    @DecimalMin(value = "0.0", inclusive = true, message = "Amount must be positive")
    private float amount;

    @NotNull(message = "Employee ID cannot be null")
    @Min(value = 1, message = "Employee ID must be at least 1")
    private Long employeeId;

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
}
