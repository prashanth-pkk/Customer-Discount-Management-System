package org.pk.cms;

import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.function.Predicate;
import java.util.stream.Collectors;

public class CustomerDiscount {
    public static void main(String[] args) {
        List<Customer> customers = Arrays.asList(
                new Customer("Khan", 30, "khan@gmail.com", true),
                new Customer("Bob", 17, "bob@yahoo.com", false),
                new Customer("Charlie", 25, "charlie@gmail.com", true),
                new Customer("David", 40, "david@email.com", true)
        );

        Predicate<Customer> isEligibleForDiscount = getCustomerPredicate();
        List<Customer> eligibleForDiscount = customers.stream()
                .filter(isEligibleForDiscount)
                .collect(Collectors.toList());

        eligibleForDiscount.forEach(customer ->
                System.out.println("Customer eligible for discount : " + customer));
    }

    private static Predicate<Customer> getCustomerPredicate() {
        Map<String, Integer> customerSpending = Map.of(
                "khan@gmail.com", 1200,  // Fixed email
                "bob@yahoo.com", 500,
                "charlie@gmail.com", 1500,
                "david@email.com", 2000
        );

        Predicate<Customer> isAdult = customer -> customer.getAge() > 18;
        Predicate<Customer> isPremium = Customer::isPremium;
        Predicate<Customer> hasSpentOver1000 = customer -> customerSpending
                .get(customer.getEmail()) > 800;
        Predicate<Customer> isEligibleForDiscount = isAdult.and(isPremium).and(hasSpentOver1000);
        return isEligibleForDiscount;
    }
}
