package br.com.zup.order.listener;

import br.com.zup.order.event.OrderCreatedEvent;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Component;

import java.io.IOException;

@Component
public class OrderListener {

    private final ObjectMapper objectMapper;

    public OrderListener(ObjectMapper objectMapper) {
        this.objectMapper = objectMapper;
    }

    @KafkaListener(topics = "order-rejected", groupId = "order-group-id")
    public void listen(String message) throws IOException {
        OrderCreatedEvent event = this.objectMapper.readValue(message, OrderCreatedEvent.class);

        System.out.println("Received event OrderListener Order Rejected: " + event.getCustomerId());
    }
}
