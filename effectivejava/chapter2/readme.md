# Chapter 2 - Creating and Destroying Objects

## 1. Consider Static Factory Methods instead of constructor
These technic has some advantages and is applyed to many consolidated libraries and classes. For example:

```java
public static Boolean valueOf(boolean b) {
    return b ? Boolean.TRUE : Boolean.FALSE;
}
```

It is not the same [Factory Method Pattern](../../designpatterns/factoryMethod.md).

### Advantages
#### 1. They have names:
Consider the class Employee:

```java
public class Employee {
    private Long id;
    private String name;
    private Float salary;
    private Float hourRate;
}
```

As can be seen, a Employee could be payed monthly with a fixed salary or else per hour, when he have instead a hour rate value. To create an Employee object we could use a simple constructor like:

```java
public Employee(final String name, final Float salary, final Float hourRate) {
    this.name = name;
    this.salary = salary;
    this.hourRate = hourRate;
}
```

and call `Employee monthlyEmployee("John", 2000.00f, null)` and similar to an hourPayedEmployee or else to offer static factory methods like:

```java
public static Employee createEmployeeWithSalary(final String name, final Float salary) {
    return new Employee(name, salary);
}

public static Employee createEmplyeeWithHourRate(final String name, final Float hourRate) {
    new Employee(name, hourRate)
}
```

