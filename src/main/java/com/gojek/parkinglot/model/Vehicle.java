package com.gojek.parkinglot.model;

import java.util.Objects;

public abstract class Vehicle {

    private String regNumber;
    private String color;

    public Vehicle(String regNumber, String color) {
        this.regNumber = regNumber;
        this.color = color;
    }

    public String getRegNumber() {
        return regNumber;
    }

    public void setRegNumber(String regNumber) {
        this.regNumber = regNumber;
    }

    public String getColor() {
        return color;
    }

    public void setColor(String color) {
        this.color = color;
    }

    @Override
    public String toString() {
        return "Vehicle{" +
                "regNumber='" + regNumber + '\'' +
                ", color='" + color + '\'' +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Vehicle)) return false;
        Vehicle vehicle = (Vehicle) o;
        return Objects.equals(getRegNumber(), vehicle.getRegNumber()) &&
                Objects.equals(getColor(), vehicle.getColor());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getRegNumber(), getColor());
    }
}