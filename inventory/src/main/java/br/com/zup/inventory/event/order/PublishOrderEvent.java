package br.com.zup.inventory.event.order;

import br.com.zup.inventory.enumeration.OrderStatus;
import br.com.zup.inventory.event.order.model.OrderRepresentation;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Component;

@Component
public class PublishOrderEvent {

    private KafkaTemplate<String, OrderRejectedEvent> template;

    public PublishOrderEvent(KafkaTemplate<String, OrderRejectedEvent> template) {
        this.template = template;
    }

    public void publish(final OrderRepresentation orderRepresentation) {

        OrderRejectedEvent event = OrderRejectedEvent.builder()
                .orderId(orderRepresentation.getOrderId())
                .customerId(orderRepresentation.getCustomerId())
                .amount(orderRepresentation.getAmount())
                .status(OrderStatus.ORDER_CREATED)
                .items(orderRepresentation.getItems())
                .build();

        this.template.send("order-rejected", event);
    }
}
