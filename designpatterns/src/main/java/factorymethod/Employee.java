package factorymethod;

import lombok.Data;
import lombok.AllArgsConstructor;
import lombok.AccessLevel;


public @Data @AllArgsConstructor(access = AccessLevel.PRIVATE) class Employee {
    
    private Long id;
    private String name;
    private Float salary;
    private Float hourRate;

    public static Employee createEmployeeWithSalary(final Long id, final String name, final Float salary) {
        return new Employee(id, name, salary, null);
    }
    
    public static Employee createEmplyeeWithHourRate(final Long id, final String name, final Float hourRate) {
        return new Employee(id, name, null, hourRate);
    }
}