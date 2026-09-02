package com.example.mapper;

import com.example.dto.EmployeeDto;
import com.example.entity.Employee;

public class EmployeeMapper {

    public static EmployeeDto toDto (Employee employee){
        EmployeeDto emp = new EmployeeDto();
        emp.setId(employee.getId());
        emp.setName(employee.getName());
        return emp;
    }
}
