package br.com.zup.inventory.event.inventory.publisher;

import br.com.zup.inventory.event.inventory.InventoryRejectedEvent;
import br.com.zup.inventory.event.order.model.OrderRepresentation;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Component;

@Component
public class InventoryRejectedEventPublisher {

    private KafkaTemplate<String, InventoryRejectedEvent> template;

    public InventoryRejectedEventPublisher(KafkaTemplate<String, InventoryRejectedEvent> template) {
        this.template = template;
    }

    public void publish(final OrderRepresentation orderRepresentation) {

        InventoryRejectedEvent event = InventoryRejectedEvent.builder()
                .orderId(orderRepresentation.getOrderId())
                .customerId(orderRepresentation.getCustomerId())
                .amount(orderRepresentation.getAmount())
                .items(orderRepresentation.getItems())
                .build();

        this.template.send("inventory-rejected", event);
    }
}
