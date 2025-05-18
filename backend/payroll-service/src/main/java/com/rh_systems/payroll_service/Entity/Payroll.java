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

    // @ManyToOne
    // @JoinColumn(name = "id_employee")
    // private Employee employee;

    // @OneToMany(mappedBy = "payroll", cascade = CascadeType.ALL)
    // private List<PayrollAdjustments> adjustments;

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

    /**
     * Gets the employee.
     * @return the employee
     */
    // public Employee getEmployee() {
    //     return employee;
    // }

    /**
     * Sets the employee.
     * @param employee the employee to set
     */
    // public void setEmployee(Employee employee) {
    //     this.employee = employee;
    // }

    /**
     * Gets the payroll adjustments.
     * @return the payroll adjustments
     */
    // public List<PayrollAdjustments> getAdjustments() {
    //     return adjustments;
    // }

    /**
     * Sets the payroll adjustments.
     * @param adjustments the payroll adjustments to set
     */
    // public void setAdjustments(List<PayrollAdjustments> adjustments) {
    //     this.adjustments = adjustments;
    // }
}
