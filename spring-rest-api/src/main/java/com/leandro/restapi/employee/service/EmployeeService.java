package com.leandro.restapi.employee.service;

import java.util.List;

import javax.persistence.EntityNotFoundException;

import com.leandro.restapi.employee.persistence.EmployeeDto;

import org.springframework.stereotype.Service;

@Service
public interface EmployeeService {

    EmployeeDto save(EmployeeDto employeeDto);
    EmployeeDto find(Long id) throws EntityNotFoundException;
    List<EmployeeDto> findAll();
	EmployeeDto replace(Long id, EmployeeDto newEmployee);
	void delete(Long id);    
}