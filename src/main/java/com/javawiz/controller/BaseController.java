package com.javawiz.controller;

import com.javawiz.model.Employee;
import com.javawiz.model.Employees;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.ValidationException;

public interface BaseController<T> {
    @PostMapping
    ResponseEntity<?> create(@RequestBody Employee employee);

    @GetMapping
    ResponseEntity<?> read();

    @GetMapping("/all")
    public Employees getEmployees();

    @PutMapping
    ResponseEntity<?> update(@RequestBody Employee employee);

    @DeleteMapping("{id}")
    ResponseEntity<?> delete(@PathVariable Integer id);

    @GetMapping("/wrong")
    Employee somethingIsWrong();

    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler(ValidationException.class)
    default String exceptionHandler(ValidationException e) {
        return e.getMessage();
    }
}
