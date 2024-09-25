package com.alex.eko.paragon.utils;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

public class UtilProduct {


    public static final String CODES = "codes";
    public static final String QUANTITY = "quantity";
    public static final String STOCK = "stock";

    public static final String PRICE = "price";
    public static final String DIMENSIONS = "dimensions";
    public static final String DELIVERY = "delivery";

    public static final String SUPPLIER = "supplier";
    public static final String MANUFACTURER = "manufacturer";
    public static final String TAX = "tax";
    public static final String DISCOUNT = "discount";

    public static final String IMAGES = "images";
    public static final String DOCUMENTS = "documents";

    public static final String COMMENTS = "comments";
    public static final String CATEGORIES = "categories";
    public static final String VALUES = "values";





    public static Set<String> getAllFields() {
        return new HashSet<>(Arrays.asList(
                CODES, QUANTITY, STOCK,
                PRICE, DIMENSIONS, DELIVERY,
                SUPPLIER, MANUFACTURER, TAX, DISCOUNT,
                IMAGES, DOCUMENTS,
                COMMENTS, CATEGORIES
        ));
    }

}
