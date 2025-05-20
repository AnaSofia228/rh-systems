package com.rh_systems.payroll_service.Entity;

import java.util.Date;

import jakarta.persistence.*;

/**
 * Entity representing a payroll record.
 */
@Entity
@Table(name = "payroll")
public class Payroll {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "paymentDate")
    private Date paymentDate;

    private float amount;
    private String status;
    private Long employeeId;

    /**
     * Default constructor.
     */
    public Payroll() {
    }

    /**
     * Constructor with all fields.
     *
     * @param id          the ID
     * @param paymentDate the payment date
     * @param amount      the amount
     * @param status      the status
     * @param employeeId  the employee ID
     */
    public Payroll(Long id, Date paymentDate, float amount, String status, Long employeeId) {
        this.id = id;
        this.paymentDate = paymentDate;
        this.amount = amount;
        this.status = status;
        this.employeeId = employeeId;
    }

    /**
     * Gets the employee ID.
     *
     * @return the employee ID
     */
    public Long getEmployeeId() {
        return employeeId;
    }

    /**
     * Sets the employee ID.
     *
     * @param employeeId the employee ID to set
     */
    public void setEmployeeId(Long employeeId) {
        this.employeeId = employeeId;
    }

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

}
