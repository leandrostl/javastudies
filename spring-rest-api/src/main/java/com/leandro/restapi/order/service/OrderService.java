package com.leandro.restapi.order.service;

import java.util.Collection;

import com.leandro.restapi.order.persistence.OrderDto;

import org.springframework.stereotype.Service;

@Service
public interface OrderService {

	OrderDto save(OrderDto order);

	Collection<OrderDto> findAll();

	OrderDto find(Long id);

	void delete(Long id);

	OrderDto change(Long id, OrderDto order);

	OrderDto cancel(Long id);

	OrderDto complete(Long id);

}