package com.ecommerce.enums;

import lombok.Getter;

@Getter
public enum OrderStatus {
    ORDERED("Ordered", 1),
    PACKED("Packed", 2),
    OUT_FOR_DELIVERY("Out For Delivery", 3),
    DELIVERED("Delivered", 4);

    private final String state;
    private final Integer stateValue;

    OrderStatus(String state, Integer stateValue) {
        this.state = state;
        this.stateValue = stateValue;
    }

    public static OrderStatus parse(String state) {
        OrderStatus orderStatus = null;
        for (OrderStatus item : OrderStatus.values()) {
            if (item.getState().equalsIgnoreCase(state)) {
                orderStatus = item;
                break;
            }
        }
        return orderStatus;
    }

    public static OrderStatus parse(int stateValue) {
        OrderStatus orderStatus = null;
        for (OrderStatus item : OrderStatus.values()) {
            if (item.getStateValue() == stateValue) {
                orderStatus = item;
                break;
            }
        }
        return orderStatus;
    }
}
