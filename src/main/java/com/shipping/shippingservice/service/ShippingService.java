package com.shipping.shippingservice.service;

import java.util.Random;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.shipping.shippingservice.Dto.OrderEvent;
import com.shipping.shippingservice.Dto.OrderResponseDto;
import com.shipping.shippingservice.Dto.OrderStatus;
import com.shipping.shippingservice.Event.ShippingPublisher;

import lombok.extern.slf4j.Slf4j;

@Service
@Slf4j
public class ShippingService {

    @Autowired
    private ShippingPublisher shippingPublisher;

    // public void createShipment(String orderId) {
    // // Here we can connect with 3rd part logistic system which can handle this
    // // cases;
    // Random rand = new Random();
    // int rand_int1 = rand.nextInt(10);
    // log.info("x --> {}", rand_int1);
    // // if (rand_int1 % 2 == 0) {
    // orderId.setOrderStatus(OrderStatus.SHIPMENT_CREATED);
    // // } else {
    // // orderEvent.setOrderStatus(OrderStatus.SHIPMENT_FAILED);
    // // }
    // shippingPublisher.sendMessageToOrderService(orderId);
    // }

    public OrderResponseDto cancelShipment(String id) {
        // orderEvent.setOrderStatus(OrderStatus.SHIPMENT_CANCELLED);
        // shippingPublisher.sendMessageToPaymentService(orderEvent);
        return new OrderResponseDto("Shipment canceled", id);
    }

    public OrderResponseDto completeShipment(String orderId) {
        OrderEvent orderEvent = new OrderEvent();
        orderEvent.setOrderId(orderId);
        orderEvent.setOrderStatus(OrderStatus.SHIPMENT_COMPLETED);
        shippingPublisher.sendMessageToOrderService(orderEvent);
        return new OrderResponseDto("Shipment completed", orderEvent.getOrderId());
    }

    public OrderResponseDto createShipment(OrderEvent orderEvent) {
        orderEvent.setOrderStatus(OrderStatus.SHIPMENT_CREATED);
        shippingPublisher.sendMessageToOrderService(orderEvent);
        return new OrderResponseDto("Shipment created", orderEvent.getOrderId());
    }

    public OrderResponseDto failedShipment(OrderEvent orderEvent) {
        orderEvent.setOrderStatus(OrderStatus.SHIPMENT_FAILED);
        shippingPublisher.sendMessageToOrderService(orderEvent);
        return new OrderResponseDto("Shipment rejected", orderEvent.getOrderId());
    }
}
