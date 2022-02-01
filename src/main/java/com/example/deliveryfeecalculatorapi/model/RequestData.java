package com.example.deliveryfeecalculatorapi.model;

import lombok.Data;

@Data
public class RequestData {
    private Integer cartValue;
    private Integer deliveryDistance;
    private Integer numberOfItems;
    private String time;
}
