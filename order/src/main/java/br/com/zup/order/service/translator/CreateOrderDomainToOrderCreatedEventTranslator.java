package br.com.zup.order.service.translator;

import br.com.zup.order.domain.CreateOrderDomain;
import br.com.zup.order.event.order.OrderCreatedEvent;
import lombok.experimental.UtilityClass;
import org.modelmapper.ModelMapper;

@UtilityClass
public class CreateOrderDomainToOrderCreatedEventTranslator {

    private static final ModelMapper MAPPER = new ModelMapper();

    public OrderCreatedEvent translate(final CreateOrderDomain createOrderDomain) {
        return MAPPER.map(createOrderDomain, OrderCreatedEvent.class);
    }
}
