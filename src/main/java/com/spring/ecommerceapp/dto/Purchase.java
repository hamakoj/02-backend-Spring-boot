package com.spring.ecommerceapp.dto;

import com.spring.ecommerceapp.entity.Address;
import com.spring.ecommerceapp.entity.Customer;
import com.spring.ecommerceapp.entity.Order;
import com.spring.ecommerceapp.entity.OrderItem;
import lombok.Data;

import java.util.Set;

@Data
public class Purchase {
    private Customer customer;
    private Address shippingAddress;
    private Address billingAddress;
    private Order order;
    private Set<OrderItem> orderItems;

}
