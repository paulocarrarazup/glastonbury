package br.com.zup.inventory.listener;

import br.com.zup.inventory.event.order.OrderCreatedEvent;
import br.com.zup.inventory.event.order.model.OrderRepresentation;
import br.com.zup.inventory.service.InventoryService;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.modelmapper.ModelMapper;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Component;

import java.io.IOException;

@Component
public class OrderListener {

    private final ObjectMapper objectMapper;
    private static final ModelMapper MAPPER = new ModelMapper();
    private InventoryService inventoryService;

    public OrderListener(ObjectMapper objectMapper, InventoryService inventoryService) {
        this.objectMapper = objectMapper;
        this.inventoryService = inventoryService;
    }

    @KafkaListener(topics = "created-orders", groupId = "inventory-group-id")
    public void listen(String message) throws IOException {
        OrderCreatedEvent event = this.objectMapper.readValue(message, OrderCreatedEvent.class);

        OrderRepresentation orderRepresentation = MAPPER.map(event, OrderRepresentation.class);

        inventoryService.checkAvailableTickets(orderRepresentation);
        System.out.println("Received event OrderListener: " + event.getCustomerId());
    }
}
