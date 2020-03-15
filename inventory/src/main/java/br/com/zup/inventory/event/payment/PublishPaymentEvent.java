package br.com.zup.inventory.event.payment;

import br.com.zup.inventory.enumeration.OrderStatus;
import br.com.zup.inventory.event.order.model.OrderRepresentation;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Component;

@Component
public class PublishPaymentEvent {

    private KafkaTemplate<String, PaymentCreateEvent> template;

    public PublishPaymentEvent(KafkaTemplate<String, PaymentCreateEvent> template) {
        this.template = template;
    }

    public void publish(final OrderRepresentation orderRepresentation) {

        PaymentCreateEvent event = PaymentCreateEvent.builder()
                .orderId(orderRepresentation.getOrderId())
                .customerId(orderRepresentation.getCustomerId())
                .amount(orderRepresentation.getAmount())
                .status(OrderStatus.IN_PAYMENT)
                .items(orderRepresentation.getItems())
                .build();

        this.template.send("order-in-payment", event);
    }
}
