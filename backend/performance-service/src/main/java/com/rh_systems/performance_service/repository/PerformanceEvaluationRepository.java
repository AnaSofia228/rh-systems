package com.rh_systems.performance_service.repository;

import java.util.Date;
import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import com.rh_systems.performance_service.Entity.PerformanceEvaluation;

/**
 * Repository interface for PerformanceEvaluation entity.
 */
@Repository
public interface PerformanceEvaluationRepository extends JpaRepository<PerformanceEvaluation, Long> {
    /**
     * Finds a PerformanceEvaluation by employee ID and date.
     * @param employeeId the employee ID
     * @param date the evaluation date
     * @return an Optional containing the PerformanceEvaluation if found, or empty otherwise
     */
    Optional<PerformanceEvaluation> findByEmployeeIdAndDate(Long employeeId, Date date);

    /**
     * Finds all PerformanceEvaluations by employee ID.
     * @param employeeId the employee ID
     * @return a list of PerformanceEvaluations
     */
    List<PerformanceEvaluation> findByEmployeeId(Long employeeId);

    /**
     * Finds all PerformanceEvaluations by date.
     * @param date the evaluation date
     * @return a list of PerformanceEvaluations
     */
    List<PerformanceEvaluation> findByDate(Date date);
}
