package com.leandro.restapi.employee.presentation;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

import com.leandro.restapi.employee.persistence.EmployeeDto;

import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.server.RepresentationModelAssembler;
import org.springframework.stereotype.Component;

@Component
class EmployeeModelAssembler implements RepresentationModelAssembler<EmployeeDto, EntityModel<EmployeeDto>> {

  @Override
  public EntityModel<EmployeeDto> toModel(EmployeeDto employee) {

    return EntityModel.of(employee, 
        linkTo(methodOn(EmployeeController.class).one(employee.getId())).withSelfRel(),
        linkTo(methodOn(EmployeeController.class).all()).withRel("employees"));
  }
}