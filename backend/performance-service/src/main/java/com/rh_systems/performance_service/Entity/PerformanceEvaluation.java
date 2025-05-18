package com.rh_systems.performance_service.Entity;

import java.util.Date;

import jakarta.persistence.*;

/**
 * Entity representing a performance evaluation.
 */
@Entity
@Table(name = "performance_evaluation")
public class PerformanceEvaluation {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private Date date;
    private float score;
    private String comments;

    // @ManyToOne
    // @JoinColumn(name = "id_employee")
    // private Employee employee;

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
     * Gets the date.
     * @return the date
     */
    public Date getDate() {
        return date;
    }

    /**
     * Sets the date.
     * @param date the date to set
     */
    public void setDate(Date date) {
        this.date = date;
    }

    /**
     * Gets the score.
     * @return the score
     */
    public float getScore() {
        return score;
    }

    /**
     * Sets the score.
     * @param score the score to set
     */
    public void setScore(float score) {
        this.score = score;
    }

    /**
     * Gets the comments.
     * @return the comments
     */
    public String getComments() {
        return comments;
    }

    /**
     * Sets the comments.
     * @param comments the comments to set
     */
    public void setComments(String comments) {
        this.comments = comments;
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
}
