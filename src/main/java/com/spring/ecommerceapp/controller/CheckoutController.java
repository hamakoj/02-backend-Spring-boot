package com.spring.ecommerceapp.controller;

import com.spring.ecommerceapp.dto.Purchase;
import com.spring.ecommerceapp.dto.PurchaseResponse;
import com.spring.ecommerceapp.service.CheckoutService;
import org.springframework.web.bind.annotation.*;


@RestController
@RequestMapping("/api/checkout")
public class CheckoutController {
    private final CheckoutService checkoutService;

    public CheckoutController(CheckoutService checkoutService) {
        this.checkoutService = checkoutService;
    }

    @PostMapping("/purchase")
    public PurchaseResponse placeOrder(@RequestBody Purchase purchase){

        return checkoutService.placeOrder(purchase);
    }


}
