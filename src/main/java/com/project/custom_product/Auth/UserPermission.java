package com.project.custom_product.Auth;

public enum UserPermission {

    CUSTOMER_READ("customer:read"),
    CUSTOMER_WRITE("customer:write"),
    PRODUCT_READ("product:read"),
    PRODUCT_WRITE("product:write"),
    PURCHASE_READ("purchase:read"),
    PURCHASE_WRITE("purchase:write");

    private final String permission;

    UserPermission(String permission) {
        this.permission = permission;
    }

    public String getPermission() {
        return permission;
    }
}
