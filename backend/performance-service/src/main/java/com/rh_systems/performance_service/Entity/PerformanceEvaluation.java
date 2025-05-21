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
    private Long employeeId;

    /**
     * Default constructor.
     */
    public PerformanceEvaluation() {
    }

    /**
     * Constructor with all fields.
     *
     * @param id         the ID
     * @param date       the date
     * @param score      the score
     * @param comments   the comments
     * @param employeeId the employee ID
     */
    public PerformanceEvaluation(Long id, Date date, float score, String comments, Long employeeId) {
        this.id = id;
        this.date = date;
        this.score = score;
        this.comments = comments;
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
}
