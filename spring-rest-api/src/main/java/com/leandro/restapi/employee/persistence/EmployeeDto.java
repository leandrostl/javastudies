package com.leandro.restapi.employee.persistence;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Pattern;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class EmployeeDto {

    private Long id;

    @NotBlank
    private String name;
    
    @NotBlank
    @Pattern(regexp = "burglar|thief", flags = Pattern.Flag.CASE_INSENSITIVE)
    private String role;

    public Employee toEntity() {
		  return Employee.builder().name(name).role(role).id(id).build();
    }
    
    public static EmployeeDto toDto(Employee employee) {
        return EmployeeDto.builder().id(employee.getId()).name(employee.getName()).role(employee.getRole()).build();
    }    
}