package com.leandro.restapi.employee.service.impl;

import java.util.List;
import java.util.stream.Collectors;

import com.leandro.restapi.employee.persistence.EmployeeDto;
import com.leandro.restapi.employee.persistence.EmployeeRepository;
import com.leandro.restapi.employee.presentation.exceptions.EmployeeNotFoundException;
import com.leandro.restapi.employee.service.EmployeeService;

import org.springframework.stereotype.Service;

import lombok.AllArgsConstructor;

@AllArgsConstructor
@Service
class EmployeeServiceImpl implements EmployeeService {

	private final EmployeeRepository repository;

	@Override
	public EmployeeDto save(EmployeeDto employeeDto) {
		return EmployeeDto.toDto(repository.save(employeeDto.toEntity()));
	}

	@Override
	public EmployeeDto find(Long id) throws EmployeeNotFoundException {
		return EmployeeDto.toDto(repository.findById(id).orElseThrow(() -> new EmployeeNotFoundException(id)));
	}

	@Override
	public List<EmployeeDto> findAll() {
		return repository.findAll().stream().map(EmployeeDto::toDto).collect(Collectors.toList());
	}

	@Override
	public EmployeeDto replace(Long id, EmployeeDto newEmployee) {
		return EmployeeDto.toDto(repository.findById(id).map(employee -> {
			employee.setName(newEmployee.getName());
			employee.setRole(newEmployee.getRole());
			return repository.save(employee);
		}).orElseGet(() -> {
			newEmployee.setId(id);
			return repository.save(newEmployee.toEntity());
		}));
	}

	@Override
	public void delete(Long id) {
		repository.delete(repository.findById(id).orElseThrow(() -> new EmployeeNotFoundException(id)));
	}
}