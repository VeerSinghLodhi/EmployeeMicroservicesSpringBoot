package com.example.client;

import com.example.dto.EmployeeDto;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@FeignClient(name = "employeeClient", url = "${employee.service.url}")
public interface EmployeeClient {

    @GetMapping("/employees/get/{id}")
    EmployeeDto getEmployeeById(@PathVariable Long id);
}
