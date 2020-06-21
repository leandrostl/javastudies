package com.leandro.restapi.employee.persistence;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@NoArgsConstructor
@AllArgsConstructor
@Builder
public @Data class Employee {
    private @Id @GeneratedValue Long id;    
    private String name;
    private String role;

    public Employee(final String name, final String role) {
        this.name = name;
        this.role = role;
    }
}