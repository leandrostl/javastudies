package factorymethod;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class EmployeeTest {

    final Employee johnEmployee = Employee.createEmployeeWithSalary(1L, "John", 2000.00f);
    final Employee markEmployee = Employee.createEmplyeeWithHourRate(2L, "Mark", 20.00f);

    @Test
    @DisplayName("An employee with salary shouldn't have hour rate.")
    void employeeMonthlyPayed() {
        assertNull(johnEmployee.getHourRate());
    }

    @Test
    @DisplayName("An employee hourly payed shouldn't have salary.")
    void employeeHourlyPayed() {
        assertNull(markEmployee.getSalary());
    }

}