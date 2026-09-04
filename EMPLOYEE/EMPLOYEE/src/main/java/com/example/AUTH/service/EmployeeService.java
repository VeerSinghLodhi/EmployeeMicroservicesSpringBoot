package com.example.AUTH.service;

import com.example.AUTH.dto.EmployeeDto;
import com.example.AUTH.dto.EmployeeRequest;

import java.util.List;

public interface EmployeeService {

    EmployeeDto addEmployee(EmployeeRequest request);

    EmployeeDto getEmployeeById(Long id);

    EmployeeDto updateEmployee(Long id,EmployeeRequest request);

    List<EmployeeDto>getEmployees();

    String deleteEmployee(Long id);
}
