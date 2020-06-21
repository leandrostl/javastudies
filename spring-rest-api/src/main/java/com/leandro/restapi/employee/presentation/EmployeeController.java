package com.leandro.restapi.employee.presentation;

import static java.util.Optional.of;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

import java.util.stream.Collectors;

import com.leandro.restapi.employee.persistence.EmployeeDto;
import com.leandro.restapi.employee.service.EmployeeService;

import org.springframework.hateoas.CollectionModel;
import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.IanaLinkRelations;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import lombok.AllArgsConstructor;

@RestController
@RequestMapping("/employees")
@AllArgsConstructor
public class EmployeeController {

    private final EmployeeService service;
    private final EmployeeModelAssembler assembler;

    @GetMapping(produces = "application/json")
    CollectionModel<EntityModel<EmployeeDto>> all() {
        return CollectionModel.of(service.findAll().stream().map(assembler::toModel).collect(Collectors.toList()),
        linkTo(methodOn(EmployeeController.class).all()).withSelfRel());
    }

    @PostMapping(consumes = "application/json")
    ResponseEntity<?> newEmployee(@RequestBody EmployeeDto newEmployee) {
        return of(service.save(newEmployee)).map(assembler::toModel).map(e ->
            ResponseEntity.created(e.getRequiredLink(IanaLinkRelations.SELF).toUri()).body(e)).get();
    }

    @GetMapping(value = "/{id}", produces = "application/json")
    EntityModel<EmployeeDto> one(@PathVariable Long id) {
        return of(service.find(id)).map(assembler::toModel).get();
    }

    @PutMapping("/{id}")
    ResponseEntity<?> replaceEmployee(@RequestBody EmployeeDto newEmployee, @PathVariable Long id) {
        return of(service.replace(id, newEmployee)).map(assembler::toModel).map(e ->
        ResponseEntity.created(e.getRequiredLink(IanaLinkRelations.SELF).toUri()).body(e)).get();
    }
  
    @DeleteMapping("/{id}")
    ResponseEntity<?> deleteEmployee(@PathVariable Long id) {
      service.delete(id);
      return ResponseEntity.noContent().build();
    }
}