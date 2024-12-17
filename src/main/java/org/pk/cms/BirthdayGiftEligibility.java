package org.pk.cms;

import java.time.LocalDate;
import java.time.Period;
import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.function.Predicate;

public class BirthdayGiftEligibility {
    public static void main(String[] args) {
        List<Customer> customers = Arrays.asList(
                new Customer("Alice", 30, "alice@example.com", true),
                new Customer("Bob", 17, "bob@example.com", false),
                new Customer("Charlie", 25, "charlie@example.com", true)
        );

        Map<String, LocalDate> membershipStartDate = Map.of(
                "alice@example.com", LocalDate.of(2020, 6, 15),
                "bob@example.com", LocalDate.of(2023, 1, 1),
                "charlie@example.com", LocalDate.of(2021, 3, 10)
        );

        Predicate<Customer> isAdult = customer -> customer.getAge() > 18;
        Predicate<Customer> isPremiumAndLongTimeMember = customer -> {
            LocalDate startDate = membershipStartDate.get(customer.getEmail());
            return Period.between(startDate, LocalDate.now()).getMonths() > 6;
        };

        Predicate<Customer> isBirthdayInCurrentMonth = customer -> {
            Map<String, LocalDate> birthDates = Map.of(
                    "alice@example.com", LocalDate.of(1990, 12, 15),
                    "bob@example.com", LocalDate.of(2000, 5, 1),
                    "charlie@example.com", LocalDate.of(1995, 12, 10)
            );
            LocalDate birthDate = birthDates.get(customer.getEmail());
            return birthDate.getMonth() == LocalDate.now().getMonth();
        };

        Predicate<Customer> isEligibleForGift = isAdult.and(isPremiumAndLongTimeMember).and(isBirthdayInCurrentMonth);
        customers.stream()
                .filter(isEligibleForGift)
                .forEach(customer -> System.out.println(customer.getName() + " is eligible for a birthday gift"));
    }
}
