package com.example.ecomerce.entity.enums;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.experimental.FieldDefaults;

@Getter
@AllArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
public enum ErrorCode {
    UNCATEGORIZED_EXCEPTION(9999, "Uncategorized exception"),
    INVALID_KEY(0001,"Invalid message key"),
    USER_NOT_FOUND(1001, "User not found"),
    USER_ALREADY_EXISTS(1002, "User already exists"),
    INVALID_CREDENTIALS(1003, "Invalid credentials"),
    PRODUCT_NOT_FOUND(1004, "Product not found"),
    CART_ITEM_NOT_FOUND(1005, "Cart item not found"),
    CART_NOT_FOUND(1006, "Cart not found"),
    CART_ITEM_ALREADY_EXISTS(1007, "Cart item already exists"),
    CART_ITEM_QUANTITY_EXCEEDED(1008, "Cart item quantity exceeded"),
    ORDER_NOT_FOUND(1009, "Order not found"),
    ORDER_ITEM_NOT_FOUND(1010, "Order item not found"),
    PAYMENT_NOT_FOUND(1011, "Payment not found"),
    PAYMENT_FAILED(1012, "Payment failed"),
    PAYMENT_ALREADY_EXISTS(1013, "Payment already exists"),
    PAYMENT_STATUS_NOT_FOUND(1014, "Payment status not found"),
    PAYMENT_STATUS_ALREADY_EXISTS(1015, "Payment status already exists"),
    PAYMENT_STATUS_INVALID(1016, "Payment status invalid"),
    CATEGORY_NOT_FOUND(1017, "Category not found"),
    CATEGORY_ALREADY_EXISTS(1018, "Category already exists"),
    PRODUCT_ALREADY_EXISTS(1019, "Product already exists"),
    PRODUCT_IMAGE_NOT_FOUND(1020, "Product image not found"),
    PRODUCT_IMAGE_ALREADY_EXISTS(1021, "Product image already exists"),
    PRODUCT_IMAGE_LIMIT_EXCEEDED(1022, "Product image limit exceeded"),
    PRODUCT_IMAGE_INVALID(1023, "Product image invalid"),
    PRODUCT_IMAGE_NOT_SET(1024, "Product image not set"),
    PRODUCT_IMAGE_NOT_DELETED(1025, "Product image not deleted"),
    PRODUCT_IMAGE_DELETED(1026, "Product image deleted"),
    PRODUCT_IMAGE_NOT_UPDATED(1027, "Product image not updated"),
    PRODUCT_IMAGE_UPDATED(1028, "Product image updated"),
    PRODUCT_IMAGE_NOT_UPLOADED(1029, "Product image not uploaded"),
    PRODUCT_IMAGE_UPLOADED(1030, "Product image uploaded"),
    PRODUCT_IMAGE_NOT_FOUND_IN_PRODUCT(1031, "Product image not found in product"),
    PRODUCT_IMAGE_NOT_FOUND_IN_PRODUCT_IMAGES(1032, "Product image not found in product images"),
    PRODUCT_IMAGE_NOT_FOUND_IN_PRODUCT_IMAGE(1033, "Product image not found in product image"),
    PRODUCT_IMAGE_NOT_FOUND_IN_PRODUCT_IMAGE_ID(1034, "Product image not found in product image id"),
    USERNAME_INVALID(1035, "Username must be at least 3 characters long"),
    PASSWORD_INVALID(1036, "Password must be at least 8 characters long");

    int code;
    String message;


}
