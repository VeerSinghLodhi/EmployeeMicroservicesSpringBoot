package com.example.mapper;

import com.example.dto.EmployeeDto;
import com.example.dto.EmployeeRequest;
import com.example.entity.Employee;

public class EmployeeMapper {

    public static EmployeeDto toDto (Employee employee){
        EmployeeDto emp = new EmployeeDto();
        emp.setEmpId(employee.getEmpId());
        emp.setEmpName(employee.getEmpName());
        emp.setEmpCode(employee.getEmpCode());
        emp.setEmpPhone(employee.getEmpPhone());
        emp.setCompanyName(employee.getCompanyName());
        return emp;
    }

    public static Employee toEntity (EmployeeRequest employee){
        Employee emp = new Employee();
        emp.setEmpName(employee.getEmpName());
        emp.setEmpCode(employee.getEmpCode());
        emp.setEmpPhone(employee.getEmpPhone());
        emp.setCompanyName(employee.getCompanyName());
        return emp;
    }
}
