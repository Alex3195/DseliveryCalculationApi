package com.example.deliveryfeecalculatorapi.service;

import com.example.deliveryfeecalculatorapi.model.RequestData;
import com.example.deliveryfeecalculatorapi.model.ResponseData;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Locale;

@Service
public class CalculationService {
    private Double deliveryFee;
    private Double surcharges;

    public CalculationService() {
        deliveryFee = 0D;
        surcharges = 0D;
    }

    public ResponseData calculation(RequestData data) {
//        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd'T'HH:mm:ss.SSS'Z'", Locale.ENGLISH);
        LocalDateTime dateTime = LocalDateTime.parse(data.getTime());
        if (data.getCartValue() < 10000) {
            if (data.getNumberOfItems() > 4) {
                surcharges = surcharges + ((data.getNumberOfItems() - 4) * 50);
            }
            if (data.getDeliveryDistance() > 1000) {
                deliveryFee = deliveryFee + 200;
                surcharges = surcharges + (((data.getDeliveryDistance() - 1000) % 500) == 0 ? (((data.getDeliveryDistance() - 1000) / 500) * 100) : ((((data.getDeliveryDistance() - 1000) / 500) + 1) * 100));
            } else {
                deliveryFee = deliveryFee + 2000;
            }
            if (data.getCartValue() <= 1000) {
                surcharges = surcharges + 110;
            }
            if (dateIsFridayAndTimeIsBetweenFromThreePMTOSevenPM(dateTime)) {
                deliveryFee = 1.1 * (deliveryFee + surcharges);
            }
            if (deliveryFee > 15000) {
                deliveryFee = 15000D;
            }
            deliveryFee = deliveryFee+surcharges;
        }

        ResponseData responseData = new ResponseData();
        responseData.setDeliveryFee(deliveryFee);
        deliveryFee = 0D;
        surcharges = 0D;
        return responseData;
    }

    /**
     * this method checks the date is friday and time is between 3 and 7 pm
     *
     * @param dateTime
     * @return boolean
     */
    private Boolean dateIsFridayAndTimeIsBetweenFromThreePMTOSevenPM(LocalDateTime dateTime) {
        int day = dateTime.getDayOfWeek().getValue();
        boolean isTrue = false;
        if (day != 5) {
            isTrue = false;
        } else {
            if (dateTime.getHour() >= 15 && (dateTime.getHour() <= 18 && dateTime.getMinute() < 59)) {
                isTrue = true;
            } else {
                isTrue = false;
            }
        }
        return isTrue;

    }
}

