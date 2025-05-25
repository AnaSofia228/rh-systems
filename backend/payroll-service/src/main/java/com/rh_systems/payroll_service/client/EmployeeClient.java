package com.rh_systems.payroll_service.client;

import com.rh_systems.payroll_service.dto.EmployeeDTO;
import com.rh_systems.payroll_service.dto.PositionDTO;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@FeignClient(name = "employee-service")
public interface EmployeeClient {

    @GetMapping("/employees/{id}")
    EmployeeDTO getEmployeeById(@PathVariable("id") Long id);

    @GetMapping("/positions/{id}")
    PositionDTO getPositionById(@PathVariable("id") Long id);

    @GetMapping("/positions/name/{name}")
    PositionDTO getPositionByName(@PathVariable("name") String name);
}
