package com.leandro.restapi.order.presentation;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

import com.leandro.restapi.order.persistence.OrderDto;
import com.leandro.restapi.order.persistence.Status;

import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.server.RepresentationModelAssembler;
import org.springframework.stereotype.Component;

@Component
public class OrderModelAssembler implements RepresentationModelAssembler<OrderDto, EntityModel<OrderDto>> {
        @Override
        public EntityModel<OrderDto> toModel(OrderDto order) {
                var model = EntityModel.of(order,
                                linkTo(methodOn(OrderController.class).one(order.getId())).withSelfRel(),
                                linkTo(methodOn(OrderController.class).all()).withRel("orders"));

                if (order.getStatus() == Status.IN_PROGRESS) {
                        model.add(linkTo(methodOn(OrderController.class).cancel(order.getId())).withRel("cancel"));
                        model.add(linkTo(methodOn(OrderController.class).complete(order.getId())).withRel("complete"));
                }

                return model;
        }

}