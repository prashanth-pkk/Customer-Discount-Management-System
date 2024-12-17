package org.pk.cms;

import java.util.Arrays;
import java.util.List;
import java.util.function.Predicate;
import java.util.regex.Pattern;

public class CustomerValidation {
    public static void main(String[] args) {
        //customer email validation
        Predicate<Customer> isValidEmail = customer -> {
            String email = customer.getEmail();
            String emailRegex = "^[A-Za-z0-9+_.-]+@(.+)$";
            Pattern pattern = Pattern.compile(emailRegex);
            return pattern.matcher(email).matches();
        };
        //customer age validation
        Predicate<Customer> isValidAge = customer -> customer
                .getAge() >= 18 && customer.getAge() <= 100;
        List<Customer> customers = Arrays.asList(
                new Customer("Shobin", 30, "shobin@gmail.com", true),
                new Customer("Raj", 17, "raj@", false),
                new Customer("Anna", 25, "anna@yahoo.com", true),
                new Customer("David", 150, "david@example.com", false)
        );
        customers.forEach(customer -> {
            if (!isValidEmail.test(customer)) {
                System.out.println("Invalid email: " + customer.getEmail());
            }
            if (!isValidAge.test(customer)) {
                System.out.println("Invalid age: " + customer.getAge());
            }
        });
    }
}
