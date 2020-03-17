package br.com.zup.order.event.order.publisher;

import br.com.zup.order.domain.CreateOrderDomain;
import br.com.zup.order.event.order.OrderCreatedEvent;
import br.com.zup.order.service.translator.CreateOrderDomainToOrderCreatedEventTranslator;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Component;

@Component
public class OrderCreatedEventPublisher {

    private KafkaTemplate<String, OrderCreatedEvent> template;

    public OrderCreatedEventPublisher(KafkaTemplate<String, OrderCreatedEvent> template) {
        this.template = template;
    }

    public void publish(CreateOrderDomain createOrderDomain) {
        OrderCreatedEvent event = CreateOrderDomainToOrderCreatedEventTranslator.translate(createOrderDomain);

        this.template.send("created-orders", event);
    }
}
