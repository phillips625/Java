package com.philco;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.function.Function;
import java.util.function.IntPredicate;
import java.util.function.Predicate;
import java.util.function.Supplier;

public class Main {

    public static void main(String[] args) {

        Employee sam = new Employee("Sam David", 34);
        Employee john = new Employee("John Does", 24);
        Employee sarah = new Employee("Sarah Yaboa", 21);
        Employee snow = new Employee("Snow Donia", 23);
        Employee Red = new Employee("Red Rabe", 18);
        Employee charming = new Employee("charming mr", 48);

        List<Employee> employees = new ArrayList<>();
        employees.add(sam);
        employees.add(john);
        employees.add(sarah);
        employees.add(snow);
        employees.add(Red);
        employees.add(charming);

        // Employee is the argument type and String is the return type.
        Function<Employee, String> getLastName = (Employee employee) -> {
            // Extracting the surname from each of the employee.
            return employee.getName().substring(employee.getName().indexOf(' ') + 1);
        };

        // Getting the surname of the third employee.
        String lastName = getLastName.apply(employees.get(1));
        System.out.println(lastName);

                                // FUNCTIONS
        // Functions are useful when we want to write more concise code - so rather than calling an interface, we can use a function instead
        // to create code that fits the bill.

        // <Employee, String> - Employee in, String out.
        Function<Employee, String> getFirstName = (Employee employee) -> {
            // This time, we are starting from the start of the string up until we find the first space (so we can get the first name).
            return employee.getName().substring(0, employee.getName().indexOf(' '));
        };
                            // Documentation.
        // Press F1 with the cursor over 'forEach'
        // Keys: fn + F1
        // forEach requires a Consumer.
//        employees.forEach(employee -> {
//            System.out.println(employee.getName());
//            System.out.println(employee.getAge());
//        });

                                        // PREDICATE USED HERE
        // The PREDICATE arguments are Lambda expressions that match the Predicate interface.
        // This makes PREDICATES very powerful - the fact that we can send a boolean based on a pre-defined condition and still get an output.
        // The lambda here maps to the 'test' method.
        printEmployeesByAge(employees, "Employees Over 30", employee -> employee.getAge() > 30);
        printEmployeesByAge(employees, "Employees 30 and under", employee -> employee.getAge() <= 30);
                                // Prints employees that are younger than 25
        printEmployeesByAge(employees, "Employees younger than 25", new Predicate<Employee>() {
            @Override
            public boolean test(Employee employee) {
                // Returns true when an employee is younger than 25.
                return employee.getAge() < 25;
            }
        });

        // greaterThan15 is false if i is less than or equal to 15 and true if it is greater than 15.
        IntPredicate greaterThan15 = i -> i > 15;
        IntPredicate lessThan100 = i -> i < 100;
        System.out.println(greaterThan15.test(10));

                                // AND
        // 'and' is used to tie two Predicates together.
        // Since 50 is less than 100 and greater than 15, this will output 'true'.
        System.out.println(greaterThan15.and(lessThan100).test(50));
        // This will return false as one of the predicates is not satisfied.
        System.out.println(greaterThan15.and(lessThan100).test(15));

                            // SUPPLIER
        // Supplier is a method that takes no argument but returns a value.
        // Suppliers can be used in test, populate objects, etc
        // Also there are more specific flavours of suppliers e.g int supplier, boolean supplier, etc.

        Random random = new Random();
        // Returns 10 integers between 0 to 999. () means empty argument.
        // You have to specify the type of supplier we are going to be getting - Integer in this case.
        Supplier<Integer> randomSupplier = () -> random.nextInt(1000);

        for (int i = 0 ; i < 10 ; i++){
            // Returns a number between 0 and 999
//            System.out.println(random.nextInt(1000));

            System.out.println(randomSupplier.get());
        }

        // Adding the first or last name to each employee
        Random ramdom1 = new Random();
        for (Employee employee: employees){
            if (ramdom1.nextBoolean()){
                System.out.println(getAName(getFirstName, employee));
            }
            else {
                System.out.println(getAName(getLastName, employee));
            }
        }

        // Gets an Employee object and returns the name of the employee name in an uppercase format.
        Function<Employee, String> upperCase = employee -> employee.getName().toUpperCase();
        Function<String, String> firstName = name -> name.substring(0, name.indexOf(' '));

                                // CHAINING FUNCTIONS.
        // This will return ONLY the 'first name' from the 'upperCase' string that has been passed to it.
        // We can chain as many functions as we want.
        Function chainedFunction = upperCase.andThen(firstName);

        // Test to see that our chained function is working
        // We're 'applying' the chained functions to the first entry in the employees arraylist.
        System.out.println(chainedFunction.apply(employees.get(0)));
    }

    private static String getAName(Function<Employee, String> getName, Employee employee){
        return getName.apply(employee);
    }
                                // PREDICATE USED HERE

    private static void printEmployeesByAge(List<Employee> employees,
                                            String ageText,
                                            Predicate<Employee> ageCondition){

        System.out.println(ageText);
        System.out.println("==================");

        for (Employee employee: employees){
            if (ageCondition.test(employee)){
                System.out.println(employee.getName());
            }
        }

//        employees.forEach(employee -> {
//            if (employee.getAge() > 30){
//                System.out.println(employee.getName());
//            }
//        });
    }
}
