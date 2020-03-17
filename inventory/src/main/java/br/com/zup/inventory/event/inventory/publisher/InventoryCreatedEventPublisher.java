package br.com.zup.inventory.event.inventory.publisher;

import br.com.zup.inventory.event.inventory.InventoryCreatedEvent;
import br.com.zup.inventory.event.order.model.OrderRepresentation;
import br.com.zup.inventory.event.translator.OrderRepresentationToInventoryCreatedEventTranslator;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Component;

@Component
public class InventoryCreatedEventPublisher {

    private KafkaTemplate<String, InventoryCreatedEvent> template;

    public InventoryCreatedEventPublisher(KafkaTemplate<String, InventoryCreatedEvent> template) {
        this.template = template;
    }

    public void publish(final OrderRepresentation orderRepresentation) {

        InventoryCreatedEvent event = OrderRepresentationToInventoryCreatedEventTranslator.translate(orderRepresentation);

        this.template.send("inventory-created", event);
    }
}
