package com.rh_systems.schedule_service.service;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.rh_systems.schedule_service.Entity.Schedule;
import com.rh_systems.schedule_service.dto.ScheduleDTO;
import com.rh_systems.schedule_service.dto.ScheduleDTOGetPostPut;
import com.rh_systems.schedule_service.repository.ScheduleRepository;

@Service
public class ScheduleService {
    @Autowired
    private ScheduleRepository scheduleRepository;

    /**
     * Gets all schedules.
     * @return a list of ScheduleDTOGetPostPut
     */
    public List<ScheduleDTOGetPostPut> getAllSchedule() {
        List<ScheduleDTOGetPostPut> scheduleToReturn = new ArrayList<>();
        List<Schedule> schedule = scheduleRepository.findAll();
        for (Schedule s : schedule) {
            ScheduleDTOGetPostPut scheduleDTO = new ScheduleDTOGetPostPut();
            scheduleDTO.convertToScheduleDTO(s);
            scheduleToReturn.add(scheduleDTO);
        }
        return scheduleToReturn;
    }

    /**
     * Gets a schedule by its ID.
     * @param id the schedule ID
     * @return an Optional containing the ScheduleDTOGetPostPut if found, or empty otherwise
     */
    public Optional<ScheduleDTOGetPostPut> getScheduleById(Long id) {
        Optional<Schedule> schedule = scheduleRepository.findById(id);
        if (schedule.isPresent()) {
            ScheduleDTOGetPostPut scheduleDTO = new ScheduleDTOGetPostPut();
            scheduleDTO.convertToScheduleDTO(schedule.get());
            return Optional.of(scheduleDTO);
        }
        return Optional.empty();
    }

    /**
     * Gets a schedule by its date.
     * @param date the schedule date
     * @return an Optional containing the ScheduleDTOGetPostPut if found, or empty otherwise
     */
    public Optional<ScheduleDTOGetPostPut> getScheduleByDate(LocalDate date) {
        Optional<Schedule> schedule = scheduleRepository.findByScheduleDate(date);
        if (schedule.isPresent()) {
            ScheduleDTOGetPostPut scheduleDTO = new ScheduleDTOGetPostPut();
            scheduleDTO.convertToScheduleDTO(schedule.get());
            return Optional.of(scheduleDTO);
        }
        return Optional.empty();
    }

    /**
     * Creates a new schedule.
     * @param scheduleDTO the schedule data
     * @return an Optional containing the created ScheduleDTOGetPostPut, or empty if a schedule with the same date exists
     */
    public Optional<ScheduleDTOGetPostPut> createSchedule(ScheduleDTO scheduleDTO) {
        if (scheduleRepository.findByScheduleDate(scheduleDTO.getDate()).isPresent()) {
            return Optional.empty();
        }
        Schedule schedule = new Schedule();
        schedule.setDate(scheduleDTO.getDate());
        schedule.setStartTime(scheduleDTO.getStartTime());
        schedule.setExitTime(scheduleDTO.getExitTime());
        schedule.setDeductedHours(scheduleDTO.getDeductedHours());
        schedule.setTotalHours(scheduleDTO.getTotalHours());
        ScheduleDTOGetPostPut createScheduleDTO = new ScheduleDTOGetPostPut();
        createScheduleDTO.convertToScheduleDTO(scheduleRepository.save(schedule));
        return Optional.of(createScheduleDTO);
    }

    /**
     * Updates an existing schedule.
     * @param id the schedule ID
     * @param scheduleDTO the schedule data to update
     * @return an Optional containing the updated ScheduleDTOGetPostPut, or empty if not found or date conflict
     */
    public Optional<ScheduleDTOGetPostPut> updatedSchedule(Long id, ScheduleDTO scheduleDTO) {
        Optional<Schedule> schedule = scheduleRepository.findById(id);
        if (schedule.isPresent()) {
            if (!schedule.get().getDate().equals(scheduleDTO.getDate())) {
                if (scheduleRepository.findByScheduleDate(scheduleDTO.getDate()).isPresent()) {
                    return Optional.empty();
                }
            }
            Schedule scheduleToUpdate = schedule.get();
            scheduleToUpdate.setDate(scheduleDTO.getDate());
            scheduleToUpdate.setStartTime(scheduleDTO.getStartTime());
            scheduleToUpdate.setExitTime(scheduleDTO.getExitTime());
            scheduleToUpdate.setDeductedHours(scheduleDTO.getDeductedHours());
            scheduleToUpdate.setTotalHours(scheduleDTO.getTotalHours());
            ScheduleDTOGetPostPut updatedScheduleDTO = new ScheduleDTOGetPostPut();
            updatedScheduleDTO.convertToScheduleDTO(scheduleRepository.save(scheduleToUpdate));
            return Optional.of(updatedScheduleDTO);
        }
        return Optional.empty();
    }

    /**
     * Deletes a schedule by its ID.
     * @param id the schedule ID
     * @return true if the schedule was deleted, false otherwise
     */
    public boolean deleteSchedule(Long id) {
        if (scheduleRepository.findById(id).isPresent()) {
            scheduleRepository.deleteById(id);
            return true;
        }
        return false;
    }
}
