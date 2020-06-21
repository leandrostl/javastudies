package com.leandro.restapi.employee.presentation.exceptions;

public class EmployeeNotFoundException extends RuntimeException {
	private static final long serialVersionUID = -5215305803443686840L;

	public EmployeeNotFoundException(Long id) {
      super("Could not find employee " + id);
    }
}