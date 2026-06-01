package com.studyspace.employee.controller;

import com.studyspace.common.result.ApiResult;
import com.studyspace.employee.domain.Employee;
import com.studyspace.employee.service.EmployeeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;


@RestController
@RequestMapping("/api/employee")
public class EmployeeController {
    @Autowired
    private EmployeeService employeeService;

    @GetMapping("/getById")
    public ApiResult getEmployeeById(@RequestParam Long id) {
        return employeeService.getEmployeeById(id);
    }

    @GetMapping("/getAll")
    public ApiResult getAllEmployees() {
        return employeeService.getAllEmployees();
    }

    @PostMapping("/add")
    public ApiResult addEmployee(@RequestBody Employee employee) {
        return employeeService.addEmployee(employee);
    }

    @PutMapping("/update")
    public ApiResult updateEmployee(@RequestBody Employee employee) {
        return employeeService.updateEmployee(employee);
    }

    @DeleteMapping("/delete")
    public ApiResult deleteEmployee(@RequestParam Long id) {
        return employeeService.deleteEmployee(id);
    }
}
