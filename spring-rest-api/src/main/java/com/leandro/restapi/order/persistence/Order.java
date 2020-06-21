package com.leandro.restapi.order.persistence;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "CUSTORMER_ORDER")
@Builder
@AllArgsConstructor
@NoArgsConstructor
public @Data class Order {

    private @Id @GeneratedValue Long id;
    private String description;
    private Status status;

    
}