package com.example.EmployeeService.service.impl;

import java.util.List;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.modelmapper.config.Configuration;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;


import com.example.EmployeeService.repository.EmployeeRepository;
import com.example.EmployeeService.service.EmployeeService;
import com.example.sid.pdfGenratorDemo.dto.response.Employee;

@Service
@Transactional(readOnly = true)
public class EmployeeServiceImpl implements EmployeeService {

	@Autowired
	EmployeeRepository employeeRepository;

	@Autowired
	ModelMapper modelMapper;

	@Override
	public List<Employee> getEmployees() {
		List<com.example.EmployeeService.entity.Employee> employees = employeeRepository.findAll();
		List<Employee> employeeDtos = employees.stream().map(entity -> map(entity)).collect(Collectors.toList());
		return employeeDtos;

	}

	private Employee map(com.example.EmployeeService.entity.Employee employeeEntity) {
		modelMapper.getConfiguration().setFieldMatchingEnabled(true)
		.setFieldAccessLevel(Configuration.AccessLevel.PACKAGE_PRIVATE)
		.setMethodAccessLevel(Configuration.AccessLevel.PACKAGE_PRIVATE);
		Employee employee = modelMapper.map(employeeEntity, Employee.class);
		return employee;

	}

}
