package com.javawiz.service;

import com.javawiz.dao.EmployeeRepository;
import com.javawiz.model.Employee;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

import static org.mockito.Mockito.*;

@SpringBootTest
public class ServiceTests {
    @InjectMocks
    EmployeeService service;

    @Mock
    EmployeeRepository repository;

    @Test
    public void testFindAllEmployees() {
        List<Employee> employees = List.of(Employee.builder()
                        .firstName("Ranjan")
                        .lastName("Sai")
                        .build(),
                Employee.builder()
                        .firstName("John")
                        .lastName("Jack")
                        .build(),
                Employee.builder()
                        .firstName("Rakesh")
                        .lastName("Kumar")
                        .build(),
                Employee.builder()
                        .firstName("Steve")
                        .lastName("Job")
                        .build()
        );

        when(repository.findAll()).thenReturn(employees);

        //test
        List<Employee> empList = service.findAll();

        Assertions.assertEquals(4, empList.size());
        verify(repository, times(1)).findAll();
    }

    @Test
    public void testCreateOrSaveEmployee() {
        Employee employee = Employee.builder()
                .firstName("Ranjan")
                .lastName("Sai")
                .build();

        service.save(employee);

        verify(repository, times(1)).save(employee);
    }
}
