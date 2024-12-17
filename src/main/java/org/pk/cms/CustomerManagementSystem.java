package org.pk.cms;

import java.util.Arrays;
import java.util.List;
import java.util.function.Predicate;
import java.util.stream.Collectors;

public class CustomerManagementSystem {
    public static void main(String[] args) {
        List<Customer> customers = Arrays.asList(
                new Customer("Mahesh", 30, "mahesh@gmail.com", true),
                new Customer("Sobhan", 17, "sobhan@yahoo.com", false),
                new Customer("Charlie", 25, "charlie@hotmail.com", true),
                new Customer("David", 40, "david@gmail.com", false)
        );

        Predicate<Customer> isAdult = customer -> customer.getAge() > 18;
        Predicate<Customer> isPremium = Customer::isPremium;
        Predicate<Customer> isAdultAndPremium = isAdult.and(isPremium);
        //for adults
        List<Customer> adults = customers.stream()
                .filter(isAdult)
                .collect(Collectors.toList());
        adults.forEach(System.out::println);

        //eligible customers
        List<Customer> eligibleCustomers = customers.stream()
                .filter(isAdultAndPremium)
                .collect(Collectors.toList());
        eligibleCustomers.forEach(System.out::println);

    }
}
