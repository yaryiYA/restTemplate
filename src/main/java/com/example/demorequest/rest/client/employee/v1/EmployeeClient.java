package com.example.demorequest.rest.client.employee.v1;

import com.example.demorequest.DTO.v1.EmployeeDTOv1;
import com.example.demorequest.rest.client.employee.BaseClient;

import com.example.demorequest.rest.client.employee.aspect.EmployeeRestComponent;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.context.ApplicationContext;
import org.springframework.http.*;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.List;

@Service(value = "EmployeeClient")
public class EmployeeClient extends BaseClient {
    @Value("${baseEndPoint}")
    protected String baseEndPoint;
    @Value("${baseUrl}${baseEndPoint}${getEmployeesList}")
    protected String getAllEmploees;
    @Value("${baseUrl}${baseEndPoint}${getEmployeeLogin}")
    protected String getLogin;
    @Value("${baseUrl}${baseEndPoint}${createEmployee}")
    protected String createEmployee;
    @Value("${baseUrl}${baseEndPoint}${updateEmployee-Login}")
    protected String updateEmployee;
    @Value("${baseUrl}${baseEndPoint}${deleteEmployee-Login}")
    protected String deleteEmployee;


    private final RestTemplate restTemplate;
    private final EmployeeRestComponent employeeRestComponent;

    @Autowired
    public EmployeeClient(ApplicationContext applicationContext, RestTemplateBuilder restTemplateBuilder, EmployeeRestComponent employeeRestComponent) {
        this.employeeRestComponent = employeeRestComponent;
        this.restTemplate = restTemplateBuilder.build();
    }


    public ResponseEntity<EmployeeDTOv1[]> getAllEmployee() {
        HttpHeaders headers = employeeRestComponent.getEmployeeHttpHeaders();
        HttpEntity<?> httpEntity = new HttpEntity<>(headers);

        return restTemplate.exchange(getAllEmploees, HttpMethod.GET, httpEntity, EmployeeDTOv1[].class);
    }

    public ResponseEntity<EmployeeDTOv1> getEmployee(String login) {
        HttpHeaders headers = employeeRestComponent.getEmployeeHttpHeaders();

        headers.setContentType(MediaType.APPLICATION_JSON);
        HttpEntity<?> httpEntity = new HttpEntity<>(headers);
        return restTemplate.exchange(getLogin, HttpMethod.GET, httpEntity, EmployeeDTOv1.class, login);

    }

    public ResponseEntity<EmployeeDTOv1> create(EmployeeDTOv1 employeeDTOv1) {
        HttpHeaders headers = employeeRestComponent.getEmployeeHttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        HttpEntity<EmployeeDTOv1> httpEntity = new HttpEntity<>(employeeDTOv1, headers);
        return restTemplate.exchange(createEmployee, HttpMethod.POST, httpEntity, EmployeeDTOv1.class);
    }

    public ResponseEntity<EmployeeDTOv1> update(String login, EmployeeDTOv1 employeeDTOv1) {
        HttpHeaders headers = employeeRestComponent.getEmployeeHttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        HttpEntity<EmployeeDTOv1> httpEntity = new HttpEntity<>(employeeDTOv1, headers);
        return restTemplate.exchange(updateEmployee, HttpMethod.PUT, httpEntity, EmployeeDTOv1.class, login);
    }

    public ResponseEntity<EmployeeDTOv1> delete(Long id) {
        HttpHeaders headers = employeeRestComponent.getEmployeeHttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        HttpEntity<?> httpEntity = new HttpEntity<>(headers);
        return restTemplate.exchange(deleteEmployee, HttpMethod.DELETE, httpEntity, EmployeeDTOv1.class, id);
    }


}



