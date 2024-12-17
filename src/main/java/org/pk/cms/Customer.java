package org.pk.cms;

public class Customer {
    private String name;
    private int age;
    private String email;
    private boolean isPremium;

    public Customer(String name, int age, String email, boolean isPremium) {
        this.name = name;
        this.age = age;
        this.email = email;
        this.isPremium = isPremium;
    }

    public String getName() {
        return name;
    }

    public int getAge() {
        return age;
    }

    public String getEmail() {
        return email;
    }

    public boolean isPremium() {
        return isPremium;
    }

    public String toString() {
        return "Customer{name='" + name + "', age = " + age + "', email = " + email + "', isPremium = " + isPremium + "}";
    }
}
