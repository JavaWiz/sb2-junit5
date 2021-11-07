package com.javawiz.employees.controllers;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.util.Arrays;
import java.util.List;

import com.javawiz.controller.EmployeeController;
import com.javawiz.model.Employee;
import com.javawiz.service.EmployeeService;
import org.hamcrest.Matchers;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.servlet.MockMvc;

@WebMvcTest(EmployeeController.class)
public class StandaloneControllerTests {

	@MockBean
	private EmployeeService employeeService;

	@Autowired
	MockMvc mockMvc;

	@Test
	public void testFindAll() throws Exception {
		List<Employee> employees = List.of(Employee.builder()
				.firstName("Ranjan")
				.lastName("Sai")
				.build()
		);

		Mockito.when(employeeService.findAll()).thenReturn(employees);

		mockMvc.perform(get("/api/employee"))
				.andExpect(status().isOk())
				.andExpect(jsonPath("$", Matchers.hasSize(1)))
				.andExpect(jsonPath("$[0].firstName", Matchers.is("Ranjan")));
	}
}
