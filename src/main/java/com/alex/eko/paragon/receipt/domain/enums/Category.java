package com.alex.eko.paragon.receipt.domain.enums;

public enum Category {
    A(0.23), // Representing 23% as 0.23
    B(0.08), // Representing 8% as 0.08
    C(0.05); // Representing 5% as 0.05

    private final double value; // Change from int to double

    Category(double value) { // Change parameter type to double
        this.value = value;
    }

    public double getValue() { // Change return type to double
        return value;
    }
}