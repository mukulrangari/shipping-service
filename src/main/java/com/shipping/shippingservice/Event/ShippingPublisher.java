package com.shipping.shippingservice.Event;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.ObjectWriter;
import com.shipping.shippingservice.Dto.OrderEvent;
import com.shipping.shippingservice.utils.KafkaUtils;

import lombok.extern.slf4j.Slf4j;

@Service
@Slf4j
public class ShippingPublisher {
    @Autowired
    private KafkaTemplate<String, String> kafkaTemplate;

    public void sendMessageToOrderService(OrderEvent event) {
        ObjectWriter ow = new ObjectMapper().writer().withDefaultPrettyPrinter();
        String stringEvent = "";
        try {
            log.info("ShippingPublisher: Publishing message to topic with event -> {}", event);
            stringEvent = ow.writeValueAsString(event);
            log.info("ShippingPublisher: Published message to topic with event -> {}", event);
        } catch (JsonProcessingException e) {
            log.info("ShippingPublisher: Error --> {} while publishing message to topic with event -> {}",
                    e.getMessage(), event);
        }
        kafkaTemplate.send(KafkaUtils.TOPIC_ORDER, stringEvent);
    }

    public void sendMessageToPaymentService(OrderEvent event) {
        ObjectWriter ow = new ObjectMapper().writer().withDefaultPrettyPrinter();
        String stringEvent = "";
        try {
            log.info("ShippingPublisher: Publishing message to topic with event -> {}", event);
            stringEvent = ow.writeValueAsString(event);
            log.info("ShippingPublisher: Published message to topic with event -> {}", event);
        } catch (JsonProcessingException e) {
            log.info("ShippingPublisher: Error --> {} while publishing message to topic with event -> {}",
                    e.getMessage(), event);
        }
        kafkaTemplate.send(KafkaUtils.TOPIC_PAYMENT, stringEvent);
    }
}
