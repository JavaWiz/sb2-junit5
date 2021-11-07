package com.javawiz.controller;

import com.javawiz.model.Employee;
import com.javawiz.model.Employees;
import com.javawiz.service.EmployeeService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.ValidationException;
import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("api/employee")
@RequiredArgsConstructor
public class EmployeeController implements BaseController<Employee>  {

    private final EmployeeService employeeService;

    public ResponseEntity<?> create(Employee employee) {
        return ResponseEntity.status(HttpStatus.CREATED)
                .body(employeeService.save(employee));
    }

    public ResponseEntity<?> read() {
        return ResponseEntity.status(HttpStatus.OK)
                .body(employeeService.findAll());
    }

    public Employees getEmployees() {
        return employeeService.getEmployees();
    }

    public ResponseEntity<?> update(Employee employee) {
        return ResponseEntity.status(HttpStatus.OK)
                .body(employeeService.save(employee));
    }

    public ResponseEntity<?> delete(Integer id) {
      employeeService.deleteById(id);
      return ResponseEntity.status(HttpStatus.OK)
              .body("Delete with id = "+id);
    }

    public Employee somethingIsWrong() {
        throw new ValidationException("Something is wrong");
    }
}
