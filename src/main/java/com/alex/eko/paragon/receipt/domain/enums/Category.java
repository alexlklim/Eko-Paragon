package com.alex.eko.paragon.receipt.domain.enums;

public enum Category {
    A(23),
    B(8),
    C(5);

    private final int value;

    Category(int value) {
        this.value = value;
    }

    public int getValue() {
        return value;
    }
}
