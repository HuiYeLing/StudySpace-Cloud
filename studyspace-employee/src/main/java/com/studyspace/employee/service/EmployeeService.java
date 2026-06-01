package com.studyspace.employee.service;

import com.studyspace.common.result.ApiResult;
import com.studyspace.employee.domain.Employee;

public interface EmployeeService {
    ApiResult getEmployeeById(Long id);
    ApiResult getAllEmployees();
    ApiResult addEmployee(Employee employee);
    ApiResult updateEmployee(Employee employee);
    ApiResult deleteEmployee(Long id);
}
