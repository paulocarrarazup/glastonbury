package br.com.zup.inventory.event.inventory.publisher;

import br.com.zup.inventory.event.inventory.InventoryCreatedEvent;
import br.com.zup.inventory.event.order.model.OrderRepresentation;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Component;

@Component
public class InventoryCreatedEventPublisher {

    private KafkaTemplate<String, InventoryCreatedEvent> template;

    public InventoryCreatedEventPublisher(KafkaTemplate<String, InventoryCreatedEvent> template) {
        this.template = template;
    }

    public void publish(final OrderRepresentation orderRepresentation) {

        InventoryCreatedEvent event = InventoryCreatedEvent.builder()
                .orderId(orderRepresentation.getOrderId())
                .customerId(orderRepresentation.getCustomerId())
                .amount(orderRepresentation.getAmount())
                .items(orderRepresentation.getItems())
                .build();

        this.template.send("inventory-created", event);
    }
}
