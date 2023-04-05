package com.example.chooseyourfood;

import java.util.Objects;

public class Order {
    private String day;
    private String food;
    private Integer numberOfOrders;

    public Order(String day, String food) {
        this.day = day;
        this.food = food;
        this.numberOfOrders = 0;
    }

    public String getDay() {
        return day;
    }

    public Integer getNumberOfOrders() {
        return numberOfOrders;
    }

    public String getFood() {
        return food;
    }

    public void addOrder(){
        numberOfOrders++;
    }

    public void deleteOrders(){
        numberOfOrders=0;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Order order = (Order) o;
        return Objects.equals(day, order.day) && Objects.equals(food, order.food);
    }

    @Override
    public int hashCode() {
        return Objects.hash(day, food);
    }
}
