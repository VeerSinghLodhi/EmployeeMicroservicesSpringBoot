package com.example.service.impl;

import com.example.client.AddressClient;
import com.example.dto.AddressDto;
import com.example.dto.EmployeeDto;
import com.example.dto.EmployeeRequest;
import com.example.entity.Employee;
import com.example.exception.BadRequestException;
import com.example.exception.ResourceNotFoundException;
import com.example.mapper.EmployeeMapper;
import com.example.repository.EmployeeRepository;
import com.example.service.EmployeeService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class EmployeeServiceImpl implements EmployeeService {

    private final Logger log = LoggerFactory.getLogger(EmployeeServiceImpl.class);

    private final EmployeeRepository employeeRepository;
    private final AddressClient addressClient;

    public EmployeeServiceImpl(EmployeeRepository employeeRepository, AddressClient addressClient) {
        this.employeeRepository = employeeRepository;
        this.addressClient = addressClient;
    }

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
        EmployeeDto response = EmployeeMapper.toDto(employee);
        List<AddressDto>addressDtoList = new ArrayList<>();
        try{
            addressDtoList = addressClient.getAllAddress(id);
            response.setAddressDtoList(addressDtoList);
        }catch(Exception e){
            log.info("No address found for employee id {}",id);
        }



        return response;
    }

    @Override
    public EmployeeDto updateEmployee(Long id, EmployeeRequest updateRequest) {
        if(id == null)
            throw new BadRequestException("Employee id must not be null");
        Employee employee = employeeRepository.findById(id).orElseThrow(
                ()->new ResourceNotFoundException("Employee not found with id: "+id)
        );
        employee.setEmpName(updateRequest.getEmpName());
        employee.setEmpCode(updateRequest.getEmpCode());
        employee.setEmpPhone(updateRequest.getEmpPhone());
        employee.setCompanyName(updateRequest.getCompanyName());

        return EmployeeMapper.toDto(employeeRepository.save(employee));
    }

    @Override
    public List<EmployeeDto> getEmployees() {
        List<Employee>employees=employeeRepository.findAll();
        List<EmployeeDto> employeeDtos = employees.stream().map(EmployeeMapper::toDto).toList();
        List<EmployeeDto>response = new ArrayList<>();
        for(EmployeeDto employeeDto : employeeDtos){
            List<AddressDto> addressDtoList = new ArrayList<>();
            try{
                addressDtoList = addressClient.getAllAddress(employeeDto.getEmpId());
                employeeDto.setAddressDtoList(addressDtoList);
            }catch(Exception e){
                log.info("No address found for employee id {}",employeeDto.getEmpId());
            }
            response.add(employeeDto);
        }
        return response;
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
