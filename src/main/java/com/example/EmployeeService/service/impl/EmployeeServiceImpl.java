package com.example.EmployeeService.service.impl;

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import javax.persistence.Column;

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
	public List<Employee> getEmployees(List<String> columns, Map<String, String> filter) {
/*		List<String> entityColumns =   columns.stream().filter(column  ->
				Arrays.asList(com.example.EmployeeService.entity.Employee.class.getDeclaredFields())
				.stream().filter(field -> field.getName().equals(column))
				.map(field -> field.getAnnotation(Column.class).name())
				.collect(Collectors.toList());*/
	   
	   // columns to display mapped with entity columns
	   List<String> mapColumns = getMappedEntityColumns(columns);
	 
	   
	   
	   //filter column names mapped with entity columns
	   List<String> filterColumns = new ArrayList<>(filter.keySet());
	   List<String> mapfilterColumnsValues = getMappedEntityColumns(filterColumns);
	   
	   //filter column values
	   List<String> filterColumnsValues = new ArrayList<>(filter.values());
	   
		List<com.example.EmployeeService.entity.Employee> employees = employeeRepository.findAll();
		List<Employee> employeeDtos = employees.stream().map(entity -> map(entity)).collect(Collectors.toList());
		return employeeDtos;

	}

	private List<String> getMappedEntityColumns(List<String> columns) {
		Field[] fields = com.example.EmployeeService.entity.Employee.class.getDeclaredFields();		
		   List<String> mapColumns = new ArrayList<>();
		   for (int i =0; i<columns.size(); i++) {
			     for(int j=0; j< fields.length; j++) {
			    	  if(columns.get(i).equals(fields[j].getName())) {
			    		  mapColumns.add(fields[j].getAnnotation(Column.class).name());
			    		  break;
			    	  }
			     }
		   }
		return mapColumns;
	}

	private Employee map(com.example.EmployeeService.entity.Employee employeeEntity) {
		modelMapper.getConfiguration().setFieldMatchingEnabled(true)
		.setFieldAccessLevel(Configuration.AccessLevel.PACKAGE_PRIVATE)
		.setMethodAccessLevel(Configuration.AccessLevel.PACKAGE_PRIVATE);
		Employee employee = modelMapper.map(employeeEntity, Employee.class);
		return employee;

	}

}
