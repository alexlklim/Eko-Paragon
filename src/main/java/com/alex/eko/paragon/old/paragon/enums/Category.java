package com.alex.eko.paragon.old.paragon.enums;

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
