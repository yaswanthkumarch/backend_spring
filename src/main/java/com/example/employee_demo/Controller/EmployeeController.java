package com.example.employee_demo.Controller;

import com.example.employee_demo.Entity.Employee;
import com.example.employee_demo.Service.EmployeeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api")
public class EmployeeController {

    @Autowired
    private EmployeeService employeeService;

    // New GetMapping for /message
    @GetMapping("/message")
    public String message() {
        System.out.println("message");
        return "Hello from the message endpoint!";
    }

    @PostMapping("/submit")
    public ResponseEntity<Employee> saveEmployee(@RequestBody Employee employee) {
        Employee save = employeeService.createEmployee(employee);
        return new ResponseEntity<>(save, HttpStatus.CREATED);
    }

    @GetMapping
    public ResponseEntity<List<Employee>> getAllEmployees() {
        return new ResponseEntity<>(employeeService.getAllEmployees(), HttpStatus.OK);
    }

    @GetMapping("/get")
    public ResponseEntity<Employee> getEmployeeById(@RequestParam int employeeId) {
        Employee getEmployee = employeeService.getEmployeeById(employeeId);
        return new ResponseEntity<>(getEmployee, HttpStatus.OK);
    }

    @PutMapping("/{employeeId}")
    public ResponseEntity<Employee> updateEmployee(@PathVariable int employeeId, @RequestBody Employee updateEmployee) {
        Employee updated = employeeService.updateEmployee(employeeId, updateEmployee);
        return ResponseEntity.ok(updated);
    }

    @DeleteMapping("/{employeeId}")
    public ResponseEntity<String> deleteEmployee(@PathVariable("employeeId") int employeeId) {
        employeeService.deleteEmployee(employeeId);
        return new ResponseEntity<>("Employee deleted", HttpStatus.OK);
    }
}
