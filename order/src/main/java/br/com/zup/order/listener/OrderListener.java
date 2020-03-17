package br.com.zup.order.listener;

import br.com.zup.order.event.inventory.InventoryRejectedEvent;
import br.com.zup.order.event.translator.InventoryRejectedEventToCreateOrderDomain;
import br.com.zup.order.service.OrderService;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Component;

import java.io.IOException;

@Component
public class OrderListener {

    private final ObjectMapper objectMapper;
    private final OrderService orderService;

    public OrderListener(ObjectMapper objectMapper, OrderService orderService) {
        this.objectMapper = objectMapper;
        this.orderService = orderService;
    }

    @KafkaListener(topics = "inventory-rejected", groupId = "order-group-id")
    public void listen(String message) throws IOException {
        InventoryRejectedEvent event = this.objectMapper.readValue(message, InventoryRejectedEvent.class);

        orderService.save(InventoryRejectedEventToCreateOrderDomain.translate(event));
        System.out.println("Received event OrderListener Order Rejected: " + event.getCustomerId());
    }
}
