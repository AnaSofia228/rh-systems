package com.rh_systems.employee_service.controller;

import com.rh_systems.employee_service.dto.EmployeeLoginDTO;
import com.rh_systems.employee_service.dto.JwtResponse;
import com.rh_systems.employee_service.security.JwtTokenProvider;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("api//auth")
public class AuthController {

    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    private JwtTokenProvider jwtTokenProvider;

    @PostMapping("/login")
    public ResponseEntity<?> authenticateEmployee(@RequestBody EmployeeLoginDTO employeeLoginDTO){

        Authentication authentication = authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(
                        employeeLoginDTO.getEmail(),
                        employeeLoginDTO.getPassword()));

                SecurityContextHolder.getContext().setAuthentication(authentication);

                String jwt = jwtTokenProvider.generateToken(authentication);

        return ResponseEntity.ok(new JwtResponse(jwt, "","",employeeLoginDTO.getEmail()));

    }
}
