package br.com.zup.inventory.event.inventory.publisher;

import br.com.zup.inventory.event.inventory.InventoryRejectedEvent;
import br.com.zup.inventory.event.order.model.OrderRepresentation;
import br.com.zup.inventory.event.translator.OrderRepresentationToInventoryRejectedEventTranslator;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Component;

@Component
public class InventoryRejectedEventPublisher {

    private KafkaTemplate<String, InventoryRejectedEvent> template;

    public InventoryRejectedEventPublisher(KafkaTemplate<String, InventoryRejectedEvent> template) {
        this.template = template;
    }

    public void publish(final OrderRepresentation orderRepresentation) {

        InventoryRejectedEvent event = OrderRepresentationToInventoryRejectedEventTranslator.translate(orderRepresentation);

        this.template.send("inventory-rejected", event);
    }
}
