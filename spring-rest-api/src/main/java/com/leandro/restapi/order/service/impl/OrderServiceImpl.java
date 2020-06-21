package com.leandro.restapi.order.service.impl;

import java.util.Collection;
import java.util.stream.Collectors;

import com.leandro.restapi.order.exceptions.ChangeStatusNotAllowedException;
import com.leandro.restapi.order.exceptions.OrderNotFoundException;
import com.leandro.restapi.order.persistence.OrderDto;
import com.leandro.restapi.order.persistence.OrderRepository;
import com.leandro.restapi.order.persistence.Status;
import com.leandro.restapi.order.service.OrderService;

import org.springframework.stereotype.Service;

import lombok.AllArgsConstructor;

@AllArgsConstructor
@Service
class OrderServiceImpl implements OrderService {

    private final OrderRepository repository;

    @Override
    public OrderDto save(OrderDto order) {
        order.setStatus(Status.IN_PROGRESS);
        return OrderDto.toDto(repository.save(order.toEntity()));
    }

    @Override
    public Collection<OrderDto> findAll() {
        return repository.findAll().stream().map(OrderDto::toDto).collect(Collectors.toList());
    }

    @Override
    public OrderDto find(Long id) {
        return repository.findById(id).map(OrderDto::toDto).orElseThrow(() -> new OrderNotFoundException(id));
    }

    @Override
    public void delete(Long id) {
        repository.findById(id).ifPresentOrElse(repository::delete, () -> {
            throw new OrderNotFoundException(id);
        });
    }

    @Override
    public OrderDto change(Long id, OrderDto order) {
        return OrderDto.toDto(repository.findById(id).map(o -> {
            o.setDescription(order.getDescription());
            o.setStatus(order.getStatus());
            return repository.save(o);
        }).orElseGet(() -> {
            order.setId(id);
            return repository.save(order.toEntity());
        }));
    }

    @Override
    public OrderDto cancel(Long id) {
        return changeStatus(id, Status.CANCELLED);
    }

    @Override
    public OrderDto complete(Long id) {
        return changeStatus(id, Status.COMPLETED);
    }

    private OrderDto changeStatus(Long id, Status newStatus) {
        return OrderDto.toDto(repository.findById(id).map(o -> {
            if (o.getStatus() != Status.IN_PROGRESS) {
                throw new ChangeStatusNotAllowedException(OrderDto.toDto(o), newStatus);
            }
            o.setStatus(newStatus);
            return repository.save(o);
        }).orElseThrow(() -> new OrderNotFoundException(id)));
    }

}