package com.leandro.restapi;

import com.leandro.restapi.employee.persistence.EmployeeDto;
import com.leandro.restapi.employee.service.EmployeeService;
import com.leandro.restapi.order.persistence.OrderDto;
import com.leandro.restapi.order.persistence.Status;
import com.leandro.restapi.order.service.OrderService;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
class LoadDatabase {

  private static final Logger log = LoggerFactory.getLogger(LoadDatabase.class);

  @Bean
  CommandLineRunner initDatabase(EmployeeService employeeService, OrderService orderService) {
    return args -> {
      employeeService.save(EmployeeDto.builder().name("Bilbo Baggins").role("burglar").build());
      employeeService.save(EmployeeDto.builder().name("Frodo Baggins").role("thief").build());
      employeeService.findAll().forEach(employee -> log.info("Preloaded " + employee));

      orderService.save(OrderDto.builder().description("MacBook Pro").status(Status.COMPLETED).build());
      orderService.save(OrderDto.builder().description("iPhone").status(Status.IN_PROGRESS).build());
      orderService.findAll().forEach(order -> log.info("Preloaded " + order));
    };
  }
}