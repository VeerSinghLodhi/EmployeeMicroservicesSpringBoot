package com.example.service.impl;

import com.example.dto.EmployeeDto;
import com.example.dto.EmployeeRequest;
import com.example.entity.Employee;
import com.example.exception.BadRequestException;
import com.example.exception.ResourceNotFoundException;
import com.example.mapper.EmployeeMapper;
import com.example.repository.EmployeeRepository;
import com.example.service.EmployeeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class EmployeeServiceImpl implements EmployeeService {

    @Autowired
    EmployeeRepository employeeRepository;

    @Override
    public EmployeeDto addEmployee(EmployeeRequest request) {
        Employee employee= EmployeeMapper.toEntity(request);
        EmployeeDto savedEmp = EmployeeMapper.toDto(employeeRepository.save(employee));
        return savedEmp;
    }

    @Override
    public EmployeeDto getEmployeeById(Long id) {

        if(id==null){
            throw new BadRequestException("Employee id is null");
        }

        Employee employee = employeeRepository.findById(id).orElseThrow(
                ()->new ResourceNotFoundException("Employee not found with id "+id)
        );
        return EmployeeMapper.toDto(employee);
    }

    @Override
    public List<EmployeeDto> getEmployees() {
        List<Employee>employees=employeeRepository.findAll();
        return employees.stream().map(EmployeeMapper::toDto).toList();
    }

    @Override
    public String deleteEmployee(Long id) {

        if(id==null){
            throw new BadRequestException("Employee id is null");
        }

        Employee employee = employeeRepository.findById(id).orElseThrow(
                ()->new ResourceNotFoundException("Employee not found for deletion with id "+id)
        );
        employeeRepository.delete(employee);
        return "Employee has been deleted";
    }
}
