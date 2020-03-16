package br.com.zup.payment.listener;

import br.com.zup.payment.event.InventoryCreatedEvent;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Component;

import java.io.IOException;

@Component
public class InventoryListener {

    private final ObjectMapper objectMapper;

    public InventoryListener(ObjectMapper objectMapper) {
        this.objectMapper = objectMapper;
    }

    @KafkaListener(topics = "inventory-created", groupId = "payment-group-id")
    public void listen(String message) throws IOException {
        InventoryCreatedEvent event = this.objectMapper.readValue(message, InventoryCreatedEvent.class);

        System.out.println("Received event OrderListener Inventory Created: " + event.getCustomerId());
    }
}
