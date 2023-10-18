package com.spring.ecommerceapp.service;

import com.spring.ecommerceapp.dao.CustomerRepository;
import com.spring.ecommerceapp.dto.Purchase;
import com.spring.ecommerceapp.dto.PurchaseResponse;
import com.spring.ecommerceapp.entity.Customer;
import com.spring.ecommerceapp.entity.Order;
import com.spring.ecommerceapp.entity.OrderItem;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Service;

import java.util.Set;
import java.util.UUID;

@Service
public class CheckoutServiceImpl implements CheckoutService {
    private final CustomerRepository customerRepository;

    public CheckoutServiceImpl(CustomerRepository customerRepository) {
        this.customerRepository = customerRepository;
    }



    @Override
    @Transactional
    public PurchaseResponse placeOrder(Purchase purchase) {

        // retrieve the order info from dto
        Order order = purchase.getOrder();

        // generate tracking order
        String orderTrackingNumber = generateOrderTrackingNumber();
        order.setOrderTrackingNumber(orderTrackingNumber);
        //populate order with orderItems
        Set<OrderItem> orderItems = purchase.getOrderItems();
        //orderItems.forEach(item ->order.add(item));
        orderItems.forEach(order::add);
        //populate oder with billingAddress and ShippingAddress
        order.setBillingAddress(purchase.getBillingAddress());
        order.setShippingAddress(purchase.getShippingAddress());
        // populate customer with order
        Customer customer = purchase.getCustomer();
        customer.add(order);
        // save to the database
        customerRepository.save(customer);
        // return response
        return new PurchaseResponse(orderTrackingNumber);
    }

    private String generateOrderTrackingNumber() {
        // generate a random/unique  uuid number UUID (version-4)
        return UUID.randomUUID().toString();
    }
}
