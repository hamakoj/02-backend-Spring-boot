package com.spring.ecommerceapp.service;

import com.spring.ecommerceapp.dto.Purchase;
import com.spring.ecommerceapp.dto.PurchaseResponse;

public interface CheckoutService {
    PurchaseResponse placeOrder(Purchase purchase);
}
