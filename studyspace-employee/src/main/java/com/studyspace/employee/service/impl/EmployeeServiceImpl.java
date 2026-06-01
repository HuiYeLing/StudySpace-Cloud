package com.studyspace.employee.service.impl;

import com.studyspace.common.result.ApiResult;
import com.studyspace.employee.domain.Employee;
import com.studyspace.employee.mapper.EmployeeMapper;
import com.studyspace.employee.service.EmployeeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class EmployeeServiceImpl implements EmployeeService {
    @Autowired

    private EmployeeMapper employeeMapper;

    @Override
    public ApiResult getEmployeeById(Long id) {
        Employee employee = employeeMapper.getEmployeeById(id);
        if (employee != null) {
            return ApiResult.ok(employee);
        }
        return ApiResult.error("未找到该员工");
    }

    @Override
    public ApiResult getAllEmployees() {
        List<Employee> list = employeeMapper.getAllEmployees();
        return ApiResult.ok(list);
    }

    @Override
    public ApiResult addEmployee(Employee employee) {
        int res = employeeMapper.insertEmployee(employee);
        if (res > 0) {
            return ApiResult.ok("添加成功");
        }
        return ApiResult.error("添加失败");
    }

    @Override
    public ApiResult updateEmployee(Employee employee) {
        int res = employeeMapper.updateEmployee(employee);
        if (res > 0) {
            return ApiResult.ok("更新成功");
        }
        return ApiResult.error("更新失败");
    }

    @Override
    public ApiResult deleteEmployee(Long id) {
        int res = employeeMapper.deleteEmployee(id);
        if (res > 0) {
            return ApiResult.ok("删除成功");
        }
        return ApiResult.error("删除失败");
    }
}
