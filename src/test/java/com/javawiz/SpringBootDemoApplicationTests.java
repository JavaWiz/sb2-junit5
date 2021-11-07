package com.javawiz;

import java.net.URI;
import java.net.URISyntaxException;

import com.javawiz.model.Employee;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.SpringBootTest.WebEnvironment;
import org.springframework.boot.web.server.LocalServerPort;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.RestTemplate;

@Slf4j
@SpringBootTest(webEnvironment = WebEnvironment.RANDOM_PORT)
public class SpringBootDemoApplicationTests {
    @LocalServerPort
    int randomServerPort;

    @Test
    public void testAddEmployeeSuccess() throws URISyntaxException {
        RestTemplate restTemplate = new RestTemplate();
        String baseUrl = "http://localhost:"+randomServerPort+"/api/employee/";
        URI uri = new URI(baseUrl);
        Employee employee = new Employee("Adam", "Gilly");

        HttpHeaders headers = new HttpHeaders();
        headers.set("X-COM-PERSIST", "true");

        HttpEntity<Employee> request = new HttpEntity<>(employee, headers);

        ResponseEntity<String> result = restTemplate.postForEntity(uri, request, String.class);

        //Verify request succeed
        Assertions.assertEquals(201, result.getStatusCodeValue());
    }

    @Test
    public void testAddEmployeeMissingHeader() throws URISyntaxException {
        RestTemplate restTemplate = new RestTemplate();
        final String baseUrl = "http://localhost:" + randomServerPort + "/api/employee";
        URI uri = new URI(baseUrl);
        Employee employee = new Employee("Adam", "Gilly");

        HttpHeaders headers = new HttpHeaders();
        HttpEntity<Employee> request = new HttpEntity<>(employee, null);

        try {
            ResponseEntity<String> result = restTemplate.postForEntity(uri, request, String.class);
            log.info("{}", result);

            Assertions.fail();
        } catch (HttpClientErrorException ex) {
            //Verify bad request and missing header
            Assertions.assertEquals(400, ex.getRawStatusCode());
            Assertions.assertEquals(true, ex.getResponseBodyAsString().contains("Missing request header"));
        }
    }

    @Test
    public void testGetEmployeeListSuccessWithHeaders() throws URISyntaxException {
        RestTemplate restTemplate = new RestTemplate();

        final String baseUrl = "http://localhost:" + randomServerPort + "/api/employee/";
        URI uri = new URI(baseUrl);

        HttpEntity<Employee> requestEntity = new HttpEntity<>(null, new HttpHeaders());

        try {
            restTemplate.exchange(uri, HttpMethod.GET, requestEntity, String.class);
            Assertions.fail();
        } catch (HttpClientErrorException ex) {
            //Verify bad request and missing header
            Assertions.assertEquals(400, ex.getRawStatusCode());
            Assertions.assertEquals(true, ex.getResponseBodyAsString().contains("Missing request header"));
        }
    }
}