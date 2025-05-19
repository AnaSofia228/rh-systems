package com.rh_systems.performance_service.service;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.rh_systems.performance_service.Entity.PerformanceEvaluation;
import com.rh_systems.performance_service.dto.PerformanceEvaluationDTO;
import com.rh_systems.performance_service.dto.PerformanceEvaluationDTOGetPostPut;
import com.rh_systems.performance_service.repository.PerformanceEvaluationRepository;

@Service
public class PerformanceEvaluationService {
    @Autowired
    private PerformanceEvaluationRepository performanceEvaluationRepository;

    /**
     * Gets all performance evaluations.
     * @return a list of PerformanceEvaluationDTOGetPostPut
     */
    public List<PerformanceEvaluationDTOGetPostPut> getAllPerformanceEvaluation() {
        List<PerformanceEvaluationDTOGetPostPut> performanceEvaluationToReturn = new ArrayList<>();
        List<PerformanceEvaluation> performanceEvaluations = performanceEvaluationRepository.findAll();
        for (PerformanceEvaluation pe : performanceEvaluations) {
            PerformanceEvaluationDTOGetPostPut performanceEvaluationDTO = new PerformanceEvaluationDTOGetPostPut();
            performanceEvaluationDTO.convertToPerformanceEvaluationDTO(pe);
            performanceEvaluationToReturn.add(performanceEvaluationDTO);
        }
        return performanceEvaluationToReturn;
    }

    /**
     * Gets a performance evaluation by its ID.
     * @param id the performance evaluation ID
     * @return an Optional containing the PerformanceEvaluationDTOGetPostPut if found, or empty otherwise
     */
    public Optional<PerformanceEvaluationDTOGetPostPut> getPerformanceEvaluationById(Long id) {
        Optional<PerformanceEvaluation> performanceEvaluation = performanceEvaluationRepository.findById(id);
        if (performanceEvaluation.isPresent()) {
            PerformanceEvaluationDTOGetPostPut performanceEvaluationDTO = new PerformanceEvaluationDTOGetPostPut();
            performanceEvaluationDTO.convertToPerformanceEvaluationDTO(performanceEvaluation.get());
            return Optional.of(performanceEvaluationDTO);
        }
        return Optional.empty();
    }

    /**
     * Gets a performance evaluation by employee ID and date.
     * @param employeeId the employee ID
     * @param date the evaluation date
     * @return an Optional containing the PerformanceEvaluationDTOGetPostPut if found, or empty otherwise
     */
    public Optional<PerformanceEvaluationDTOGetPostPut> getPerformanceEvaluationByEmployeeIdAndDate(Long employeeId, Date date) {
        Optional<PerformanceEvaluation> performanceEvaluation = performanceEvaluationRepository.findByEmployeeIdAndDate(employeeId, date);
        if (performanceEvaluation.isPresent()) {
            PerformanceEvaluationDTOGetPostPut performanceEvaluationDTO = new PerformanceEvaluationDTOGetPostPut();
            performanceEvaluationDTO.convertToPerformanceEvaluationDTO(performanceEvaluation.get());
            return Optional.of(performanceEvaluationDTO);
        }
        return Optional.empty();
    }

    /**
     * Creates a new performance evaluation.
     * @param performanceEvaluationDTO the performance evaluation data
     * @return an Optional containing the created PerformanceEvaluationDTOGetPostPut, or empty if a performance evaluation with the same employee and date exists
     */
    public Optional<PerformanceEvaluationDTOGetPostPut> createPerformanceEvaluation(PerformanceEvaluationDTO performanceEvaluationDTO) {
        if (performanceEvaluationRepository.findByEmployeeIdAndDate(performanceEvaluationDTO.getEmployeeId(), performanceEvaluationDTO.getDate()).isPresent()) {
            return Optional.empty();
        }
        PerformanceEvaluation performanceEvaluation = new PerformanceEvaluation();
        performanceEvaluation.setDate(performanceEvaluationDTO.getDate());
        performanceEvaluation.setComments(performanceEvaluationDTO.getComments());
        performanceEvaluation.setScore(performanceEvaluationDTO.getScore());
        // Employee employee = new Employee();
        // employee.setId(performanceEvaluationDTO.getEmployeeId());
        // performanceEvaluation.setEmployee(employee);
        PerformanceEvaluationDTOGetPostPut createdPerformanceEvaluationDTO = new PerformanceEvaluationDTOGetPostPut();
        createdPerformanceEvaluationDTO.convertToPerformanceEvaluationDTO(performanceEvaluationRepository.save(performanceEvaluation));
        return Optional.of(createdPerformanceEvaluationDTO);
    }

    /**
     * Updates an existing performance evaluation.
     * @param id the performance evaluation ID
     * @param performanceEvaluationDTO the performance evaluation data to update
     * @return an Optional containing the updated PerformanceEvaluationDTOGetPostPut, or empty if not found or date conflict
     */
    public Optional<PerformanceEvaluationDTOGetPostPut> updatePerformanceEvaluation(Long id, PerformanceEvaluationDTO performanceEvaluationDTO) {
        Optional<PerformanceEvaluation> performanceEvaluation = performanceEvaluationRepository.findById(id);
        if (performanceEvaluation.isPresent()) {
            if (!performanceEvaluation.get().getDate().equals(performanceEvaluationDTO.getDate())) {
                if (performanceEvaluationRepository.findByEmployeeIdAndDate(performanceEvaluationDTO.getEmployeeId(), performanceEvaluationDTO.getDate()).isPresent()) {
                    return Optional.empty();
                }
            }
            PerformanceEvaluation updatedPerformanceEvaluation = performanceEvaluation.get();
            updatedPerformanceEvaluation.setDate(performanceEvaluationDTO.getDate());
            updatedPerformanceEvaluation.setComments(performanceEvaluationDTO.getComments());
            updatedPerformanceEvaluation.setScore(performanceEvaluationDTO.getScore());
            // Employee employee = new Employee();
            // employee.setId(performanceEvaluationDTO.getEmployeeId());
            // updatedPerformanceEvaluation.setEmployee(employee);
            PerformanceEvaluationDTOGetPostPut updatedPerformanceEvaluationDTO = new PerformanceEvaluationDTOGetPostPut();
            updatedPerformanceEvaluationDTO.convertToPerformanceEvaluationDTO(performanceEvaluationRepository.save(updatedPerformanceEvaluation));
            return Optional.of(updatedPerformanceEvaluationDTO);
        }
        return Optional.empty();
    }

    /**
     * Deletes a performance evaluation by its ID.
     * @param id the performance evaluation ID
     * @return true if the performance evaluation was deleted, false otherwise
     */
    public boolean deletePerformanceEvaluation(Long id) {
        if (performanceEvaluationRepository.findById(id).isPresent()) {
            performanceEvaluationRepository.deleteById(id);
            return true;
        }
        return false;
    }
}
