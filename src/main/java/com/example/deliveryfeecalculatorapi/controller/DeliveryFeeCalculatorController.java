package com.example.deliveryfeecalculatorapi.controller;

import com.example.deliveryfeecalculatorapi.model.RequestData;
import com.example.deliveryfeecalculatorapi.model.ResponseData;
import com.example.deliveryfeecalculatorapi.service.CalculationService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/delivery-fee-calculator")
public class DeliveryFeeCalculatorController {
    private final CalculationService calculationService;

    @PostMapping({"/", ""})
    public ResponseData calculateTheFee(@RequestBody RequestData data) {
        return calculationService.calculation(data);
    }
}
