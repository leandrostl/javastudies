package com.leandro.restapi.order.presentation;

import static java.util.Optional.of;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;
import static org.springframework.http.MediaType.APPLICATION_JSON_VALUE;

import java.util.stream.Collectors;

import javax.websocket.server.PathParam;

import com.leandro.restapi.order.persistence.OrderDto;
import com.leandro.restapi.order.service.OrderService;

import org.apache.catalina.connector.Response;
import org.springframework.hateoas.CollectionModel;
import org.springframework.hateoas.EntityModel;
import static org.springframework.hateoas.IanaLinkRelations.*;
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

@AllArgsConstructor
@RestController
@RequestMapping("/orders")
public class OrderController {

    private final OrderService service;
    private final OrderModelAssembler assembler;

    @PostMapping(consumes = "application/json")
    ResponseEntity<?> createOrder(@RequestBody OrderDto newOrder) {
        return of(service.save(newOrder)).map(assembler::toModel)
                .map(o -> ResponseEntity.created(o.getRequiredLink(SELF).toUri()).body(o)).get();
    }

    @GetMapping(produces = APPLICATION_JSON_VALUE)
    CollectionModel<EntityModel<OrderDto>> all() {
        return CollectionModel.of(service.findAll().stream().map(assembler::toModel).collect(Collectors.toList()),
                linkTo(methodOn(OrderController.class).all()).withSelfRel());
    }

    @GetMapping(value = "/{id}", produces = APPLICATION_JSON_VALUE)
    EntityModel<OrderDto> one(@PathVariable Long id) {
        return of(service.find(id)).map(assembler::toModel).get();
    }

    @DeleteMapping(value = "/{id}")
    ResponseEntity<?> delete(@PathVariable Long id) {
        service.delete(id);
        return ResponseEntity.noContent().build();
    }

    @PutMapping(value = "/{id}", consumes = APPLICATION_JSON_VALUE)
    ResponseEntity<?> change(@PathVariable Long id, @RequestBody OrderDto order) {
        return of(service.change(id, order)).map(assembler::toModel)
                .map(o -> ResponseEntity.created(o.getRequiredLink(SELF).toUri()).body(o)).get();
    }

    @DeleteMapping("/{id}/cancel")
    ResponseEntity<?> cancel(@PathVariable Long id) {
        return of(service.cancel(id)).map(assembler::toModel).map(ResponseEntity::ok).get();
    }

    @PutMapping("/{id}/complete")
    ResponseEntity<?> complete(@PathVariable Long id) {
        return of(service.complete(id)).map(assembler::toModel).map(ResponseEntity::ok).get();
    }

}