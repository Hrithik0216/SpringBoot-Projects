package com.flipkartProduct.product.Enumeration;

public enum SortingEnum {
    PRICE("price"),
    CATEGORY("category");
    private final String fieldName;
    SortingEnum(String fieldName) {
        this.fieldName = fieldName;
    }

    public String getFieldName() {
        return fieldName;
    }
}
