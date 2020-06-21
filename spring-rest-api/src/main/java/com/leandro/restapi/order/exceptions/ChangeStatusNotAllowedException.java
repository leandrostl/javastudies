package com.leandro.restapi.order.exceptions;

import com.leandro.restapi.order.persistence.OrderDto;
import com.leandro.restapi.order.persistence.Status;

public class ChangeStatusNotAllowedException extends RuntimeException {
    private static final long serialVersionUID = 1L;

    public ChangeStatusNotAllowedException(OrderDto order, Status newStatus) {
        super("It't not allowed to change the status of the order " + order.getId() + " from " + order.getStatus()
                + " to " + newStatus);
    }

}