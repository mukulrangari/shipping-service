package com.shipping.shippingservice.Event;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.shipping.shippingservice.Dto.OrderEvent;
import com.shipping.shippingservice.Dto.OrderStatus;
import com.shipping.shippingservice.service.ShippingService;

@Service
public class EventHandler {

    @Autowired
    private ShippingService shippingService;

    public void eventProcceser(OrderEvent orderEvent) {
        if (orderEvent.getOrderStatus() == OrderStatus.ORDER_FULFILLED) {
            // shippingService.createShipment(orderEvent);
        }
    }

}
