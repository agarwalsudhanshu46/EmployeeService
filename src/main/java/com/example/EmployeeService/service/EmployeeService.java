package com.example.EmployeeService.service;

import java.util.List;
import java.util.Map;

import com.example.sid.pdfGenratorDemo.dto.response.Employee;


public interface EmployeeService {
	public List<Employee> getEmployees(List<String> columns, Map<String, String> filter);
}
