package com.example.demorequest.controller;

import com.example.demorequest.DTO.v1.EmployeeDTOv1;
import com.example.demorequest.rest.client.employee.v1.EmployeeClient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Objects;

// #TODO переделать rest controller в controller для работы с тимлиф
@Controller
@RequestMapping("/employee")
public class EmployeeDtoController {
    private final EmployeeClient employeeClient;

    @Autowired
    public EmployeeDtoController(EmployeeClient employeeClient) {
        this.employeeClient = employeeClient;
    }

    @GetMapping("/list")
    public String getAllEmployees(Model model)  {
       List<EmployeeDTOv1> employees = List.of(Objects.requireNonNull(employeeClient.getAllEmployee().getBody()));
        model.addAttribute("employees",employees);
        return "employee/list";
    }

    @GetMapping("/{login}")
    public String getEmployee(@PathVariable String login, Model model) {
        EmployeeDTOv1 employeeDTOv1 = employeeClient.getEmployee(login).getBody();
        model.addAttribute("employee",employeeDTOv1);
        return "employee/login";
    }


    @GetMapping("/create")
    public String createGetEmployee(Model model) {
        model.addAttribute("employee", new EmployeeDTOv1());
        return "employee/new";
    }

    @PostMapping()
    public String createEmployee(@ModelAttribute("employee") EmployeeDTOv1 employeeDTOv1) {
         employeeClient.create(employeeDTOv1);
        return "redirect:employee";
    }

    @PutMapping("/update/{login}")
    public ResponseEntity<EmployeeDTOv1> updateEmployee(@PathVariable String login, @RequestBody EmployeeDTOv1 employeeDTOv1) {
        return employeeClient.update(login, employeeDTOv1);
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<EmployeeDTOv1> deleteEmployee(@PathVariable Long id) {
        return employeeClient.delete(id);
    }

}
