package br.com.zup.order.service.translator;

import br.com.zup.order.domain.CreateOrderDomain;
import br.com.zup.order.entity.Order;
import lombok.experimental.UtilityClass;
import org.modelmapper.ModelMapper;

@UtilityClass
public class CreateOrderDomainToOrderEntityTranslator {

    private static final ModelMapper MAPPER = new ModelMapper();

    public Order translate(final CreateOrderDomain createOrderDomain) {
        return MAPPER.map(createOrderDomain, Order.class);
    }
}
