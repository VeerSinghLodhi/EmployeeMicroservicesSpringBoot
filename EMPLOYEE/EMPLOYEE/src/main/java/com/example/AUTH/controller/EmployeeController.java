package com.example.AUTH.controller;

import com.example.AUTH.dto.EmployeeRequest;
import com.example.AUTH.service.EmployeeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/employees")
public class EmployeeController {

    @Autowired
    EmployeeService employeeService;

    @PostMapping("/add")
    public ResponseEntity<?>addEmployee(@RequestBody EmployeeRequest request){
        return ResponseEntity.ok(employeeService.addEmployee(request));
    }

    @GetMapping("/get/{id}")
    public ResponseEntity<?>getEmployeeById(@PathVariable Long id){
        return ResponseEntity.ok(employeeService.getEmployeeById(id));
    }

    @GetMapping("/get/all")
    public ResponseEntity<?>getAllEmployees(){
        return ResponseEntity.ok(employeeService.getEmployees());
    }

    @PutMapping("/update/{id}")
    public ResponseEntity<?>getUpdateEmployee(@PathVariable Long id,@RequestBody EmployeeRequest employeeRequest){
        return ResponseEntity.ok(employeeService.updateEmployee(id,employeeRequest));
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<?>deleteEmployee(@PathVariable Long id){
        return ResponseEntity.ok(employeeService.deleteEmployee(id));
    }


}
