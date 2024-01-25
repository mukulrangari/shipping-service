package com.shipping.shippingservice.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.shipping.shippingservice.Dto.OrderEvent;
import com.shipping.shippingservice.Dto.OrderResponseDto;
import com.shipping.shippingservice.service.ShippingService;

import lombok.extern.slf4j.Slf4j;

@RestController
@RequestMapping("api/v1")
@Slf4j
public class ShippingController {

    @Autowired
    ShippingService shippingService;

    @GetMapping("/shipping/cancel/{id}")
    public OrderResponseDto cancelOrder(@PathVariable String id) {
        log.info("ShippingController: Cancelling order shipment request for orderId:{}", id);
        return shippingService.cancelShipment(id);
    }

    @PostMapping("/shipping/complete/{orderId}")
    public OrderResponseDto completeOrder(@PathVariable String orderId) {
        log.info("ShippingController: Delivered order shipment request for orderId:{}", orderId);
        return shippingService.completeShipment(orderId);
    }

    @PostMapping("/shipping/accept")
    public OrderResponseDto createShipment(@RequestBody OrderEvent orderEvent) {
        log.info("ShippingController: Creating order shipment request for orderId:{}", orderEvent.getOrderId());
        return shippingService.createShipment(orderEvent);
    }

    @PostMapping("/shipping/reject")
    public OrderResponseDto failedShipment(@RequestBody OrderEvent orderEvent) {
        log.info("ShippingController: Creating order shipment request for orderId:{}", orderEvent.getOrderId());
        return shippingService.failedShipment(orderEvent);
    }
}
