package com.rh_systems.schedule_service.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.rh_systems.schedule_service.Entity.CountEmployeeSchedule;
import com.rh_systems.schedule_service.Entity.EmployeeSchedule;
import com.rh_systems.schedule_service.dto.CountEmployeeScheduleDTO;
import com.rh_systems.schedule_service.dto.CountEmployeeScheduleDTOGetPostPut;
import com.rh_systems.schedule_service.repository.CountEmployeeScheduleRepository;

@Service
public class CountEmployeeScheduleService {
    @Autowired
    private CountEmployeeScheduleRepository countEmployeeScheduleRepository;

    /**
     * Gets all count employee schedules.
     * @return a list of CountEmployeeScheduleDTOGetPostPut
     */
    public List<CountEmployeeScheduleDTOGetPostPut> getAllEmployeeSchedule() {
        List<CountEmployeeScheduleDTOGetPostPut> countEmployeeScheduleToReturn = new ArrayList<>();
        List<CountEmployeeSchedule> countEmployeeSchedules = countEmployeeScheduleRepository.findAll();
        for (CountEmployeeSchedule ces : countEmployeeSchedules) {
            CountEmployeeScheduleDTOGetPostPut countEmployeeScheduleDTO = new CountEmployeeScheduleDTOGetPostPut();
            countEmployeeScheduleDTO.convertToCountEmployeeScheduleDTO(ces);
            countEmployeeScheduleToReturn.add(countEmployeeScheduleDTO);
        }
        return countEmployeeScheduleToReturn;
    }

    /**
     * Gets a count employee schedule by its ID.
     * @param id the count employee schedule ID
     * @return an Optional containing the CountEmployeeScheduleDTOGetPostPut if found, or empty otherwise
     */
    public Optional<CountEmployeeScheduleDTOGetPostPut> getCountEmployeeScheduleById(Long id) {
        Optional<CountEmployeeSchedule> countEmployeeSchedule = countEmployeeScheduleRepository.findById(id);
        if (countEmployeeSchedule.isPresent()) {
            CountEmployeeScheduleDTOGetPostPut countEmployeeScheduleDTO = new CountEmployeeScheduleDTOGetPostPut();
            countEmployeeScheduleDTO.convertToCountEmployeeScheduleDTO(countEmployeeSchedule.get());
            return Optional.of(countEmployeeScheduleDTO);
        }
        return Optional.empty();
    }

    /**
     * Gets a count employee schedule by work hours.
     * @param workHours the work hours
     * @return an Optional containing the CountEmployeeScheduleDTOGetPostPut if found, or empty otherwise
     */
    public Optional<CountEmployeeScheduleDTOGetPostPut> getCountEmployeeScheduleByWorkHours(Float workHours) {
        Optional<CountEmployeeSchedule> countEmployeeSchedule = countEmployeeScheduleRepository.findByCountEmployeeSchedule(workHours);
        if (countEmployeeSchedule.isPresent()) {
            CountEmployeeScheduleDTOGetPostPut countEmployeeScheduleDTO = new CountEmployeeScheduleDTOGetPostPut();
            countEmployeeScheduleDTO.convertToCountEmployeeScheduleDTO(countEmployeeSchedule.get());
            return Optional.of(countEmployeeScheduleDTO);
        }
        return Optional.empty();
    }

    /**
     * Creates a new count employee schedule.
     * @param countEmployeeScheduleDTO the count employee schedule data
     * @return an Optional containing the created CountEmployeeScheduleDTOGetPostPut, or empty if a schedule with the same work hours exists
     */
    public Optional<CountEmployeeScheduleDTOGetPostPut> createCountEmployeeSchedule(CountEmployeeScheduleDTO countEmployeeScheduleDTO) {
        if (countEmployeeScheduleRepository.findByCountEmployeeSchedule(countEmployeeScheduleDTO.getWorkHours()).isPresent()) {
            return Optional.empty();
        } 
        CountEmployeeSchedule countEmployeeSchedule = new CountEmployeeSchedule();
        countEmployeeSchedule.setWorkHours(countEmployeeScheduleDTO.getWorkHours());
        countEmployeeSchedule.setWorkDate(countEmployeeScheduleDTO.getWorkDate());
        EmployeeSchedule employeeSchedule = new EmployeeSchedule();
        employeeSchedule.setId(countEmployeeScheduleDTO.getEmployeeScheduleId());
        countEmployeeSchedule.setEmployeeSchedule(employeeSchedule);
        CountEmployeeScheduleDTOGetPostPut createdCountEmployeeScheduleDTO = new CountEmployeeScheduleDTOGetPostPut();
        createdCountEmployeeScheduleDTO.convertToCountEmployeeScheduleDTO(countEmployeeScheduleRepository.save(countEmployeeSchedule));
        return Optional.of(createdCountEmployeeScheduleDTO);
    }

    /**
     * Updates an existing count employee schedule.
     * @param id the count employee schedule ID
     * @param countEmployeeScheduleDTO the count employee schedule data to update
     * @return an Optional containing the updated CountEmployeeScheduleDTOGetPostPut, or empty if not found or work hours conflict
     */
    public Optional<CountEmployeeScheduleDTOGetPostPut> updateCountEmployeeSchedule(Long id, CountEmployeeScheduleDTO countEmployeeScheduleDTO) {
        Optional<CountEmployeeSchedule> countEmployeeSchedule = countEmployeeScheduleRepository.findById(id);
        if (countEmployeeSchedule.isPresent()) {
            if (!countEmployeeSchedule.get().getWorkHours().equals(countEmployeeScheduleDTO.getWorkHours())) {
                if (countEmployeeScheduleRepository.findByCountEmployeeSchedule(countEmployeeScheduleDTO.getWorkHours()).isPresent()) {
                    return Optional.empty();
                }
            }
            CountEmployeeSchedule updatedCountEmployeeSchedule = countEmployeeSchedule.get();
            updatedCountEmployeeSchedule.setWorkHours(countEmployeeScheduleDTO.getWorkHours());
            updatedCountEmployeeSchedule.setWorkDate(countEmployeeScheduleDTO.getWorkDate());
            EmployeeSchedule employeeSchedule = new EmployeeSchedule();
            employeeSchedule.setId(countEmployeeScheduleDTO.getEmployeeScheduleId());
            updatedCountEmployeeSchedule.setEmployeeSchedule(employeeSchedule);
            CountEmployeeScheduleDTOGetPostPut updatedCountEmployeeScheduleDTO = new CountEmployeeScheduleDTOGetPostPut();
            updatedCountEmployeeScheduleDTO.convertToCountEmployeeScheduleDTO(countEmployeeScheduleRepository.save(updatedCountEmployeeSchedule));
            return Optional.of(updatedCountEmployeeScheduleDTO);
        }
        return Optional.empty();
    }

    /**
     * Deletes a count employee schedule by its ID.
     * @param id the count employee schedule ID
     * @return true if the count employee schedule was deleted, false otherwise
     */
    public boolean deleteCountEmployeeSchedule(Long id) {
        if (countEmployeeScheduleRepository.findById(id).isPresent()) {
            countEmployeeScheduleRepository.deleteById(id);
            return true;
        }
        return false;
    }
}
