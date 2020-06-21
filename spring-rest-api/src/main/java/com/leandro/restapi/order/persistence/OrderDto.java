package com.leandro.restapi.order.persistence;

import javax.validation.constraints.NotBlank;

import org.springframework.beans.BeanUtils;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Builder
public @Data class OrderDto {

    private Long id;
    @NotBlank
    private String description;
    private Status status;

    public Order toEntity() {
        return Order.builder().id(id).description(description).status(status).build();
    }

    public static OrderDto toDto(Order order) {
        return OrderDto.builder().id(order.getId()).description(order.getDescription()).status(order.getStatus())
                .build();
    }
}