package com.example.service;

import com.example.dto.EmployeeDto;
import com.example.dto.EmployeeRequest;

import java.util.List;

public interface EmployeeService {

    EmployeeDto addEmployee(EmployeeRequest request);

    EmployeeDto getEmployeeById(Long id);

    List<EmployeeDto>getEmployees();

    String deleteEmployee(Long id);
}
