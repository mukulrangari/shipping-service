package com.shipping.shippingservice.Event;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.shipping.shippingservice.Dto.OrderEvent;
import com.shipping.shippingservice.Dto.OrderStatus;
import com.shipping.shippingservice.utils.KafkaUtils;

import lombok.extern.slf4j.Slf4j;

@Service
@Slf4j
public class ShippingConsumer {

    @Autowired
    private EventHandler eventHandler;

    @KafkaListener(topics = KafkaUtils.TOPIC_SHIPPING, groupId = KafkaUtils.GROUP_ID)
    public void consume(String message) throws JsonProcessingException {
        log.info("PaymentConsumer: Consuming message from topic with event -> {}", message);
        ObjectMapper objectMapper = new ObjectMapper();
        try {
            JsonNode jsonNode = objectMapper.readTree(message);
            OrderEvent orderEvent = new OrderEvent();
            orderEvent.setAmount(jsonNode.get("amount").asInt());
            orderEvent.setOrderId(jsonNode.get("orderId").asText());
            orderEvent.setProductId(jsonNode.get("productId").asText());
            orderEvent.setUserId(jsonNode.get("userId").asText());
            orderEvent.setOrderStatus(OrderStatus.valueOf(jsonNode.get("orderStatus").asText()));
            log.info("PaymentConsumer: Processing event for Order update from topic with event -> {}", message);
            eventHandler.eventProcceser(orderEvent);
        } catch (Exception e) {
            log.error("PaymentConsumer: Error --> {} while Consuming message from topic with event -> {}",
                    e.getMessage(), message);
        }
    }
}
